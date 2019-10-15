package org.sundqvist;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class RulesOfLife {

    private static boolean valuesChanged = true;
    private final int width;
    private final int size;
    private final List<Boolean> cells = new LinkedList<>();

    public RulesOfLife(int width, int size, double liveFactor) {
        this.width = width;
        this.size = size;
        populateCells(liveFactor);
    }

    private void populateCells(double liveFactor) {
        for (int i = 0; i < size; i++) {
            cells.add(Math.random() < liveFactor);
        }
    }

    public List<Boolean> applyRules() {
        List<Boolean> clonedList = List.copyOf(cells);
        for (int i = 0; i < size; i++) {
            boolean element = shouldCellLive(i, clonedList);
            cells.set(i, element);
        }
        valuesChanged = !clonedList.equals(cells);
        return Collections.unmodifiableList(cells);
    }


    public boolean gameActive() {
        return valuesChanged;
    }

    /**
     * Method that takes out the neighbours of a tested cell to see if cell should be dead or alive for next round
     *
     * @param pos      - Position of cell that we are checking
     * @param cellList - The list of cells and their state
     * @return - Sends back the new state(dead or alive) for tested cell.
     */

    // method set as package to enable outside testing
    boolean shouldCellLive(int pos, List<Boolean> cellList) {
        int aliveNeighbours = 0;
        // Check left neighbour
        if ((pos % width) > 0 && cellList.get(pos - 1)) {
            aliveNeighbours++;
        }
        // Check right neighbour
        if ((pos % width) != (width - 1) && cellList.get(pos + 1)) {
            aliveNeighbours++;
        }
        // Check top neighbour
        if ((pos - width) >= 0 && cellList.get(pos - width)) {
            aliveNeighbours++;
        }
        // Check bottom neighbour
        if ((pos + width) <= (size - 1) && cellList.get(pos + width)) {
            aliveNeighbours++;
        }
        // Check top left neighbour
        if ((pos - width >= 0 && (pos % width) > 0) && cellList.get(pos - width - 1)) {
            aliveNeighbours++;
        }
        // Check top right neighbour
        if ((pos - width >= 0 && (pos % width) != (width - 1)) && cellList.get(pos - width + 1)) {
            aliveNeighbours++;
        }
        // Check bottom left neighbour
        if ((pos + width) <= (size - 1) && (pos % width) > 0 && cellList.get(pos + width - 1)) {
            aliveNeighbours++;
        }
        // Check bottom right neighbour
        if ((pos + width) <= (size - 1) && (pos % width) != (width - 1) && cellList.get(pos + width + 1)) {
            aliveNeighbours++;
        }

        if (cellList.get(pos)) {
            return aliveNeighbours == 2 || aliveNeighbours == 3;
        } else {
            return aliveNeighbours == 3;
        }
    }
}
