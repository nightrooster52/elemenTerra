package elemenTerra.entity;

import java.awt.Color;

public class Entity {

	protected int x, y;
	protected char identity = '#';
	protected char facing = 'w';
	protected String clockwise = "wdsa";
	protected String counterclockwise = "wasd";

	public Entity(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Entity(int x, int y, char identity) {
		this(x, y);
		this.identity = identity;
	}



	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public char getFacing() {
		return facing;
	}

	public void die() {
		;
	}

	public void move(int dx, int dy) {
		x += dx;
		y += dy;
	}

	public void bump(Entity e) {
		;
	}

	public void setIdentity(char key) {
		identity = key;
	}
	public char getIdentity(){
		return identity;
	}

	@Override
	public String toString() {
		return ""+identity;
	}

	public void face(char direction) {
		//wasd
		facing = direction;
	}

	public void turn(String lr) {
		//left or right
		String direction = clockwise; //default turns right
		if (lr.equals("left")) {
			direction = counterclockwise;
		}
		if (lr.equals("right")) {
			direction = clockwise;
		}
		for (int cursor = 0; cursor < 4; cursor++) {
			if (direction.charAt(cursor) == facing) {
				face(direction.charAt((cursor + 1) % 4)); //
				break;
			}
		}
	}

	public Color getColor() {
		return Color.GRAY;
	}
}