package elemenTerra.GFX;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import elemenTerra.Game;
import elemenTerra.TileKeys;
import elemenTerra.world.Board;
import elemenTerra.world.Tile;

public class BoardView extends JPanel implements KeyListener, TileKeys {
  protected int height;
  protected int width;
  protected int tileSize = 10;
  protected int gutter;

  protected Board board;
  protected Game game;

  public BoardView(Board board) {
    this.board = board;
    width = tileSize * board.getWidth();
    height = tileSize * board.getHeight();

    setFocusable(true);
    requestFocus();
    addKeyListener(this);
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(width, height);
  }

  @Override
  public void paintComponent(Graphics g) {
    for (int row = 0; row < board.getHeight(); row++) {
      for (int col = 0; col < board.getWidth(); col++) {
        Tile tile = board.getTile(col, row);

        g.setColor(tile.getColor());
        g.fillRect(col * tileSize + gutter, row * tileSize + gutter, tileSize
            - gutter, tileSize - gutter);
      }
    }
  }

  public void setGame(Game game) {
    this.game = game;
  }

  @Override
  public void keyTyped(KeyEvent e) {
    //System.out.println("keyTyped");
  }

  @Override
  public void keyPressed(KeyEvent e) {
    game.handleMove(e.getKeyChar(), board.getPlayer());
  }

  @Override
  public void keyReleased(KeyEvent e) {
    //System.out.println("keyReleased");
  }
}
