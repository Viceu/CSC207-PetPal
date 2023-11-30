package use_case.edit;

import java.util.Map;

public class EditInputData {
    private final Map<String, String> edits;

    public EditInputData(Map<String, String> edits) {
        this.edits = edits;
    }

    public Map<String, String> getEdits() {
        return edits;
    }
}
