package vooga.rts.networking.server;

import java.util.HashMap;
import java.util.Map;


public class GameContainer extends AbstractThreadContainer {

    private Map<Integer, Room> myRooms = new HashMap<Integer, Room>();
    private int myNextRoomNumber = 0;

    protected void removeRoom (Room room) {
        myRooms.remove(room.getID());
    }
    
    protected void addRoom (Room room) {
        myRooms.put(room.getID(), room);
    }

    @Override
    public void joinLobby (ConnectionThread thread, String lobbyName) {
        Room room;
        if (!myRooms.containsKey(lobbyName)) {
            room = new Lobby(myNextRoomNumber, this);
            addRoom(room);
            myNextRoomNumber++;
        }
        removeConnection(thread);
        room = myRooms.get(lobbyName);
        room.addConnection(thread);
    }

}
