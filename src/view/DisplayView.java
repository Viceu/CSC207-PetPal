package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeListener;

import entities.Pet;
import interface_adaptor.display.DisplayController;
import interface_adaptor.display.DisplayViewModel;
import interface_adaptor.display.DisplayState;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Map;

import static javax.swing.JOptionPane.PLAIN_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_OPTION;

public class DisplayView extends JPanel implements PropertyChangeListener {
    public final String viewName = "display";

    private final DisplayViewModel displayViewModel;
    private final DisplayController displayController;
    private final JButton adopt = new JButton("Adopt!");


    public DisplayView(DisplayViewModel newDisplayViewModel, DisplayController newDisplayController) {

        this.displayViewModel = newDisplayViewModel;
        this.displayViewModel.addPropertyChangeListener(this);
        this.displayController = newDisplayController;

        JLabel title = new JLabel(DisplayViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (this.displayViewModel.getState().getPets() == null) {
            this.add(new JLabel("No results found for this search, please change your search."));
        }
        else {

            ArrayList<LabelButtonPanel> buttons = new ArrayList<LabelButtonPanel>();
            for (Map.Entry<Integer, Pet> entry : this.displayViewModel.getState().getPets().entrySet()) {
                JButton seeMore = new JButton("See more");
                LabelButtonPanel newButton = new LabelButtonPanel(
                        new JLabel(entry.getValue().getName() + ": " + entry.getValue().getSpecies()), seeMore, entry.getValue());
                buttons.add(newButton);

                seeMore.addMouseListener(
                        new MouseListener() {
                            public void mouseClicked(MouseEvent evt) {
                                if (evt.getSource().equals(seeMore)) {

                                    Pet thisPet = newButton.getPet();

                                    String message = "";

                                    for (Map.Entry<String, String> attributes : thisPet.getAll().entrySet()) {
                                        String key = attributes.getKey();
                                        message += key + " " + thisPet.getAll().get(key) + "\n";
                                    }

                                    Object[] options = {"Adopt!",
                                            "Return to search"};
                                    int optionChosen = JOptionPane.showOptionDialog(null, message, null, YES_NO_OPTION, PLAIN_MESSAGE, null, options, options[1]);
                                    if (optionChosen == 0) {
                                        displayController.execute(thisPet);
                                    }
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
                        });
            }

            for (LabelButtonPanel button : buttons) {

            }

            for (LabelButtonPanel someButton : buttons) {
                this.add(someButton);
            }
        }

        DisplayState state = (DisplayState) evt.getNewValue();
        if (state.getRequirementsError() != null) {
            JOptionPane.showMessageDialog(this, state.getRequirementsError());
        }
    }
}
