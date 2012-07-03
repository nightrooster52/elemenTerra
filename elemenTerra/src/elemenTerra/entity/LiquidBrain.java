package elemenTerra.entity;

import elemenTerra.world.Board;

public class LiquidBrain extends SeekerBrain{
	public LiquidBrain(Ai body, Board board){
		super(body, board);
		this.delay = 10;
	}
	@Override
	public void tick(){
		wait++;

		if (wait == delay){
			wait = 0;
			this.target = closestEntity(body.getIdentity());
			char output = goToTarget();
			game.handleMove(output, body);
		}
	}
}