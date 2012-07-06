package elemenTerra.entity;

public class SolidPlayerDecisions extends Decisions{
    public SolidPlayerDecisions(Player p){
	super(p);
    }
    //gas interactions
    public void analagousGas(Entity e){
	//move e
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
	//absorb liquid ^-> to solid
	;
    }
    public void StrongerLiquid(Entity e){
	//lose liquid
	;
    }
    public void WeakerLiquid(Entity e){
	//move e
	;
    }

    //solid interactions
    public void analagousSolid(Entity e){
	//absorb e
	;
    }
    public void StrongerSolid(Entity e){
	//nothing
	;
    }
    public void WeakerSolid(Entity e){
	//break e into 4 liquids
	;
    }



}