package elemenTerra.entity.decisions;

import elemenTerra.*;
import elemenTerra.entity.*;
import elemenTerra.entity.brain.Brain;

public abstract class LiquidElementDecisions {
  protected Entity body;
  protected Brain brain;
  protected Game game;

  public Decisions(Entity e){
    this.body = e;
    this.brain = e.getBrain();
    this.game = brain.getGame();
  }

  //gas interactions
  public void analagousGas(Entity e){
    ;
  }

  public void strongerGas(Entity e){
    ;
  }
  public void weakerGas(Entity e){
    ;
  }

  //liquid interactions
  public void analagousLiquid(Entity e){
    ;
  }
  public void strongerLiquid(Entity e){
    ;
  }
  public void weakerLiquid(Entity e){
    ;
  }

  //solid interactions
  public void analagousSolid(Entity e){
    ;
  }
  public void strongerSolid(Entity e){
    ;
  }
  public void weakerSolid(Entity e){
    ;
  }


}