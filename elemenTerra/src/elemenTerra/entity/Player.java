package elemenTerra.entity;
public class Player extends Entity{

    private char elementState = 'X';

    public Player(int x, int y){
	super(x, y);
	identity = 'X';
    }

    public Player(int x, int y, char identity){
	super(x, y, identity);
    }
    public void handleBump(Entity e){
	elementState = e.getIdentity();
	color = e.getColor();
	;
    }
    
    
}