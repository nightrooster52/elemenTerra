package elemenTerra;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import elemenTerra.GFX.GUI;
import elemenTerra.entity.Ai;
import elemenTerra.entity.Entity;
import elemenTerra.entity.Player;
import elemenTerra.world.Board;
import elemenTerra.world.Maps;
import elemenTerra.world.Tile;

public class Game {
  // primitive fields
  protected Ai[] aiArray;

  // object fields
  protected Board board;
  protected GUI display;
  protected Player player;
  protected Maps maps;
  protected Scanner scanner;
  protected Timer gameTimer;
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
    display = new GUI(board);
    player = board.getPlayer();
    aiArray = board.getAiArray();
    scanner = new Scanner(System.in);
    gameTimer = new Timer();
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

  public void handleMove(String s, Entity e) {
    //System.out.println("called handleMove");
    if (s.equals("w")) {
      e.face(s);
      moveEntity(0, -1, e);
    } else if (s.equals("a")) {
      e.face(s);
      moveEntity(-1, 0, e);
    } else if (s.equals("s")) {
      e.face(s);
      moveEntity(0, 1, e);
    } else if (s.equals("d")) {
      e.face(s);
      moveEntity(1, 0, e);
    } else {
      ; // do nothing
    }
  }

  public void start() {
    board.takeGame(this);
    board.gameAi();

    gameTimer.schedule(new TimerTask() {
      @Override
      public void run() {
        tick(); // game's tick
        display.repaint(); // redraw game
      }
    }, 0, 1000);
  }

  public void tick() {
    //handleMove(scanner.next(), player);
    for (Ai element : aiArray) {
      element.tick();
    }
  }

  public String toString() {
    return board.toString();
  }
}