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

// Panel that allows user to view their party pokemon
public class ViewPartyPanel extends JFrame implements ActionListener {
    private Trainer trainer;
    JTable table;

    // Constructs a view party panel
    // EFFECTS:  sets size, buttons, labels of panel,
    //           updates this with the game to be displayed
    public ViewPartyPanel(Trainer trainer) {
        super("Pokemon App");
        this.trainer = trainer;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 500));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());
        JButton back = new JButton("Back");
        back.addActionListener(this);
        JScrollPane pane = getjScrollPane(trainer);
        add(pane);
        add(back);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // EFFECTS: generates a scroll pane with the trainer's party pokemons' names
    private JScrollPane getjScrollPane(Trainer trainer) {
        ArrayList<Pokemon> partyPokemon = trainer.getPartyPokemon();
        ArrayList<String> pokemonNames = new ArrayList<>();
        for (Pokemon pokemon : partyPokemon) {
            pokemonNames.add(pokemon.getName());
        }
        Object[] columName = {"Name"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columName);
        table = new JTable();
        table.setModel(model);
        for (Pokemon pokemon : trainer.getPartyPokemon()) {
            Vector<String> result = new Vector<>();
            result.add(pokemon.getName());
            model.addRow(result);
        }
        JScrollPane pane = new JScrollPane(table);
        return pane;
    }

    // REQUIRES: btn clicked
    // EFFECTS: if back is clicked, bring user to menu
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Back")) {
            MenuPanel menuPanel = new MenuPanel(trainer);
            setVisible(false);
        }
    }

}