package me.burngemios3643.jfxgame.game.objects.tiles;

import me.burngemios3643.jfxgame.game.Game;
import me.burngemios3643.jfxgame.game.objects.Player;
import me.burngemios3643.jfxgame.game.objects.Tile;

public class SpikeTile extends Tile{
 
	public SpikeTile(Game game) {
		super(game, "sprites/tile_spike.png", false);
	}

	@Override
	public void interract(Tile tile) {
		if(tile instanceof Player) {
			((Player) tile).kill();
			if(((Player) tile) == game.level.getActualPlayer()) {
				game.level.clearLevel();
				game.level.startCycle(true);
			}
		}
	}

}
