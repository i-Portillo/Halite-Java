import java.util.ArrayList;
import java.util.List;

public class MyBot {
    public static void main(String[] args) throws java.io.IOException {

        final InitPackage iPackage = Networking.getInit();
        final int myID = iPackage.myID;
        final GameMap gameMap = iPackage.map;

        Networking.sendInit("MyJavaBot");

        while(true) {
            List<Move> moves = new ArrayList<Move>();

            Networking.updateFrame(gameMap);

            for (int y = 0; y < gameMap.height; y++) {
                for (int x = 0; x < gameMap.width; x++) {
                    final Location location = gameMap.getLocation(x, y);
                    final Site site = location.getSite();
                    if(site.owner == myID) {
                        //moves.add(new Move(location, Direction.randomDirection())); // Random move

                        Location[] neighbours = new Location[4];
                        neighbours[0] = gameMap.getLocation(x, y+1);
                        neighbours[1] = gameMap.getLocation(x+1, y);
                        neighbours[2] = gameMap.getLocation(x, y-1);
                        neighbours[3] = gameMap.getLocation(x-1, y);

                        for (Location neighbour : neighbours) {
                            Site nSite = neighbour.getSite();
                            if (nSite.owner != myID && nSite.strength < site.strength)
                                moves.add(new Move(location, ))
                        }

//                        System.out.println(neighbours);

                        //for (Direction dir : )

                        if(site.strength <= site.production * 5) {
                            moves.add(new Move(location, Direction.STILL));     // Stay still and gather strength
                        } else {
                            moves.add(new Move(location, Direction.northOrWestDirection())); // Move North or West
                        }
                    }
                }
            }
            Networking.sendFrame(moves);
        }
    }
}
