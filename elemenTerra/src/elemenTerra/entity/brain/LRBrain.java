package elemenTerra.entity.brain;

import elemenTerra.world.Board;
import elemenTerra.entity.*;

public class LRBrain extends Brain {
	private String lr; //is this a left or a right turning brain

	public LRBrain(Ai body, Board board, String lr){
		super(body, board);
		this.lr = lr;
		this.delay = 2;
		if (lr.equals("left")){
			wait = delay/2; //offsets the ticks
		}
	}
	private char walk(){
		think(4); //think a maximum of 4 times (for if the ai gets trapped
		return body.getFacing();
		// project in direction 'facing'
		//if blocked, try left
	}

	private void think(int esc){
		esc--;
		if (esc > 0){
			if (!look(body.getFacing())){ //if you can't go forward
				body.turn(lr);  //turn in the left or right direction
				think(esc);  //look
			}
		}
	}
	@Override
	public void tick(){
		wait++;
		if (wait == delay){
			wait = 0;
			char output= walk();
			game.handleMove(output, body);
		}
	}
	public String getLR(){
		return lr;
	}
}