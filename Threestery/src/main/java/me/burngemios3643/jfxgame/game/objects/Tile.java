package me.burngemios3643.jfxgame.game.objects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import me.burngemios3643.jfxgame.game.Game;

public class Tile extends ImageView{

	private boolean solid;
	private Game game;
	
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
