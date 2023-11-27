package view;

import interface_adaptor.search.SearchController;
import interface_adaptor.search.SearchState;
import interface_adaptor.search.SearchViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SearchView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "search";

    private final SearchViewModel searchViewModel;
    private final JTextField speciesField = new JTextField(15);
    private final JTextField breedsField = new JTextField(15);
    private final JTextField colorField = new JTextField(15);
    private final JTextField phoneField = new JTextField(15);
    private final JTextField emailField = new JTextField(15);
    private final JComboBox<String> ageField = new JComboBox<String>(new String[] {"Choose", "Baby", "Young", "Adult", "Senior"});
    private final JComboBox<String> sexField = new JComboBox<String>(new String[] {"Choose", "Male", "Female", "Unknown"});
    private final JComboBox<String> sizeField = new JComboBox<String>(new String[] {"Choose", "Small", "Medium", "Large", "Xlarge"});
    private final JComboBox<String> coatField = new JComboBox<String>(new String[] {"Choose", "Short", "Medium", "Long", "Wire", "Hairless", "Curly"});
    private final JComboBox<String> houseTrainedField = new JComboBox<String>(new String[] {"Choose", "true", "false"});
    private final JComboBox<String> declawedField = new JComboBox<String>(new String[] {"Choose", "true", "false"});
    private final JComboBox<String> goodWithKidsField = new JComboBox<String>(new String[] {"Choose", "true", "false"});
    private final JComboBox<String> goodWithDogsField = new JComboBox<String>(new String[] {"Choose", "true", "false"});
    private final JComboBox<String> goodWithCatsField = new JComboBox<String>(new String[] {"Choose", "true", "false"});
    private final SearchController searchController;

    private final JButton search;


    public SearchView(SearchController controller, SearchViewModel searchViewModel) {

        this.searchController = controller;
        this.searchViewModel = searchViewModel;
        searchViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(SearchViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel speciesInfo = new LabelTextPanel(
                new JLabel(SearchViewModel.SPECIES_LABEL), speciesField);
        LabelTextPanel breedInfo = new LabelTextPanel(
                new JLabel(SearchViewModel.BREED_LABEL), breedsField);
        LabelTextPanel colorInfo = new LabelTextPanel(
                new JLabel(SearchViewModel.COLOR_LABEL), colorField);
        LabelTextPanel phoneInfo = new LabelTextPanel(
                new JLabel(SearchViewModel.PHONE_LABEL), phoneField);
        LabelTextPanel emailInfo = new LabelTextPanel(
                new JLabel(SearchViewModel.EMAIL_LABEL), emailField);
        LabelDropdownPanel ageInfo = new LabelDropdownPanel(
                new JLabel(SearchViewModel.AGE_LABEL), ageField);
        LabelDropdownPanel sexInfo = new LabelDropdownPanel(
                new JLabel(SearchViewModel.SEX_LABEL), sexField);
        LabelDropdownPanel sizeInfo = new LabelDropdownPanel(
                new JLabel(SearchViewModel.SIZE_LABEL), sizeField);
        LabelDropdownPanel coatInfo = new LabelDropdownPanel(
                new JLabel(SearchViewModel.COAT_LABEL), coatField);
        LabelDropdownPanel houseTrainedInfo = new LabelDropdownPanel(
                new JLabel(SearchViewModel.HOUSE_TRAINED_LABEL), houseTrainedField);
        LabelDropdownPanel declawedInfo = new LabelDropdownPanel(
                new JLabel(SearchViewModel.DECLAWED_LABEL), declawedField);
        LabelDropdownPanel goodWithDogsInfo = new LabelDropdownPanel(
                new JLabel(SearchViewModel.GOOD_WITH_DOGS_LABEL), goodWithDogsField);
        LabelDropdownPanel goodWithCatsInfo = new LabelDropdownPanel(
                new JLabel(SearchViewModel.GOOD_WITH_CATS_LABEL), goodWithCatsField);
        LabelDropdownPanel goodWithKidsInfo = new LabelDropdownPanel(
                new JLabel(SearchViewModel.GOOD_WITH_KIDS_LABEL), goodWithKidsField);

        JPanel buttons = new JPanel();
        search = new JButton(searchViewModel.SEARCH_BUTTON_LABEL);
        buttons.add(search);

        search.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(search)) {

                            SearchState currentState = searchViewModel.getState();
                            searchController.execute(
                                    currentState.getRequirements()
                            );
                        }
                    }
                }
        );

        speciesField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SearchState currentState = searchViewModel.getState();
                        String text = speciesField.getText() + e.getKeyChar();
                        currentState.addRequirement(SearchViewModel.SPECIES_LABEL, text);
                        searchViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        breedsField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SearchState currentState = searchViewModel.getState();
                        String text = breedsField.getText() + e.getKeyChar();
                        currentState.addRequirement(SearchViewModel.BREED_LABEL, text);
                        searchViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        colorField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SearchState currentState = searchViewModel.getState();
                        String text = colorField.getText() + e.getKeyChar();
                        currentState.addRequirement(SearchViewModel.COLOR_LABEL, text);
                        searchViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                }
        );

        phoneField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SearchState currentState = searchViewModel.getState();
                        String text = phoneField.getText() + e.getKeyChar();
                        currentState.addRequirement(SearchViewModel.PHONE_LABEL, text);
                        searchViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                }
        );

        emailField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SearchState currentState = searchViewModel.getState();
                        String text = emailField.getText() + e.getKeyChar();
                        currentState.addRequirement(SearchViewModel.EMAIL_LABEL, text);
                        searchViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                }
        );

        ageField.addItemListener(
                new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if (e.getStateChange() == ItemEvent.SELECTED) {
                            SearchState currentState = searchViewModel.getState();
                            String text = ageField.getSelectedItem().toString();
                            currentState.addRequirement(SearchViewModel.AGE_LABEL, text);
                            searchViewModel.setState(currentState);
                        }
                    }
                }
        );

        sexField.addItemListener(
                new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if (e.getStateChange() == ItemEvent.SELECTED) {
                            SearchState currentState = searchViewModel.getState();
                            String text = sexField.getSelectedItem().toString();
                            currentState.addRequirement(SearchViewModel.SEX_LABEL, text);
                            searchViewModel.setState(currentState);
                        }
                    }
                }
        );

        sizeField.addItemListener(
                new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if (e.getStateChange() == ItemEvent.SELECTED) {
                            SearchState currentState = searchViewModel.getState();
                            String text = sizeField.getSelectedItem().toString();
                            currentState.addRequirement(SearchViewModel.SIZE_LABEL, text);
                            searchViewModel.setState(currentState);
                        }
                    }
                }
        );

        coatField.addItemListener(
                new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if (e.getStateChange() == ItemEvent.SELECTED) {
                            SearchState currentState = searchViewModel.getState();
                            String text = coatField.getSelectedItem().toString();
                            currentState.addRequirement(SearchViewModel.COAT_LABEL, text);
                            searchViewModel.setState(currentState);
                        }
                    }
                }
        );

        houseTrainedField.addItemListener(
                new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if (e.getStateChange() == ItemEvent.SELECTED) {
                            SearchState currentState = searchViewModel.getState();
                            String text = houseTrainedField.getSelectedItem().toString();
                            currentState.addRequirement(SearchViewModel.HOUSE_TRAINED_LABEL, text);
                            searchViewModel.setState(currentState);
                        }
                    }
                }
        );

        declawedField.addItemListener(
                new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if (e.getStateChange() == ItemEvent.SELECTED) {
                            SearchState currentState = searchViewModel.getState();
                            String text = declawedField.getSelectedItem().toString();
                            currentState.addRequirement(SearchViewModel.DECLAWED_LABEL, text);
                            searchViewModel.setState(currentState);
                        }
                    }
                }
        );

        goodWithDogsField.addItemListener(
                new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if (e.getStateChange() == ItemEvent.SELECTED) {
                            SearchState currentState = searchViewModel.getState();
                            String text = goodWithDogsField.getSelectedItem().toString();
                            currentState.addRequirement(SearchViewModel.GOOD_WITH_DOGS_LABEL, text);
                            searchViewModel.setState(currentState);
                        }
                    }
                }
        );

        goodWithCatsField.addItemListener(
                new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if (e.getStateChange() == ItemEvent.SELECTED) {
                            SearchState currentState = searchViewModel.getState();
                            String text = goodWithCatsField.getSelectedItem().toString();
                            currentState.addRequirement(SearchViewModel.GOOD_WITH_CATS_LABEL, text);
                            searchViewModel.setState(currentState);
                        }
                    }
                }
        );

        goodWithKidsField.addItemListener(
                new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if (e.getStateChange() == ItemEvent.SELECTED) {
                            SearchState currentState = searchViewModel.getState();
                            String text = goodWithKidsField.getSelectedItem().toString();
                            currentState.addRequirement(SearchViewModel.GOOD_WITH_KIDS_LABEL, text);
                            searchViewModel.setState(currentState);
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(speciesInfo);
        this.add(breedInfo);
        this.add(colorInfo);
        this.add(phoneInfo);
        this.add(emailInfo);
        this.add(ageInfo);
        this.add(sexInfo);
        this.add(coatInfo);
        this.add(sizeInfo);
        this.add(houseTrainedInfo);
        this.add(declawedInfo);
        this.add(goodWithCatsInfo);
        this.add(goodWithDogsInfo);
        this.add(goodWithKidsInfo);
        this.add(buttons);
    }

    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showConfirmDialog(this, "This is not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SearchState state = (SearchState) evt.getNewValue();

        if (state.getRequirementsError() != null) {
            JOptionPane.showMessageDialog(this, state.getRequirementsError());
        }
    }
}