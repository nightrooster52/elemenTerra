package elemenTerra.entity.brain;

import elemenTerra.world.Board;
import elemenTerra.entity.*;

public class GasBrain extends SeekerBrain {
    
    public GasBrain(Ai body, Board board) {
	super(body, board);
	delay = 5;
	searchBuffer = 3;
	targetStable = true;
    }

    public void tick() {
	wait++;
	if (wait == delay) {
	    wait = 0;
	    target = closestEntity(body.getIdentity());
	    char output = goToTarget();
	    game.handleMove(output, body);
	}
    }
}