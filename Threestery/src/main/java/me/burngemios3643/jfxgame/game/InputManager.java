package me.burngemios3643.jfxgame.game;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import me.burngemios3643.jfxgame.game.event.InputEvent;

public class InputManager {
	
	List<KeyCode> inputs;
	
	public InputManager() {
		inputs = new ArrayList<KeyCode>();
	}
	
	public void registerInputs(Game game, InputEvent inputEvent) {
		
		game.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				if(!inputs.contains(e.getCode())) {
					inputEvent.typed(game, e.getCode());
					inputs.add(e.getCode());
				}
			}
		});
		
		game.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				if(inputs.contains(e.getCode())) {
					inputs.remove(e.getCode());
				}
				inputEvent.released(game, e.getCode());
			}
		});
		
		new Thread() {
			int everx = 10;
			public void run() {
				long ms = System.currentTimeMillis();
				while (true) {
					if(System.currentTimeMillis()-ms >= everx) {
						ms = System.currentTimeMillis();
						List<KeyCode> temp = new ArrayList<KeyCode>();
						temp.addAll(inputs);
						for(KeyCode input:temp) {
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									inputEvent.pressed(game, input);
								}
							});
						}
					}
				}
			}
		}.start();
		
	}
}
