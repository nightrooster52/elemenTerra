package elemenTerra.world;

import java.util.LinkedList;
import java.util.List;

import elemenTerra.TileKeys;
import elemenTerra.entity.Ai;
import elemenTerra.entity.Entity;
import elemenTerra.entity.Player;

public class Board implements TileKeys {

  private Tile[][] board;
  private int height;
  private int width;
  private Player player;
  private int cursor;
  private Boolean playerHasSpawned = false;

  public String testString = "ACCESS TO BOARD";
  public String map;

  public Board(String map) {
    this.map = map;
    parse(this.map);
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
        char identity = map.charAt(cursor++);
        Tile tile = new Tile();
        board[row][col] = tile;

        if (identity == TileKeys.blockTile) {

          tile.setIdentity(identity);
          tile.occupy(new Entity(col, row, TileKeys.blockTile));

        } else if (identity == TileKeys.playerTile) {

          player = new Player(col, row, TileKeys.playerTile);
          tile.occupy(player);
          playerHasSpawned = true;

        } else if (identity != TileKeys.defaultTile) {
          tile.occupy(Ai.parse(identity, this, col, row));
        }
      }
    }

    if (playerHasSpawned == false) {
      board[10][10].vacate();
      player = new Player(10, 10, TileKeys.playerTile);
      board[10][10].occupy(player);
      playerHasSpawned = true;
    }
    System.out.println("EntityGen complete");
  }

  public boolean checkTile(int x, int y) {
    return inBounds(x, y) && !board[y][x].isOccupied();
  }

  public boolean inBounds(int x, int y) {
    return x < width && x > -1 && y < height && y > -1;
  }

  public void bump(int x, int y, Entity e) {
    if (inBounds(x, y)) {
      board[y][x].getEntity().bump(e);
    }

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

  public List<Entity> getEntities() {
    List<Entity> entityList = new LinkedList<Entity>();
    for (Tile tile : getTiles()) {
      if (tile.isOccupied()) {
        entityList.add(tile.getEntity());
      }
    }
    return entityList;
  }

  public List<Tile> getTiles() {
    List<Tile> tiles = new LinkedList<Tile>();
    for (int r = 0; r < height; r++) {
      for (int c = 0; c < width; c++) {
        tiles.add(getTile(c, r));
      }
    }
    return tiles;
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