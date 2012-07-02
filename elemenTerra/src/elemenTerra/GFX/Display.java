package elemenTerra.GFX;

import javax.swing.JFrame;

import elemenTerra.Board;

public class Display extends JFrame {
  private Board board;
  private int height, width;
  private int tileSize = 12;
  private int gutter = 1;

  //constructor
  public Display(Board board) {
    this.board = board;
    width = board.getWidth();
    height = board.getHeight();

    setBounds(100, 30, width * tileSize, height * tileSize + 22);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    getContentPane().add(new Render(board, width, height, tileSize, gutter));

    setVisible(true);

  }

}