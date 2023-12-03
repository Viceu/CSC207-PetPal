package view;

import entities.Requests;
import interface_adaptor.org_adopt.OrgHomeController;
import interface_adaptor.org_adopt.OrgHomeState;
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

import static javax.swing.JOptionPane.PLAIN_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_OPTION;

public class OrgHomeView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "org home";
    private final OrgHomeViewModel orgHomeViewModel;
    private final OrgHomeController orgHomeController;
    JLabel username;
    final JButton logOut;


    public OrgHomeView(OrgHomeController controller, OrgHomeViewModel orgHomeViewModel) {

        this.orgHomeController = controller;
        this.orgHomeViewModel = orgHomeViewModel;
        this.orgHomeViewModel.addPropertyChangeListener(this);
        this.username = new JLabel(orgHomeViewModel.getLoggedInOrg());

        JLabel title = new JLabel(OrgHomeViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(username);

        JLabel pendingRequests = new JLabel("See pending requests");
        this.add(pendingRequests);
        ArrayList<LabelButtonPanel> buttons = new ArrayList<LabelButtonPanel>();
        for (Requests req: this.orgHomeViewModel.getState().getPendingRequests()) {

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
                                if (optionChosen != JOptionPane.CANCEL_OPTION) {
                                    orgHomeController.execute("see request", req, optionChosen);
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

        JLabel acceptedRequests = new JLabel("These are the requests you have accepted!");
        this.add(acceptedRequests);
        for (Requests req: this.orgHomeViewModel.getState().getAcceptedRequests()) {
            this.add(new JLabel(req.toString()));
        }

        JLabel deniedRequests = new JLabel("These are the requests you have denied!");
        this.add(deniedRequests);
        for (Requests req: this.orgHomeViewModel.getState().getDeniedRequests()) {
            this.add(new JLabel(req.toString()));
        }

        logOut = new JButton(OrgHomeViewModel.LOGOUT_BUTTON_LABEL);
        this.add(logOut);
        logOut.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(logOut)) {
                            orgHomeController.execute("log out", null, null);
                        }
                    }
                }
        );
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showConfirmDialog(this, "This is not a feature.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        OrgHomeState state = (OrgHomeState) evt.getNewValue();
        username.setText(state.getID());
        if (state.getRequestError() != null) {
            JOptionPane.showMessageDialog(this, state.getRequestError());
        }
    }
}
