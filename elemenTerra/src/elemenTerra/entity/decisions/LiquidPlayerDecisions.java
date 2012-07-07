package elemenTerra.entity.decisions;

import elemenTerra.entity.*;

public class LiquidPlayerDecisions extends Decisions{
    public LiquidPlayerDecisions(Player p){
	super(p);
    }
    //gas interactions
    public void analagousGas(Entity e){
	//absorb e and ^-> it to Liquid when have 4
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
	//absorb e
	;
    }
    public void strongerLiquid(Entity e){
	//lose liquid
	;
    }
    public void weakerLiquid(Entity e){
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
	//move
	;
    }


}