package elemenTerra.entity;
import java.util.Arrays;
import java.util.Random;

import elemenTerra.Board;
import elemenTerra.Game;
import elemenTerra.Tile;

public abstract class Brain{
    protected int delay = 10;
    protected int wait = 0;
    protected Ai body;
    protected Board board;
    protected Game game;
    protected Random random = new Random();


   public Brain(Ai body, Board board){
	this.body = body;
	this.board = board;
    }

    public void takeGame(){
	System.out.println("took Game");
	this.game = board.getGame();
    }

    public boolean look(String wasd){
	int ix = body.getX();
	int iy = body.getY();

	if (wasd.equals("w")){
	    return board.checkTile(ix, iy-1);
	}
	if (wasd.equals("a")){
	    return board.checkTile(ix-1, iy);
	}
	if (wasd.equals("s")){
	    return board.checkTile(ix, iy+1);
	}
	if (wasd.equals("d")){
	    return board.checkTile(ix+1, iy);
	}
	return false;
    }
    public Entity closestEntity(String type){
	Entity entity;
	for (int range = 1; range < 10; range++){
	    Tile[]  shell = searchTiles(range);
	    int lookfirst = random.nextInt(shell.length-1); //randomizes the first direction looked, so there isn't an upper-left dx bias
	    //System.out.println("");
	    //System.out.println("cursor ints:");
	    for (int searched = 0; searched < shell.length; searched++){
		int cursor = ((searched + lookfirst) % (shell.length-1));
		//System.out.print(cursor);
		if (shell[cursor].isOccupied()){
		    entity = shell[cursor].getEntity();
		    if (type.equals(entity.toString())){
			System.out.print(body.getX()+body.getY());
			System.out.print(" found Target at");
			System.out.println(entity.getX()+entity.getY());
			return entity;
		    }
		}
	    }
	}
	return body;
    }
    public Tile[] searchTiles(int range){
	int ix = body.getX();
	int iy = body.getY();
	Tile[] shell = new Tile[0];

	for (int row = -range; row <= range; row++){
	    for (int col = -range; col <= range; col++){
		if ((row*row == range*range) || (col*col) == (range*range)){ //either row or col are == +- range)
		    int tilex = (col+ix);
		    int tiley = (row+iy);		    
		    if (board.inBounds(tilex, tiley)){
			Tile tile = board.getTile(tilex, tiley);
			shell = Arrays.copyOf(shell, shell.length+1);
			shell[shell.length-1]= tile;
		    }
		}
	    }
	}
	return shell;

    }
    public abstract void tick();
    
    
}