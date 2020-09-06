package me.burngemios3643.jfxgame.game.objects.maps;

import me.burngemios3643.jfxgame.game.Game;
import me.burngemios3643.jfxgame.game.objects.Map;
import me.burngemios3643.jfxgame.game.objects.tiles.GrassTile;
import me.burngemios3643.jfxgame.game.objects.tiles.StoneTile;

public class TestMap extends Map {

	public TestMap(Game game) {
		super(game, new GrassTile(game), new StoneTile(game), 10, 5, 1, 2);
		setTerrain(5, 1, new StoneTile(game));
		setTerrain(5, 3, new StoneTile(game));
	}

}
