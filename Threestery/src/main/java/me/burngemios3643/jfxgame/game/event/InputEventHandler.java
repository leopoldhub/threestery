package me.burngemios3643.jfxgame.game.event;

import javafx.scene.input.KeyCode;
import me.burngemios3643.jfxgame.game.Game;

public interface InputEventHandler {

	public void pressed(Game game, KeyCode code);
	
	public void typed(Game game, KeyCode code);
	
	public void released(Game game, KeyCode code);
	
}
