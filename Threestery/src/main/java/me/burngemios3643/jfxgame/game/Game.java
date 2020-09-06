package me.burngemios3643.jfxgame.game;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import me.burngemios3643.jfxgame.game.menu.Menu;
import me.burngemios3643.jfxgame.game.objects.Level;
import me.burngemios3643.jfxgame.game.objects.levels.TestLevel;

public class Game extends Menu {

	public static final double TILE_SIZE = 150;

	public Game(App app) {
		super(app, "Game");
	}

	public AnchorPane renderPane;
	
	@Override
	public void initMenu() {
		setFill(Color.AQUAMARINE);
		renderPane = new AnchorPane();
		renderPane.setMinSize(getApp().getPrimaryStage().getWidth(), getApp().getPrimaryStage().getHeight());
		getMenuRoot().getChildren().add(renderPane);
		
		Level levelTest = new TestLevel(this);
		levelTest.startLevel();
		
		/*getApp().inputEvent.unRegisterHandler(player);
		clear();*/
	}

	public void clear() {
		renderPane.getChildren().clear();
	}
	
}
