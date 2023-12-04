package api;

import okhttp3.*;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.*;
import static java.lang.Math.min;

public class ApiResults {

    public static List<String> getAnimals(Map<String, String> params) throws JSONException {

        // first get live API access token
        String API_TOKEN;
        try {
            API_TOKEN = GetToken.accessToken();
        } catch (OAuthSystemException | OAuthProblemException e) {
            throw new RuntimeException(e);
        }

        // build URL that searches for animals with inputted requirements
        StringBuilder paramURL = new StringBuilder("https://api.petfinder.com/v2/animals?");

        if(!params.isEmpty()) {
            // take first requirement and add manually
            HashMap.Entry<String, String> firstEntry = params.entrySet().iterator().next();
            String firstKey = firstEntry.getKey();
            paramURL.append(firstKey).append("=").append(firstEntry.getValue());
            // rest of the requirements need a "&" before inserting to URL
            for (String param : params.keySet()) {
                if (!param.equals(firstKey)) {
                    paramURL.append("&").append(param).append("=").append(params.get(param));
                }
            }
        }

        // initialize request through okhttp3 and input values of URL and token
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(String.valueOf(paramURL))
                .method("GET", null)
                .addHeader("Authorization", API_TOKEN)
                .build();

        // try to get response through request call
        try {
            Response response = client.newCall(request).execute();
            // store response body as JSONObject
            assert response.body() != null;
            JSONObject responseBody = new JSONObject(response.body().string());

            // transform JSONObject result to ArrayList
            List<String> animalList = new ArrayList<String>();
            // first get the array of "animals"
            // (there should be another array in response object "pignation" for system info)
            JSONArray animalArray = responseBody.getJSONArray("animals");

            // if there are results (there exists animals that match requirements)
            // keep first 5 results for use (present to user)
            // or keep all results if total results less than 5
            if (!animalArray.isEmpty()) {
                for (int i = 0; i < min(animalArray.length(), 100); i++) {
                    animalList.add(String.valueOf(animalArray.getJSONObject(i)));
                }
            }

            // successful is Response code() is 200
            if (response.isSuccessful()) {
                // return results (now in List of Strings)
                return animalList;
            } else {
                throw new RuntimeException(responseBody.getString("message"));
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getOrg(String orgID) throws JSONException {


        // first get live API access token
        String API_TOKEN;
        try {
            API_TOKEN = GetToken.accessToken();
        } catch (OAuthSystemException | OAuthProblemException e) {
            throw new RuntimeException(e);
        }


        // build URL that searches for animals with inputted requirements
        String orgURL = "https://api.petfinder.com/v2/organizations/" + orgID;


        // initialize request through okhttp3 and input values of URL and token
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(orgURL)
                .method("GET", null)
                .addHeader("Authorization", API_TOKEN)
                .build();


        // try to get response through request call
        try {
            Response response = client.newCall(request).execute();
            // store response body as JSONObject
            assert response.body() != null;
            JSONObject responseBody = new JSONObject(response.body().string());


            // transform JSONObject result to ArrayList
            String orgInfo = null;
            JSONObject orgObject = responseBody.getJSONObject("organization");


            // if there are results (there exists animals that match requirements)
            // keep first 5 results for use (present to user)
            // or keep all results if total results less than 5
            if (!orgObject.equals(JSONObject.NULL)) {
                orgInfo = String.valueOf(orgObject);
            }


            // successful is Response code() is 200
            if (response.isSuccessful()) {
                // return results (now in List of Strings)
                return orgInfo;
            } else {
                throw new RuntimeException(responseBody.getString("message"));
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

}