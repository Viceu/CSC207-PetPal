package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeListener;

import entities.Pet;
import viviansong.*;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Map;

public class DisplayView extends JPanel implements PropertyChangeListener {
    public final String viewName = "display";

    private final DisplayViewModel displayViewModel;
    private final JButton seeMore = new JButton("See more");


    public DisplayView(DisplayViewModel displayViewModel) {

        this.displayViewModel = displayViewModel;
        displayViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(DisplayViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);


        ArrayList<LabelButtonPanel> buttons = new ArrayList<LabelButtonPanel>();

        for (Map.Entry<Integer, Pet> entry: displayViewModel.getState().getPets().entrySet()) {
            LabelButtonPanel newButton = new LabelButtonPanel(
                    new JLabel(entry.getValue().getName() + ": " + entry.getValue().getSpecies()), seeMore, entry.getValue());
            buttons.add(newButton);
        }

        for (LabelButtonPanel button: buttons) {
            button.addMouseListener(
                    new MouseListener() {
                        public void mouseClicked(MouseEvent evt) {
                            if (evt.getSource().equals(seeMore)) {

                                Pet thisPet = button.getPet();

                                String message = "";

                                for (Map.Entry<String, Boolean> attributes: thisPet.getAttributes().entrySet()) {
                                    if (attributes.getValue()) {
                                        String key = attributes.getKey();
                                        message += key + thisPet.getAll().get(key) + "\n";
                                    }
                                }

                                JOptionPane.showMessageDialog(null, message);
                            }
                        }

                        @Override
                        public void mousePressed(MouseEvent e) {
                        }
                        @Override
                        public void mouseReleased(MouseEvent e) {
                        }
                        @Override
                        public void mouseEntered(MouseEvent e) {
                        }
                        @Override
                        public void mouseExited(MouseEvent e) {
                        }
                    }
            );
        }

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        for (LabelButtonPanel someButton: buttons) {
            this.add(someButton);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        DisplayState state = (DisplayState) evt.getNewValue();

        if (state.getRequirementsError() != null) {
            JOptionPane.showMessageDialog(this, state.getRequirementsError());
        }
    }
}
