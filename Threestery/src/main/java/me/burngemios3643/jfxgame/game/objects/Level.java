package me.burngemios3643.jfxgame.game.objects;

import java.util.ArrayList;
import java.util.List;

import me.burngemios3643.jfxgame.game.Game;
import me.burngemios3643.jfxgame.game.event.InputEventHandler;

public abstract class Level implements LevelConstructor, InputEventHandler{

	private Game game;
	private Map map;
	private List<Player> previousPlayers;
	private Player player;
	
	public Level(Game game, Map map) {
		this.game = game;
		this.map = map;
		this.previousPlayers = new ArrayList<Player>();
	}
	
	public void startLevel() {
		game.getApp().inputEvent.unRegisterHandler(this);
		for(Player pl:previousPlayers) {
			game.getApp().inputEvent.unRegisterHandler(pl);
		}
		if(player != null) previousPlayers.add(player);
		game.clear();
		map.renderMap(game.renderPane);
		player = new Player(game, game.renderPane);
		player.setLayoutX((map.getSpawnX()*game.TILE_SIZE)+(game.TILE_SIZE/2)-(player.getFitWidth()/2));
		player.setLayoutY((map.getSpawnY()*game.TILE_SIZE)+(game.TILE_SIZE/2)-(player.getFitHeight()/2));
		player.centerCamera();
		
		setupLevel();
		
		game.getApp().inputEvent.registerHandler(this);
		game.getApp().inputEvent.registerHandler(player);
		
		/*new java.util.Timer().schedule( 
		        new java.util.TimerTask() {
		            @Override
		            public void run() {
		            	Platform.runLater(new Runnable() {
							@Override
							public void run() {
								startLevel();
							}
						});
		            }
		        }, 
		        5000 
		);*/
	}
	
	public void restartLevel() {
		game.getApp().inputEvent.unRegisterHandler(player);
		previousPlayers.clear();
		startLevel();
	}
	
}
