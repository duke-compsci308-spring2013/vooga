package vooga.rts.map;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import vooga.rts.IGameLoop;
import vooga.rts.gamedesign.sprite.map.Terrain;


public class TerrainManager implements IGameLoop {

    private List<Terrain> myTerrain;

    public TerrainManager () {
        myTerrain = new ArrayList<Terrain>();
    }

    @Override
    public void update (double elapsedTime) {
        for (Terrain t : myTerrain) {
            t.update(elapsedTime);
        }
    }

    @Override
    public void paint (Graphics2D pen) {
        for (Terrain t : myTerrain) {
            t.paint(pen);
        }
    }

    public void addTerrain (Terrain ter) {
        myTerrain.add(ter);
    }
}
