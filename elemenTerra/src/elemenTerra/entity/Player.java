package elemenTerra.entity;

import java.awt.Color;
import java.awt.Graphics;
import elemenTerra.entity.decisions.*;
import elemenTerra.entity.brain.Brain;
import elemenTerra.world.Board;
import elemenTerra.world.Tile;
import elemenTerra.TileKeys;

public class Player extends Entity{
  protected int gasNum = 0;
  protected int liquidNum = 0;
  protected int solidNum = 0;

  public Player(int x, int y, Board b, char identity){
    super(x, y, b, identity);
  }

  public Player(int x, int y, Board b){
    super(x, y, b, 'X');
  }

  public void giveBump(Entity e){
    char bumpIdentity = e.getIdentity();

    if (identity == 'X'){
      for (int index = 0; index < totElements; index++){
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
    if (input == 'r'){
      System.out.println("Gas, Liq, Sol");
      System.out.print(gasNum);
      System.out.print("    ");
      System.out.print(liquidNum);
      System.out.print("    ");
      System.out.println(solidNum);
    }
    if (input == 'x'){
      if ((gasNum + liquidNum + solidNum) >0){
        dropParticle();
      }
    }
  }

  public void immitate(Entity e){
    identity = e.getIdentity();
    color = e.getColor();
    setInteractionKeys();
  }


  public void upConvert(){
    for (int index = 0; index < totElements; index++){
      if (identity == TileKeys.liquids[index]){ //if i am a liquid
        color = TileKeys.solidColors[index]; //I become solid color
        identity = analagousStates[2];  //I take solid type
        decisions = new SolidPlayerDecisions(this); //I get solid decisions
        liquidNum = 0;
        solidNum = 1;
      }
      if (identity == TileKeys.gasses[index]){
        color = TileKeys.liquidColors[index];
        identity = analagousStates[1];
        decisions = new LiquidPlayerDecisions(this);
        gasNum = 0;
        liquidNum = 1;
      }
    }
  }

  public void downConvert(){
    for (int index = 0; index < totElements; index++){
      if (identity == TileKeys.liquids[index]){
        color = TileKeys.gasColors[index];
        identity = analagousStates[0];
        decisions = new GasPlayerDecisions(this);
        gasNum = 3;
        liquidNum = 0;
      }
      if (identity == TileKeys.solids[index]){
        color = TileKeys.liquidColors[index];
        identity = analagousStates[1];
        decisions = new LiquidPlayerDecisions(this);
        liquidNum = 3;
        solidNum = 0;

      }
    }
  }

  public void convertGasToLiquid(){
    if (gasNum == 4){
      gasNum = 0;
      liquidNum += 1;
    } else {
      System.out.println("convert gas to liquid called at inappropriate time");
    }
  }

  public void convertLiquidToSolid(){
    if (liquidNum == 4){
      liquidNum = 0;
      solidNum += 1;
    } else {
      System.out.println("convert liquid to solid called at inappropriate time");
      System.out.print("Liquids : ");
      System.out.println(liquidNum);
      System.out.print("Solids : ");
      System.out.println(solidNum);

    }
  }


  public void absorb(Entity e){
    char particleIdentity = e.getIdentity();
    for (int index = 0; index < totElements; index++){
      if (particleIdentity == TileKeys.gasses[index]){
        gasNum++;
      }
      if (particleIdentity == TileKeys.liquids[index]){
        liquidNum++;
      }
      if (particleIdentity == TileKeys.solids[index]){
        solidNum++;
      }
    }
    e.die();
  }

  public void dropParticle(){
    if (gasNum + liquidNum + solidNum > 0){
      char type;
      if (gasNum > 0){
        gasNum--;
        type = analagousStates[0];
      }else if (liquidNum > 0){
        if (liquidNum == 1 && solidNum == 0){
          downConvert();
          type = analagousStates[0];
        }else{
          liquidNum--;
          type = analagousStates[1];
        }
      }else{ //solidNum must be greater than zero
        if (solidNum == 1){
          downConvert();
          type = identity;
          type = analagousStates[1];
        }else {
          solidNum--;
          type = analagousStates[2];
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
        Ai ai = Ai.parse(type, board, aix, aiy);
        board.getTile(aix, aiy).occupy(ai);
        brain.passGame(ai);
      }
      if ((gasNum + liquidNum + solidNum) <= 0){
        setIdentity(getOriginalIdentity());
        setColor(getOriginalColor());
      }
    }
  }

  public void draw(Graphics g){
    g.setColor(color);
    g.fillOval(x * 10 , y * 10, size, size);
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

  public void die(){
    ;
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