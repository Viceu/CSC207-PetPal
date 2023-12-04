package view;

import entities.Pet;
import entities.User;
import interface_adaptor.edit.EditController;
import interface_adaptor.edit.EditState;
import interface_adaptor.edit.EditViewModel;
import use_case.edit.EditPetDataAccessInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.Map;

public class EditView extends JPanel implements PropertyChangeListener {
    public final String viewName = "user profile";

    private final EditViewModel editviewModel;
    private final JTextField petnameInputField = new JTextField(15);
    private final JTextField petnameErrorField = new JTextField();

    private final JPasswordField petbioInputField = new JPasswordField(15);

    private final EditController editController;

    private final EditPetDataAccessInterface editPetDataAccessInterface;

    public EditView(EditPetDataAccessInterface editPetDataAccessInterface,
                    EditViewModel editviewModel,
                    EditController editController) throws IOException {
        this.editviewModel = editviewModel;
        this.editController = editController;
        this.editPetDataAccessInterface = editPetDataAccessInterface;

        editviewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(EditViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        EditState state = (EditState) evt.getNewValue();
        if (state.getPetnameError() != null) {
            JOptionPane.showMessageDialog(this, state.getPetnameError());
        }

        this.removeAll();

        JLabel username = new JLabel("Username: " + editviewModel.getState().getUser().getName());
        this.add(username);

        JLabel bio = new JLabel("Bio: " + editviewModel.getState().getUser().getBio());
        this.add(bio);

        JButton add = new JButton("add");
        LabelButtonPanel petsAdd = new LabelButtonPanel(new JLabel("Pets: "), add, null);
        this.add(petsAdd);

        add.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        User user = editviewModel.getState().getUser();

                        String petname = JOptionPane.showInputDialog("Pet name:");
                        String petbio = JOptionPane.showInputDialog("Pet bio:");

                        editController.execute(user, petname, petbio, editviewModel.getState().getUser().getName());

                        ((JButton) e.getSource()).removeActionListener(this);
                    }
                }
        );

        // for loop tha iterates over ll the pets {
        if (editviewModel.getState().getPets() != null) {
            for (Pet pet : editviewModel.getState().getPets()) {
                JLabel petlabel = new JLabel("Pet: " + pet.getName() + ": " + pet.getBio());
                this.add(petlabel);
            }
        }
        // JLabel petlabel = new JLabel("pet.getMa,e()")
        // this.add(petLabel);
        // }

        JButton exit = new JButton(EditViewModel.EXIT_BUTTON_LABEL);
        this.add(exit);

        exit.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        User user = editviewModel.getState().getUser();

                        editController.execute(user, null, null, null);

                        ((JButton) e.getSource()).removeActionListener(this);
                    }
                }
        );

    }
}
