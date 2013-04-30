package vooga.rts.networking.server;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import util.logger.HandlerTxt;
import vooga.rts.networking.NetworkBundle;
import vooga.rts.networking.communications.infoobjects.SmallLobbyInfo;
import vooga.rts.networking.communications.servermessages.LobbyListMessage;


/**
 * This is a class that contains all the information about the game on the server. In this case a
 * game refers to a generic game created through VOOGA such as "Age of Empires". It contains methods
 * for clients to interact with Rooms.
 * 
 * @author David Winegar
 * 
 */
public class GameContainer extends AbstractThreadContainer {

    private Map<Integer, Room> myRooms = new HashMap<Integer, Room>();
    private Map<Integer, SmallLobbyInfo> myLobbyInfos = new HashMap<Integer, SmallLobbyInfo>();
    private int myRoomNumber = 0;
    private String myGameName;

    /**
     * Constructor that stores the game name and starts the logger.
     * 
     * @param gameName
     */
    public GameContainer (String gameName) {
        myGameName = gameName;
        getLogger().addHandler(new HandlerTxt(myGameName).getHandler());
    }

    /**
     * Removes the room from the game container.
     */
    protected void removeRoom (Room room) {
        myRooms.remove(room.getID());
        myLobbyInfos.remove(room.getID());
    }

    /**
     * Adds the room to the game container.
     */
    protected void addRoom (Room room) {
        myRooms.put(room.getID(), room);
    }

    /**
     * Returns the next room number.
     */
    protected int getRoomNumber () {
        return myRoomNumber;
    }

    /**
     * Increments the room number.
     */
    protected void incrementRoomNumber () {
        myRoomNumber++;
    }

    /**
     * Increments lobby info size with the given id
     */
    protected void incrementLobbyInfoSize (int id) {
        myLobbyInfos.get(id).addPlayer();
    }

    /**
     * Decrements lobby info size with the given id
     */
    protected void decrementLobbyInfoSize (int id) {
        myLobbyInfos.get(id).removePlayer();
    }

    /**
     * Joins the lobby if the lobby exists.
     * 
     * @param thread that is joining
     * @param lobbyNumber number of lobby
     */
    @Override
    public void joinLobby (ConnectionThread thread, int lobbyNumber) {
        if (myRooms.containsKey(lobbyNumber) &&
            myRooms.get(lobbyNumber).getNumberOfConnections() < myRooms.get(lobbyNumber)
                    .getMaxConnections()) {
            removeConnection(thread);
            myRooms.get(lobbyNumber).addConnection(thread);
            getLogger().log(Level.INFO, NetworkBundle.getString("LobbyJoined"));
        }
    }

    @Override
    public void startLobby (ConnectionThread thread, SmallLobbyInfo lobbyInfo) {
        SmallLobbyInfo newLobby = new SmallLobbyInfo(lobbyInfo, getRoomNumber());
        Room lobby = new Lobby(getRoomNumber(), this, newLobby, getLogger());
        addLobby(thread, newLobby, lobby);
    }

    /**
     * Adds a room to the GameContainer. Separated out so that subclasses can instantiates different
     * Rooms.
     */
    protected void addLobby (ConnectionThread thread, SmallLobbyInfo newLobby, Room lobby) {
        myLobbyInfos.put(getRoomNumber(), newLobby);
        incrementRoomNumber();
        lobby.addConnection(thread);
        addRoom(lobby);
        getLogger().log(Level.INFO, NetworkBundle.getString("LobbyStarted"));
    }

    @Override
    public void requestLobbies (ConnectionThread thread) {
        SmallLobbyInfo[] infoArray = myLobbyInfos.values().toArray(new SmallLobbyInfo[myLobbyInfos.size()]);
        thread.sendMessage(new LobbyListMessage(infoArray));
    }

}
