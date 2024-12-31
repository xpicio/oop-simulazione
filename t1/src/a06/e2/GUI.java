package a06.e2;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.*;

public class GUI extends JFrame {

    private final List<JButton> cells = new ArrayList<>();
    private final GridController gridController;

    private void viewUpdate(final int size, final JPanel panel, final JButton go) {
        panel.removeAll();
        panel.revalidate();
        final Map<Pair<Integer, Integer>, Optional<Integer>> gridValues = this.gridController.getValues();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final Optional<Integer> gridItemValue = gridValues.get(new Pair<>(j, i));
                final JButton jb = new JButton(gridItemValue.isPresent() ? gridItemValue.get().toString() : "");
                this.cells.add(jb);
                panel.add(jb);
            }
        }
        go.setEnabled(this.gridController.gridColumnsCanCollapse());
    }

    public GUI(int size) {
        this.gridController = new GridController(size);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100 * size, 100 * size);

        JPanel main = new JPanel(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(size, size));
        this.getContentPane().add(main);
        main.add(BorderLayout.CENTER, panel);
        JButton go = new JButton("Fire");
        main.add(BorderLayout.SOUTH, go);
        go.addActionListener(e -> {
            gridController.collapseColumns();
            viewUpdate(size, panel, go);
        });

        this.viewUpdate(size, panel, go);
        this.setVisible(true);
    }
}
