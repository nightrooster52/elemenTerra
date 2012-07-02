package elemenTerra.entity;

import elemenTerra.world.Board;

public class Ai extends Entity{
    protected Brain brain;
    protected Board board;
    protected Entity target;

    public Ai(int x, int y, Board b){
	super(x, y);
	this.board = b;
	this.string = "?";
    }

    public Ai(int x, int y, Entity target, Board b){
	super(x, y);
	this.board = b;
	this.target = target;
	this.setTarget(target);
    }

    public void tick(){
	//System.out.println("asked the Ai to tick");
	//System.out.println(board.testString);
	brain.tick();
    }
    
    public void setTarget(Entity target){
	this.target = target;
	setBrain(new SeekerBrain(this, target, board));
	this.string = "+";
    }
    public Brain getBrain(){
	return brain;
    }

    public void setBrain(Brain brain){ 
	this.brain = brain;
    }

    public void setString(String s){
	this.string = s;
    }
}