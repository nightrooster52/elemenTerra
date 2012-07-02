package elemenTerra.entity;

import elemenTerra.Board;

public class LiquidBrain extends SeekerBrain{
    public LiquidBrain(Ai body, Board board){
	super(body, board);
	this.delay = 10;
    }
    public void tick(){
	wait++;
	
	if (wait == delay){
	    wait = 0;
	    this.target = closestEntity(body.toString());
	    String output = goToTarget();
	    game.handleMove(output, body);
	}
    }
}