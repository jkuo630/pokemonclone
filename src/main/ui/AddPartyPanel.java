package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import model.Pokemon;
import model.Trainer;

// Panel that allows the user to add a pokemon to their collection
public class AddPartyPanel extends JFrame implements ActionListener {
    private Trainer trainer;
    JTable table;
    private JTextField addedPokemon;
    private JButton addPokemon;
    private Pokemon pokemon;

    // Constructs a party panel
    // EFFECTS:  sets size, buttons, labels of panel,
    //           updates this with the game to be displayed
    public AddPartyPanel(Trainer trainer) {
        super("Pokemon App");
        this.trainer = trainer;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 500));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());
        JButton back = new JButton("Back");
        JButton addPokemon = new JButton("Add Pokemon To Party");
        addedPokemon = new JTextField(10);
        back.addActionListener(this);
        addPokemon.addActionListener(this);
        JScrollPane pane = getjScrollPane(trainer);
        add(pane);
        add(addedPokemon);
        add(addPokemon);
        add(back);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // EFFECTS:  crates a scroll pane of all the trainer's collection pokemon's name
    private JScrollPane getjScrollPane(Trainer trainer) {
        ArrayList<Pokemon> ownedPokemon = trainer.getOwnedPokemon();
        ArrayList<String> pokemonNames = new ArrayList<>();
        for (Pokemon pokemon : ownedPokemon) {
            pokemonNames.add(pokemon.getName());
        }
        Object[] columName = {"Name"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columName);
        table = new JTable();
        table.setModel(model);
        for (Pokemon pokemon : trainer.getOwnedPokemon()) {
            Vector<String> result = new Vector<>();
            result.add(pokemon.getName());
            model.addRow(result);
        }
        JScrollPane pane = new JScrollPane(table);
        return pane;
    }

    //This is the method that is called when the JButton btn is clicked
    // REQUIRES: a btn clicked
    // MODIFIES:
    // EFFECTS: if the back button is clicked, it creates a new menu panel,
    //          if the add pokemon to party button is clicked, it will add that
    //          pokemon to the party and take the user back to the menu panel
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Back")) {
            MenuPanel menuPanel = new MenuPanel(trainer);
            setVisible(false);
        }
        if (e.getActionCommand().equals("Add Pokemon To Party")) {
            pokemon = trainer.getPokemon(addedPokemon.getText());
            trainer.removePokemon(pokemon);
            trainer.addPartyPokemon(pokemon);
            MenuPanel menuPanel = new MenuPanel(trainer);
            setVisible(false);
        }
    }

}