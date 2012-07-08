package elemenTerra.entity.decisions;


import elemenTerra.*;
import elemenTerra.entity.*;
import elemenTerra.entity.brain.Brain;

public class GasPlayerDecisions extends Decisions{
    public GasPlayerDecisions(Player player){
	super(player);
    }

    //gas interactions
    public void analagousGas(Entity e){
	if (player.getGasNum() < 200){
	    player.absorb(e);//absorb e
	} else {
	    game.push(player, e, player.getFacing());
	}
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