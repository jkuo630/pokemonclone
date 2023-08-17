package ui;

import model.Event;
import model.EventLog;
import model.Trainer;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

// Panel that allows user to choose which function they want to use
public class MenuPanel extends JFrame implements ActionListener {
    private Trainer trainer;
    private static final String JSON_STORE = "./data/trainer.json";
    private JsonWriter jsonWriter;
    private EventLog loggers;

    // Constructs a menu panel
    // EFFECTS:  sets size, buttons, labels of panel,
    //           updates this with the game to be displayed
    public MenuPanel(Trainer trainer) {
        super("Pokemon App Menu");
        jsonWriter = new JsonWriter(JSON_STORE);
        loggers = EventLog.getInstance();
        this.trainer = trainer;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 200));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new GridLayout());
        JLabel welcome = new JLabel("Welcome back " + trainer.getName());
        JButton viewCollectionPokemon = new JButton("View Collection Pokemon");
        JButton catchPokemon = new JButton("Catch");
        JButton manageParty = new JButton("Manage Party");
        JButton saveAndQuit = new JButton("Save and Quit");
        viewCollectionPokemon.addActionListener(this);
        catchPokemon.addActionListener(this);
        manageParty.addActionListener(this);
        saveAndQuit.addActionListener(this);
        generateVisuals(welcome, viewCollectionPokemon, catchPokemon, manageParty, saveAndQuit);
    }

    // EFFECTS: adds the visuals (labels, textfields, buttons) to the panel
    private void generateVisuals(JLabel welcome, JButton viewCollectionPokemon, JButton catchPokemon,
                                 JButton manageParty, JButton saveAndQuit) {
        add(welcome);
        add(viewCollectionPokemon);
        add(catchPokemon);
        add(manageParty);
        add(saveAndQuit);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // REQUIRES: btn clicked
    // EFFECTS: if view collection pokemon is clicked, it will generate a new view collection panel
    //          if catch is clicked, it will generate a new catch panel
    //          if manage party is clicked, it will generate a new manage party panel
    //          if save and quit is clicked, it will save the state to a file and exit the application
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("View Collection Pokemon")) {
            ViewCollectionPanel viewCollectionPanel = new ViewCollectionPanel(trainer);
            setVisible(false);
        }
        if (e.getActionCommand().equals("Catch")) {
            CatchPanel catchPanel = new CatchPanel(trainer);
            setVisible(false);
        }
        if (e.getActionCommand().equals("Manage Party")) {
            PartyManagerPanel partyManagerPanel = new PartyManagerPanel(trainer);
            setVisible(false);
        }
        if (e.getActionCommand().equals("Save and Quit")) {
            try {
                performQuit();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    // EFFECTS: Writes a new saved file, prints log, clears log.
    private void performQuit() throws FileNotFoundException {
        jsonWriter.open();
        jsonWriter.write(trainer);
        jsonWriter.close();
        printLog(loggers);
        loggers.clear();
        System.exit(0);
    }

    // EFFECTS: prints out all the event logs in the given event log
    public void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next);
        }
    }

}