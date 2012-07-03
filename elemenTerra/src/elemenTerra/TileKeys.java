package elemenTerra;

public interface TileKeys {

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

	char[] gasses = {fireGas, waterGas, earthGas};
	char[] liquids = {fireLiquid, waterLiquid, earthLiquid};
	char[] solids = {fireSolid, waterSolid, earthSolid};

	char[] fires = {fireGas, fireLiquid, fireSolid};
	char[] waters = {waterGas, waterLiquid, waterSolid};
	char[] earths = {earthGas, earthLiquid, earthSolid};

	char[] KeyArray = {defaultTile, blockTile, playerTile, LTile, RTile, fireGas, fireLiquid, fireSolid, waterGas, waterLiquid,  waterSolid, earthGas, earthLiquid, earthSolid};
}