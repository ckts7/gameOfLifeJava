package org.sundqvist;

import java.util.List;

public class GameOfLifeRunner {

    public static void main(String[] args) {
        final int width = 60;
        final int height = 60;
        final int size = width * height;
        final int refreshRateMs = 200;
        final double initCellRate = 0.10;

        final RulesOfLife game = new RulesOfLife(width, size, initCellRate);
        final RulesOfLifeRender render = new RulesOfLifeRender(width, height);
        render.renderUI();

        while (game.gameActive()) {
            final List<Boolean> cellList = game.applyRules();
            render.updateUI(cellList);
            try {
                Thread.sleep(refreshRateMs);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}

