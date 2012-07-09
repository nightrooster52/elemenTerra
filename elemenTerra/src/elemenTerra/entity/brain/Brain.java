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
  protected int searchBuffer = 1;
  protected int searchMax = 100;
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
    for (int range = 1; range < searchMax; range++) {//range = 1
      List<Tile> shell = searchTiles(range);
      int shellSize = shell.size();
      if (shellSize >0){
        int lookfirst = random.nextInt(shellSize -1);//randomizes the first direction looked, so there isn't an upper-left dx bias
        for (int searched = 0; searched <= shellSize; searched++) {
          int cursor = (searched + lookfirst) % (shellSize);//-1
          Tile tile = shell.get(cursor);
          if (!tile.isOccupied()){
            return tile;
          }
        }
      }
    }
    return null;
  }

  public Entity closestEntity(char type){
    Entity entity;
    for (int range = searchBuffer; range < searchMax; range++) {
      List<Tile> shell = searchTiles(range);
      int shellSize = shell.size();
      if (shellSize > 0){
        //-1 //randomizes the first direction looked, so there isn't an upper-left dx bias
        int indexOffset = (int) Math.random() * shellSize;
        for (int i = 0; i <= shellSize; i++) {//searched = 1
          int cursor = (i + indexOffset) % (shellSize);//-1
          Tile tile = shell.get(cursor);
          if (tile.isOccupied()) {
            entity = tile.getEntity();
            if (type == entity.getIdentity()) {
              return entity;
            }
          }
        }
      }
    }
    return null;
  }

  public List<Tile> searchTiles(int range) {
    int ix = body.getX();
    int iy = body.getY();
    List<Tile> shell = new ArrayList<Tile>();

    for (int row = range; row >= -range; row--) {
      for (int col = range; col >= -range; col--) {
        if (row*row == range*range || col*col == range*range) { //either row or col are == +- range
          int tilex = col + ix;
          int tiley = row + iy;
          if (board.inBounds(tilex, tiley)) {
            Tile tile = board.getTile(tilex, tiley);
            shell.add(tile);
          }
        }
      }
    }
    return shell;
  }

  public void passGame(Ai ai){
    ai.getBrain().setGame(game);
    ai.getDecisions().setGame(game);
  }




  public void tick(){
    ;
  }

}