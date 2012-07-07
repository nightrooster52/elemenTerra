package elemenTerra.entity.decisions;

import elemenTerra.entity.*;

public class GasPlayerDecisions extends Decisions{
    public GasPlayerDecisions(Player p){
	super(p);
    }

    //gas interactions
    public void analagousGas(Entity e){
	//absorb e
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
	//move e
	;
    }
    public void strongerLiquid(Entity e){
	//lose gas
	;
    }
    public void weakerLiquid(Entity e){
	//move e
	;
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
	//nothing
	;
    }




}