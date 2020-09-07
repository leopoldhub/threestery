package me.burngemios3643.jfxgame.game.objects.tiles;

import me.burngemios3643.jfxgame.game.Game;
import me.burngemios3643.jfxgame.game.objects.Player;
import me.burngemios3643.jfxgame.game.objects.Tile;

public class ObjectifTile extends Tile{
 
	public ObjectifTile(Game game) {
		super(game, "sprites/tile_objectif.png", false);
	}

	@Override
	public void interract(Tile tile) {
		/*if(tile instanceof Player && ((Player) tile) == game.level.getActualPlayer()) {
			((Player) tile).kill();
			game.level.startLevel();
		}*/
	}

}
