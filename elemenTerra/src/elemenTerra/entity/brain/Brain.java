package elemenTerra.entity.brain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import elemenTerra.Game;
import elemenTerra.entity.Ai;
import elemenTerra.entity.Entity;
import elemenTerra.world.Board;
import elemenTerra.world.Tile;

public class Brain {
  protected int delay = 10;
  protected int wait = 0;
  protected Entity body;
  protected Board board;
  protected Game game;
  protected Random random = new Random();
  protected int searchBuffer = 1;
  protected int searchMax = 100;
  

  public Brain(Entity body, Board board, Game game) {
    this(body, board);
    this.game = game;
  }

  public Brain(Entity body, Board board) {
    this.body = body;
    this.board = board;
  }

  public void setGame(Game game) {
    this.game = game;
  }
  public Game getGame(){
    return game;
  }

  public boolean look(char wasd) {
    int ix = body.getX();
    int iy = body.getY();

    if (wasd == 'w') {
      return board.checkTile(ix, iy - 1);
    }
    if (wasd == 'a') {
      return board.checkTile(ix - 1, iy);
    }
    if (wasd == 's') {
      return board.checkTile(ix, iy + 1);
    }
    if (wasd == 'd') {
      return board.checkTile(ix + 1, iy);
    }
    return false;
  }

  public Tile closestEmptyTile(){
    return closestEmptyTile(1);
  }

  public Tile closestEmptyTile(int emptyBuffer){
    for (int range = emptyBuffer; range < 200; range++) {//range = 1
      List<Tile> shell = searchTiles(range);
      while (shell.size() > 0){
        int cursor = (int) Math.random()*shell.size();
        Tile tile = shell.get(cursor);
        shell.remove(cursor);
        if (!tile.isOccupied()){
          return tile;
        }
      }
    }
    return null;
  }

  public int neighborNum(){
    int x = body.getX();
    int y = body.getY();

    int neighborNum = 0;
    for (int i = -1; i <2; i++){
      for (int j = -1; j <2; j++){
        if (board.inBounds(x+i, y+j)){
          if (board.getTile(x+i, y+j).getIdentity() == body.getIdentity()){
            neighborNum++;
          }
        }
      }
    }
    return neighborNum -1; // -1 because of counting self
  }
  public Entity closestEntity(char type){
    int nn = neighborNum(); //fux with this for fun searchbuffer effects
    int searchBuffer = nn + 2;
    int searchMax = nn + 20;
    return closestEntity(type, searchBuffer, searchMax);
  }


  public Entity closestEntity(char type, int searchBuffer, int searchMax){
    Entity entity;
    int cursor;
    for (int range = searchBuffer; range < searchMax; range++) {
      List<Tile> shell = searchTiles(range);
      while (shell.size() > 0){
        cursor = (int) Math.random() * shell.size();
        Tile tile = shell.get(cursor);
        shell.remove(cursor);
        if (tile.getIdentity() == type) {
          return  tile.getEntity();
        }
      }
    }
    return null;
  }




  public List<Tile> searchTiles(int range) {
    int ix = body.getX();
    int iy = body.getY();

    int localRange = range;
    if (Math.random() > .5){
      localRange = -range;
    }

    List<Tile> finalShell = new ArrayList<Tile>();
    List<Tile> right = new ArrayList<Tile>();
    List<Tile> bottom = new ArrayList<Tile>();
    List<Tile> left = new ArrayList<Tile>();

    for (int col = -range; col <= range; col++) {
      for (int row = -range; row <= range; row++) {
        int tilex = col + ix;
        int tiley = row + iy;
        Tile tile;
        if (board.inBounds(tilex, tiley)) {
          tile = board.getTile(tilex, tiley);
          if (row == -localRange){
            finalShell.add(tile);
          }else if (col == localRange){
            right.add(tile);
          }else if(row == localRange){
            bottom.add(0, tile); //bottom must be backwards
          }else if (col == -localRange){
            left.add(0, tile); //left must be backwards, inserting at 0
          }
        }
      }
    }
    finalShell.addAll(right);
    finalShell.addAll(bottom);
    finalShell.addAll(left);
    return finalShell;
  }

  public void passGame(Ai ai){
    ai.getBrain().setGame(game);
    ai.getDecisions().setGame(game);
  }




  public void tick(){
    ;
  }

}