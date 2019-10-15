package org.sundqvist;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedList;
import java.util.List;

/**
 * RulesOfLifeRender is a swing-component to render the cells to show the Game of Life Gameplay
 */
public class RulesOfLifeRender {
    private final int width;
    private final int height;
    private final int size;
    private List<PropertyChangeListener> changeListener = new LinkedList<>();

    public RulesOfLifeRender(int width, int height) {
        this.width = width;
        this.height = height;
        this.size = width * height;
    }

    /**
     * renderUI sets up first render of GameCanvas and adds eventlistener to change on cell life
     */
    public void renderUI() {
        JFrame demo = new JFrame("demo");
        demo.setSize(1000, 1000);
        demo.setLayout(new GridLayout(height, width));
        demo.setBackground(Color.WHITE);

        for (int i = 0; i < size; i++) {
            JLabel jLabel = new JLabel();
            jLabel.setBackground(Color.WHITE);
            jLabel.setOpaque(true);
            jLabel.setBorder(BorderFactory.createLineBorder(Color.lightGray, 1));
            demo.add(jLabel);
            PropertyChangeListener propertyChangeListener = evt -> {
                if (evt.getNewValue().getClass().isAssignableFrom(Boolean.class)) {
                    jLabel.setBackground((boolean) evt.getNewValue() ? Color.BLACK : Color.WHITE);
                }
            };
            jLabel.addPropertyChangeListener(propertyChangeListener);
            changeListener.add(propertyChangeListener);
        }
        demo.setVisible(true);
    }

    /**
     * updateUI is run after every generation has been processed to render the new generation of cells
     * @param cells - contains list-information regarding state of life of the cells
     */
    public void updateUI(List<Boolean> cells) {
        for (int i = 0; i < size; i++) {
            boolean element = cells.get(i);
            changeListener.get(i).propertyChange(new PropertyChangeEvent(element, null, !element, element));
        }
    }
}
