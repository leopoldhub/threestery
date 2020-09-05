package me.burngemios3643.jfxgame.game;

import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import me.burngemios3643.jfxgame.game.event.InputEvent;
import me.burngemios3643.jfxgame.game.event.InputEventHandler;
import me.burngemios3643.jfxgame.game.menu.Menu;
import me.burngemios3643.jfxgame.game.objects.Map;
import me.burngemios3643.jfxgame.game.objects.Player;
import me.burngemios3643.jfxgame.game.objects.Tile;
import me.burngemios3643.jfxgame.game.objects.tiles.StoneTile;

public class Game extends Menu {

	public static final double TILE_SIZE = 150;

	public Game(App game) {
		super(game, "Game");
	}

	@Override
	public void initMenu() {
		setFill(Color.AQUAMARINE);
		AnchorPane pane = new AnchorPane();
		pane.setMinSize(getApp().getPrimaryStage().getWidth(), getApp().getPrimaryStage().getHeight());
		getMenuRoot().getChildren().add(pane);
		
		Map map = new Map(this, 10, 10);
		map.setTerrain(5, 3, new StoneTile(this));
		map.renderMap(pane);
		
		Player player = new Player(this, pane);
		
		InputManager inputManager = new InputManager();
		InputEvent inputEvent = new InputEvent();
		inputManager.registerInputs(this, inputEvent);
		inputEvent.registerHandler(player);
		
		addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
			/*switch (key.getCode()) {
			case Z:
				player.setLayoutY(player.getLayoutY()-2);
				player.centerCamera();
				break;
			case Q:
				player.setLayoutX(player.getLayoutX()-2);
				player.centerCamera();
				break;
			case S:
				player.setLayoutY(player.getLayoutY()+2);
				player.centerCamera();
				
				Tile[][] mapTiles = map.getMap();
				
				for(Tile[] x:mapTiles) {
					for (Tile tile : x) {
						
					}
				}
				break;
			case D:
				player.setLayoutX(player.getLayoutX()+2);
				player.centerCamera();
				break;

			default:
				break;
			}*/
		});
	}

}
