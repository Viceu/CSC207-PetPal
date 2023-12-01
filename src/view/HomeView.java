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
