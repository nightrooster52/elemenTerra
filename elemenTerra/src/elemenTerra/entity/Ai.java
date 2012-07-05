package elemenTerra.entity;

import elemenTerra.TileKeys;
import elemenTerra.world.Board;

public class Ai extends Entity {
  protected Brain brain;
  protected Board board;
  protected Entity target;
  protected char[] strongerStates;
  protected char[] weakerStates;
  protected char[] analagousStates;
  

  public Ai(int x, int y, Board b) {
    super(x, y);
    board = b;
    identity = '+';
  }

  public Ai(int x, int y, Entity target, Board b) {
    super(x, y);
    board = b;
    this.target = target;
    setTarget(target);
  }

  public void tick() {
    //System.out.println("asked the Ai to tick");
    //System.out.println(board.testString);
    brain.tick();
  }

  public void setTarget(Entity target) {
    this.target = target;
    setBrain(new SeekerBrain(this, target, board));
    identity = '+';
  }

  public Brain getBrain() {
    return brain;
  }

  public void setBrain(Brain brain) {
    this.brain = brain;
  }
  
  /*sets the state interaction arrays for easy access in collisions
   * I was wondering if we could use a hashmap here instead of arrays to make running collision logic faster?
   * 
   */
  protected void setInteractionKeys(){
	  analagousStates = analagousStates(identity);
	  weakerStates = weakerStates(identity);
	  strongerStates = strongerStates(identity);
  }

  protected char[] strongerStates(char identity){
	  for (int element = 0; element < 3; element++){
			for (int state = 0; state < 3; state++){
				if identity == TileKeys.interactionKey[element][state]{
					return TileKeys.interactionKey[(element+1)%3];
					}
				}		
	  		}
  		}
  protected char[] weakerStates(char identity){
	  for (int element = 0; element < 3; element++){
		  for (int state = 0; state < 3; state++){
			  if identity == TileKeys.interactionKey[element][state]{
				  return TileKeys.interactionKey[(element+2)%3];
				  }
			  }			
		  }
  }
  protected char[] analagousStates(char identity){
	  for (int element = 0; element < 3; element++){
			for (int state = 0; state < 3; state++){
				if identity == TileKeys.interactionKey[element][state]{
					return TileKeys.interactionKey[(element)];
					}	
				}	
			}	
  		}

  public static Ai parse(char c, Board board, int x, int y) {
    Ai ai = new Ai(x, y, board); // TODO: this
    ai.setIdentity(c);
    ai.setInteractionKeys();

    switch (c) {
    case TileKeys.fireGas:
      ai.setBrain(new GasBrain(ai, board));
      ai.setColor(TileKeys.lightRed);
      break;
    case TileKeys.fireLiquid:
      ai.setBrain(new LiquidBrain(ai, board));
      ai.setColor(TileKeys.red);
      break;
    case TileKeys.fireSolid:
      ai.setBrain(new LiquidBrain(ai, board));
      ai.setColor(TileKeys.darkRed);
      break;
    case TileKeys.earthGas:
      ai.setBrain(new GasBrain(ai, board));
      ai.setColor(TileKeys.lightGreen);
      break;
    case TileKeys.earthLiquid:
      ai.setBrain(new LiquidBrain(ai, board));
      ai.setColor(TileKeys.green);
      break;
    case TileKeys.earthSolid:
      ai.setBrain(new LiquidBrain(ai, board));
      ai.setColor(TileKeys.darkGreen);
      break;
    case TileKeys.waterGas:
      ai.setBrain(new GasBrain(ai, board));
      ai.setColor(TileKeys.lightBlue);
      break;
    case TileKeys.waterLiquid:
      ai.setBrain(new LiquidBrain(ai, board));
      ai.setColor(TileKeys.blue);
      break;
    case TileKeys.waterSolid:
      ai.setBrain(new LiquidBrain(ai, board));
      ai.setColor(TileKeys.darkBlue);
      break;
    }

    return ai;
  }
}