package ui;

import model.Pokemon;
import model.Trainer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// Panel that allows user to view the stats of a pokemon
public class ViewPokemonStats extends JFrame implements ActionListener {
    private JLabel name;
    private JLabel level;
    private ArrayList<String> attacks;
    private JLabel hitpoints;
    private Trainer trainer;
    private Pokemon pokemon;

    // Constructs a view pokemon stats panel
    // EFFECTS:  sets size, buttons, labels, images of panel,
    //           updates this with the game to be displayed
    public ViewPokemonStats(Trainer trainer, Pokemon pokemon) {
        super("Pokemon App");
        this.trainer = trainer;
        this.pokemon = pokemon;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 125));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new GridLayout());
        JButton back = new JButton("Back");
        back.addActionListener(this);
        name = new JLabel(pokemon.getName());
        level = new JLabel(String.valueOf(pokemon.getLevel()));
        hitpoints = new JLabel(String.valueOf(pokemon.getHitpoints()));
        attacks = pokemon.getAttacks();
        JPanel panel = new JPanel(new GridLayout(attacks.size(), 1));
        for (String str : attacks) {
            JLabel label = new JLabel(str);
            panel.add(label);
        }
        generateVisuals(back, panel);
    }

    // EFFECTS: adds visuals to panel
    private void generateVisuals(JButton back, JPanel panel) {
        add(name);
        add(level);
        add(hitpoints);
        add(panel);
        add(back);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // REQUIRES: btn clicked
    // EFFECTS: if back clicked, brings user back to the meny
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Back")) {
            MenuPanel menuPanel = new MenuPanel(trainer);
            setVisible(false);
        }
    }

}
