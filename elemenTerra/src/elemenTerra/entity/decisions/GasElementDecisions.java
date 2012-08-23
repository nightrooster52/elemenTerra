package elemenTerra.entity.decisions;

import elemenTerra.*;
import elemenTerra.entity.*;
import elemenTerra.entity.brain.Brain;

public class GasElementDecisions extends Decisions{
  protected Ai body;

  public GasElementDecisions(Ai body){
    super(body);
    this.body = body;
  }

  //analagous interactions
  public void analagousGas(Entity e){
    game.push(body, e, body.getFacing());
    ;
  }
  public void analagousLiquid(Entity e){
    ;
  }
  public void analagousSolid(Entity e){
    ;
  }

  //stronger interactions
  public void strongerGas(Entity e){
    ;
  }
  public void strongerLiquid(Entity e){
    ;
  }
  public void strongerSolid(Entity e){
    //game.push(body, e, body.getFacing());
    ;
  }

  //weaker interactions
  public void weakerGas(Entity e){
    body.condense();
    game.push(body, e, body.getFacing());
  }
  public void weakerLiquid(Entity e){
    body.condense();
    game.push(body, e, body.getFacing());
  }

  public void weakerSolid(Entity e){
    body.condense();
    game.push(body, e, body.getFacing());
  }
}