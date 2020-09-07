package me.burngemios3643.jfxgame.game.event;

import me.burngemios3643.jfxgame.game.Game;
import me.burngemios3643.jfxgame.game.objects.Tile;

public interface MoveEventHandler {

	public void move(Game game, Tile element, double x, double y);
	
}
