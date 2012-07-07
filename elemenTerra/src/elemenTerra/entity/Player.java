package elemenTerra.entity;

import java.awt.Color;
import elemenTerra.entity.decisions.*;
import elemenTerra.entity.brain.Brain;
import elemenTerra.world.Board;
import elemenTerra.world.Tile;
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
	 *classify e
	 *decisions.classification(e);
	 */
	char bumpIdentity = e.getIdentity();

	if (identity == 'X'){
	    for (int index = 0; index < 3; index++){
		if (bumpIdentity == TileKeys.gasses[index]){
		    immitate(e);
		    decisions = new GasPlayerDecisions(this);
		}
	    }
	}
    }

    private void immitate(Entity e){
	identity = e.getIdentity();
	color = e.getColor();
	setInteractionKeys();
    }

    private void absorb(Entity e){
	char particleIdentity = e.getIdentity();
	for (int index = 0; index < 3; index++){
	    if (particleIdentity == TileKeys.gasses[index]){
		e.die();
		gasNum++;
	    }
	}
	for (int index = 0; index < 3; index++){
	    if (particleIdentity == TileKeys.liquids[index]){
		e.die();
		liquidNum++;
	    }
	}
	for (int index = 0; index < 3; index++){
	    if (particleIdentity == TileKeys.solids[index]){
		e.die();
		solidNum++;
	    }
	}
    }

    public void dropParticle(char type){
	if (brain.look(facing)){
	    int[] xy = wasdToXY(facing);
	    int aix = x + xy[0];
	    int aiy = y + xy[1];
	    Ai.parse(type, board, aix, aiy);
	}else {
	    Tile destination = brain.closestEmptyTile();
	    x = destination.getX();
	    y = destination.getY();
	    Ai.parse(type, board, x, y);
	}
    }
    //utility method
    public int[] wasdToXY(char input){



	if (input == 'w') {
	    int[] xy = {0, -1};
	    return xy ; 
	} else if (input == 'a') {
	    int[] xy = {-1, 0};
	    return xy ;
	} else if (input == 's') {
	    int[] xy = {0, 1}; 
	    return xy; 
	} else if (input == 'd') {
	    int[] xy = {1, 0}; 
	    return  xy;
	} else {
	    int[] xy = {0, 0}; 
	    return xy;
	}
	
    }


    
}