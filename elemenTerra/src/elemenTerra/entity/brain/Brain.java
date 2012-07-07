package elemenTerra.entity.brain;

import java.util.Arrays;
import java.util.Random;

import elemenTerra.Game;
import elemenTerra.world.Board;
import elemenTerra.world.Tile;
import elemenTerra.entity.*;

public abstract class Brain {
  protected int delay = 10;
  protected int wait = 0;
  protected int searchBuffer = 1;
  protected Ai body;
  protected Board board;
  protected Game game;
  protected Random random = new Random();

  public Brain(Ai body, Board board) {
    this.body = body;
    this.board = board;
  }

  public void setGame(Game game) {
    this.game = game;
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
    //takes a char type (identity) because it may be used to search for other types than body.identity
    Entity entity;
    for (int range = searchBuffer; range < 10; range++) {//range = 1
      Tile[] shell = searchTiles(range);
      int lookfirst = random.nextInt(shell.length -1);//randomizes the first direction looked, so there isn't an upper-left dx bias
      for (int searched = 1; searched <= shell.length; searched++) {
	  int cursor = (searched + lookfirst) % (shell.length);//-1
        //System.out.print(cursor);
        if (shell[cursor].isOccupied()) {
          entity = shell[cursor].getEntity();
          if (type == entity.getIdentity()) {
            //System.out.print(body.getX() + body.getY());
            //System.out.print(" found Target at");
            //System.out.println(entity.getX() + entity.getY());
            return entity;
          }
        }
      }
    }
    return body;
  }

  public Tile[] searchTiles(int range) {
    int ix = body.getX();
    int iy = body.getY();
    Tile[] shell = new Tile[0];

    for (int row = -range; row <= range; row++) {
      for (int col = -range; col <= range; col++) {
        if (row * row == range * range || col * col == range * range) { //either row or col are == +- range)
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
    public void push(Entity e){
	char direction = e.getFacing();
	if (look(direction)){
	    game.handleMove(direction, body);
	}else {
	    Tile destination = closestEmptyTile();
	    game.portEntity(destination, body);
	    
	}
    }

  public abstract void tick();

}