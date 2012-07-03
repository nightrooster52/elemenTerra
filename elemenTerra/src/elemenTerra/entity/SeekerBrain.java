package elemenTerra.entity;

import elemenTerra.world.Board;

public class SeekerBrain extends Brain {
  protected Entity target;

  public SeekerBrain(Ai body, Board board) {
    super(body, board);
    target = body;
  }

  public SeekerBrain(Ai body, Entity target, Board board) {
    super(body, board);
    this.target = target;
  }

  public String goToXY(int targetx, int targety, Board b) {
    int ix = body.getX();
    int iy = body.getY();
    //System.out.println("AI");
    //System.out.println(ix);
    //System.out.println(iy);

    int tx = targetx;
    int ty = targety;
    //System.out.println(tx);
    //System.out.println("Target");
    //System.out.println(ty);

    int distancex = tx - ix;
    int distancey = ty - iy;
    //System.out.println("Distance");
    //System.out.println(distancex);
    //System.out.println(distancey);

    //set up the dx/dy
    int attemptx = 0;
    int attempty = 0;
    String aXstr = "";
    String aYstr = "";
    if (distancex > 0) {
      attemptx = 1;
      aXstr = "d";
      //System.out.println("Right attempt");
    } else if (distancex < 0) {
      attemptx = -1;
      aXstr = "a";
      //System.out.println("Left attempt");
    }
    if (distancey > 0) {
      attempty = 1;
      aYstr = "s";

      //System.out.println("Down attempt");
    } else if (distancey < 0) {
      attempty = -1;
      aYstr = "w";
      //System.out.println("Up attempt");
    }

    int dx = ix + attemptx;
    int dy = iy + attempty;
    String attempt = " ";
    //farther in x than y distance (squared for absolute value), x dominates
    if (distancex * distancex > distancey * distancey) {
      //System.out.println("distance larger: y");
      if (board.checkTile(dx, iy)) {
        //System.out.println("check x valid");
        attempt = aXstr;
      } else {
        if (!board.getTile(dx, iy).getEntity().toString().equals("X")) {
          attempt = aYstr;
          //System.out.println(board.getTile(dx, iy).getEntity().toString());
        }
      }
    } else {
      //System.out.println("distance larger: y");
      if (board.checkTile(ix, dy)) {
        //System.out.println("check y valid");
        attempt = aYstr;
      } else {
        if (!board.getTile(ix, dy).getEntity().toString().equals("X")) {
          attempt = aXstr;
          //System.out.println(board.getTile(ix, dy).getEntity().toString());
        }
      }
    }
    return attempt;
  }

  public String goToTarget() {
    int targetx = target.getX();
    int targety = target.getY();
    String attempt = goToXY(targetx, targety, board);
    //System.out.println("Ai attempted move");
    return attempt;
  }

  public void tick() {
    //System.out.println("SeekerBrain ticked");
    //System.out.println(board.testString);
    wait++;
    if (wait == delay) {
      wait = 0;
      String output = goToTarget();
      //System.out.println(output);
      game.handleMove(output, body);
    }
  }
}
