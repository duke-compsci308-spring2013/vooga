package vooga.scroller.viewUtil;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import vooga.scroller.util.Pixmap;

/**
 * This class handles both the logical and visual grouping for a group of Radio Buttons.
 * It notifies the listener provided at instantiation.
 * @author Dagbedji Fagnisse
 *
 */
public class RadioGroup extends JPanel {

    
    private Collection<JToggleButton> myButtons;
    private ButtonGroup myGrouper;
    
    private ActionListener myListener;
    
    private String myName = "group";
    
    /**
     * For internal logical handling
     */
    private Map<String, String> myOptions;
    
    private RadioGroup(ActionListener listener) {
        myButtons = new ArrayList<JToggleButton>();
        myGrouper = new ButtonGroup();
        myListener = listener;
        myOptions = new TreeMap<String, String>();
        applyLayout();
    }
    
    /**
     * Create a RadioGroup with the specified Radio Options
     * @param listener - listener supposed to handle events for the Radio Group
     * @param buttons - representing Radio options
     */
    public RadioGroup(ActionListener listener, JRadioButton ...buttons) {
        this(listener);
        add(buttons);
    }
    
    /**
     * Create a RadioGroup with the specified optionsMap
     * @param listener - listener supposed to handle events for the Radio Group
     * @param optionsMap - representing Radio options (key-value pairs)
     */
    public RadioGroup(ActionListener listener, Map<Object, String> optionsMap) {
        this(listener);
        add(makeRadioButtons(optionsMap));
    }
    

    /**
     * Create a simple RadioGroup with the specified Radio String Options
     * @param listener - listener supposed to handle events for the Radio Group
     * @param options - representing Radio options (literals)
     */
    public RadioGroup(ActionListener listener, String ...options) {
        this(listener);
        add(makeRadioButtons(mapify(options)));
    }
    
    
//    public RadioGroup (ActionListener listener, Map<Pixmap, String> optionsMap) {
//        // TODO Auto-generated constructor stub
//    }

    /**
     * Create 2 radio groups and retrieve the user input in an interactive manner.
     * Show retrieval through the command line.
     * @param args
     */
    public static void main (String[] args) {
        // TODO 
        JFrame tester = new JFrame("RadioGroup Tester");
        tester.setSize(250, 150);
        tester.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ActionListener testAction = getTestAction();
        RadioGroup rg1 = new RadioGroup(testAction, "2013", "2014", "2015", "2016");
        System.out.println(rg1.getHTML());
        Map<Object, String> sex = new HashMap<Object, String>();
        sex.put("Male", "M");
        sex.put("Female", "F");
        RadioGroup rg2 = new RadioGroup(testAction, sex);
        System.out.println(rg2);
        EasyGridFactory.layout(tester, rg1, rg2);
        tester.pack();
        tester.setVisible(true);
    }
    
    /**
     * Add the buttons passes to the RadioGroup
     * @param buttons - to be added
     */
    public void add (Collection<JToggleButton>buttons) {
        for (JToggleButton b:buttons) {
            add(b);
        }
    }
    

    private void applyLayout() {
//        this.setPreferredSize(new Dimension(150, 100));
//        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        this.setLayout(new GridLayout(0, 4));//TODO - Generalize
    }
    
    private void add (JToggleButton b) {
        super.add(b);
        myButtons.add(b);
        myGrouper.add(b);
        myOptions.put(b.getText(), b.getActionCommand());
        b.addActionListener(myListener);
    }
    
    /**
     * Add the buttons passes to the RadioGroup
     * @param buttons - to be added
     */
    private void add (JRadioButton ...buttons) {
        for (JRadioButton b:buttons) {
            add(b);
        }
    }
    
    private String compose (String before, String after) {
        StringBuilder res = new StringBuilder();
        for (JToggleButton b: myButtons) {
            String n = b.getText();
            res.append(before + n + after + "\n");
        }
        return res.toString();
    }
    
    /**
     * 
     * @return an HTML version of this element
     */
    public String getHTML() {
        StringBuilder res = new StringBuilder();
        for (JToggleButton b: myButtons) {
            String n = getHTML(b);
            res.append(n + "\n");
        }
        return res.toString();
    }
    
    private String getHTML(JToggleButton b) {
        return "<input type=\"radio\" " +
                        "name=" + myName + " " +
                        "value=" + b.getActionCommand() + 
                        " />" 
                        + b.getText() + "<br>";
    }
    
    private Collection<JToggleButton> makeRadioButtons(Map<Object, String> optionsMap) {
        Collection<JToggleButton> buttons = new ArrayList<JToggleButton>();
        for (Object o: optionsMap.keySet()) {
            JToggleButton currB = new JToggleButton();
            if (o instanceof String) {
                String s = (String)o;
                currB = makeRadioOption(s, optionsMap.get(o));
            }
            else if (o instanceof Icon) {
                Icon i = (Icon)o;
                currB = makeRadioOption(i, optionsMap.get(o));
            }
            else if (o instanceof Pixmap) {
                Pixmap p = (Pixmap)o;
                currB = makeRadioOption(p, optionsMap.get(o));
            }
            buttons.add(currB);
        }
        return buttons;
    }
    
    private JRadioButton makeRadioOption (Pixmap p, String optionValue) {
        JRadioButton res = new JRadioButton((Icon) p);
        res.setActionCommand(optionValue);
        res.setSelected(true);
        return res;
    }

    private JToggleButton makeRadioOption (String optionLabel, String optionValue) {
        JToggleButton res = new JToggleButton(optionLabel);
        res.setActionCommand(optionValue);
        res.setSelected(true);
        return res;
    }
    
    private JToggleButton makeRadioOption (Icon c, String optionValue) {
        JToggleButton res = new JToggleButton(c);
        res.setActionCommand(optionValue);
        res.setSelected(true);
        return res;
    }
    
    private Map<Object, String> mapify(String ...options) {
        Map<Object, String> res = new HashMap<Object, String>();
        for (String o: options) {
            res.put(o, o);
        }
        return res;
    }
    
    @Override
    public String toString() {
        return compose("� ", "");
    }
    
    /**
     * TODO
     * @author Dagbedji Fagnisse
     *
     */
    public static class TestAction extends AbstractAction {
        /**
         * 
         */
        private static final long serialVersionUID = -3838081948621848563L;

        TestAction() {
            super("TestAction");
        }

        @Override
        public void actionPerformed (ActionEvent e) {
            System.out.println(e.getActionCommand());
        }
    }
    
    private static final long serialVersionUID = 1L;
    public static AbstractAction getTestAction() {
        return new TestAction();
    }
}


