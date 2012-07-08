package elemenTerra.entity.decisions;


import elemenTerra.*;
import elemenTerra.entity.*;
import elemenTerra.entity.brain.Brain;

public class SolidPlayerDecisions extends Decisions{
  public SolidPlayerDecisions(Player body){
    super(body);
  }
  //gas interactions
  public void analagousGas(Entity e){
    //move e
    ;
  }
  public void strongerGas(Entity e){
    //slowed by e
    ;
  }
  public void weakerGas(Entity e){
    //move e
    ;
  }

  //liquid interactions
  public void analagousLiquid(Entity e){
    //absorb liquid ^-> to solid
    ;
  }
  public void strongerLiquid(Entity e){
    //lose liquid
    ;
  }
  public void weakerLiquid(Entity e){
    //move e
    ;
  }

  //solid interactions
  public void analagoussolid(Entity e){
    //absorb e
    ;
  }
  public void strongersolid(Entity e){
    //nothing
    ;
  }
  public void weakersolid(Entity e){
    //break e into 4 liquids
    ;
  }



}