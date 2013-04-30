package vooga.scroller.level_editor.view;

import vooga.scroller.util.mvc.vcFramework.IDomainDescriptor;
import vooga.scroller.util.mvc.vcFramework.Tools;

public class LevelEditing implements IDomainDescriptor {

    @Override
    public String getDomainName () {
        return "Level Editor";
    }

    @Override
    public Tools getDomainTools () {
        // TODO Auto-generated method stub
        return null;
    }
    
    public static class VIEW_CONSTANTS {
        static final double DEFAULT_GRIDVIEW_HEIGHT_RATIO = .95;
        static final double DEFAULT_GRIDVIEW_WIDTH_RATIO = .7;
        static final double DEFAULT_TOOLSVIEW_HEIGHT_RATIO = .9;
        static final double DEFAULT_TOOLSVIEW_WIDTH_RATIO = .25;
    }

}
