package elemenTerra.entity;

import elemenTerra.world.Board;

public class GasBrain extends SeekerBrain{
    public GasBrain(Ai body, Board board){
	super(body, board);
	this.delay = 5;
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