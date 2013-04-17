package vooga.rts.networking.client.lobby;
/**
 * Class represents a player that can play a game
 * (can be either AI or Human)
 * Note: hopefully this will be replaced by a Player class from the arcade, if not this
 * will at a minimum serve as a handover between arcade settings and game settigns
 * @author srwareham
 *
 */
public class Player {
    private String myName;
    public Player (String name) {
        myName = name;
    }
    
    public String getName() {
        return myName;
    }

}
