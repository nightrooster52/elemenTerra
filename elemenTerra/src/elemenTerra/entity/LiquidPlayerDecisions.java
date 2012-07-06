package elemenTerra.entity;

public class LiquidPlayerDecisions extends Decisions{
    public LiquidPlayerDecisions(Player p){
	super(p);
    }
    //gas interactions
    public void analagousGas(Entity e){
	//absorb e and ^-> it to Liquid when have 4
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
	//absorb e
	;
    }
    public void StrongerLiquid(Entity e){
	//lose liquid
	;
    }
    public void WeakerLiquid(Entity e){
	//break e down into 4 gas
	;
    }

    //solid interactions
    public void analagousSolid(Entity e){
	//blocked
	;
    }
    public void StrongerSolid(Entity e){
	//blocked
	;
    }
    public void WeakerSolid(Entity e){
	//move
	;
    }


}