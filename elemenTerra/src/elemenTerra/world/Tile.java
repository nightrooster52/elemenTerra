package elemenTerra.world;

import java.awt.Color;
import java.awt.Graphics;

import elemenTerra.entity.Entity;

public class Tile {

  private boolean occupied = false;
  private Entity occupant = null;
  private char identity = '0';
  private int x;
  private int y;
  private int size = 9;
  private int gutter = 1;

  public Tile(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public Tile(int x, int y, char identity) {
    this.x = x;
    this.y = y;
    this.identity = identity;
  }
  public int getX(){
    return x;
  }
  public int getY(){
    return y;
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
    return occupied ? occupant.getIdentity() : identity;
  }

  public Entity getEntity() {
    return occupant;
  }

  @Override

    public String toString() {
    return occupied ? occupant.getIdentity() + "" : identity + "";
  }
  public void draw(Graphics g){
    g.setColor(getColor());
    if (occupied){
      occupant.draw(g);
    }else {
      g.fillRect(x * 10 , y * 10, size, size);
    }


  }

  public void die() {
    if (occupied) {
      vacate();
    }
  }

  public Color getColor() {
    return occupied ? occupant.getColor() : Color.gray;
  }
}