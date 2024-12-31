package a02b.e2;

import javax.swing.*;

import a02b.e2.GridModel.CellStatus;

import java.util.*;
import java.util.List;
import java.awt.*;

public class GUI extends JFrame {
    private static final int ITEM_SELECTED_PER_DIAGONAL = 3;
    private static final String SELECTED_TEXT_BUTTON = "*";
    private static final String DEFAULT_TEXT_BUTTON = " ";
    private final int gridSize;
    private final List<JButton> cells = new ArrayList<>();
    private final JPanel gridPanel;
    private final GridController gridController;
    private boolean gameRestart;

    private void updateGridView() {
        this.cells.clear();
        this.gridPanel.removeAll();
        this.gridPanel.revalidate();

        for (int y = 0; y < this.gridSize; y++) {
            for (int x = 0; x < this.gridSize; x++) {
                final CellStatus cellStatus = this.gridController.getValues().get(new Pair<>(x, y));
                final JButton jb = new JButton(cellStatus.isSelected() ? SELECTED_TEXT_BUTTON : DEFAULT_TEXT_BUTTON);
                jb.setEnabled(cellStatus.isEnabled());
                this.cells.add(jb);
                jb.addActionListener(a -> {
                    final JButton curretButton = (JButton) a.getSource();
                    final int cellIndex = this.cells.indexOf(curretButton);
                    final CellStatus currentCellStatus = this.gridController.selectCell(cellIndex % this.gridSize,
                            cellIndex / this.gridSize);
                    curretButton.setText(currentCellStatus.isSelected() ? SELECTED_TEXT_BUTTON : DEFAULT_TEXT_BUTTON);
                });
                this.gridPanel.add(jb);
            }
        }
    }

    public GUI(int size) {
        this.gameRestart = false;
        this.gridSize = size;
        this.gridController = new GridController(this.gridSize, ITEM_SELECTED_PER_DIAGONAL);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100 * size, 100 * size);

        JPanel main = new JPanel(new BorderLayout());
        this.gridPanel = new JPanel(new GridLayout(size, size));
        JButton checkButton = new JButton("Check > Restart");
        this.getContentPane().add(main);
        main.add(BorderLayout.CENTER, this.gridPanel);
        main.add(BorderLayout.SOUTH, checkButton);

        checkButton.addActionListener(x -> {
            if (this.gameRestart) {
                this.gridController.restart();
                this.gameRestart = false;
            } else {
                final boolean diagonalHasBeenUpdated = this.gridController.updateDiagonal();
                this.gameRestart = diagonalHasBeenUpdated;
            }
            this.updateGridView();
        });

        this.updateGridView();
        this.setVisible(true);
    }
}
