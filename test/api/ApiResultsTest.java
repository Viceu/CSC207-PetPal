package api;

import org.json.JSONObject;
import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import java.util.Map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static api.ApiResults.getAnimals;
import static org.junit.jupiter.api.Assertions.*;


class ApiResultTest {
        @Test
        void successFilterTest() {
                HashMap<String, String> exParam = new HashMap<>();
                exParam.put("age", "young");
                exParam.put("name", "Serenity");
                List<String> exResults = getAnimals(exParam);

                assertFalse(exResults.isEmpty());

                List<String> ages = new ArrayList<>();
                for (int i = 0; i < exResults.size(); i++) {
                        JSONObject petJson = new JSONObject(exResults.get(i));
                        String type = (String) petJson.get("age");
                        ages.add(type);
                }

                List<String> expectedList = new ArrayList<>();
                for (int i = 0; i < ages.size(); i++) {
                        expectedList.add("Young");
                }

                assertFalse(ages.isEmpty());
                assertEquals(ages, expectedList);
        }

        // Test JSONArray can be converted to a Java HashMap of desired data type for key and value
        // This is to test functionality of method in ApiPetDAO
        @Test
        void mapAttributeConvertTest() {
                JSONObject jo = new JSONObject();
                jo.put("declawed", true);
                jo.put("friendly", false);
                jo.put("others", JSONObject.NULL);
                JSONArray keys = jo.names();

                Map<String, Boolean> environment = new HashMap<>();
                for (int i = 0; i < keys.length (); i++) {
                    String key = keys.getString (i);
                    if(jo.get(key)==JSONObject.NULL) {
                        environment.put(key, null);
                    }
                    else {
                        Boolean value = (Boolean) jo.get(key);
                        environment.put(key, value);
                    }
                }

                Map<String, Boolean> expectedEnv = new HashMap<>();
                expectedEnv.put("declawed", true);
                expectedEnv.put("friendly", false);
                expectedEnv.put("others", null);

                assertEquals(environment, expectedEnv);
        }

}