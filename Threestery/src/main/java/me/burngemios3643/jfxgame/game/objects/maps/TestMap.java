package me.burngemios3643.jfxgame.game.objects.maps;

import me.burngemios3643.jfxgame.game.Game;
import me.burngemios3643.jfxgame.game.objects.Map;
import me.burngemios3643.jfxgame.game.objects.tiles.ObjectifTile;
import me.burngemios3643.jfxgame.game.objects.tiles.SpikeTile;
import me.burngemios3643.jfxgame.game.objects.tiles.StoneTile;

public class TestMap extends Map {
 
	public TestMap(Game game) {
		super(game, 10, 5, 1, 2);
		setTerrain(5, 1, new StoneTile(game));
		setTerrain(5, 3, new StoneTile(game));
		setElement(3, 2, new SpikeTile(game));
		setElement(7, 2, new ObjectifTile(game));
	}

}
