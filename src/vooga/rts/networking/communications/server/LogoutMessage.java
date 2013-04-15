package vooga.rts.networking.communications.server;

import vooga.rts.networking.server.ConnectionThread;
import vooga.rts.networking.server.IThreadContainer;

public class LogoutMessage extends ServerSystemMessage {

    private static final long serialVersionUID = 3025289959143418637L;

    @Override
    public void execute (ConnectionThread thread, IThreadContainer server) {
        server.removeConnection(thread);
        thread.close();
    }

}