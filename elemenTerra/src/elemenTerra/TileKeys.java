package elemenTerra;

import java.awt.Color;

public interface TileKeys {

  Color lightBlue = new Color(180, 180, 255);
  Color blue = new Color(0, 0, 255);
  Color darkBlue = new Color(0, 0, 120);

  Color lightRed = new Color(255, 180, 180);
  Color red = new Color(255, 0, 0);
  Color darkRed = new Color(120, 0, 0);

  Color lightGreen = new Color(180, 255, 180);
  Color green = new Color(0, 255, 0);
  Color darkGreen = new Color(0, 120, 0);

  char defaultTile = '0';
  char blockTile = '#';
  char playerTile = 'X';
  char SeekerTile = '+';
  char LTile = 'L';
  char RTile = 'R';

  char fireGas = 'r';
  char fireLiquid = 'f';
  char fireSolid = 'F';

  char waterGas = 'b';
  char waterLiquid = 'w';
  char waterSolid = 'W';

  char earthGas = 'g';
  char earthLiquid = 'e';
  char earthSolid = 'E';

  //(+1)%3 for stronger element, (+2)%3 for weaker element
  char[][] interactionKey ={
		  {fireGas, fireLiquid, fireSolid},
		  {waterGas, waterLiquid, waterSolid},
		  {earthGas, earthLiquid, earthSolid}
	  };
  
  public char[] strongerStates(char identity){
	  for (int element = 0; element < 3; element++){
			for (int state = 0; state < 3; state++){
				if identity == interactionKey[element][state]{
					return interactionKey[(element+1)%3];
					}
				}		
	  		}
  		}
  public char[] strongerStates(char identity){
	  for (int element = 0; element < 3; element++){
		  for (int state = 0; state < 3; state++){
			  if identity == interactionKey[element][state]{
				  return interactionKey[(element+1)%3];
				  }
			  }			
		  }
  }
  public char[] analagousStates(char identity){
	  for (int element = 0; element < 3; element++){
			for (int state = 0; state < 3; state++){
				if identity == interactionKey[element][state]{
					return interactionKey[(element)];
					}	
				}	
			}	
  		}
  

  char[] KeyArray = { TileKeys.defaultTile, TileKeys.blockTile,
      TileKeys.playerTile, TileKeys.LTile, TileKeys.RTile, TileKeys.fireGas,
      TileKeys.fireLiquid, TileKeys.fireSolid, TileKeys.waterGas,
      TileKeys.waterLiquid, TileKeys.waterSolid, TileKeys.earthGas,
      TileKeys.earthLiquid, TileKeys.earthSolid };
}