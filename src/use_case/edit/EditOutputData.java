package use_case.edit;

import java.util.Map;

public class EditOutputData {

    private final Map<String, String> edited;

    private  boolean editFail;

    public EditOutputData(Map<String, String> edited, boolean editFail) {
        this.edited = edited;
        this.editFail = editFail;
    }

    public Map<String, String> getEdited() {
        return edited;
    }
}
