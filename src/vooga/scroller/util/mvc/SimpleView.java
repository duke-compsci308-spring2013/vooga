package vooga.scroller.util.mvc;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import vooga.scroller.util.Renderable;

public class SimpleView extends JFrame implements IView<Gaming> {

    /**
     * 
     */
    private static final long serialVersionUID = -3140147255061541256L;

    public SimpleView (String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void process (Object command) {
        // TODO Auto-generated method stub

    }

    @Override
    public Dimension getSize () {
        // TODO Auto-generated method stub
        return null;
    }

   
    public void start () {
        pack();
        setVisible(true);
        
    }

    @Override
    public String getLiteral (String string) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void registerMenu (JMenu jMenu) {
        // TODO Auto-generated method stub
        
    }

    /**
     * Helper to show a message to the user
     * @param message - to be shown
     */
    @Override
    public void showMessageDialog (String message) {
        JOptionPane.showMessageDialog(this, message);
    }

}
