package me.burngemios3643.jfxgame.game.objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.util.Duration;
import me.burngemios3643.jfxgame.game.Game;
import me.burngemios3643.jfxgame.game.objects.tiles.ObjectifTile;

public abstract class Level implements LevelConstructor {

	private Game game;
	private Map map;
	
	public static final int CYCLES = 3;
	
	private int seconds;
	
	private List<Player> players;
	public java.util.Map<Player,java.util.Map<Integer,Vector2D>> playersPaths;

	public Level(Game game, Map map, int seconds) {
		this.game = game;
		this.map = map;
		this.seconds = seconds;
		this.players = new ArrayList<Player>();
		this.playersPaths = new HashMap<Player, java.util.Map<Integer,Vector2D>>();
	}

	public void clearLevel() {
		stopLevelRuntime();
		
		for(int i = 0 ; i < players.size(); i++) {
			game.getApp().moveEvent.unRegisterHandler(players.get(i));
		}
		
		game.clear();
	}
	
	public void startLevel() {
		
		clearLevel();
		
		startCycle(false);
		
	}
	
	public boolean checkVictory() {
		if(players.size() >= CYCLES) {
			List<Node> temp = new ArrayList<Node>();
			temp.addAll(game.renderPane.getChildren());
			for(Node node:temp) {
				if(node instanceof Tile) {
					Tile tile = (Tile) node;
					if(tile instanceof ObjectifTile) {
						for(int i = 0 ; i < players.size(); i++) {
							if(!players.get(i).alive)continue;
							Bounds bnd = players.get(i).getBoundsInParent();
							Bounds tileBounds = tile.getBoundsInParent();
							if(bnd.intersects(tileBounds)) {
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	private java.util.Map<Integer,Vector2D> actPlayerPath;
	
	public void startCycle(boolean restart) {
		
		map.renderMap(game.renderPane);
		
		Player player = new Player(game, game.renderPane, 0, 0);
		
		if(restart) {
			if(players.size() > 0) {
				players.set(players.size()-1, player);
			}
		}else {
			if(players.size() > 0) {
				playersPaths.put(getActualPlayer(), actPlayerPath);
			}
			players.add(player);
		}
		
		actPlayerPath = new HashMap<Integer, Vector2D>();
		
		setupLevel();
		
		game.getApp().inputEvent.registerHandler(player);
		game.getApp().moveEvent.registerHandler(player);
		
		Timeline back = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				for(int i = 0 ; i < players.size(); i++) {
					players.get(i).setLayoutX((map.getSpawnX() * game.TILE_SIZE) + (game.TILE_SIZE/2) - (players.get(i).getFitWidth() / 2));
					players.get(i).setLayoutY((map.getSpawnY() * game.TILE_SIZE) + (game.TILE_SIZE/2) - (players.get(i).getFitHeight() / 2));
					if(players.get(i) != player) {
						game.renderPane.getChildren().add(players.get(i));
						game.getApp().moveEvent.registerHandler(players.get(i));
					}
					players.get(i).alive = true;
				}
				player.centerCamera();
			}
		}));
		back.setCycleCount(1);
		back.play();

		showGui();
		
		startLevelRuntime();
		
		System.out.println("players: "+players.size());
	}
	
	private Timeline runtime;
	
	private int cs = 0;
	
	public void startLevelRuntime() {
		
		cs = seconds*100;
		
		runtime = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				cs = cs - 1;
				actPlayerPath.put(cs, new Vector2D(getActualPlayer().getLayoutX(), getActualPlayer().getLayoutY()));
				for(java.util.Map.Entry<Player, java.util.Map<Integer,Vector2D>> plEntry:playersPaths.entrySet()) {
					Player pl = plEntry.getKey();
					if(plEntry.getValue().containsKey(cs)) {
						game.getApp().moveEvent.move(game, pl, plEntry.getValue().get(cs).getX(), plEntry.getValue().get(cs).getY());
					}
				}
				//System.out.println(((double)cs/100));
				timeLeft.setText(((double)cs/100)+" s");
				if(cs <= 0) {
					getActualPlayer().kill();
					if(checkVictory()) {
						clearLevel();
						game.nextLevel();
						System.out.println("next level!");
					}else {
						if(players.size() >= CYCLES) {
							System.out.println("restart level!");
							restartLevel();
						}else {
							System.out.println("continue...");
							startLevel();
						}
					}
				}
			}
		}));
		runtime.setCycleCount(seconds*100);
		runtime.play();
	}
	
	private Label timeLeft;
	
	public void showGui() {
		Label cycles = new Label("Cycle "+players.size()+"/"+CYCLES);
		timeLeft = new Label();
		timeLeft.setFont(new Font("Arial", 30));
		Label howToUse = new Label("Utilisez les fleches pour vous déplacer\nObjectif: avoir au moins un joueur a l'objectif a la fin du 3eme cycle\nAppuyez sur espace pour creer des piques");
		game.guiPane.getChildren().add(cycles);
		game.guiPane.getChildren().add(timeLeft);
		game.guiPane.getChildren().add(howToUse);
		cycles.setLayoutX(0);
		cycles.setLayoutY(0);
		timeLeft.setLayoutX(0);
		timeLeft.setLayoutY(20);
		howToUse.setLayoutX(0);
		howToUse.setLayoutY(70);
	}
	
	public void stopLevelRuntime() {
		if(runtime != null) {
			runtime.stop();
		}
		cs = 0;
	}

	public void restartLevel() {
		players.clear();
		playersPaths.clear();
		startLevel();
	}

	public Player getActualPlayer() {
		if(players.size() <= 0)return null;
		return players.get(players.size()-1);
	}
	
}
