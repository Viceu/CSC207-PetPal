package view;

import interface_adaptor.adopt_user_preview.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AdoptUserPreviewView extends JPanel implements PropertyChangeListener {
    public final String viewName = "adopt preview";
    private final AdoptUserPreviewViewModel adoptUserPreviewViewModel;
    private final AdoptUserPreviewController adoptUserPreviewController;
    private final JTextField userMessage = new JTextField(15);
    private final JButton next;


    public AdoptUserPreviewView(AdoptUserPreviewViewModel newDisplayViewModel, AdoptUserPreviewController newAdoptUserPreviewController) {

        this.adoptUserPreviewViewModel = newDisplayViewModel;
        this.adoptUserPreviewViewModel.addPropertyChangeListener(this);
        this.adoptUserPreviewController = newAdoptUserPreviewController;

        JLabel title = new JLabel(AdoptUserPreviewViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel userMessageInfo = new LabelTextPanel(
                new JLabel(AdoptUserPreviewViewModel.MESSAGE_LABEL), userMessage);

        userMessage.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        AdoptUserPreviewState currentState = adoptUserPreviewViewModel.getState();
                        String text = userMessage.getText() + e.getKeyChar();
                        currentState.addUserMessage(text);
                        adoptUserPreviewViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        next = new JButton(AdoptUserPreviewViewModel.SUBMIT_BUTTON_LABEL);

        next.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(next)) {
                            AdoptUserPreviewState currentState = adoptUserPreviewViewModel.getState();

                            adoptUserPreviewController.execute(currentState.getPet(), currentState.getUserMessage(), currentState.getUsername());
                        }
                    }
                }
        );



        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(userMessageInfo);
        this.add(next);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        AdoptUserPreviewState state = (AdoptUserPreviewState) evt.getNewValue();

        if (state.getRequirementsError() != null) {
            JOptionPane.showMessageDialog(this, state.getRequirementsError());
        }
    }
}