package arcade.view.panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import arcade.model.Model;


@SuppressWarnings("serial")
public class StorePanel extends JPanel {
    private Model myModel;
    
    public StorePanel (Model model) {
        //setLayout(null);
        myModel = model;

        setBackground(Color.WHITE);

        //JLabel title = new JLabel("Store");
        //title.setBounds(5, 5, 40, 40);
        //add(title);

        add(createPublishButton());
    }

    private Component createPublishButton () {
        JButton button = new JButton("Publish");
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed (ActionEvent arg0) {
                JOptionPane.showMessageDialog(null, 
                        "To publish your game, select the directory of its package.");

                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int returnVal = chooser.showOpenDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    String path = chooser.getSelectedFile().getPath();
                    myModel.publishGame(path);
                }

            }
        });
        return button;
    }
}
