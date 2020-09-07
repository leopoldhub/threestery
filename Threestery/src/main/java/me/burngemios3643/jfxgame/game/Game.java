package me.burngemios3643.jfxgame.game;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import me.burngemios3643.jfxgame.game.event.MoveEventHandler;
import me.burngemios3643.jfxgame.game.menu.Menu;
import me.burngemios3643.jfxgame.game.objects.Level;
import me.burngemios3643.jfxgame.game.objects.Tile;
import me.burngemios3643.jfxgame.game.objects.levels.TestLevel;
import me.burngemios3643.jfxgame.game.objects.levels.TestLevel2;

public class Game extends Menu implements MoveEventHandler{

	public static final double TILE_SIZE = 150;

	private List<Level> levels;
	
	public Game(App app) {
		super(app, "Game");
		levels = new ArrayList<Level>();
		levels.add(new TestLevel(this));
		levels.add(new TestLevel2(this));
	}

	public AnchorPane renderPane;
	public AnchorPane guiPane;
	public Level level;
	
	@Override
	public void initMenu() {
		setFill(Color.AQUAMARINE);
		renderPane = new AnchorPane();
		renderPane.setMinSize(getApp().getPrimaryStage().getWidth(), getApp().getPrimaryStage().getHeight());
		guiPane = new AnchorPane();
		guiPane.setMinSize(getApp().getPrimaryStage().getWidth(), getApp().getPrimaryStage().getHeight());
		getMenuRoot().getChildren().add(renderPane);
		getMenuRoot().getChildren().add(guiPane);
		
		nextLevel();
	}

	public void clear() {
		renderPane.getChildren().clear();
		guiPane.getChildren().clear();
	}

	public void nextLevel() {
		if(levels.size() > 0) {
			level = levels.get(0);
			level.startLevel();
			levels.remove(0);
		}
	}
	
	@Override
	public void move(Game game, Tile element, double x, double y) {
		List<Node> temp = new ArrayList<Node>();
		temp.addAll(renderPane.getChildren());
		for(Node node:temp) {
			if(node instanceof Tile) {
				Tile tile = (Tile) node;
				Bounds bnd = new BoundingBox(x, y, element.getFitWidth(), element.getFitHeight());
				Bounds tileBounds = tile.getBoundsInParent();
				if(bnd.intersects(tileBounds)) {
					element.interract(tile);
					tile.interract(element);
				}
			}
		}
	}
	
}
