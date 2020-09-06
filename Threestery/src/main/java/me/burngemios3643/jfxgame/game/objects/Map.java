package me.burngemios3643.jfxgame.game.objects;

import javafx.scene.layout.AnchorPane;
import me.burngemios3643.jfxgame.game.Game;
import me.burngemios3643.jfxgame.game.objects.tiles.GrassTile;
import me.burngemios3643.jfxgame.game.objects.tiles.SpawnTile;
import me.burngemios3643.jfxgame.game.objects.tiles.StoneTile;

public class Map {

	private Tile[][] terrain;
	private Tile[][] elements;
	private Game game;
	private int spawnX;
	private int spawnY;
	
	public Map(Game game, int width, int height) {
		this(game, new GrassTile(game), width, height);
	}
	
	public Map(Game game, Tile def, int width, int height) {
		this(game, def, new StoneTile(game), width, height);
	}
	
	public Map(Game game, Tile def, Tile border, int width, int height) {
		this(game, def, border, width, height, 1, 1);
	}
	
	public Map(Game game, Tile def, Tile border, int width, int height, int spawnX, int spawnY) {
		this.terrain = new Tile[width][height];
		this.elements = new Tile[width][height];
		this.spawnX = spawnX;
		this.spawnY = spawnY;
		elements[spawnX][spawnY] = new SpawnTile(game);
		this.game = game;
		for(int x = 0; x < terrain.length; x++) {
			for(int y = 0; y < terrain[x].length; y++) {
				if(x%(width-1) == 0 || y%(height-1) == 0) {
					terrain[x][y] = new Tile(game, border.getImage(), border.isSolid());
				}else {
					terrain[x][y] = new Tile(game, def.getImage(), def.isSolid());
				}
			}
		}
	}

	public void renderMap(AnchorPane pane) {
		for(int x = 0; x < terrain.length; x++) {
			for(int y = 0; y < terrain[x].length; y++) {
				terrain[x][y].resize(5, 5);
				terrain[x][y].setX(x*game.TILE_SIZE);
				terrain[x][y].setY(y*game.TILE_SIZE);
				pane.getChildren().add(terrain[x][y]);
				if(elements[x][y] != null) {
					elements[x][y].resize(5, 5);
					elements[x][y].setX(x*game.TILE_SIZE);
					elements[x][y].setY(y*game.TILE_SIZE);
					pane.getChildren().add(elements[x][y]);
				}
			}
		}
	}
	
	public void setTerrain(int x, int y, Tile tile) {
		terrain[x][y] = tile;
	}
	
	public void setElement(int x, int y, Tile element) {
		elements[x][y] = element;
	}
	
	public Tile[][] getMap(){
		return terrain;
	}

	public int getSpawnX() {
		return spawnX;
	}

	public int getSpawnY() {
		return spawnY;
	}
	
}
