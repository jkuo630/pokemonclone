package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import model.Trainer;
import persistence.JsonReader;
import persistence.JsonWriter;

// Panel that allows user to select a new or saved file
public class FirstPanel extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/trainer.json";
    private JLabel question1;
    private JsonReader jsonReader;
    private Trainer trainer;


    // Constructs the first panel
    // EFFECTS:  sets size, buttons, labels of panel,
    //           updates this with the game to be displayed
    public FirstPanel() {
        super("Pokemon App");
        jsonReader = new JsonReader(JSON_STORE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 100));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());
        JButton newFile = new JButton("New File");
        JButton savedFile = new JButton("Load Saved File");
        newFile.addActionListener(this); // Sets "this" object as an action listener for btn
        savedFile.addActionListener(this);
        // so that when the btn is clicked,
        // this.actionPerformed(ActionEvent e) will be called.
        question1 = new JLabel("Save or New File? " + "\n");
        add(question1);
        add(newFile);
        add(savedFile);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // REQUIRES: btn clicked
    // MODIFIES: this
    // EFFECTS: if new file is clicked, it will bring the user to the signup panel,
    //          if load saved file is clicked, it will try to read the current file and
    //          load the old user.
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("New File")) {
            SignupPanel panel = new SignupPanel();
            setVisible(false);
        }
        if (e.getActionCommand().equals("Load Saved File")) {
            try {
                this.trainer = jsonReader.read();
                MenuPanel menuPanel = new MenuPanel(this.trainer);
                setVisible(false);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

}
