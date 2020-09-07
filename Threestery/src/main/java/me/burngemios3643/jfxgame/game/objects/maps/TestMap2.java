package me.burngemios3643.jfxgame.game.objects.maps;

import me.burngemios3643.jfxgame.game.Game;
import me.burngemios3643.jfxgame.game.objects.Map;
import me.burngemios3643.jfxgame.game.objects.tiles.ObjectifTile;
import me.burngemios3643.jfxgame.game.objects.tiles.SpikeTile;
import me.burngemios3643.jfxgame.game.objects.tiles.StoneTile;

public class TestMap2 extends Map {
 
	public TestMap2(Game game) {
		super(game, 11, 11, 5, 1);
		setTerrain(4, 1, new StoneTile(game));
		setTerrain(4, 2, new StoneTile(game));
		setTerrain(4, 3, new StoneTile(game));
		setTerrain(4, 5, new StoneTile(game));
		setTerrain(3, 5, new StoneTile(game));
		setTerrain(2, 5, new StoneTile(game));
		setTerrain(1, 5, new StoneTile(game));
		setTerrain(4, 6, new StoneTile(game));
		setTerrain(4, 7, new StoneTile(game));
		setTerrain(4, 8, new StoneTile(game));
		setTerrain(4, 9, new StoneTile(game));
		
		setTerrain(6, 1, new StoneTile(game));
		setTerrain(6, 2, new StoneTile(game));
		setTerrain(6, 3, new StoneTile(game));
		setTerrain(6, 5, new StoneTile(game));
		setTerrain(7, 5, new StoneTile(game));
		setTerrain(8, 5, new StoneTile(game));
		setTerrain(9, 5, new StoneTile(game));
		setTerrain(6, 6, new StoneTile(game));
		setTerrain(6, 7, new StoneTile(game));
		setTerrain(6, 8, new StoneTile(game));
		setTerrain(6, 9, new StoneTile(game));
		
		setElement(5, 9, new ObjectifTile(game));
	}

}
