package view;

import entities.Pet;
import entities.Requests;
import interface_adaptor.home.HomeState;
import interface_adaptor.home.HomeViewModel;
import interface_adaptor.org_adopt.OrgHomeViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Map;

import static javax.swing.JOptionPane.PLAIN_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_OPTION;

public class OrgHomeView extends JPanel implements PropertyChangeListener {

    public final String viewName = "org home";
    private final OrgHomeViewModel orgHomeViewModel;
    private final OrgHomeController orgHomeController;
    JLabel username;
    final JButton logOut;


    public OrgHomeView(OrgHomeController controller, OrgHomeViewModel orgHomeViewModel) {

        this.orgHomeController = controller;
        this.orgHomeViewModel = orgHomeViewModel;
        this.orgHomeViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(OrgHomeViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        ArrayList<LabelButtonPanel> buttons = new ArrayList<LabelButtonPanel>();
        for (Requests req: orgHomeViewModel.getState().getRequestList()) {

            JButton seeRequest = new JButton("See request");
            LabelButtonPanel newButton = new LabelButtonPanel(
                    new JLabel(req.getPet().getName()), seeRequest, null);
            buttons.add(newButton);

            seeRequest.addMouseListener(
                    new MouseListener() {
                        public void mouseClicked(MouseEvent evt) {
                            if (evt.getSource().equals(seeRequest)) {

                                Object[] options = {"Accept request",
                                        "Deny request", "Return to list of requests"};
                                int optionChosen = JOptionPane.showOptionDialog(null, req.toString(), null, YES_NO_OPTION, PLAIN_MESSAGE, null, options, options[2]);
                                if (optionChosen != 2) {
                                    OrgHomeController.execute();
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

        for (LabelButtonPanel someButton : buttons) {
            this.add(someButton);
        }

        JPanel buttons = new JPanel();
        search = new JButton(HomeViewModel.SEARCH_BUTTON_LABEL);
        buttons.add(search);
        search.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(search)) {

                            HomeState currentState = homeViewModel.getState();
                            homeController.execute("search");
                        }
                    }
                }
        );

        edit = new JButton(HomeViewModel.EDIT_BUTTON_LABEL);
        buttons.add(edit);
        edit.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(edit)) {

                            HomeState currentState = homeViewModel.getState();
                            homeController.execute("edit");
                        }
                    }
                }
        );

        logOut = new JButton(HomeViewModel.LOGOUT_BUTTON_LABEL);
        buttons.add(logOut);
        logOut.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(logOut)) {

                            HomeState currentState = homeViewModel.getState();
                            homeController.execute("logOut");
                        }
                    }
                }
        );



        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(usernameInfo);
        this.add(username);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showConfirmDialog(this, "This is not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        HomeState state = (HomeState) evt.getNewValue();
        username.setText(state.getUsername());
        if (state.getFetchError() != null) {
            JOptionPane.showMessageDialog(this, state.getFetchError());
        }
    }
}
