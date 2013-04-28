package vooga.scroller.util.mvc.vcFramework;

import java.awt.Image;
import java.util.List;
import java.util.Map;

/**
 * Defines a general tools class that is necessary for workspace
 * customization
 * By default, Tools support mapping of instantaneous options and delayed options.
 * @param <D> is a domain descriptor
 * @author Dagbedji Fagnisse
 *
 */
public abstract class Tools<D extends IDomainDescriptor> {

    public abstract List<Map<Image, String>> getEditableDependents ();

    public abstract Map<String, Map<Image, String>> getEditableIndependents ();

    public abstract String getEditableDependentsTitle ();

    public abstract String getEditableIndependentsTitle ();
    
}
