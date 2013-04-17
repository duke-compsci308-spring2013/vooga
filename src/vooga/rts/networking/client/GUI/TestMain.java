package vooga.rts.networking.client.GUI;

import javax.swing.JFrame;
import vooga.rts.networking.communications.clientmessages.HostDescriptionMessage;

public class TestMain {

    public TestMain () {
    }

    public static void main(String[] args) {
        String[] imageFileNames = { "Scroll.png", "Scroll1.jpg",
                                            "Scroll2.jpg", "Scroll3.jpg","Scroll.png", "Scroll1.jpg",
                                            "Scroll2.jpg", "Scroll3.jpg"};
        ServerBrowser s = new ServerBrowser();
        for ( int i = 0 ; i < imageFileNames.length ; i++){
            HostDescriptionMessage m = new HostDescriptionMessage("user "+i,"map "+i,"server "+i,
                                                                  "../../resources/" +imageFileNames[i]);
            s.addConnection(m);
        }
    }
}
