package viviansong;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchInputData {
    // changed data type of requirements to hashmap to store category and category value
    final private Map<String, String> requirements;


    public SearchInputData(Map<String, String> requirements) {
        this.requirements = requirements;
    }

    Map<String, String> getRequirements() {
        return requirements;
    }
}
