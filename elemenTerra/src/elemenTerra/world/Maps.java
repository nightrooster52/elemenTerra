package elemenTerra.world;

import java.util.Random;

import elemenTerra.TileKeys;

public class Maps implements TileKeys {

  String blankMap = "020 020 " + "00000000000000000000"
      + "00000000000000000000" + "00000000000000000000"
      + "00000000000000000000" + "00000000000000000000"
      + "00000000000000000000" + "00000000000000000000"
      + "00000000000000000000" + "00000000000000000000"
      + "000000000X0000000000" + "00000000000000000000"
      + "00000000000000000000" + "00000000000000000000"
      + "00000000000000000000" + "00000000000000000000"
      + "00000000000000000000" + "00000000000000000000"
      + "00000000000000000000" + "00000000000000000000"
      + "00000000000000000000";
  String elementMap = "020 020 " + "00000000000000000000"
      + "00000000000000000000" + "00000000000000000000"
      + "00000000000000000000" + "00b00000000000000000"
      + "000fF000000000000000" + "00000000000000000000"
      + "00bwW000000000000000" + "00000000000000000000"
      + "000eE0000X0000000000" + "00000000000000000000"
      + "00000000000000000000" + "00000000000000000000"
      + "00000000000000000000" + "00000000000000000000"
      + "00000000000000000000" + "00000000000000000000"
      + "00000000000000000000" + "00000000000000000000"
      + "00000000000000000000";
  String lrMap = "020 020 " + "00000000000000000000" + "0000######0000000000"
      + "000#000000#000000000" + "00000000000000000000"
      + "0000L000000000000000" + "000#000000#000000000"
      + "0000######0000000000" + "00000000000000000000"
      + "0000######0000000000" + "000#000000#000000000"
      + "00000000000000000000" + "0000R000000000000000"
      + "000#000000#000000000" + "0000######0000000000"
      + "00000000000000000000" + "0000000X000000000000"
      + "00000000000000000000" + "00000000000000000000"
      + "00000000000000000000" + "00000000000000000000";

  String SeekerMap = "020 020 " + "++++++++++++++++++++"
      + "00000000000000000000" + "########0000########"
      + "00000000000000000000" + "0000############0000"
      + "00000000000000000000" + "00000000000000000000"
      + "00000000000000000000" + "00000000000000000000"
      + "00000000000000000000" + "00000000000000000000"
      + "00000000000000000000" + "#########00#########"
      + "00000000000000000000" + "0000############0000"
      + "0000#0000000000#0000" + "0000#0000000000#0000"
      + "0000############0000" + "00000000000000000000"
      + "000000000X0000000000";

  String randomMap = randomMap();

  public String randomMap() {
    String randomMap = "020 020 ";
    Random random = new Random();
    for (int i = 0; i < 400; i++) {
      char tileStr;
      int type = random.nextInt(100);

      switch (type) {
      case 0:
        tileStr = TileKeys.blockTile;
        break;
      case 1:
        tileStr = TileKeys.SeekerTile;
        break;
      case 2:
        tileStr = TileKeys.LTile;
        break;
      case 3:
        tileStr = TileKeys.RTile;
        break;
      default:
        tileStr = TileKeys.defaultTile;
      }

      randomMap += tileStr;
    }
    return randomMap;
  }

  public String randomElements() {
    String randomElements = "050 050 ";
    Random random = new Random();
    for (int i = 0; i < 2500; i++) {
      char tileStr;
      int type = random.nextInt(80);

      switch (type) {
      case 0:
        tileStr = TileKeys.fireGas;
        break;
      case 1:
        tileStr = TileKeys.fireLiquid;
        break;
      case 2:
        tileStr = TileKeys.fireSolid;
        break;
      case 3:
        tileStr = TileKeys.waterGas;
        break;
      case 4:
        tileStr = TileKeys.waterLiquid;
        break;
      case 5:
        tileStr = TileKeys.waterSolid;
        break;
      case 6:
        tileStr = TileKeys.earthGas;
        break;
      case 7:
        tileStr = TileKeys.earthLiquid;
        break;
      case 8:
        tileStr = TileKeys.earthSolid;
        break;
      default:
        tileStr = TileKeys.defaultTile;
      }

      randomElements += tileStr;
    }
    return randomElements;
  }

  public String randomGas() {
    String randomElements = "050 050 ";
    Random random = new Random();
    for (int i = 0; i < 2500; i++) {
      char tileStr = TileKeys.defaultTile;
      int type = random.nextInt(3);

      if (type == 0) {
        tileStr = TileKeys.fireGas;
      } else if (type == 1) {
        //tileStr = TileKeys.waterGas;
      }

      randomElements += tileStr;
    }
    return randomElements;
  }
}