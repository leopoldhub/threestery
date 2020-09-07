package me.burngemios3643.jfxgame.game.menu.menus;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import me.burngemios3643.jfxgame.game.App;
import me.burngemios3643.jfxgame.game.Game;
import me.burngemios3643.jfxgame.game.menu.Menu;
import me.burngemios3643.jfxgame.utils.ImageUtils;

public class MainMenu extends Menu {
 
	public MainMenu(App game) {
		super(game, "MainMenu");
	}

	@Override
	public void initMenu() {
		AnchorPane pane = new AnchorPane();
		pane.setMinSize(getApp().getPrimaryStage().getWidth(), getApp().getPrimaryStage().getHeight());
		getMenuRoot().getChildren().add(pane);
		
		VBox vbox = new VBox(15);
		vbox.setAlignment(Pos.CENTER);
		
		ImageView logo = ImageUtils.getImageView("icons/mainmenu_logo.png");
		logo.setFitWidth(getApp().getPrimaryStage().getWidth());
		
		ImageView playButtonLogo = ImageUtils.getImageView("sprites/mainmenu_playbutton.png");
		playButtonLogo.setFitWidth(getApp().getPrimaryStage().getWidth()*0.66);
		Button playButton = new Button("", playButtonLogo);
		playButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent var1) {
				Game game = new Game(getApp());
				game.initMenu();
				game.show();
			}
		});
		
		ImageView exitButtonLogo = ImageUtils.getImageView("sprites/mainmenu_exitbutton.png");
		exitButtonLogo.setFitWidth(getApp().getPrimaryStage().getWidth()*0.66);
		Button exitButton = new Button("", exitButtonLogo);
		exitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent var1) {
				System.exit(0);
			}
		});
		
		vbox.getChildren().add(logo);
		vbox.getChildren().add(playButton);
		vbox.getChildren().add(exitButton);
		
		pane.getChildren().add(vbox);
		BackgroundImage myBI = new BackgroundImage(new Image("backgrounds/mainmenu_background.png", 5245, 1720, true, false), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		pane.setBackground(new Background(myBI));
	}

}
