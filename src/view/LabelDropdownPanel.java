package view;

import javax.swing.*;

public class LabelDropdownPanel extends JPanel {
    LabelDropdownPanel(JLabel label, JComboBox comboBox) {
        this.add(label);
        this.add(comboBox);
    }
}
