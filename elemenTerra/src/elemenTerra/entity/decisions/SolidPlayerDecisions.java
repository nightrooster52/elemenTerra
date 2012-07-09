package elemenTerra.entity.decisions;


import elemenTerra.*;
import elemenTerra.entity.*;
import elemenTerra.entity.brain.Brain;

public class SolidPlayerDecisions extends Decisions{

  protected Player body;
  public SolidPlayerDecisions(Player body){
    super(body);
    this.body = body;
  }
  //gas interactions
  public void analagousGas(Entity e){
    if (body.getGasNum() < 3){
      body.absorb(e);//absorb e
    } else if (body.getLiquidNum() < 3){
      body.absorb(e);//absorb e
      body.convertGasToLiquid();
    } else {
      body.absorb(e);
      body.convertGasToLiquid();
      body.convertLiquidToSolid();
      body.dropParticle(body.getIdentity());
    }
  }
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
      body.convertLiquidToSolid();
      body.dropParticle(body.getIdentity());
    }
  }


  public void strongerLiquid(Entity e){
    //lose liquid
    ;
  }
  public void weakerLiquid(Entity e){
    game.push(body, e, body.getFacing());
    //move e
    ;
  }

  //solid interactions
  public void analagousSolid(Entity e){
    if (body.getSolidNum() < 4){
      body.absorb(e);
    } else {
      game.push(body, e, body.getFacing());
    }
    
  }
  public void strongerSolid(Entity e){
    //nothing
    ;
  }
  public void weakerSolid(Entity e){
    //break e into 4 liquids
    ;
  }



}