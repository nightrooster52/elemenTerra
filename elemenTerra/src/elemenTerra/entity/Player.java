package elemenTerra.entity;
public class Player extends Entity{


	public Player(int x, int y){
		super(x, y);
		identity = 'X';
	}

	public Player(int x, int y, char identity){
		super(x, y, identity);
	}
}