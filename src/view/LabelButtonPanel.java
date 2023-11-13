package view;

import entities.Pet;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LabelButtonPanel extends JPanel {

    Pet pet;
    LabelButtonPanel(JLabel label, JButton button, Pet pet) {
        this.add(label);
        this.add(button);
        this.pet = pet;
    }

    public Pet getPet() {
        return pet;
    }

}
