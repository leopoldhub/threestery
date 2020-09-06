package me.burngemios3643.jfxgame.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import me.burngemios3643.jfxgame.game.event.InputEvent;

public class InputManager {
	
	public Map<InputEvent, Thread> registeredThreads = new HashMap<InputEvent, Thread>();
	
	List<KeyCode> inputs;
	
	public InputManager() {
		inputs = new ArrayList<KeyCode>();
	}
	
	public void registerInputs(App app, InputEvent inputEvent) {
		if(registeredThreads.containsKey(inputEvent)) return;
		app.getPrimaryStage().getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				if(!inputs.contains(e.getCode())) {
					inputEvent.typed(e.getCode());
					inputs.add(e.getCode());
				}
			}
		});
		
		app.getPrimaryStage().getScene().setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				if(inputs.contains(e.getCode())) {
					inputs.remove(e.getCode());
				}
				inputEvent.released(e.getCode());
			}
		});
		
		Thread t = new Thread() {
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
									inputEvent.pressed(input);
								}
							});
						}
					}
				}
			}
		};
		t.start();
		registeredThreads.putIfAbsent(inputEvent, t);
	}
	
	public void unRegisterInputs(App app, InputEvent inputEvent) {
		if(!registeredThreads.containsKey(inputEvent)) return;
		app.getPrimaryStage().getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
			}
		});
		
		app.getPrimaryStage().getScene().setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
			}
		});
		
		unRegisterThread(inputEvent);
	}
	
	public void unRegisterThread(InputEvent inputEvent) {
		Thread t = registeredThreads.get(inputEvent);
		t.stop();
		registeredThreads.remove(inputEvent);
	}
	
	public void unRegisterAll() {
		Map<InputEvent, Thread> temp = new HashMap<InputEvent, Thread>();
		temp.putAll(registeredThreads);
		for(Map.Entry<InputEvent, Thread> entry:temp.entrySet()) {
			unRegisterThread(entry.getKey());
		}
	}
	
}
