package elemenTerra.entity.decisions;


import elemenTerra.*;
import elemenTerra.entity.*;
import elemenTerra.entity.brain.Brain;

public class GasPlayerDecisions extends Decisions{

  protected Player body;
  public GasPlayerDecisions(Player body){
    super(body);
    this.body = body;
  }

  //gas interactions
  public void analagousGas(Entity e){
    if (body.getGasNum() < 3){
      body.absorb(e);//absorb e
    } else {
      body.absorb(e);//absorb e
      body.upConvert();
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
    //move e
    ;
  }
  public void strongerLiquid(Entity e){
    //lose gas
    ;
  }
  public void weakerLiquid(Entity e){
    game.push(body, e, body.getFacing());
  }

  //solid interactions
  public void analagousSolid(Entity e){
    //nothing
    ;
  }
  public void strongerSolid(Entity e){
    //nothing
    ;
  }
  public void weakerSolid(Entity e){
    game.push(body, e, body.getFacing());
  }




}