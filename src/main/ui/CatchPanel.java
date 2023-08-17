package ui;

import model.Pokemon;
import model.Trainer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;

// Panel that allows user to catch new pokemon
public class CatchPanel extends JFrame implements ActionListener {
    private Trainer trainer;
    private Pokemon wildPokemon;
    private ArrayList<Pokemon> wildPokemons;
    private JLabel name;
    private JLabel result;
    private JButton catchAttempt;
    private JLabel imageLabel;

    // Constructs a catch panel
    // EFFECTS:  sets size, buttons, labels, images of panel,
    //           updates this with the game to be displayed
    public CatchPanel(Trainer trainer) {
        super("Pokemon App");
        this.trainer = trainer;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 300));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());
        wildPokemons = new ArrayList<Pokemon>();
        model.Pokemon.populateWildPokemon(wildPokemons);
        wildPokemon = wildPokemons.get(model.Pokemon.generateRandomInt());
        JButton back = new JButton("Back");
        name = new JLabel("A wild " + wildPokemon.getName() + " appeared!");
        result = new JLabel("");
        catchAttempt = new JButton("Catch");
        back.addActionListener(this);
        catchAttempt.addActionListener(this);
        generateVisuals(back);
    }

    // EFFECTS: generates an image of the pokeball, and adds all the visuals to the panel
    private void generateVisuals(JButton back) {
        ImageIcon originalImageIcon = new ImageIcon("./data/pokemonball.png");
        Image originalImage = originalImageIcon.getImage();
        int desiredWidth = 200;
        int desiredHeight = 200;
        Image resizedImage = originalImage.getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH);
        ImageIcon resizedImageIcon = new ImageIcon(resizedImage);
        JLabel label = new JLabel();
        label.setIcon(resizedImageIcon);
        label.setPreferredSize(new Dimension(desiredWidth, desiredHeight));
        add(label);
        add(name);
        add(catchAttempt);
        add(result);
        add(back);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    //This is the method that is called when the JButton btn is clicked
    // REQUIRES: a btn clicked
    // EFFECTS: if the back button is clicked, it will take the user back to the menu,
    //          if the catch button is clicked, it will check if the pokemon is caught, if
    //          it is, it will add it to the trainer's collection pokemon, if not, if will
    //          output a message and not add the pokemon to their collection.
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Back")) {
            MenuPanel menuPanel = new MenuPanel(trainer);
            setVisible(false);
        }
        if (e.getActionCommand().equals("Catch")) {
            if (model.Pokemon.checkIfCaught(model.Pokemon.generateRandomInt())) {
                trainer.addPokemon(wildPokemon);
                MenuPanel menuPanel = new MenuPanel(trainer);
                setVisible(false);
            } else {
                result.setText("Aw so close! Try again.");
            }
        }
    }
}
