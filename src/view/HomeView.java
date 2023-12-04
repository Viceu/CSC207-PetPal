package view;

import entities.Pet;
import entities.User;
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
    public final String viewName = "home";
    private final HomeViewModel homeViewModel;
    private final HomeController homeController;

    JLabel username;
    JLabel bio;
    JButton logOut;
    JButton edit;
    private JButton search;

    public HomeView(HomeController controller, HomeViewModel homeViewModel) {

        this.homeController = controller;
        this.homeViewModel = homeViewModel;
        this.homeViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(HomeViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showConfirmDialog(this, "This is not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        HomeState state = (HomeState) evt.getNewValue();

        this.removeAll();

        JLabel usernameInfo = new JLabel("Currently logged in: "+ homeViewModel.getUsername());
        bio = new JLabel(homeViewModel.getUserBio());
        bio.setText(state.getBio());

        JPanel buttons = new JPanel();
        search = new JButton(HomeViewModel.SEARCH_BUTTON_LABEL);
        buttons.add(search);
        search.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        homeController.execute("search", null, null);
                    }
                }
        );

        edit = new JButton(HomeViewModel.EDIT_BUTTON_LABEL);
        buttons.add(edit);
        edit.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        homeController.execute("edit", homeViewModel.getUser(), null);
                    }
                }
        );

        logOut = new JButton(HomeViewModel.LOGOUT_BUTTON_LABEL);
        buttons.add(logOut);
        logOut.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        homeController.execute("logOut", null, null);
                    }
                }
        );

        this.add(usernameInfo);
        this.add(bio);
        this.add(buttons);

        if (state.getFetchError() != null) {
            JOptionPane.showMessageDialog(this, state.getFetchError());
        }

        if (this.homeViewModel.getState().getPets() == null) {
            this.add(new JLabel("There are currently no pets to adopt, please come back later."));
        }
        else {
            ArrayList<LabelButtonPanel> petButtons = new ArrayList<LabelButtonPanel>();
            for (Map.Entry<Integer, Pet> entry : this.homeViewModel.getState().getPets().entrySet()) {
                JButton seeMore = new JButton("See More");
                LabelButtonPanel newButton = new LabelButtonPanel(
                        new JLabel(entry.getValue().getName() + ": " + entry.getValue().getSpecies()),
                        seeMore, entry.getValue());
                petButtons.add(newButton);

                seeMore.addMouseListener(
                        new MouseListener() {
                            public void mouseClicked(MouseEvent evt) {
                                Pet thisPet = newButton.getPet();

                                String message = "";

                                for (Map.Entry<String, Boolean> attributes : thisPet.getAttributes().entrySet()) {
                                    if (attributes.getValue()) {
                                        String key = attributes.getKey();
                                        message += key + thisPet.getAll().get(key) + "\n";
                                    }
                                }

                                Object[] options = {"Adopt!",
                                        "Return to search"};
                                int optionChosen = JOptionPane.showOptionDialog(null, message,
                                        null, YES_NO_OPTION, PLAIN_MESSAGE, null, options, options[1]);
                                if (optionChosen == 0) {
                                    homeController.execute("adopt", null, thisPet);
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

            for (LabelButtonPanel someButton : petButtons) {
                this.add(someButton);
            }
        }

    }
}
