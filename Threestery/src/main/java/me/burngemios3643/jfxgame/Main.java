package me.burngemios3643.jfxgame;

import javafx.application.Application;
import javafx.stage.Stage;
import me.burngemios3643.jfxgame.game.App;

public class Main extends Application{

	public static final String GAME_NAME = "JFxGame";
	public static final String GAME_VERSION = "0.1-Dev";
	public static final String GAME_OWNER = "BurnGemios3643";
	
	public static void main(String... args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		App game = new App(primaryStage);
		game.init();
		game.launch();
	}
 
}
