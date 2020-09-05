package me.burngemios3643.jfxgame.game.event;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import me.burngemios3643.jfxgame.game.Game;

public class InputEvent {

	private List<InputEventHandler> handlers = new ArrayList<InputEventHandler>();
	
	public void pressed(Game game, KeyCode code) {
		List<InputEventHandler> temp = new ArrayList<InputEventHandler>();
		temp.addAll(handlers);
		for(InputEventHandler handler:temp) {
			handler.pressed(game, code);
		}
	}
	
	public void typed(Game game, KeyCode code) {
		List<InputEventHandler> temp = new ArrayList<InputEventHandler>();
		temp.addAll(handlers);
		for(InputEventHandler handler:temp) {
			handler.typed(game, code);
		}
	}
	
	public void released(Game game, KeyCode code) {
		List<InputEventHandler> temp = new ArrayList<InputEventHandler>();
		temp.addAll(handlers);
		for(InputEventHandler handler:temp) {
			handler.released(game, code);
		}
	}
	
	public void registerHandler(InputEventHandler handler) {
		handlers.add(handler);
	}
	
	public void unRegisterHandler(InputEventHandler handler) {
		handlers.add(handler);
	}
	
}
