package me.burngemios3643.jfxgame.game.objects;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import me.burngemios3643.jfxgame.game.Game;
import me.burngemios3643.jfxgame.game.event.InputEventHandler;

public class Player extends Tile implements InputEventHandler {

	private Game game;
	private AnchorPane mapPane;
	
	private double maxSpeed = 3D;

	public Player(Game game, AnchorPane mapPane) {
		this(game, mapPane, 1*game.TILE_SIZE, 1*game.TILE_SIZE);
	}
	
	public Player(Game game, AnchorPane mapPane, double spawnX, double spawnY) {
		super(game, new Image("sprites/player/player.png", game.TILE_SIZE * 0.7, game.TILE_SIZE * 0.7, true, false),
				true);
		this.game = game;
		this.mapPane = mapPane;
		setFitHeight(getImage().getHeight());
		setFitWidth(getImage().getWidth());
		mapPane.getChildren().add(this);
		setLayoutX((mapPane.getLayoutX() + (game.getApp().getPrimaryStage().getWidth() / 2) - (getFitWidth() / 2)));
		setLayoutY((mapPane.getLayoutY() + (game.getApp().getPrimaryStage().getHeight() / 2) - (getFitWidth() / 2)));
	}

	public void centerCamera() {
		mapPane.setLayoutX(
				(getLayoutX() - (game.getApp().getPrimaryStage().getWidth() / 2) + (getFitWidth() / 2)) * (-1));
		mapPane.setLayoutY(
				(getLayoutY() - (game.getApp().getPrimaryStage().getHeight() / 2) + (getFitHeight() / 2)) * (-1));
	}

	@Override
	public void pressed(KeyCode code) {
		double wantedx = getLayoutX();
		double wantedy = getLayoutY();
		
		switch (code) {
		case UP:
			wantedy = wantedy-maxSpeed;
			break;
		case LEFT:
			wantedx = wantedx-maxSpeed;
			break;
		case DOWN:
			wantedy = wantedy+maxSpeed;
			break;
		case RIGHT:
			wantedx = wantedx+maxSpeed;
			break;

		default:
			break;
		}
		
		List<Node> temp = new ArrayList<Node>();
		temp.addAll(mapPane.getChildren());
		for(Node node:temp) {
			if(node instanceof Tile) {
				Tile tile = (Tile) node;
				if(tile != this) {
					if(tile.isSolid()) {
						Bounds bnd = new BoundingBox(wantedx-maxSpeed, wantedy-maxSpeed, getFitWidth()+(maxSpeed*2), getFitHeight()+(maxSpeed*2));
						Bounds tileBounds = tile.getBoundsInParent();
						if(bnd.intersects(tileBounds)) {
							Bounds upBounds = new BoundingBox(bnd.getMinX()+maxSpeed, bnd.getMinY(), bnd.getWidth()-(maxSpeed*2), maxSpeed);
							Bounds downBounds = new BoundingBox(bnd.getMinX()+maxSpeed, bnd.getMaxY()-maxSpeed, bnd.getWidth()-(maxSpeed*2), maxSpeed);
							Bounds leftBounds = new BoundingBox(bnd.getMinX(), bnd.getMinY()+maxSpeed, maxSpeed, bnd.getHeight()-(maxSpeed*2));
							Bounds rightBounds = new BoundingBox(bnd.getMaxX()-maxSpeed, bnd.getMinY()+maxSpeed, maxSpeed, bnd.getHeight()-(maxSpeed*2));
							
							if(tileBounds.intersects(upBounds)) {
								System.out.println("collide up");
								wantedy = upBounds.getMaxY()+maxSpeed;
							}
							if(tileBounds.intersects(downBounds)) {
								System.out.println("collide down");
								wantedy = downBounds.getMinY()-getFitHeight()-maxSpeed;
							}
							if(tileBounds.intersects(leftBounds)) {
								System.out.println("collide left");
								wantedx = leftBounds.getMaxX()+maxSpeed;
							}
							if(tileBounds.intersects(rightBounds)) {
								System.out.println("collide right");
								wantedx = rightBounds.getMinX()-getFitWidth()-maxSpeed;
							}
						}
					}
				}
			}
		}
		
		setLayoutX(wantedx);
		setLayoutY(wantedy);
		centerCamera();
	}

	@Override
	public void typed(KeyCode code) {
	}

	@Override
	public void released(KeyCode code) {
	}

}
