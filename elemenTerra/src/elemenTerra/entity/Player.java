package elemenTerra.entity;

import java.awt.Color;
import elemenTerra.entity.decisions.*;
import elemenTerra.entity.brain.Brain;
import elemenTerra.world.Board;
import elemenTerra.world.Tile;
import elemenTerra.TileKeys;

public class Player extends Entity{
  protected int gasNum = 0;
  protected int liquidNum = 0;
  protected int solidNum = 0;
  protected Decisions decisions;

  public Player(int x, int y, Board b, char identity){
    super(x, y, b, identity);
  }

  public Player(int x, int y, Board b){
    super(x, y, b, 'X');
  }

  public void handleBump(Entity e){
    char bumpIdentity = e.getIdentity();

    if (identity == 'X'){
      for (int index = 0; index < 3; index++){
        if (bumpIdentity == TileKeys.gasses[index]){
          immitate(e);
          decisions = new GasPlayerDecisions(this);
          decisions.analagousGas(e);
        }
      }
    }else {
      if (bumpIdentity == analagousStates[0]){
        decisions.analagousGas(e);
      }
      if (bumpIdentity == analagousStates[1]){
        decisions.analagousLiquid(e);
      }
      if (bumpIdentity == analagousStates[2]){
        decisions.analagousSolid(e);
      }
      if (bumpIdentity == weakerStates[0]){
        decisions.weakerGas(e);
      }
      if (bumpIdentity == weakerStates[1]){
        decisions.weakerLiquid(e);
      }
      if (bumpIdentity == weakerStates[2]){
        decisions.weakerSolid(e);
      }
      if (bumpIdentity == strongerStates[0]){
        decisions.strongerGas(e);
      }
      if (bumpIdentity == strongerStates[1]){
        decisions.strongerLiquid(e);
      }
      if (bumpIdentity == strongerStates[2]){
        decisions.strongerSolid(e);
      }
    }

  }
  public void handleInput(char input){
    if ((gasNum + liquidNum + solidNum) >0){
      dropParticle(identity);
    }
    if ((gasNum + liquidNum + solidNum) == 0){
      setIdentity(getOriginalIdentity());
      setColor(getOriginalColor());
    }
  }

  public void immitate(Entity e){
    identity = e.getIdentity();
    color = e.getColor();
    setInteractionKeys();
  }

  public void absorb(Entity e){
    char particleIdentity = e.getIdentity();
    for (int index = 0; index < 3; index++){
      if (particleIdentity == TileKeys.gasses[index]){
        gasNum++;
      }
    }
    for (int index = 0; index < 3; index++){
      if (particleIdentity == TileKeys.liquids[index]){
        liquidNum++;
      }
    }
    for (int index = 0; index < 3; index++){
      if (particleIdentity == TileKeys.solids[index]){
        solidNum++;
      }
    }
    e.die();

  }

  public void dropParticle(char type){
    for (int index = 0; index < 3; index++){
      if (type == TileKeys.gasses[index]){
        gasNum--;
      }
    }
    for (int index = 0; index < 3; index++){
      if (type == TileKeys.liquids[index]){
        liquidNum--;
      }
    }
    for (int index = 0; index < 3; index++){
      if (type == TileKeys.solids[index]){
        solidNum--;
      }
    }
    if (brain.look(facing)){
      int[] xy = wasdToXY(facing);
      int aix = x + xy[0];
      int aiy = y + xy[1];
      Ai ai = Ai.parse(type, board, aix, aiy);
      board.getTile(aix, aiy).occupy(ai);
      brain.passGame(ai);
    }else {
      Tile destination = brain.closestEmptyTile();
      int aix = destination.getX();
      int aiy = destination.getY();
      //System.out.println(aix);
      //System.out.println(aiy);
      Ai ai = Ai.parse(type, board, aix, aiy);
      board.getTile(aix, aiy).occupy(ai);
      brain.passGame(ai);
    }
  }
  //utility method
  public int[] wasdToXY(char input){
    if (input == 'w') {
      int[] xy = {0, -1};
      return xy ;
    } else if (input == 'a') {
      int[] xy = {-1, 0};
      return xy ;
    } else if (input == 's') {
      int[] xy = {0, 1};
      return xy;
    } else if (input == 'd') {
      int[] xy = {1, 0};
      return  xy;
    } else {
      int[] xy = {0, 0};
      return xy;
    }
  }
  public int getGasNum(){
    return gasNum;
  }
  public int getLiquidNum(){
    return liquidNum;
  }
  public int getSolidNum(){
    return solidNum;
  }


}