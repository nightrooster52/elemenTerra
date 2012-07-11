package elemenTerra.entity.decisions;

import elemenTerra.*;
import elemenTerra.entity.*;
import elemenTerra.entity.brain.Brain;

public class SolidElementDecisions extends Decisions{

  public SolidElementDecisions(Entity body){
    super(body);
  }

  //gas interactions
  public void analagousGas(Entity e){
    game.push(body, e, body.getFacing());
  }

  public void strongerGas(Entity e){
    ;
  }
  public void weakerGas(Entity e){
    game.push(body, e, body.getFacing());
  }

  //liquid interactions
  public void analagousLiquid(Entity e){
    game.push(body, e, body.getFacing());
  }
  public void strongerLiquid(Entity e){
    ;
  }
  public void weakerLiquid(Entity e){
    game.push(body, e, body.getFacing());
  }

  //solid interactions
  public void analagousSolid(Entity e){
    ;
  }
  public void strongerSolid(Entity e){
    ;
  }
  public void weakerSolid(Entity e){
    game.push(body, e, body.getFacing());
  }


}