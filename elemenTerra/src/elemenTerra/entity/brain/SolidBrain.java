package elemenTerra.entity.brain;

import elemenTerra.world.Board;
import elemenTerra.entity.*;
import java.awt.Graphics;

public class SolidBrain extends SeekerBrain{
  public SolidBrain(Ai body, Board board){
    super(body, board);
    size = 9;
    delay = 60;
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
  public void draw(Graphics g){
    g.setColor(body.getColor());
    g.fillRect(body.getX() * 10 , body.getY() * 10, size, size);
  }
}