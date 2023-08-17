package ui;

import model.Pokemon;
import model.Trainer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Panel that allows user to manage their party pokemon
public class PartyManagerPanel extends JFrame implements ActionListener {
    private Trainer trainer;

    // Constructs a party panel
    // EFFECTS:  sets size, buttons, labels, pane of panel,
    //           updates this with the game to be displayed
    public PartyManagerPanel(Trainer trainer) {
        super("Pokemon App");
        this.trainer = trainer;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 125));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new GridLayout());
        JButton back = new JButton("Back");
        JButton viewParty = new JButton("View Party");
        JButton addToParty = new JButton("Add to Party");
        back.addActionListener(this);
        viewParty.addActionListener(this);
        addToParty.addActionListener(this);
        add(viewParty);
        add(addToParty);
        add(back);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // REQUIRES: btn clicked
    // EFFECTS: if back clicked, it will take the user back to the menu,
    //          if view party clicked, it will generate a new party panel
    //          if add to party clicked, it will generate a new add party panel
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Back")) {
            MenuPanel menuPanel = new MenuPanel(trainer);
            setVisible(false);
        }
        if (e.getActionCommand().equals("View Party")) {
            ViewPartyPanel partyPanel = new ViewPartyPanel(trainer);
            setVisible(false);
        }
        if (e.getActionCommand().equals("Add to Party")) {
            AddPartyPanel addPartyPanel = new AddPartyPanel(trainer);
            setVisible(false);
        }
    }

}