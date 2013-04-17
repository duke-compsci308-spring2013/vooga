package vooga.rts.networking.client.GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class HostServer extends ServerBrowser {

    public HostServer() {
        this.setPreferredSize(new Dimension(800,600));

        getContentPane().add(createHostPanel());

        pack();
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }
    
    private JPanel createHostPanel () {
        JPanel mainPanel = new BackgroundPanel();
        
        
        
        JPanel hostPanel = new JPanel();
        //add(hostPanel, BorderLayout.SOUTH);
        hostPanel.setLayout(new BorderLayout(0, 0));

        JButton btnCreateNewServer = new JButton("Create New Server");
        btnCreateNewServer.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent arg0) {
            }
        });
        hostPanel.add(btnCreateNewServer, BorderLayout.WEST);
        
        JButton btnJoinServer = new JButton("Join Server");

        btnJoinServer.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent arg0) {
            }
        });
        hostPanel.add(btnJoinServer, BorderLayout.EAST);
        return hostPanel;
    }
}
