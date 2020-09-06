package me.burngemios3643.jfxgame.game.objects.levels;

import javafx.scene.input.KeyCode;
import me.burngemios3643.jfxgame.game.Game;
import me.burngemios3643.jfxgame.game.objects.Level;
import me.burngemios3643.jfxgame.game.objects.maps.TestMap;

public class TestLevel extends Level{

	public TestLevel(Game game) {
		super(game, new TestMap(game));
		
	}

	@Override
	public void setupLevel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pressed(KeyCode code) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void typed(KeyCode code) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void released(KeyCode code) {
		// TODO Auto-generated method stub
		
	}

}
