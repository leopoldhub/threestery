package me.burngemios3643.jfxgame.game.event;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.ImageView;
import me.burngemios3643.jfxgame.game.Game;

public class MoveEvent {

	private List<MoveEventHandler> handlers = new ArrayList<MoveEventHandler>();
	
	public void move(Game game, ImageView element, double x, double y) {
		List<MoveEventHandler> temp = new ArrayList<MoveEventHandler>();
		temp.addAll(handlers);
		for(MoveEventHandler handler:temp) {
			handler.move(game, element, x, y);
		}
	}
	
	public void registerHandler(MoveEventHandler handler) {
		handlers.add(handler);
	}
	
	public void unRegisterHandler(MoveEventHandler handler) {
		handlers.add(handler);
	}
	
}
