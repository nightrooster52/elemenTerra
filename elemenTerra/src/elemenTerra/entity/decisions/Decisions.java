package elemenTerra.entity.decisions;

import elemenTerra.entity.*;

public abstract class Decisions {
    private Player player;

    public Decisions(Player p){
	this.player = p;
    }
    //gas interactions
    public void analagousGas(Entity e){
	;
    }
    public void StrongerGas(Entity e){
	;
    }
    public void WeakerGas(Entity e){
	;
    }

    //liquid interactions
    public void analagousLiquid(Entity e){
	;
    }
    public void StrongerLiquid(Entity e){
	;
    }
    public void WeakerLiquid(Entity e){
	;
    }

    //solid interactions
    public void analagousSolid(Entity e){
	;
    }
    public void StrongerSolid(Entity e){
	;
    }
    public void WeakerSolid(Entity e){
	;
    }


}