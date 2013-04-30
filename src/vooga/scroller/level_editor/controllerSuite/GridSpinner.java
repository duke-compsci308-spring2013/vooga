package vooga.scroller.level_editor.controllerSuite;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import vooga.scroller.util.mvc.vcFramework.ViewConstants;

public class GridSpinner extends JPanel implements ChangeListener {

        /**
     * 
     */
    private static final long serialVersionUID = -4816807088612765588L;
        private int gridHeight;
        private int gridWidth;
        private static int ourMin = LEController.MIN_SPRITE_GRID_SIZE;
        private static int ourMax = LEController.MAX_SPRITE_GRID_SIZE;
        private JSpinner hspinner;
        private JSpinner wspinner;
        int step = 5;
        
        public GridSpinner() {
            this(LEController.DEFAULT_SPRITE_GRID_SIZE,
                 LEController.DEFAULT_SPRITE_GRID_SIZE);
        }
        public GridSpinner(int width, int height) {
            this.setBorder(ViewConstants.DEFAULT_BORDER);
            this.setLayout(new GridLayout(0, 2));
            gridHeight = height;
            gridWidth = width;
            SpinnerModel hmodel =
                    new SpinnerNumberModel(gridHeight, 
                                           ourMin, ourMax,
                                           step);
            SpinnerModel wmodel =
                    new SpinnerNumberModel(gridWidth,
                                           ourMin, ourMax,
                                           step);
            hspinner = new JSpinner(hmodel);
            JLabel l = new JLabel("Height");
            wspinner= new JSpinner(wmodel);
            JLabel l2 = new JLabel("Width");
            this.add(l);
            l.setLabelFor(hspinner);
            this.add(hspinner);
            this.add(l2);
            l2.setLabelFor(wspinner);
            this.add(wspinner);
            hspinner.addChangeListener(this);
            wspinner.addChangeListener(this);
        }
        @Override
        public void stateChanged (ChangeEvent e) {
            gridHeight = (Integer) hspinner.getModel().getValue();
            gridWidth = (Integer) wspinner.getModel().getValue();
        }
        public int getGridWidth () {
            return gridWidth;
        }
        public int getGridHeight () {
            return gridHeight;
        }
        
    }