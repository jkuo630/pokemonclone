package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import model.Pokemon;
import model.Trainer;

// Panel that allows user to view their collection pokemon
public class ViewCollectionPanel extends JFrame implements ActionListener {
    private Trainer trainer;
    JTable table;
    private JTextField viewedPokemon;
    private Pokemon pokemon;


    // Constructs a view collection panel
    // EFFECTS:  sets size, buttons, labels, fields of panel,
    //           updates this with the game to be displayed
    public ViewCollectionPanel(Trainer trainer) {
        super("Pokemon App");
        this.trainer = trainer;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 500));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());
        JButton back = new JButton("Back");
        JButton viewPokemon = new JButton("View Pokemon");
        viewedPokemon = new JTextField(10);
        back.addActionListener(this);
        viewPokemon.addActionListener(this);
        JScrollPane pane = getjScrollPane(trainer);
        add(pane);
        add(viewedPokemon);
        add(viewPokemon);
        add(back);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // EFFECTS: generates a scroll pane with all the trainer's owned pokemon's names
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

    // REQUIRES: btn clicked
    // EFFECTS: if back clicked, takes user back to menu,
    //          if view pokemon clicked, generates a new view pokemon stats panel for the pokemon in the
    //          textfield
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Back")) {
            MenuPanel menuPanel = new MenuPanel(trainer);
            setVisible(false);
        }
        if (e.getActionCommand().equals("View Pokemon")) {
            pokemon = trainer.getPokemon(viewedPokemon.getText());
            ViewPokemonStats viewPokemonStats = new ViewPokemonStats(trainer, pokemon);
            setVisible(false);
        }
    }

}
