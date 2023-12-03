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

    private final JButton add;

    private final JButton exit;

    public EditView(EditPetDataAccessInterface editPetDataAccessInterface,
                    EditViewModel editviewModel,
                    EditController editController) throws IOException {
        this.editviewModel = editviewModel;
        this.editController = editController;

        editviewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(EditViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(title);

        JPanel buttons = new JPanel();
        add = new JButton(EditViewModel.EDIT_BUTTON_LABEL);
        buttons.add(add);
        exit = new JButton(EditViewModel.EXIT_BUTTON_LABEL);
        buttons.add(exit);

        add.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(add)) {

                            User user = editviewModel.getState().getUser();
                            String petname = JOptionPane.showInputDialog("Pet name:");
                            String petbio = JOptionPane.showInputDialog("Pet bio:");

                            editController.execute(user, petname, petbio, editviewModel.getState().getUser().getName());
                        }
                    }
                }
        );

        exit.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(exit)) {
                            User user = editviewModel.getState().getUser();
                            editController.execute(user, null, null, null);
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel username = new JLabel("Username: " + editviewModel.getState().getUser().getName());
        this.add(username);

        JLabel bio = new JLabel("Bio: " + editviewModel.getState().getUser().getBio());
        this.add(bio);

        JLabel pets = new JLabel("Pets: ");
        this.add(pets);

        Map<String, Pet> petsmap = editPetDataAccessInterface.getPetsbyOwner(editviewModel.
                getState().getUser().getName());

        // for loop tha iterates over ll the pets {
        for (Pet pet : petsmap.values()) {
            JLabel petlabel = new JLabel(pet.getName() + ": " + pet.getBio());
            this.add(petlabel);
        }
        // JLabel petlabel = new JLabel("pet.getMa,e()")
        // this.add(petLabel);
        // }

    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue() instanceof EditState) {
            EditState state = (EditState) evt.getNewValue();
            if (state.getPetnameError() != null) {
                JOptionPane.showMessageDialog(this, state.getPetnameError());
            }
        }
    }
}
