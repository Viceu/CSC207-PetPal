package interface_adaptor.search;

import javax.swing.*;
import java.util.*;

public class SearchState {

    private String failMessage = null;
    private HashMap<String, String> requirements = new HashMap<String, String>();

    public Map<String, String> getRequirements() {
        return requirements;
    }

    public void addRequirement(String req, String input) {
        requirements.put(req, input);
    }
    public void setSearchFailMessage(String errorMessage) {
        failMessage = errorMessage;
    }

    public Object getRequirementsError() {
        return failMessage;
    }
}
