package me.burngemios3643.jfxgame.game.objects.levels;

import me.burngemios3643.jfxgame.game.Game;
import me.burngemios3643.jfxgame.game.objects.Level;
import me.burngemios3643.jfxgame.game.objects.maps.TestMap;

public class TestLevel extends Level{
 
	public TestLevel(Game game) {
		super(game, new TestMap(game), 10);
	}

	@Override
	public void setupLevel() {
		
	}

}
