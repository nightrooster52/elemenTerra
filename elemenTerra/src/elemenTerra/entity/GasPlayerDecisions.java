package elemenTerra.entity;

public class GasPlayerDecisions extends Decisions{
    public GasPlayerDecisions(Player p){
	super(p);
    }

    //gas interactions
    public void analagousGas(Entity e){
	//absorb e
	;
    }
    public void StrongerGas(Entity e){
	//slowed by e
	;
    }
    public void WeakerGas(Entity e){
	//move e
	;
    }

    //liquid interactions
    public void analagousLiquid(Entity e){
	//move e
	;
    }
    public void StrongerLiquid(Entity e){
	//lose gas
	;
    }
    public void WeakerLiquid(Entity e){
	//move e
	;
    }

    //solid interactions
    public void analagousSolid(Entity e){
	//nothing
	;
    }
    public void StrongerSolid(Entity e){
	//nothing
	;
    }
    public void WeakerSolid(Entity e){
	//nothing
	;
    }




}