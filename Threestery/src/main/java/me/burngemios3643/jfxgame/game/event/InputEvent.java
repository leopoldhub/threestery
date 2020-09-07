package me.burngemios3643.jfxgame.game.event;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import me.burngemios3643.jfxgame.game.Game;

public class InputEvent {

	private List<InputEventHandler> handlers = new ArrayList<InputEventHandler>();
	
	public void pressed(KeyCode code) {
		List<InputEventHandler> temp = new ArrayList<InputEventHandler>();
		temp.addAll(handlers);
		for(InputEventHandler handler:temp) { 
			handler.pressed(code);
		}
	}
	
	public void typed(KeyCode code) {
		List<InputEventHandler> temp = new ArrayList<InputEventHandler>();
		temp.addAll(handlers);
		for(InputEventHandler handler:temp) {
			handler.typed( code);
		}
	}
	
	public void released(KeyCode code) {
		List<InputEventHandler> temp = new ArrayList<InputEventHandler>();
		temp.addAll(handlers);
		for(InputEventHandler handler:temp) {
			handler.released(code);
		}
	}
	
	public void registerHandler(InputEventHandler handler) {
		handlers.add(handler);
	}
	
	public void unRegisterHandler(InputEventHandler handler) {
		handlers.remove(handler);
	}
	
}
