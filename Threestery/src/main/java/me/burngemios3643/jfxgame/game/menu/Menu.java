package me.burngemios3643.jfxgame.game.menu;

import javafx.scene.Group;
import javafx.scene.Scene;
import me.burngemios3643.jfxgame.game.App;

public abstract class Menu extends Scene implements MenuFormat{
 
	private App app;
	private String name;
	private Group root;
	
	public Menu(App app, String name) {
		super(new Group());
		this.root = (Group) super.getRoot();
		this.app = app;
		this.name = name;
	}

	public App getApp() {
		return app;
	}

	public Group getMenuRoot() {
		return root;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public void show() {
		getApp().showMenu(this);
	}
	
}
