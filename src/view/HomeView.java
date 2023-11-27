package view;

import interface_adaptor.home.HomeController;
import interface_adaptor.home.HomeState;
import interface_adaptor.home.HomeViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class HomeView extends JPanel implements ActionListener, PropertyChangeListener {
    // TODO: usage in Main:
    //  LoggedInView loggedInView = new LoggedInView(loggedInViewModel);
    //  views.add(loggedInView, loggedInView.viewName);
    public final String viewName = "search";

    private final HomeViewModel homeViewModel;
    private final HomeController homeController;

    JLabel username;
    final JButton logOut;
    private final JButton seeMore = new JButton("See more");


    public HomeView(HomeController controller, HomeViewModel homeViewModel) {

        this.homeController = controller;
        this.homeViewModel = homeViewModel;
        this.homeViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(HomeViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel usernameInfo = new JLabel("Currently logged in: ");
        username = new JLabel();

        JPanel buttons = new JPanel();
        logOut = new JButton(HomeViewModel.LOGOUT_BUTTON_LABEL);
        buttons.add(logOut);

        logOut.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(usernameInfo);
        this.add(username);
        this.add(buttons);

    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        HomeState state = (HomeState) evt.getNewValue();
        username.setText(state.getUsername());
    }
}
