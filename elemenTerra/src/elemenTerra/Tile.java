package elemenTerra;

import elemenTerra.entity.Entity;

public class Tile{

    private boolean occupied = false;
    private Entity occupant = null;
    private String type = "0";
    public Tile(){
    }

    
    public Tile(String type){
	this.type = type;
    }


    public boolean isOccupied(){
	return occupied;
    } 

    public void occupy(Entity e){
	occupant = e;
	occupied = true;
    }

    public void vacate(){
	occupant = null;
	occupied = false;
    }

    public void mutate(String type){
	this.type = type;
    }

    public String getType(){
	return type;
    }
    
    public Entity getEntity(){
	return occupant;
    }

    public String toString(){
	String s = type;
	if (occupied){
	    s = occupant.toString();
	}
	return s;
    }
    public void die(){
	if (occupied){
	    vacate();
	    occupant.die();
	    
	}
    }
}