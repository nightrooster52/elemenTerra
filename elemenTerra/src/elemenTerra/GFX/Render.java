package elemenTerra.GFX;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComponent;

import elemenTerra.Board;
import elemenTerra.Game;
import elemenTerra.TileKeys;

public class Render extends JComponent implements KeyListener, TileKeys {
  private Board board;
  private Game game;
  private int height, width;
  private int tileSize;
  private int gutter;

  Color lightBlue = new Color(180, 180, 255);
  Color blue = new Color(0, 0, 255);
  Color darkBlue = new Color(0, 0, 120);

  Color lightRed = new Color(255, 180, 180);
  Color red = new Color(255, 0, 0);
  Color darkRed = new Color(120, 0, 0);

  Color lightGreen = new Color(180, 255, 180);
  Color green = new Color(0, 255, 0);
  Color darkGreen = new Color(0, 120, 0);

  public Render(Board board, int width, int height, int tileSize, int gutter) {
    this.board = board;
    game = board.getGame();
    this.width = width;
    this.height = height;
    this.tileSize = tileSize;
    this.gutter = gutter;
    setFocusable(true);
    requestFocus();
    addKeyListener(this);
  }

  public void paintComponent(Graphics g) {
    String type;
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        type = board.getTile(col, row).toString();
        if (type.equals(TileKeys.defaultTile)) {
          g.setColor(Color.GRAY);
        }
        if (type.equals(TileKeys.blockTile)) {
          g.setColor(Color.BLACK);
        }
        if (type.equals(TileKeys.playerTile)) {
          g.setColor(Color.WHITE);
        }
        if (type.equals(TileKeys.SeekerTile)) {
          g.setColor(Color.RED);
        }
        if (type.equals(TileKeys.RTile)) {
          g.setColor(Color.YELLOW);
        }
        if (type.equals(TileKeys.LTile)) {
          g.setColor(Color.BLUE);
        }
        //elements
        if (type.equals(TileKeys.fireGas)) {
          g.setColor(lightRed);
        }
        if (type.equals(TileKeys.fireLiquid)) {
          g.setColor(red);
        }
        if (type.equals(TileKeys.fireSolid)) {
          g.setColor(darkRed);
        }
        if (type.equals(TileKeys.waterGas)) {
          g.setColor(lightBlue);
        }
        if (type.equals(TileKeys.waterLiquid)) {
          g.setColor(blue);
        }
        if (type.equals(TileKeys.waterSolid)) {
          g.setColor(darkBlue);
        }
        if (type.equals(TileKeys.earthGas)) {
          g.setColor(lightGreen);
        }
        if (type.equals(TileKeys.earthLiquid)) {
          g.setColor(green);
        }
        if (type.equals(TileKeys.earthSolid)) {
          g.setColor(darkGreen);
        }
        g.fillRect(col * tileSize + gutter, row * tileSize + gutter, tileSize
            - gutter, tileSize - gutter);
      }
    }
  }

  public void keyTyped(KeyEvent e) {
    //System.out.println("keyTyped");   
  }

  public void keyPressed(KeyEvent e) {
    String input = "" + e.getKeyChar();
    System.out.println(input);
    game.handleMove(input, board.getPlayer());
  }

  public void keyReleased(KeyEvent e) {
    //System.out.println("keyReleased");    
  }
}
