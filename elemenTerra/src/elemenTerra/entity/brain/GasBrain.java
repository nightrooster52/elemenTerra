package elemenTerra.entity.brain;

import elemenTerra.world.Board;
import elemenTerra.entity.*;

public class GasBrain extends SeekerBrain {

  public GasBrain(Ai body, Board board) {
    super(body, board);
    delay = 5;
    searchBuffer = 5;
    targetStable = true;
  }

  public void tick() {
    wait++;

    if (wait == delay){
      wait = 0;
      target = closestEntity(body.analagousStates[2]);
      if (target == null){
        target = closestEntity(body.getIdentity());
      }

      if (target != null){
        char output = goToTarget(target);
        game.handleMove(output, body);
      }
    }
  }
}