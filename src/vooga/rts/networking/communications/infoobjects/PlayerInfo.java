package vooga.rts.networking.communications.infoobjects;

import java.io.Serializable;


/**
 * This interface represents the player information to the server and client, as well as information
 * for the game itself. Intended to be sent over the network. Contains the name, ID, and methods to
 * compare based on the ID. This is extensible for games that need additional information.
 * 
 * The superclass of all PlayerInfos is the SmallPlayerInfo class:
 * 
 * @see vooga.rts.networking.communications.infoobjects.SmallPlayerInfo
 * 
 *      This class implements the Serializable interface for serialization to a stream:
 * @see java.io.Serializable
 * 
 * @author David Winegar
 * 
 */
public interface PlayerInfo extends Serializable {

    /**
     * Returns the name of the player.
     * 
     * @return name of player
     */
    String getName ();

    /**
     * Returns the ID of the player.
     * 
     * @return id of player
     */
    int getId ();

    /**
     * This checks to see if the passed in object is a PlayerInfo and if it has the same ID, and if
     * so, returns true.
     * 
     * @param obj object to compare with
     * @see java.lang.Object#equals(Object)
     */
    @Override
    boolean equals (Object obj);

    /**
     * This is a hashcode implementation for the PlayerInfo. It should return a hash based on the
     * player's ID.
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    int hashCode ();

}
