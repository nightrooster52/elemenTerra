package elemenTerra;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import elemenTerra.GFX.Display;

import Entity.Entity;
import Entity.Player;

public class Game{
    Board board;
    Player player;
    Ai[] aiArray;
    Display display;
    Scanner scanner = new Scanner(System.in);
    Maps maps = new Maps();

    public String testString = "GAME REFERENCE VALID";

    public Game(){

	this.board = new Board(maps.randomElements());	////////////////////////////////////SELECT MAP///////////////////////////////////
	this.player = board.getPlayer();
	this.aiArray = board.getAiArray();
    }

    public String toString(){
	return board.toString();
    }

    public void moveEntity(int dx, int dy, Entity e){
      //System.out.println("called moveEntity");

      int px = e.getX();
      int py = e.getY();

      int x = px + dx;
      int y = py + dy;
      //System.out.println(x);      
      //System.out.println(y);      
      if (board.checkTile(x,y)){
	  //System.out.println("checkTile valid");
	  Tile targetTile = board.getTile(x, y);
	  // vacate Entity's current tile
	  board.getTile(px, py).vacate();
	  // move the entity
	  e.move(dx, dy);
	  // occupy the new tile
	  targetTile.occupy(e);
      } else { board.bump(x, y, e);
      }
    }

    public void handleMove(String s, Entity e){
	//System.out.println("called handleMove");
	if (s.equals("w")){
	    e.face(s);
	    moveEntity(0, -1, e);
	}
	else if (s.equals("a")){
	    e.face(s);
	    moveEntity(-1, 0, e);
	}
	else if (s.equals("s")){
	    e.face(s);
	    moveEntity(0, 1, e);
	}
	else if (s.equals("d")){
	    e.face(s);
	    moveEntity(1, 0, e);
	}
	else{
	    ; // do nothing
	}
    }
    public void mainloop(Game game){
	board.takeGame(this);
	board.gameAi();

	this.display = new Display(board);

	Timer t = new Timer();
	double seconds = 0.1;
	t.schedule(new Tick(game), 0, Math.round(seconds*1000));
    }
    public void tick(){
	//System.out.println(this);
	//handleMove(scanner.next(), player);
	display.repaint();
	for (int i=0 ; i<aiArray.length; i++){
	    aiArray[i].tick();
	}
    }
    

  /***********************************/
    public static class Tick extends TimerTask{
	Game game;
	public Tick(Game game){
	    this.game = game;
	}
	public void run(){
	    game.tick();
	}
    }
    public static void main(String[] args){
	Game g = new Game();

	g.mainloop(g);
    }
  /************************************/

}