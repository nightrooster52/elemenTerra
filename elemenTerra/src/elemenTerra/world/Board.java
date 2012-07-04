package elemenTerra.world;

import java.util.LinkedList;
import java.util.List;

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
  private Game game;
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
          Ai ai = new Ai(col, row, this);
          tile.occupy(ai);
          ai.setIdentity(identity);
          char aiIdentity = ai.getIdentity();
          if (aiIdentity == TileKeys.SeekerTile) {
            ai.setTarget(player);
          } else if (aiIdentity == TileKeys.LTile) {
            ai.setBrain(new LRBrain(ai, this, "left"));
          } else if (aiIdentity == TileKeys.RTile) {
            ai.setBrain(new LRBrain(ai, this, "right"));
          } else if (aiIdentity == TileKeys.fireGas
              || aiIdentity == TileKeys.earthGas
              || aiIdentity == TileKeys.waterGas) {
            ai.setBrain(new GasBrain(ai, this));
          } else if (aiIdentity == TileKeys.fireLiquid
              || aiIdentity == TileKeys.earthLiquid
              || aiIdentity == TileKeys.waterLiquid) {
            ai.setBrain(new LiquidBrain(ai, this));
          } else if (aiIdentity == TileKeys.fireSolid
              || aiIdentity == TileKeys.earthSolid
              || aiIdentity == TileKeys.waterSolid) {
            ai.setBrain(new SolidBrain(ai, this));
          }
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
    for (Entity entity : getEntities()) {
      if (entity instanceof Ai) {
        ((Ai) entity).getBrain().setGame(game);
      }
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

  public List<Entity> getEntities() {
    List<Entity> entityList = new LinkedList<Entity>();
    for (Tile tile : getTiles()) {
      entityList.add(tile.getEntity());
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