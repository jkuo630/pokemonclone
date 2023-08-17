package ui;

import model.Pokemon;
import model.Trainer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Panel that allows user to create a new file
public class SignupPanel extends JFrame implements ActionListener {
    private JLabel question1;
    private JTextField question1Answer;

    // Constructs a signup panel
    // EFFECTS:  sets size, buttons, labels, fields of panel,
    //           updates this with the game to be displayed
    public SignupPanel() {
        super("Pokemon App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 125));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());
        JButton bulbasaur = new JButton("Bulbasaur");
        JButton squirtle = new JButton("Squirtle");
        JButton charmander = new JButton("Charmander");
        bulbasaur.addActionListener(this);
        squirtle.addActionListener(this);
        charmander.addActionListener(this);
        question1 = new JLabel("                         What is your name?");
        JLabel space = new JLabel("                                ");
        question1Answer = new JTextField(8);
        generateVisuals(bulbasaur, squirtle, charmander, space);
    }

    // EFFECTS: adds visuals to panel
    private void generateVisuals(JButton bulbasaur, JButton squirtle, JButton charmander, JLabel space) {
        add(question1);
        add(question1Answer);
        add(space);
        add(bulbasaur);
        add(squirtle);
        add(charmander);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // REQUIRES: btn clicked
    // MODIFIES: this, trainer
    // EFFECTS: if bulbasaur is clicked, adds it as a pokemon to the trainer's collection pokemon
    //          if squirtle is clicked, adds it as a pokemon to the trainer's collection pokemon
    //          if charmander is clicked, adds it as a pokemon to the trainer's collection pokemon
    //          if any btn is clicked, it will take the text in the textfield and set it as the trainer's name
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Bulbasaur")) {
            Trainer trainer = new Trainer(question1Answer.getText());
            Pokemon bulbasaur = new Pokemon(15, 5, "Bulbasaur");
            trainer.addPokemon(bulbasaur);
            MenuPanel menuPanel = new MenuPanel(trainer);
            setVisible(false);
        }
        if (e.getActionCommand().equals("Squirtle")) {
            Trainer trainer = new Trainer(question1Answer.getText());
            Pokemon squirtle = new Pokemon(15, 5, "Squirtle");
            trainer.addPokemon(squirtle);
            MenuPanel menuPanel = new MenuPanel(trainer);
            setVisible(false);
        }
        if (e.getActionCommand().equals("Charmander")) {
            Trainer trainer = new Trainer(question1Answer.getText());
            Pokemon charmander = new Pokemon(15, 5, "Charmander");
            trainer.addPokemon(charmander);
            MenuPanel menuPanel = new MenuPanel(trainer);
            setVisible(false);
        }
    }

}