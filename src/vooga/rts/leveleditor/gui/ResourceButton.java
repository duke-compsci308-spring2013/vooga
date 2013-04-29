package vooga.rts.leveleditor.gui;

import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import util.input.Input;
import util.input.InputClassTarget;
import util.input.InputMethodTarget;
import util.input.PositionObject;
import vooga.rts.leveleditor.components.EditableResource;


/**
 * This class represents the resource button on Resource Panel
 * User can click and add the resource onto the map
 * 
 * @author Ziqiang Huang
 * 
 */

@SuppressWarnings("serial")
@InputClassTarget
public class ResourceButton extends JToggleButton {

    public static final String INPUT_DIR = "vooga.rts.resources.properties.Input";

    private EditableResource myResource;
    private ResourcePanel myOwner;
    private Input myInput;
    private boolean isInitialized;

    /**
     * Constructor for this class
     * 
     * @param r : the Resource which the button represents
     * @param image 
     * @param owner : the ResourcePanel which holds this button;
     */
    public ResourceButton (EditableResource editableResource, BufferedImage image, ResourcePanel owner) {
        myResource = editableResource;
        editableResource.getImage();
        Image image2 = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        myOwner = owner;
        myInput = new Input(INPUT_DIR, this);
        myInput.addListenerTo(this);
        isInitialized = false;

        setToolTipText(editableResource.getType());
        setIcon(new ImageIcon(image2));
        setMargin(new Insets(2, 2, 2, 2));
    }
    
    /**
     * get the resource from the button when being clicked
     * @param p
     */
    @InputMethodTarget(name = "onLeftMouseDown")
    public void getResource (PositionObject p) {
        if(!isInitialized) {
            showCustmizationDailog();
        }
        myOwner.setCurrentSelectResource(myResource);
        myOwner.getCanvas().setMode(MapPanel.RESOURCEMODE);
    }

    /**
     * show the customizationDaliog of this resource 
     * users should enter in the type and the amount of the resource
     */
    private void showCustmizationDailog() {
        JTextField resourceType = new JTextField();
        JTextField resourceAmount = new JTextField();
        Object[] message = {"Type", resourceType, "Amount", resourceAmount};
        int option = JOptionPane.showConfirmDialog(null, message,"Set resource",JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                String type = resourceType.getText();
                myResource.setType(type);
                int amount = Integer.parseInt(resourceAmount.getText());
                myResource.setAmount(amount);
                isInitialized = true;
            }
            catch (Exception e1) {
               
            }
        }
    }
    
    

}
