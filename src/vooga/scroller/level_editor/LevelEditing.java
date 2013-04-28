package vooga.scroller.level_editor;

import vooga.scroller.util.mvc.vcFramework.IDomainDescriptor;
import vooga.scroller.util.mvc.vcFramework.Tools;

public class LevelEditing implements IDomainDescriptor {

    public static final String UPDATE_GRID_SIZE = "Update Grid Size";
    public static final String SIMULATION_ERROR_MESSAGE = "All levels need a start point and a portal";
    public static final String WEB_CONNECTION_PROBLEMS = "Problems connecting with the Web";
    public static final String HELP_URL = "http://en.wikipedia.org/wiki/Side-scrolling_video_game";
    public static final String GRID_RENDERING_ERROR = "Error encountered when trying to display the grid";
    public static final String EDITABLE_DEPENDENTS_TITLE = "Sprites";
    public static final String EDITABLE_INDEPENDENTS_TITLE = "Backgrounds & other";

    @Override
    public String getDomainName () {
        return VIEW_CONSTANTS.DOMAIN_NAME;
    }

    @Override
    public Tools<LevelEditing> getDomainTools () {
        // TODO Auto-generated method stub
        return null;
    }
    
    public static class VIEW_CONSTANTS {
        public static final String DOMAIN_NAME = "Level Editor";
    }

    
    
    

}
