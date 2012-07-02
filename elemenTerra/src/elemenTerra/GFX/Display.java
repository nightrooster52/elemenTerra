package elemenTerra.GFX;
import javax.swing.*;

import elemenTerra.Board;
import elemenTerra.Game;
import elemenTerra.TileKeys;

import java.awt.*;
import java.awt.event.*;



public class Display extends JFrame{
    public static class Render extends JComponent implements KeyListener, TileKeys{
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
	    

	public Render(Board board, int width, int height, int tileSize, int gutter){
	    this.board = board;
	    this.game = board.getGame();
	    this.width = width;
	    this.height = height;
	    this.tileSize = tileSize;
	    this.gutter = gutter;
	    setFocusable(true);
	    requestFocus();
	    addKeyListener(this);
	}

	public void paintComponent(Graphics g){
	    String type;
	    for (int row = 0; row < height; row++){
		for (int col = 0; col < width; col++){
		    type = board.getTile(col, row).toString();
		    if (type.equals(defaultTile)){
			g.setColor(Color.GRAY);
		    }
		    if (type.equals(blockTile)){
			g.setColor(Color.BLACK);
		    }
		    if (type.equals(playerTile)){
			g.setColor(Color.WHITE);
		    }
		    if (type.equals(SeekerTile)){
			g.setColor(Color.RED);
		    }
		    if (type.equals(RTile)){
			g.setColor(Color.YELLOW);    
		    }
		    if (type.equals(LTile)){
			g.setColor(Color.BLUE);
		    }
		    //elements
		    if (type.equals(fireGas)){
			g.setColor(lightRed);
		    }
		    if (type.equals(fireLiquid)){
			g.setColor(red);
		    }
		    if (type.equals(fireSolid)){
			g.setColor(darkRed);    
		    }
		    if (type.equals(waterGas)){
			g.setColor(lightBlue);
		    }
		    if (type.equals(waterLiquid)){
			g.setColor(blue);
		    }
		    if (type.equals(waterSolid)){
			g.setColor(darkBlue);    
		    }
		    if (type.equals(earthGas)){
			g.setColor(lightGreen);
		    }
		    if (type.equals(earthLiquid)){
			g.setColor(green);    
		    }
		    if (type.equals(earthSolid)){
			g.setColor(darkGreen);
		    }
		    g.fillRect(col*tileSize+gutter, row*tileSize+gutter, tileSize-gutter, tileSize-gutter);
		}
	    }
	}
	public void keyTyped(KeyEvent e) {
	    //System.out.println("keyTyped");	  
	}
	public void keyPressed(KeyEvent e) {
	    String input = ""+e.getKeyChar();
	    System.out.println(input);
	    game.handleMove(input, board.getPlayer());
	}
	public void keyReleased(KeyEvent e) {
	    //System.out.println("keyReleased");	  
	}
    }

    private Board board;
    private int height, width;
    private int tileSize = 12;
    private int gutter = 1;
    


	//constructor
    public Display(Board board){
	this.board = board;
	this.width = board.getWidth();
	this.height = board.getHeight();

	setBounds(100, 30, width*tileSize, (height*tileSize)+22);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	getContentPane().add(new Render(board, width, height, tileSize, gutter));


	setVisible(true);
	
    }
	
}