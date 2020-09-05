package me.burngemios3643.jfxgame.game.event;

import javafx.scene.image.ImageView;
import me.burngemios3643.jfxgame.game.Game;

public interface MoveEventHandler {

	public void move(Game game, ImageView element, double x, double y);
	
}
