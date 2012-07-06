package elemenTerra.entity;
import java.awt.Color;

public class Player extends Entity{
    
    protected Decisions decisions;

    public Player(int x, int y){
	super(x, y);
	identity = 'X';
    }

    public Player(int x, int y, char identity){
	super(x, y, identity);
    }
    public void handleBump(Entity e){
	/* ****next to implement****** 
	 *if Player is  blank and e is a gas
	 *immitate and absorbe e
	 *
	 *if Player is not blank
	 *classify e
	 *decisions.classification(e);
	 */
	identity = e.getIdentity();
	color = e.getColor();
	setInteractionKeys();
    }
    
}