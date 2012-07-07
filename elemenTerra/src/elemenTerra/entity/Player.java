package elemenTerra.entity;

import java.awt.Color;
import elemenTerra.entity.decisions.*;
import elemenTerra.entity.brain.Brain;
import elemenTerra.world.Board;
import elemenTerra.TileKeys;

public class Player extends Entity{
    protected int gasNum = 0;
    protected int liquidNum = 0;
    protected int solidNum = 0;
    protected Decisions decisions;
    protected Brain brain;

    public Player(int x, int y, Board b){
	super(x, y, b, 'X');
	
    }

    public Player(int x, int y, Board b, char identity){
	super(x, y, b, identity);
    }
    public void handleBump(Entity e){
	/* ****next to implement****** 
	 *if Player is  blank and e is a gas
	 *immitate and absorbe e
	 *
	 *if Player is not blank
	 *classify e
	 *decisions.classification(e);
	 */
	char bumpIdentity = e.getIdentity();

	if (identity == 'X'){
	    for (int index = 0; index < 3; index++){
		if (bumpIdentity == TileKeys.gasses[index]){
		    immitate(e);
		}
	    }
	}
    }

    private void immitate(Entity e){
	identity = e.getIdentity();
	color = e.getColor();
	setInteractionKeys();
    }
    public void dropParticle(){
	
    }
    
}