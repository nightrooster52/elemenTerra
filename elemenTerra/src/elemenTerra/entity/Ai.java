package elemenTerra.entity;

import elemenTerra.TileKeys;
import elemenTerra.world.Board;
import elemenTerra.entity.brain.*;


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
    brain.tick();
  }

  public void setTarget(Entity target) {
    this.target = target;
    setBrain(new SeekerBrain(this, target, board));
    identity = '+';
  }

  
  /*sets the state interaction arrays for easy access in collisions
   * I was wondering if we could use a hashmap here instead of arrays to make running collision logic faster?
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
      break;
    case TileKeys.fireLiquid:
      ai.setBrain(new LiquidBrain(ai, board));
      ai.setColor(TileKeys.red);
      break;
    case TileKeys.fireSolid:
      ai.setBrain(new SolidBrain(ai, board));
      ai.setColor(TileKeys.darkRed);
      break;
    case TileKeys.earthGas:
      ai.setBrain(new GasBrain(ai, board));
      ai.setColor(TileKeys.lightGreen);
      break;
    case TileKeys.earthLiquid:
      ai.setBrain(new LiquidBrain(ai, board));
      ai.setColor(TileKeys.green);
      break;
    case TileKeys.earthSolid:
      ai.setBrain(new SolidBrain(ai, board));
      ai.setColor(TileKeys.darkGreen);
      break;
    case TileKeys.waterGas:
      ai.setBrain(new GasBrain(ai, board));
      ai.setColor(TileKeys.lightBlue);
      break;
    case TileKeys.waterLiquid:
      ai.setBrain(new LiquidBrain(ai, board));
      ai.setColor(TileKeys.blue);
      break;
    case TileKeys.waterSolid:
      ai.setBrain(new SolidBrain(ai, board));
      ai.setColor(TileKeys.darkBlue);
      break;
    }
    return ai;
  }
}