package vooga.rts.networking.communications.client;

import vooga.rts.networking.communications.Message;

public class HostDescriptionMessage extends Message {
    String myUsername;
    String myMapDescription;
    String myHostName;
    String myServer;

    public HostDescriptionMessage(String...args) {
        super();
        
    }
    

}
