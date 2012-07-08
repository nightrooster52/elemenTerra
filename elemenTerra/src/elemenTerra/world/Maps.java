package elemenTerra.world;

import java.util.Random;

import elemenTerra.TileKeys;

public class Maps implements TileKeys {

  public String blankMap = "020 020 "
    + "00000000000000000000"
    + "00000000000000000000"
    + "00000000000000000000"
    + "00000000000000000000"
    + "00000000000000000000"
    + "00000000000000000000"
    + "00000000000000000000"
    + "00000000000000000000"
    + "00000000000000000000"
    + "000000000X0000000000"
    + "00000000000000000000"
    + "00000000000000000000"
    + "00000000000000000000"
    + "00000000000000000000"
    + "00000000000000000000"
    + "00000000000000000000"
    + "00000000000000000000"
    + "00000000000000000000"
    + "00000000000000000000"
    + "00000000000000000000";



  public String elementMap = "020 020 " + "00000000000000000000"
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
  public String lrMap = "020 020 " + "00000000000000000000" + "0000######0000000000"
    + "000#000000#000000000" + "00000000000000000000"
    + "0000L000000000000000" + "000#000000#000000000"
    + "0000######0000000000" + "00000000000000000000"
    + "0000######0000000000" + "000#000000#000000000"
    + "00000000000000000000" + "0000R000000000000000"
    + "000#000000#000000000" + "0000######0000000000"
    + "00000000000000000000" + "0000000X000000000000"
    + "00000000000000000000" + "00000000000000000000"
    + "00000000000000000000" + "00000000000000000000";

  public String SeekerMap = "020 020 " + "++++++++++++++++++++"
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

  public String biasTestMap = "020 020 "
    + "00000000000000000000"
    + "0X000000000000000000"
    + "00000000000000000000"
    + "00000000000000000000"
    + "00000000000000000000"
    + "000000000r0000000000"
    + "00000000r0r000000000"
    + "0000000r000r00000000"
    + "000000r00000r0000000"
    + "00000r0000000r000000"
    + "000000r00000r0000000"
    + "0000000r000r00000000"
    + "00000000r0r000000000"
    + "000000000r0000000000"
    + "00000000000000000000"
    + "00000000000000000000"
    + "00000000000000000000"
    + "00000000000000000000"
    + "00000000000000000000"
    + "00000000000000000000";

  String randomMap = randomMap();

  public String randomMap() {
    String randomMap = "020 020 ";
    Random random = new Random();
    for (int i = 0; i < 400; i++) {
      char tileChar;
      int type = random.nextInt(100);

      switch (type) {
      case 0:
        tileChar = TileKeys.blockTile;
        break;
      case 1:
        tileChar = TileKeys.SeekerTile;
        break;
      case 2:
        tileChar = TileKeys.LTile;
        break;
      case 3:
        tileChar = TileKeys.RTile;
        break;
      default:
        tileChar = TileKeys.defaultTile;
      }

      randomMap += tileChar;
    }
    return randomMap;
  }

  public String randomElements() {
    String randomElements = "125 075 ";
    Random random = new Random();
    for (int i = 0; i < 9375; i++) {
      char tileChar;
      int type = random.nextInt(40);

      switch (type) {
      case 0:
        tileChar = TileKeys.fireGas;
        break;
      case 1:
        tileChar = TileKeys.fireLiquid;
        break;
      case 2:
        tileChar = TileKeys.fireSolid;
        break;
      case 3:
        tileChar = TileKeys.waterGas;
        break;
      case 4:
        tileChar = TileKeys.waterLiquid;
        break;
      case 5:
        tileChar = TileKeys.waterSolid;
        break;
      case 6:
        tileChar = TileKeys.earthGas;
        break;
      case 7:
        tileChar = TileKeys.earthLiquid;
        break;
      case 8:
        tileChar = TileKeys.earthSolid;
        break;
      default:
        tileChar = TileKeys.defaultTile;
      }

      randomElements += tileChar;
    }
    return randomElements;
  }

  public String randomGas() {
    String randomElements = "050 050 ";
    Random random = new Random();
    for (int i = 0; i < 2500; i++) {
      char tileChar = TileKeys.defaultTile;
      int type = random.nextInt(3);

      if (type == 0) {
        tileChar = TileKeys.fireGas;
      } else if (type == 1) {
        //tileChar = TileKeys.waterGas;
      }

      randomElements += tileChar;
    }
    return randomElements;
  }

  public String randomGasHuge() {
    String randomElements = "125 075 ";
    Random random = new Random();
    for (int i = 0; i < 9375; i++) {
      char tileChar = TileKeys.defaultTile;
      int type = random.nextInt(3);

      if (type == 0) {
        tileChar = TileKeys.fireGas;
      } else if (type == 1) {
        //tileChar = TileKeys.waterGas;
      }

      randomElements += tileChar;
    }
    return randomElements;
  }

  public String randomFireHuge() {
    String randomElements = "125 075 ";
    Random random = new Random();
    for (int i = 0; i < 9375; i++) {
      char tileChar = TileKeys.defaultTile;
      int type = random.nextInt(12);

      if (type == 0) {
        tileChar = TileKeys.fireGas;
      } else if (type == 1) {
        tileChar = TileKeys.fireLiquid;
      } else if (type == 2) {
        tileChar = TileKeys.fireSolid;
      }

      randomElements += tileChar;
    }
    return randomElements;
  }
}