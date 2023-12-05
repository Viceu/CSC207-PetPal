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

public class OrgHomeView extends JPanel implements PropertyChangeListener {

    public final String viewName = "org home";
    private final OrgHomeViewModel orgHomeViewModel;
    private final OrgHomeController orgHomeController;
    private final JButton logOut = new JButton(OrgHomeViewModel.LOGOUT_BUTTON_LABEL);


    public OrgHomeView(OrgHomeController controller, OrgHomeViewModel orgHomeViewModel) {

        this.orgHomeController = controller;
        this.orgHomeViewModel = orgHomeViewModel;
        this.orgHomeViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(OrgHomeViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        this.removeAll();

        JLabel title = new JLabel(OrgHomeViewModel.TITLE_LABEL);
        this.add(title, BorderLayout.CENTER);

        JLabel pendingRequests = new JLabel("See requests");
        this.add(pendingRequests);

        ArrayList<Requests> pendingReqs = this.orgHomeViewModel.getState().getOrg().getRequests();
        if (!pendingReqs.isEmpty()) {
            for (Requests req : pendingReqs) {
                JButton seeRequest = new JButton("See request");
                LabelButtonPanel newButton = new LabelButtonPanel(
                        new JLabel(req.getPet().getName()), seeRequest, null);

                seeRequest.addMouseListener(
                        new MouseListener() {
                            public void mouseClicked(MouseEvent evt) {
                                Object[] options = {"Accept request",
                                        "Deny request", "Return to list of requests"};
                                    int optionChosen = JOptionPane.showOptionDialog(null, req.toString(), null, YES_NO_OPTION, PLAIN_MESSAGE, null, options, options[2]);
                                    if (optionChosen != JOptionPane.CANCEL_OPTION) {
                                        orgHomeController.execute("see request", req, optionChosen);
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

                this.add(newButton);
            }
        }
        else {
            JLabel noReqs = new JLabel("There are no pending requests");
            this.add(noReqs);
        }

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

        OrgHomeState state = (OrgHomeState) evt.getNewValue();
        if (state.getRequestError() != null) {
            JOptionPane.showMessageDialog(this, state.getRequestError());
        }
    }
}
