package view;

import entities.Pet;
import interface_adaptor.home.HomeController;
import interface_adaptor.home.HomeState;
import interface_adaptor.home.HomeViewModel;

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


public class HomeView extends JPanel implements ActionListener, PropertyChangeListener {
    // TODO: usage in Main:
    //  LoggedInView loggedInView = new LoggedInView(loggedInViewModel);
    //  views.add(loggedInView, loggedInView.viewName);
    public final String viewName = "home";

    private final HomeViewModel homeViewModel;
    private final HomeController homeController;

    JLabel username;
    final JButton logOut;
    final JButton edit;
    private final JButton search;
    private final JButton seeMore = new JButton("See more");
    private final JButton adopt = new JButton("Adopt!");


    public HomeView(HomeController controller, HomeViewModel homeViewModel) {

        this.homeController = controller;
        this.homeViewModel = homeViewModel;
        this.homeViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(HomeViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel usernameInfo = new JLabel("Currently logged in: ");
        username = new JLabel(homeViewModel.getLoggedInUser());

        JPanel buttons = new JPanel();
        search = new JButton(HomeViewModel.SEARCH_BUTTON_LABEL);
        buttons.add(search);
        search.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(search)) {

                            HomeState currentState = homeViewModel.getState();
                            homeController.executeView("search");
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
                            homeController.executeView("edit");
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
                            homeController.executeView("logOut");
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

        if (this.homeViewModel.getState().getPets() == null) {
            this.add(new JLabel("There are currently no pets to adopt, please come back later."));
        }
        else {
            ArrayList<LabelButtonPanel> buttons = new ArrayList<LabelButtonPanel>();
            for (Map.Entry<Integer, Pet> entry : this.homeViewModel.getState().getPets().entrySet()) {
                LabelButtonPanel newButton = new LabelButtonPanel(
                        new JLabel(entry.getValue().getName() + ": " + entry.getValue().getSpecies()), seeMore, entry.getValue());
                buttons.add(newButton);
            }

            for (LabelButtonPanel button : buttons) {
                button.addMouseListener(
                    new MouseListener() {
                        public void mouseClicked(MouseEvent evt) {
                            if (evt.getSource().equals(seeMore)) {

                                Pet thisPet = button.getPet();

                                String message = "";

                                for (Map.Entry<String, Boolean> attributes : thisPet.getAttributes().entrySet()) {
                                    if (attributes.getValue()) {
                                        String key = attributes.getKey();
                                        message += key + thisPet.getAll().get(key) + "\n";
                                    }
                                }

                                Object[] options = {"Adopt!",
                                        "Return to search"};
                                int optionChosen = JOptionPane.showOptionDialog(null, message, null, YES_NO_OPTION, PLAIN_MESSAGE, null, options, options[1]);
                                if (optionChosen == 0) {
                                    homeController.executeRec(thisPet);
                                }
                            }
                        }

                        @Override
                        public void mousePressed(MouseEvent e) {}
                        @Override
                        public void mouseReleased(MouseEvent e) {}
                        @Override
                        public void mouseEntered(MouseEvent e) {}
                        @Override
                        public void mouseExited(MouseEvent e) {}
                    }
                );
            }

            for (LabelButtonPanel someButton : buttons) {
                this.add(someButton);
            }
        }

    }
}
