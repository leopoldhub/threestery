package me.burngemios3643.jfxgame.game.objects;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import me.burngemios3643.jfxgame.game.Game;
import me.burngemios3643.jfxgame.game.event.InputEventHandler;
import me.burngemios3643.jfxgame.game.event.MoveEventHandler;
import me.burngemios3643.jfxgame.game.objects.tiles.SpikeTile;

public class Player extends Tile implements InputEventHandler, MoveEventHandler {
 
	private Game game;
	private AnchorPane mapPane;
	
	private double maxSpeed = 3D;
	
	public boolean alive = true;

	public Player(Game game, AnchorPane mapPane) {
		this(game, mapPane, 1*game.TILE_SIZE, 1*game.TILE_SIZE);
	}
	
	public Player(Game game, AnchorPane mapPane, double spawnX, double spawnY) {
		super(game, new Image("sprites/player/player.png", game.TILE_SIZE * 0.7, game.TILE_SIZE * 0.7, true, false), false);
		this.game = game;
		this.mapPane = mapPane;
		setFitHeight(getImage().getHeight());
		setFitWidth(getImage().getWidth());
		mapPane.getChildren().add(this);
		setLayoutX(spawnX - (getFitWidth() / 2));
		setLayoutY(spawnY - (getFitHeight() / 2));
		centerCamera();
	}

	public void centerCamera() {
		mapPane.setLayoutX(
				(getLayoutX() - (game.getApp().getPrimaryStage().getWidth() / 2) + (getFitWidth() / 2)) * (-1));
		mapPane.setLayoutY(
				(getLayoutY() - (game.getApp().getPrimaryStage().getHeight() / 2) + (getFitHeight() / 2)) * (-1));
	}
	
	public void kill() {
		game.getApp().inputEvent.unRegisterHandler(this);
		game.getApp().moveEvent.unRegisterHandler(this);
		mapPane.getChildren().remove(this);
		alive = false;
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
		
		if(wantedx != getLayoutX() || wantedy != getLayoutY())game.getApp().moveEvent.move(game, this, wantedx, wantedy);
		
	}

	@Override
	public void typed(KeyCode code) {
		if(code == KeyCode.SPACE) {
			Tile spk = new SpikeTile(game);
			game.renderPane.getChildren().add(spk);
			spk.setLayoutX(5*game.TILE_SIZE);
			spk.setLayoutY(2*game.TILE_SIZE);
		}
	}

	@Override
	public void released(KeyCode code) {
	}

	@Override
	public void move(Game game, Tile element, double x, double y) {
		if(element != this)return;
		double wantedx = x;
		double wantedy = y;
		
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
		if(game.level.getActualPlayer() == this)centerCamera();
	}

	@Override
	public void interract(Tile tile) {
		
	}

}
