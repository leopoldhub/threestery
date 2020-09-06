package me.burngemios3643.jfxgame.game.event;

import javafx.scene.input.KeyCode;
import me.burngemios3643.jfxgame.game.Game;

public interface InputEventHandler {

	public void pressed(KeyCode code);
	
	public void typed(KeyCode code);
	
	public void released(KeyCode code);
	
}
