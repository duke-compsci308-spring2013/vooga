package vooga.rts.networking.communications.clientmessages;

import vooga.rts.networking.communications.Message;

public class HostDescriptionMessage extends Message {
    String myUsername;
    String myMapDescription;
    String myServer;
    String myImagePath;

    public HostDescriptionMessage(String...args) {
        super();
        myUsername = args[0];
        myMapDescription = args[1];
        myServer = args[2];
        myImagePath = args[3];   
    }
    
    public String getUsername(){
        return myUsername;
    }
    
    public String getMapDescription(){
        return myMapDescription;
    }
    
    public String getServerName(){
        return myServer;
    }
    
    public String getImagePath(){
        return myImagePath;
    }
    

}
