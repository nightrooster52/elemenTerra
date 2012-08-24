package elemenTerra.entity.brain;

import elemenTerra.world.Board;
import elemenTerra.entity.*;

public class LiquidBrain extends SeekerBrain{
  public LiquidBrain(Ai body, Board board){
    super(body, board);
    delay = 15;
    searchMax = 30; //normal mode
  }

  public void tick(){
    wait++;

    if (wait == delay) {
      wait = 0;
      target = closestEntity(body.analagousStates[2]);
      if (target == null){
        target = closestEntity(body.analagousStates[1]);
      }
      if (target == null){
        target = closestEntity(body.analagousStates[0]);
      }

      if (target != null){
        char output = goToTarget(target);
        game.handleMove(output, body);
      }
    }
  }
}