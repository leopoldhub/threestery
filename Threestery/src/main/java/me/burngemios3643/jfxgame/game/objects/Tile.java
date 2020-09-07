package me.burngemios3643.jfxgame.game.objects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import me.burngemios3643.jfxgame.game.Game;

public abstract class Tile extends ImageView implements TileConstructor{
 
	private boolean solid;
	protected Game game;
	
	public Tile(Game game, String path, boolean solid) {
		this(game, new Image(path, game.TILE_SIZE, game.TILE_SIZE, true, false), solid);
	}
	
	public Tile(Game game, Image img, boolean solid) {
		super(img);
		this.game = game;
		this.solid = solid;
		setSmooth(false);
		setPreserveRatio(true);
	}

	public boolean isSolid() {
		return solid;
	}
	
}
