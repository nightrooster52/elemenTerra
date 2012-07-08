package elemenTerra.entity.brain;

import java.util.Arrays;
import java.util.Random;

import elemenTerra.Game;
import elemenTerra.world.Board;
import elemenTerra.world.Tile;
import elemenTerra.entity.*;

public class Brain {
  protected int delay = 10;
  protected int wait = 0;
  protected int searchBuffer = 1;
  protected Entity body;
  protected Board board;
  protected Game game;
  protected Random random = new Random();

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
    for (int range = 1; range < 100; range++) {//range = 1
      Tile[] shell = searchTiles(range);
      int lookfirst = random.nextInt(shell.length -1);//randomizes the first direction looked, so there isn't an upper-left dx bias
      for (int searched = 1; searched <= shell.length; searched++) {
        int cursor = (searched + lookfirst) % (shell.length);//-1
        if (!shell[cursor].isOccupied()){
          return shell[cursor];
        }
      }
    }
    return board.getTile(body.getX(), body.getY());
  }

  public Entity closestEntity(char type){
    Entity entity;
    for (int range = searchBuffer; range < 10; range++) {
      Tile[] shell = searchTiles(range);
      int lookfirst = random.nextInt(shell.length );//-1 //randomizes the first direction looked, so there isn't an upper-left dx bias
      for (int searched = 0; searched <= shell.length; searched++) {//searched = 1
        int cursor = (searched + lookfirst) % (shell.length);//-1
        if (shell[cursor].isOccupied()) {
          entity = shell[cursor].getEntity();
          if (type == entity.getIdentity()) {
            return entity;
          }
        }
      }
    }
    return null;
  }

  public Tile[] searchTiles(int range) {
    int ix = body.getX();
    int iy = body.getY();
    Tile[] shell = new Tile[0];

    for (int row = -range; row <= range; row++) {
      for (int col = -range; col <= range; col++) {
        if (row*row == range*range || col*col == range*range) { //either row or col are == +- range
          int tilex = col + ix;
          int tiley = row + iy;
          if (board.inBounds(tilex, tiley)) {
            Tile tile = board.getTile(tilex, tiley);
            shell = Arrays.copyOf(shell, shell.length + 1);
            shell[shell.length - 1] = tile;
          }
        }
      }
    }
    return shell;
  }

  public void passGame(Ai ai){
    ai.getBrain().setGame(game);
  }




  public void tick(){
    ;
  }

}