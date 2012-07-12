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

  //gas interactions
  public void analagousGas(Entity e){
    ;
  }
  public void strongerGas(Entity e){
    ;
  }
  public void weakerGas(Entity e){
    if (game.interElementReactions){
      body.condense();
    }
    game.push(body, e, body.getFacing());
  }

  //liquid interactions
  public void analagousLiquid(Entity e){
    ;
  }
  public void strongerLiquid(Entity e){
    ;
  }
  public void weakerLiquid(Entity e){
    if (game.interElementReactions){
      body.condense();
    }
    game.push(body, e, body.getFacing());
  }

  //solid interactions
  public void analagousSolid(Entity e){
    if (game.interElementReactions){
      //body.condense();
      ;
    }
  }
  public void strongerSolid(Entity e){
    ;
  }
  public void weakerSolid(Entity e){
    if (game.interElementReactions){
      body.condense();
      ;
    }
    game.push(body, e, body.getFacing());
  }


}