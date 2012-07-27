package elemenTerra.entity.decisions;

import elemenTerra.*;
import elemenTerra.entity.*;
import elemenTerra.entity.brain.Brain;

public class SolidElementDecisions extends Decisions{
  protected Ai body;

  public SolidElementDecisions(Ai body){
    super(body);
    this.body = body;
  }

  //analagous interactions
  public void analagousGas(Entity e){
    game.push(body, e, body.getFacing());
  }
  public void analagousLiquid(Entity e){
    game.push(body, e, body.getFacing());
  }
  public void analagousSolid(Entity e){
    //game.push(body, e, body.getFacing());

  }

  //stronger interactioins
  public void strongerGas(Entity e){
    //body.dissipate();
  }
  public void strongerLiquid(Entity e){
    body.dissipate();
  }
  public void strongerSolid(Entity e){
    body.dissipate();
  }

  //weaker interactions
  public void weakerGas(Entity e){
    game.push(body, e, body.getFacing());
  }
  public void weakerLiquid(Entity e){
    game.push(body, e, body.getFacing());
  }
  public void weakerSolid(Entity e){
    game.push(body, e, body.getFacing());
  }


}