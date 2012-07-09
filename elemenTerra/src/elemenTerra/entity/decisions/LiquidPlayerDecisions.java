package elemenTerra.entity.decisions;

import elemenTerra.*;
import elemenTerra.entity.*;
import elemenTerra.entity.brain.Brain;

public class LiquidPlayerDecisions extends Decisions{

  protected Player body;
  public LiquidPlayerDecisions(Player body){
    super(body);
    this.body = body;
  }
  //gas interactions
  public void analagousGas(Entity e){
    if (body.getGasNum() < 3){
      body.absorb(e);
    } else {
      body.absorb(e);
      body.convertGasToLiquid();
      body.dropParticle(body.getIdentity());
    }
  }
  //absorb e and ^-> it to Liquid when have 4
  public void strongerGas(Entity e){
    //slowed by e
    ;
  }
  public void weakerGas(Entity e){
    game.push(body, e, body.getFacing());
  }

  //liquid interactions
  public void analagousLiquid(Entity e){
    if (body.getLiquidNum() < 3){
      body.absorb(e);
    } else {
      body.absorb(e);
      body.upConvert();
    }
  }
  public void strongerLiquid(Entity e){
    //lose liquid
    ;
  }
  public void weakerLiquid(Entity e){
    game.push(body, e, body.getFacing());
    //break e down into 4 gas
    ;
  }

  //solid interactions
  public void analagousSolid(Entity e){
    //blocked
    ;
  }
  public void strongerSolid(Entity e){
    //blocked
    ;
  }
  public void weakerSolid(Entity e){
    game.push(body, e, body.getFacing());
    //move
    ;
  }


}