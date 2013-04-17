package vooga.rts.networking.client;

import vooga.rts.networking.communications.Message;
import vooga.rts.networking.communications.clientmessages.InitialConnectionMessage;

public class ServerBrowserModel implements IMessageReceiver {

    private IClient myClient = new Client(this); 
    
    public ServerBrowserModel () {
        // TODO get info from user
        Message initialConnection = new InitialConnectionMessage ("game", "user");
        myClient.sendData(initialConnection);
    }

    @Override
    public void getMessage (Message message) {
        
    }
    
    
    
    
}
