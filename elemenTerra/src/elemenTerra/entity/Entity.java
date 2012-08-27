package elemenTerra.entity;

import elemenTerra.TileKeys;
import java.awt.Graphics;
import java.awt.Color;
import elemenTerra.world.Board;
import elemenTerra.entity.brain.*;
import elemenTerra.entity.decisions.*;

public class Entity {

  protected Brain brain;
  protected Board board;
  
  protected int size = 9;
  protected int x, y;
  protected char identity = '#';
  protected char originalIdentity = '#';
  protected Color color = Color.WHITE;
  protected Color originalColor = Color.WHITE;
  protected String clockwise = "wdsa";
  protected String counterclockwise = "wasd";
  protected char facing = randomFace();
  protected boolean alive = true;

  protected Decisions decisions;
  public char[] strongerStates;
  public char[] weakerStates;
  public char[] analagousStates;


  public Entity(int x, int y, Board b) {
    this.x = x;
    this.y = y;
    this.board = b;
    this.brain = new Brain(this, b);
    this.decisions = new Decisions(this);
  }

  public Entity(int x, int y, Board b, char identity) {
    this(x, y, b);
    this.identity = identity;
    this.originalIdentity = identity;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }
  public void setX(int x) {
    this.x = x;
  }
  public void setY(int y) {
    this.y = y;
  }

  public Decisions getDecisions(){
    return decisions;
  }

  public char getFacing() {
    return facing;
  }

  public char randomFace() {
    int index = ((int) (Math.random()*4));
    return clockwise.charAt(index);
  }

  public void die() {
    alive = false;
    board.getTile(x, y).vacate();
  }



  public void move(int dx, int dy) {
    x += dx;
    y += dy;
  }

  public void recieveBump(Entity e){
    ;
  }
  public void giveBump(Entity e){
    ;
  }

  public void bump(Entity e) {  //from
    //I recievebump from e
    recieveBump(e);

    //e gives bump to me
    e.giveBump(this);
  }

  public Brain getBrain() {
    return brain;
  }

  public void setBrain(Brain brain) {
    this.brain = brain;
  }

  public void setDecisions(Decisions decisions){
    this.decisions = decisions;
  }

  public void setIdentity(char key) {
    identity = key;
  }

  public char getIdentity() {
    return identity;
  }

  public char getOriginalIdentity() {
    return originalIdentity;
  }
  public void handleInput(char input){
    ;
  }

  protected char[] analagousStates(char identity){
    for (int element = 0; element < 3; element++){
      for (int state = 0; state < 3; state++){
        if (identity == TileKeys.interactionKey[element][state]){
          return TileKeys.interactionKey[element];
        }
      }
    }
    return TileKeys.junkCharArray;
  }

  protected char[] strongerStates(char identity){
    for (int element = 0; element < 3; element++){
      for (int state = 0; state < 3; state++){
        if (identity == TileKeys.interactionKey[element][state]){
          return TileKeys.interactionKey[(element+1)%3];
        }
      }
    }
    return TileKeys.junkCharArray;
  }

  protected char[] weakerStates(char identity){
    for (int element = 0; element < 3; element++){
      for (int state = 0; state < 3; state++){
        if (identity == TileKeys.interactionKey[element][state]){
          return TileKeys.interactionKey[(element+2)%3];
        }
      }
    }
    return TileKeys.junkCharArray;
  }

  protected void setInteractionKeys(){
    analagousStates = analagousStates(identity);
    weakerStates = weakerStates(identity);
    strongerStates = strongerStates(identity);
  }




  @Override
    public String toString() {
    return "" + identity;
  }

  public void face(char direction) {
    //wasd
    facing = direction;
  }

  public void tick() {
    ;
  }
  public void draw(Graphics g){
    g.setColor(color);
    g.fillRect(x * 10 , y * 10, size, size);
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
    return color;
  }

  public Color getOriginalColor() {
    return originalColor;
  }

  public void setColor(Color color) {
    this.color = color;
  }

}