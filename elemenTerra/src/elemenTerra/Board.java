package elemenTerra;
import java.util.Arrays;

import Entity.Entity;
import Entity.GasBrain;
import Entity.LRBrain;
import Entity.LiquidBrain;
import Entity.Player;
import Entity.SolidBrain;

public class Board implements TileKeys{

    private Tile[][] board;
    private int height;
    private int width;
    private Player player;
    private Ai[] aiArray = new Ai[0];
    private Game game;
    private int cursor;
    private Boolean playerSpawn = false;
    

    public String testString = "ACCESS TO BOARD";
    public String map;

    public Board(String map){
        this.map = map;
        parse(this.map);
        //System.out.println(g.testString);
    }


    public void parse(String map){
        //slices out the w and h
        this.width = Integer.parseInt(map.substring(0,3));
        this.height = Integer.parseInt(map.substring(4,7));

        //new tile array
        board = new Tile[height][width];

        cursor = 8; //the starting index of the map data

        for (int row = 0; row<height; row++){
            for (int col = 0; col<width; col++){
                String type = (map.substring(cursor, cursor+1));

                if (type.equals(blockTile)){
                    //System.out.println("Block Generated");
                    board[row][col] = new Tile(defaultTile);
                    Entity entity = new Entity(col, row, blockTile);
                    board[row][col].occupy(entity);
                    cursor++;
                } else if (type.equals(playerTile)){
                    board[row][col] = new Tile(defaultTile);
                    this.player = new Player(col, row, playerTile);
                    board[row][col].occupy(player);
		    playerSpawn = true;
                    cursor++;
                } else if (type.equals(defaultTile)){
                    board[row][col] = new Tile(type);
                    cursor++;
                } else {
		    spawnAi(row, col, type);
		}
	    }
	}
	if (playerSpawn == false){
	    board[10][10].vacate();
	    this.player = new Player(10, 10, playerTile);
	    board[10][10].occupy(player);
	    playerSpawn = true;
	}
        System.out.println("EntityGen complete");
	brainAi();
	
    }
    
    public void spawnAi(int  row,int col, String type){
	board[row][col] = new Tile(defaultTile);
	aiArray = Arrays.copyOf(aiArray, aiArray.length+1);
	Ai ai = new Ai(col, row, this);
	aiArray[aiArray.length-1] = ai;
	board[row][col].occupy(ai);
	ai.setString(type);
	this.cursor++;
    }
    public void brainAi(){
	for (int i = 0; i < aiArray.length; i++){
	    Ai ai = aiArray[i];
	    String aiString = ai.toString();

            if (aiString.equals(SeekerTile)){
		ai.setTarget(player);
	    }
	    if (aiString.equals(LTile)){
		ai.setBrain(new LRBrain(ai, this, "left"));
		//left brain
	    }
	    if (aiString.equals(RTile)){
		ai.setBrain(new LRBrain(ai, this, "right"));
		//Right brain
	    }
	    if (aiString.equals(fireGas) || aiString.equals(earthGas) || aiString.equals(waterGas)){
		//System.out.println(ai);
		ai.setBrain(new GasBrain(ai, this));
	    }
	    if (aiString.equals(fireLiquid) || aiString.equals(earthLiquid) || aiString.equals(waterLiquid)){
		//System.out.println(ai);
		ai.setBrain(new LiquidBrain(ai, this));
	    }
	    if (aiString.equals(fireSolid) || aiString.equals(earthSolid) || aiString.equals(waterSolid)){
		//System.out.println(ai);
		ai.setBrain(new SolidBrain(ai, this));
	    }

	}
    }


    public boolean checkTile(int x, int y){
        //System.out.println("called checkTile")
        if (inBounds(x, y)){
            return (!board[y][x].isOccupied());
        }
        return false;
    }
    public boolean inBounds(int x, int y){
	return (x<width && x>-1 && y<height && y>-1);
    }
    public void bump(int x, int y, Entity e){
	if (inBounds(x, y)){
	    board[y][x].getEntity().bump(e);
	}

    }
    public void gameAi(){
	for (int i = 0; i<aiArray.length ;i++){
	    //System.out.println(aiArray[i]);
	    aiArray[i].getBrain().takeGame();
	}
    }
    public void takeGame(Game g){
        this.game = g;
    }

    public Game getGame(){
        //System.out.println("getGame() called");
        return this.game;
    }

    public Tile getTile(int x, int y){
        return board[y][x];
    }

    public int getHeight(){
        return height;
    }

    public int getWidth(){
        return width;
    }

    public Player getPlayer(){
        return player;
    }
    public Ai[] getAiArray(){
        return aiArray;
    }

    public String toString(){
        String t = "";
        for (int row = 0; row < height; row++){
            for (int col = 0; col < width; col++){
                t += board[row][col].toString() + " ";
            }

            t += "\n";
        }
        return t;
    }

}