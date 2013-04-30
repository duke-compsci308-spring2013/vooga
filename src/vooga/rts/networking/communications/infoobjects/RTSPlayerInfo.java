package vooga.rts.networking.communications.infoobjects;

/**
 * Represents the additional player information needed for the RTS genre. Includes player id, player
 * name, team number, and faction name.
 * 
 * This class is a subclass of SmallPlayerInfo:
 * 
 * @see vooga.rts.networking.communications.infoobjects.SmallPlayerInfo
 * 
 *      This class, by extension, implements the PlayerInfo interface:
 * @see vooga.rts.networking.communications.infoobjects.PlayerInfo
 * 
 * @author David Winegar
 * 
 */
public class RTSPlayerInfo extends SmallPlayerInfo {

    private static final long serialVersionUID = 336729477542445193L;
    private String myFaction;
    private int myTeam;

    /**
     * Sets all the parameters, including the user name, team number, faction name, and id numner.
     * 
     * @param name user name
     * @param team team number
     * @param faction faction name
     * @param id id number
     */
    public RTSPlayerInfo (String name, int team, String faction, int id) {
        super(name, id);
        myFaction = faction;
        myTeam = team;
    }

    /**
     * Returns the faction of the player.
     * 
     * @return faction name
     */
    public String getFaction () {
        return myFaction;
    }

    /**
     * Returns the team of the player.
     * 
     * @return team number
     */
    public int getTeam () {
        return myTeam;
    }

    /**
     * Setter for the faction of the player.
     * 
     * @param faction name
     */
    public void setFaction (String faction) {
        myFaction = faction;
    }

    /**
     * Setter for the team of the player.
     * 
     * @param team number
     */
    public void setTeam (int team) {
        myTeam = team;
    }

}
