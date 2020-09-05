package me.burngemios3643.jfxgame.game;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import me.burngemios3643.jfxgame.Main;
import me.burngemios3643.jfxgame.game.menu.Menu;
import me.burngemios3643.jfxgame.game.menu.menus.MainMenu;

public class App {
	
	private Stage primaryStage;
	
	public App(Stage stage) {
		this.primaryStage = stage;
	}
	
	public void init() {
		primaryStage.setWidth(720);
		primaryStage.setHeight(1080);
		primaryStage.centerOnScreen();
		primaryStage.setTitle(Main.GAME_NAME+" "+Main.GAME_VERSION+" by:"+Main.GAME_OWNER);
		primaryStage.setFocused(true);
		primaryStage.getIcons().add(new Image("icons/logo.png"));
		primaryStage.setResizable(false);
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent var1) {
				System.exit(0);
			}
		});
		primaryStage.show();
	}
	
	public void launch() {
		Menu mainMenu = new MainMenu(this);
		mainMenu.initMenu();
		mainMenu.show();
	}
	
	public void showMenu(Menu menu) {
		this.primaryStage.setScene(menu);
	}
	
	public Stage getPrimaryStage() {
		return this.primaryStage;
	}
	
}
