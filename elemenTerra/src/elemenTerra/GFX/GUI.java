package elemenTerra.GFX;

import javax.swing.JFrame;

import elemenTerra.world.Board;

public class GUI extends JFrame {

  protected BoardView boardView;

  //constructor
  public GUI(Board board) {
    boardView = new BoardView(board);
    setContentPane(boardView);
    setResizable(false);
    pack();
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }

}