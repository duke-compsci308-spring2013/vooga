package arcade.view.panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import arcade.model.Model;
import arcade.view.PublishView;


@SuppressWarnings("serial")
public class StorePanel extends JPanel {
    private Model myModel;
    private ResourceBundle myResources;
    
    public StorePanel (Model model, ResourceBundle resources) {
        //setLayout(null);
        myModel = model;
        myResources = resources;

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
                new PublishView(myModel, myResources);
            }
        });
        return button;
    }
}
