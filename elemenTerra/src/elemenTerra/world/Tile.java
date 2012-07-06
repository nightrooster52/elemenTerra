package elemenTerra.world;

import java.awt.Color;

import elemenTerra.entity.Entity;

public class Tile {

	private boolean occupied = false;
	private Entity occupant = null;
	private char identity = '0';

	public Tile() {
	}

	public Tile(char identity) {
		this.identity = identity;
	}

	public boolean isOccupied() {
		return occupied;
	}

	public void occupy(Entity e) {
		occupant = e;
		occupied = true;
	}

	public void vacate() {
		occupant = null;
		occupied = false;
	}

	public void setIdentity(char identity) {
		this.identity = identity;
	}

	public char getIdentity() {
		return identity;
	}

	public Entity getEntity() {
		return occupant;
	}

	@Override
	public String toString() {
		return occupied ? occupant.toString() : identity + "";
	}

	public void die() {
		if (occupied) {
			vacate();
			occupant.die();
		}
	}

	public Color getColor() {
		return occupied ? occupant.getColor() : Color.gray;
	}
}