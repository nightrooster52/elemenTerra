package elemenTerra.world;

import java.util.Arrays;

import elemenTerra.Game;
import elemenTerra.TileKeys;
import elemenTerra.entity.Ai;
import elemenTerra.entity.Entity;
import elemenTerra.entity.GasBrain;
import elemenTerra.entity.LRBrain;
import elemenTerra.entity.LiquidBrain;
import elemenTerra.entity.Player;
import elemenTerra.entity.SolidBrain;

public class Board implements TileKeys {

	private Tile[][] board;
	private int height;
	private int width;
	private Player player;
	private Ai[] aiArray = new Ai[0];
	private Game game;
	private int cursor;
	private Boolean playerSpawn = false;

	public String testString = "ACCESS TO BOARD";
	public String map;

	public Board(String map) {
		this.map = map;
		parse(this.map);
		//System.out.println(g.testString);
	}

	public void parse(String map) {
		//slices out the w and h
		width = Integer.parseInt(map.substring(0, 3));
		height = Integer.parseInt(map.substring(4, 7));

		//new tile array
		board = new Tile[height][width];

		cursor = 8; //the starting index of the map data

		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				char identity = map.charAt(cursor);

				if (identity == TileKeys.blockTile) {
					//System.out.println("Block Generated");
					board[row][col] = new Tile(TileKeys.defaultTile);
					Entity entity = new Entity(col, row, TileKeys.blockTile);
					board[row][col].occupy(entity);
					cursor++;
				} else if (identity == TileKeys.playerTile) {
					board[row][col] = new Tile(TileKeys.defaultTile);
					player = new Player(col, row, TileKeys.playerTile);
					board[row][col].occupy(player);
					playerSpawn = true;
					cursor++;
				} else if (identity == TileKeys.defaultTile) {
					board[row][col] = new Tile(identity);
					cursor++;
				} else {
					spawnAi(row, col, identity);
				}
			}
		}
		if (playerSpawn == false) {
			board[10][10].vacate();
			player = new Player(10, 10, TileKeys.playerTile);
			board[10][10].occupy(player);
			playerSpawn = true;
		}
		System.out.println("EntityGen complete");
		brainAi();

	}

	public void spawnAi(int row, int col, char identity) {
		board[row][col] = new Tile(TileKeys.defaultTile);
		aiArray = Arrays.copyOf(aiArray, aiArray.length + 1);
		Ai ai = new Ai(col, row, this);
		aiArray[aiArray.length - 1] = ai;
		board[row][col].occupy(ai);
		ai.setIdentity(identity);
		cursor++;
	}

	public void brainAi() {
		for (Ai ai : aiArray) {
			char aiIdentity = ai.getIdentity();

			if (aiIdentity == TileKeys.SeekerTile) {
				ai.setTarget(player);
			}
			if (aiIdentity == TileKeys.LTile) {
				ai.setBrain(new LRBrain(ai, this, "left"));
				//left brain
			}
			if (aiIdentity == TileKeys.RTile) {
				ai.setBrain(new LRBrain(ai, this, "right"));
				//Right brain
			}
			if (aiIdentity == TileKeys.fireGas
					|| aiIdentity == TileKeys.earthGas
					|| aiIdentity == TileKeys.waterGas) {
				//System.out.println(ai);
				ai.setBrain(new GasBrain(ai, this));
			}
			if (aiIdentity == TileKeys.fireLiquid
					|| aiIdentity == TileKeys.earthLiquid
					|| aiIdentity == TileKeys.waterLiquid) {
				//System.out.println(ai);
				ai.setBrain(new LiquidBrain(ai, this));
			}
			if (aiIdentity == TileKeys.fireSolid
					|| aiIdentity == TileKeys.earthSolid
					|| aiIdentity == TileKeys.waterSolid) {
				//System.out.println(ai);
				ai.setBrain(new SolidBrain(ai, this));
			}

		}
	}

	public boolean checkTile(int x, int y) {
		//System.out.println("called checkTile")
		if (inBounds(x, y)) {
			return !board[y][x].isOccupied();
		}
		return false;
	}

	public boolean inBounds(int x, int y) {
		return x < width && x > -1 && y < height && y > -1;
	}

	public void bump(int x, int y, Entity e) {
		if (inBounds(x, y)) {
			board[y][x].getEntity().bump(e);
		}

	}

	public void gameAi() {
		for (Ai element : aiArray) {
			//System.out.println(aiArray[i]);
			element.getBrain().takeGame();
		}
	}

	public void takeGame(Game g) {
		game = g;
	}

	public Game getGame() {
		return game;
	}

	public Tile getTile(int x, int y) {
		return board[y][x];
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public Player getPlayer() {
		return player;
	}

	public Ai[] getAiArray() {
		return aiArray;
	}

	@Override
	public String toString() {
		String t = "";
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				t += board[row][col].toString() + " ";
			}

			t += "\n";
		}
		return t;
	}

}