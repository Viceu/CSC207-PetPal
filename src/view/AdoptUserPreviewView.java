package view;

import entities.Pet;
import interface_adaptor.adopt_user_preview.*;
import interface_adaptor.display.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AdoptUserPreviewView extends JPanel implements PropertyChangeListener {
    public final String viewName = "adopt preview";
    private final AdoptUserPreviewViewModel adoptUserPreviewViewModel;
    // TODO: private final AdoptUserPreviewController adoptUserPreviewController;
    private final JButton next;


    public AdoptUserPreviewView(AdoptUserPreviewViewModel newDisplayViewModel/*TODO: , AdoptUserPreviewController newAdoptUserPreviewController*/) {

        this.adoptUserPreviewViewModel = newDisplayViewModel;
        this.adoptUserPreviewViewModel.addPropertyChangeListener(this);
        // TODO: this.adoptUserPreviewController = newAdoptUserPreviewController;

        JLabel title = new JLabel(DisplayViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        next = new JButton(AdoptUserPreviewViewModel.SUBMIT_BUTTON_LABEL);

        next.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(next)) {
                            AdoptUserPreviewState currentState = adoptUserPreviewViewModel.getState();

                            // TODO: adoptUserPreviewController.execute(currentState.getPet());
                        }
                    }
                }
        );



        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(next);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        DisplayState state = (DisplayState) evt.getNewValue();

        if (state.getRequirementsError() != null) {
            JOptionPane.showMessageDialog(this, state.getRequirementsError());
        }
    }
}