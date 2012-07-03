package elemenTerra.GFX;

import java.awt.Color;
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
	protected Board board;
	protected Game game;
	protected int height;
	protected int width;
	protected int tileSize = 10;
	protected int gutter;

	Color lightBlue = new Color(180, 180, 255);
	Color blue = new Color(0, 0, 255);
	Color darkBlue = new Color(0, 0, 120);

	Color lightRed = new Color(255, 180, 180);
	Color red = new Color(255, 0, 0);
	Color darkRed = new Color(120, 0, 0);

	Color lightGreen = new Color(180, 255, 180);
	Color green = new Color(0, 255, 0);
	Color darkGreen = new Color(0, 120, 0);

	public BoardView(Board board) {
		this.board = board;
		width = tileSize * board.getWidth();
		height = tileSize * board.getHeight();

		game = board.getGame();
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
		char identity;
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
		char input = e.getKeyChar();
		game.handleMove(input, board.getPlayer());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//System.out.println("keyReleased");
	}
}
