package elemenTerra.entity;

import elemenTerra.TileKeys;
import java.awt.Graphics;
import elemenTerra.world.Board;
import elemenTerra.Game;
import elemenTerra.world.Tile;
import elemenTerra.entity.decisions.*;
import elemenTerra.entity.brain.*;
import java.awt.Color;


public class Ai extends Entity {


  protected Entity target;
  public Ai(int x, int y, Board b) {
    super(x, y, b);
    identity = '+';
  }

  public Ai(int x, int y, Entity target, Board b) {
    super(x, y, b);
    this.target = target;
    setTarget(target);
  }

  public void tick() {
    if (alive){
      brain.tick();
    }
  }

  public void setTarget(Entity target) {
    this.target = target;
    setBrain(new SeekerBrain(this, target, board));
    identity = '+';
  }
  public void recieveBump(Entity e){
    //giveBump(e); //same thing for now
    ;
  }

  public void giveBump(Entity e){
    char bumpIdentity = e.getIdentity();

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

  public void condense(){
    Entity condensee;
    for (int index = 0; index < 3; index++){
      if (identity == TileKeys.liquids[index]){ //if i am a liquid
        for (int i = 0; i < 3; i++){
          condensee = brain.closestEntity(weakerStates[1], 1, 20); // kills 3 weaker liquids
          if (!(condensee == null)){
            condensee.die();
          }else{ //target failed
            for(int j = 0; j<i; j++){//undo kills
              spawnAi(weakerStates[1]);
            }
            break;
          }
          if (i == 2){ //if I killed 3 closest liquids
            color = TileKeys.solidColors[index]; //I become solid color
            Game game = brain.getGame(); //goofy rewiring because entities don't have game reference
            brain = (new SolidBrain(this, board));
            brain.setGame(game);
            identity = analagousStates[2];  //I take liquid type
            decisions = new SolidElementDecisions(this); //I get solid decisions

          }
        }
      }

      if (identity == TileKeys.gasses[index]){
        for (int i = 0; i < 3; i++){
          condensee = brain.closestEntity(weakerStates[0], 1, 20); // kills 3 weaker gasses
          if (!(condensee == null)){//if there is a target
            condensee.die();
          }else{ //target failed
            for(int j = 0; j<i; j++){//undo kills
              spawnAi(weakerStates[0]);
            }
            break;
          }
          if (i == 2){ //if I successfully killed 3 closest gasses
            color = TileKeys.liquidColors[index];
            Game game = brain.getGame();
            brain = (new LiquidBrain(this, board));
            brain.setGame(game);
            identity = analagousStates[1]; //I take liquid identity
            decisions = new LiquidElementDecisions(this);

          }
        }
      }
    }
  }

  public void dissipate(){
    for (int index = 0; index < 3; index++){
      if (identity == TileKeys.liquids[index]){//if I am a liquid
        color = TileKeys.gasColors[index]; //I take a gas color
        identity = analagousStates[0]; //I become gas state
        decisions = new GasElementDecisions(this); //set decisions
        Game game = brain.getGame(); //goofy rewiring because entities don't have game reference
        brain = (new GasBrain(this, board));
        brain.setGame(game);
        for (int i = 0; i < 3; i++){//spawn 3 gasses
          spawnAi(strongerStates[0]); //stronger
        }
      }
      if (identity == TileKeys.solids[index]){//if I am a solid
        color = TileKeys.liquidColors[index]; //I take my liquid color
        identity = analagousStates[1]; //I become liquid state
        Game game = brain.getGame();
        brain = (new LiquidBrain(this, board));
        brain.setGame(game);
        decisions = new LiquidElementDecisions(this);
        for (int i = 0; i < 3; i++){//spawn 3 liquids
          spawnAi(strongerStates[1]);//stronger
        }
      }
    }
  }
  public void spawnAi(char type){
    Tile destination = brain.closestEmptyTile();
    int aix = destination.getX();
    int aiy = destination.getY();
    Ai ai = Ai.parse(type, board, aix, aiy);
    board.getTile(aix, aiy).occupy(ai);
    brain.passGame(ai);
  }

  public void draw(Graphics g){
    //super.draw(g);
    brain.draw(g);
  }
  /*
   *
   ***********STATIC METHOD*********
   *
   *
   */

  public static Ai parse(char c, Board board, int x, int y) {
    Ai ai = new Ai(x, y, board);
    ai.setIdentity(c);
    ai.setInteractionKeys();

    switch (c) {
    case TileKeys.fireGas:
      ai.setBrain(new GasBrain(ai, board));
      ai.setColor(TileKeys.lightRed);
      ai.decisions = new GasElementDecisions(ai);
      //ai.setColor(new Color((int) (Math.random()*255), 0, 0));
      break;
    case TileKeys.fireLiquid:
      ai.setBrain(new LiquidBrain(ai, board));
      ai.decisions = new LiquidElementDecisions(ai);
      ai.setColor(TileKeys.red);
      break;
    case TileKeys.fireSolid:
      ai.setBrain(new SolidBrain(ai, board));
      ai.decisions = new SolidElementDecisions(ai);
      ai.setColor(TileKeys.darkRed);
      break;
    case TileKeys.earthGas:
      ai.setBrain(new GasBrain(ai, board));
      ai.setColor(TileKeys.lightGreen);
      ai.decisions = new GasElementDecisions(ai);
      break;
    case TileKeys.earthLiquid:
      ai.setBrain(new LiquidBrain(ai, board));
      ai.decisions = new LiquidElementDecisions(ai);
      ai.setColor(TileKeys.green);
      break;
    case TileKeys.earthSolid:
      ai.setBrain(new SolidBrain(ai, board));
      ai.decisions = new SolidElementDecisions(ai);
      ai.setColor(TileKeys.darkGreen);
      break;
    case TileKeys.waterGas:
      ai.setBrain(new GasBrain(ai, board));
      ai.setColor(TileKeys.lightBlue);
      ai.decisions = new GasElementDecisions(ai);
      break;
    case TileKeys.waterLiquid:
      ai.setBrain(new LiquidBrain(ai, board));
      ai.decisions = new LiquidElementDecisions(ai);
      ai.setColor(TileKeys.blue);
      break;
    case TileKeys.waterSolid:
      ai.setBrain(new SolidBrain(ai, board));
      ai.decisions = new SolidElementDecisions(ai);
      ai.setColor(TileKeys.darkBlue);
      break;
    case TileKeys.elecGas:
      ai.setBrain(new GasBrain(ai, board));
      ai.setColor(TileKeys.lightYellow);
      ai.decisions = new GasElementDecisions(ai);
      break;
    case TileKeys.elecLiquid:
      ai.setBrain(new LiquidBrain(ai, board));
      ai.decisions = new LiquidElementDecisions(ai);
      ai.setColor(TileKeys.yellow);
      break;
    case TileKeys.elecSolid:
      ai.setBrain(new SolidBrain(ai, board));
      ai.decisions = new SolidElementDecisions(ai);
      ai.setColor(TileKeys.darkYellow);
      break;
    }
    return ai;
  }
}