package view;

import view.SearchViewModel;
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
    private final JTextField cityField = new JTextField(15);
    private final JTextField stateField = new JTextField(15);
    private final JComboBox<String> ageField = new JComboBox<String>(new String[] {"baby", "young", "adult", "senior"});
    private final JComboBox<String> sexField = new JComboBox<String>(new String[] {"male", "female", "unknown"});
    private final JComboBox<String> sizeField = new JComboBox<String>(new String[] {"small", "medium", "large", "xlarge"});
    private final JComboBox<String> coatField = new JComboBox<String>(new String[] {"short", "medium", "long", "wire", "hairless", "curly"});
    private final JComboBox<Boolean> houseTrainedField = new JComboBox<Boolean>(new Boolean[] {true, false});
    private final JComboBox<Boolean> declawedField = new JComboBox<Boolean>(new Boolean[] {true, false});
    private final JComboBox<Boolean> goodWithKidsField = new JComboBox<Boolean>(new Boolean[] {true, false});
    private final JComboBox<Boolean> goodWithDogsField = new JComboBox<Boolean>(new Boolean[] {true, false});
    private final JComboBox<Boolean> goodWithCatsField = new JComboBox<Boolean>(new Boolean[] {true, false});
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
        LabelTextPanel stateInfo = new LabelTextPanel(
                new JLabel(SearchViewModel.STATE_LABEL), stateField);
        LabelTextPanel cityInfo = new LabelTextPanel(
                new JLabel(SearchViewModel.CITY_LABEL), cityField);
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
                // This creates an anonymous subclass of ActionListener and instantiates it.
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

        // This makes a new KeyListener implementing class, instantiates it, and
        // makes it listen to keystrokes in the usernameInputField.
        //
        // Notice how it has access to instance variables in the enclosing class!
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
                        String text = speciesField.getText() + e.getKeyChar();
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

        cityField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SearchState currentState = searchViewModel.getState();
                        String text = colorField.getText() + e.getKeyChar();
                        currentState.addRequirement(SearchViewModel.CITY_LABEL, text);
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

        stateField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SearchState currentState = searchViewModel.getState();
                        String text = colorField.getText() + e.getKeyChar();
                        currentState.addRequirement(SearchViewModel.STATE_LABEL, text);
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
                            currentState.addRequirement(SearchViewModel.STATE_LABEL, text);
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
                            currentState.addRequirement(SearchViewModel.STATE_LABEL, text);
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
                            currentState.addRequirement(SearchViewModel.STATE_LABEL, text);
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
                            currentState.addRequirement(SearchViewModel.STATE_LABEL, text);
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
                            currentState.addRequirement(SearchViewModel.STATE_LABEL, text);
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
                            currentState.addRequirement(SearchViewModel.STATE_LABEL, text);
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
                            currentState.addRequirement(SearchViewModel.STATE_LABEL, text);
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
                            currentState.addRequirement(SearchViewModel.STATE_LABEL, text);
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
                            currentState.addRequirement(SearchViewModel.STATE_LABEL, text);
                            searchViewModel.setState(currentState);
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(speciesInfo);
        this.add(colorInfo);
        this.add(goodWithKidsInfo);
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     */
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