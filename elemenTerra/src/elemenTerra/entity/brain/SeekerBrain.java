package elemenTerra.entity.brain;

import elemenTerra.world.Board;
import elemenTerra.entity.*;

public class SeekerBrain extends Brain {
  protected Entity target;
  protected boolean targetStable = true;

  public SeekerBrain(Ai body, Board board) {
    super(body, board);
    target = body;
  }

  public SeekerBrain(Ai body, Entity target, Board board) {
    super(body, board);
    this.target = target;
  }

  public char goToXY(int targetx, int targety, Board b) {
    int ix = body.getX();
    int iy = body.getY();

    int tx = targetx;
    int ty = targety;

    int distancex = tx - ix;
    int distancey = ty - iy;

    int attemptx = 0;
    int attempty = 0;
    char aXchar = 'Z';
    char aYchar = 'Z';

    if (distancex > 0) {
      attemptx = 1;
      aXchar = 'd';
      //System.out.println("Right attempt");
    } else if (distancex < 0) {
      attemptx = -1;
      aXchar = 'a';
      //System.out.println("Left attempt");
    }
    if (distancey > 0) {
      attempty = 1;
      aYchar = 's';

      //System.out.println("Down attempt");
    } else if (distancey < 0) {
      attempty = -1;
      aYchar = 'w';
      //System.out.println("Up attempt");
    }

    int dx = ix + attemptx;
    int dy = iy + attempty;

    char attempt = 'Z';
    //farther in x than y distance (squared for absolute value), x check first
    if (distancex*distancex > distancey*distancey) {
      if (board.checkTile(dx, iy)) {
        attempt = aXchar;
      } else {
	  if (!(board.getTile(dx, iy).getIdentity() == body.getIdentity())) {
          attempt = aYchar;
        }
      }
    //farther in y than x distance (squared for absolute value), y check first
    } else if (distancex*distancex < distancey*distancey) {
      if (board.checkTile(ix, dy)) {
        attempt = aYchar;
      } else {
	  if (!(board.getTile(ix, dy).getIdentity() == body.getIdentity())) {
          attempt = aXchar;
	  }
      }
    } else {
	boolean xory = random.nextBoolean();
	if (xory){
	    attempt = aYchar;
	}else {
	    attempt = aXchar;
	}
    }
    return attempt;
  }

  public char goToTarget() {
    int targetx = target.getX();
    int targety = target.getY();
    char attempt = goToXY(targetx, targety, board);
    //System.out.println("Ai attempted move");
    return attempt;
  }

  @Override
  public void tick() {
    //System.out.println("SeekerBrain ticked");
    //System.out.println(board.testString);
    wait++;
    if (wait == delay) {
      wait = 0;
      char output = goToTarget();
      //System.out.println(output);
      game.handleMove(output, body);
    }
  }
}
