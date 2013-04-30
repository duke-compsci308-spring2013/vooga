package vooga.rts.networking.communications.infoobjects;

/**
 * Class represents a player that can play a game (can be either AI or Human). This serves as a
 * handover between the networking game setup and then the in-game settings. It contains a name and
 * an ID but is extensible.
 * 
 * Implements the PlayerInfo interface:
 * 
 * @see vooga.rts.networking.communications.infoobjects.PlayerInfo
 * 
 * @author srwareham
 * @author Henrique Moraes
 * @author David Winegar
 * 
 */
public class SmallPlayerInfo implements PlayerInfo {

    private static final long serialVersionUID = -2457375513280272584L;
    private String myName;
    private int myID;

    /**
     * A player object whose purpose is to contain state for the game simulation to read in and
     * construct based off of appropriately.
     * 
     * @param name name of the player
     * @param id unique id number of player
     */
    public SmallPlayerInfo (String name, int id) {
        myName = name;
        myID = id;
    }

    @Override
    public String getName () {
        return myName;
    }

    @Override
    public int getId () {
        return myID;
    }

    @Override
    public boolean equals (Object obj) {
        if (!(obj instanceof SmallPlayerInfo)) {
            return false;
        }
        SmallPlayerInfo other = (SmallPlayerInfo) obj;
        if (other.getId() == myID) {
            return true;
        }

        return false;
    }

    @Override
    public int hashCode () {
        return myID;
    }

}
