package vooga.rts.networking.client.lobby;

import java.util.Comparator;

public class LobbyTeamComparator implements Comparator<Team> {

    @Override
    public int compare (Team o1, Team o2) {
        // TODO Auto-generated method stub 1 <2 -> -1
        if (o1.getSize() == o2.getSize()) {
            return o1.getNumber() -o2.getNumber(); //TODO: confirm this is not backwards sorting
        }
        return o1.getSize() - o2.getSize();
    }

}
