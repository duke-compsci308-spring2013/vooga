package vooga.rts.leveleditor.components;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import vooga.rts.gamedesign.sprite.map.Tile;
import vooga.rts.map.TileMap;
import vooga.rts.util.Location;
import vooga.rts.util.Location3D;
import vooga.rts.util.Pixmap;

public class EditableTileMap extends TileMap {

    public EditableTileMap (Dimension tileSize, int width, int height) {
        super(tileSize, width, height);
        initialize();
    }

    public void initialize() {
        int x = this.getMyHeight();
        int y = this.getMyWidth();
        for (int i = 0 ; i < x ; i++) {
            for(int j = 0 ; j < y ; j++) {
                setTile(i,j,new EditableTile(i,j,getMyTileSize()));
            }
        }
    }    
    
    public void addTile(int i, int j, int id, String name , String imageName, Pixmap image) {
        setTile(i,j,new EditableTile(image,i,j,getMyTileSize(),id,name,imageName,false));
    }
    

    public void addTile(Location3D loc, int id, String name, String imageName, Pixmap image) {
        int i = (int)(loc.getX()/this.getMyTileSize().getWidth() * 2);
        int j = (int)(loc.getY()/this.getMyTileSize().getHeight() * 2);
        addTile(i,j,id,name,imageName,image);
       
    
    }
    
    public void removeTile(int i, int j) throws IOException {
        this.setTile(i, j, new EditableTile(i,j,getMyTileSize()));
    }
    
    public void removeTile(Location3D center) throws IOException {
        int i = (int)(center.getY()/getMyTileSize().getHeight());
        int j = (int)(center.getX()/getMyTileSize().getWidth());
        removeTile(i,j);
    }
    
    public void removeAllTiles() {
        initialize();
    }
    
    @Override
    public EditableTile getTile(int i , int j) {
        return (EditableTile) super.getTile(i, j);    
    }
    
    public int getXCount (Location loc) {
        return (int)(loc.getY()/getMyTileSize().getHeight());
    }
    
    public int getYCount (Location loc) {
        return (int)(loc.getX()/getMyTileSize().getWidth());
    }
    

    
}
