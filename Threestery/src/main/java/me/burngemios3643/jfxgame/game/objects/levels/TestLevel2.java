package me.burngemios3643.jfxgame.game.objects.levels;

import me.burngemios3643.jfxgame.game.Game;
import me.burngemios3643.jfxgame.game.objects.Level;
import me.burngemios3643.jfxgame.game.objects.maps.TestMap2;

public class TestLevel2 extends Level{

	public TestLevel2(Game game) {
		super(game, new TestMap2(game), 10);
	}

	@Override
	public void setupLevel() {
		
	}

}
