package elemenTerra.GFX;

import javax.swing.JFrame;

import elemenTerra.Board;

public class GUI extends JFrame {

  protected BoardView boardView;

  //constructor
  public GUI(Board board) {
    boardView = new BoardView(board);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setContentPane(boardView);
    setResizable(false);
    pack();
    setVisible(true);
  }

}