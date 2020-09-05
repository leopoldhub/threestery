package me.burngemios3643.jfxgame.utils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageUtils {

	public static ImageView getImageView(String path) {
		ImageView res = new ImageView(new Image(path));
		res.setSmooth(false);
		res.setPreserveRatio(true);
		return res;
	}
	
	public static ImageView getImageView(String path, double width, double height) {
		ImageView res = new ImageView(new Image(path, width, height, true, false));
		return res;
	}
	
}
