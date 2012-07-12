package elemenTerra;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import elemenTerra.GFX.BoardView;
import elemenTerra.GFX.Display;
import elemenTerra.entity.Entity;
import elemenTerra.entity.Player;
import elemenTerra.world.Board;
import elemenTerra.world.Maps;
import elemenTerra.world.Tile;

public class Game {
  // object fields
  protected Board board;
  protected Display display;
  protected Player player;
  protected Maps maps;
  protected Scanner scanner;
  protected Timer gameTimer;


  public boolean interElementReactions = true;
  //public boolean interElementReactions = true;

  public String testString = "GAME REFERENCE VALID";

  /**
   *
   * MAIN METHOD
   *
   */
  public static void main(String[] args) {
    Game g = new Game();
    g.start();
  }

  /**
   *
   * CONSTRUCTOR
   *
   */

  public Game() {
    maps = new Maps();
    board = new Board(maps.randomElements());

    //board = new Board(maps.randomGasHuge());
    //board = new Board(maps.randomFireHuge());
    //board = new Board(maps.biasTestMap);
    //board = new Board(maps.elementMap);
    player = board.getPlayer();
    scanner = new Scanner(System.in);
    gameTimer = new Timer();
  }
  public void portEntity(Tile destination,  Entity e){
    int px = e.getX();
    int py = e.getY();

    int x = destination.getX();
    int y = destination.getY();

    if (board.checkTile(x, y)) {
      // vacate Entity's current tile
      board.getTile(px, py).vacate();

      // move the entity
      e.setX(x);
      e.setY(y);

      // occupy the new tile
      destination.occupy(e);

    } else {
      board.bump(x, y, e);
    }
  }
  public void push(Entity pusher, Entity pushed, char direction){

    if (pushed.getBrain().look(direction)){
      handleMove(direction, pushed);
    }else {
      Tile destination = pusher.getBrain().closestEmptyTile();
      portEntity(destination, pushed);

    }
    handleMove(direction, pusher);

  }


  public void moveEntity(int dx, int dy, Entity e) {
    //System.out.println("called moveEntity");
    int px = e.getX();
    int py = e.getY();

    int x = px + dx;
    int y = py + dy;

    if (board.checkTile(x, y)) {

      Tile targetTile = board.getTile(x, y);

      // vacate Entity's current tile
      board.getTile(px, py).vacate();

      // move the entity
      e.move(dx, dy);

      // occupy the new tile
      targetTile.occupy(e);

    } else {
      board.bump(x, y, e);
    }
  }
  public void handleInput(char input, Entity e){
    //resets the entity that inputs 'x'
    if (input == 'x' || input == 'r'){
      playerInput(input, e);
    }else {
      handleMove(input, e);
    }
  }

  public void playerInput(char input, Entity e){
    e.handleInput(input);
  }


  public void handleMove(char input, Entity e) {
    //System.out.println("called handleMove");
    if (input == 'w') {
      e.face(input);
      moveEntity(0, -1, e);
    } else if (input == 'a') {
      e.face(input);
      moveEntity(-1, 0, e);
    } else if (input == 's') {
      e.face(input);
      moveEntity(0, 1, e);
    } else if (input == 'd') {
      e.face(input);
      moveEntity(1, 0, e);
    } else {
      ; // do nothing
    }
  }


  public void start() {
    // Now that the game is created
    display = new Display(board);

    ((BoardView) display.getContentPane()).setGame(this);

    for (Entity entity : board.getEntities()) {
      entity.getBrain().setGame(this);
      entity.getDecisions().setGame(this);
    }

    gameTimer.schedule(new TimerTask() {
      @Override
      public void run() {
        tick();
      }
    }, 0, 1000/60);
  }

  public void tick() {
    for (Entity entity : board.getEntities()) {
      entity.tick();
    }
    display.repaint(); // redraw game
  }

  @Override
  public String toString() {
    return board.toString();
  }
}