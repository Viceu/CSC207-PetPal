// code template source: https://youtu.be/6c0r6fd0uso?si=D40x0_EYhojOPRLb

package api;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthJSONAccessTokenResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class GetToken {

    // method to get API access token
    public static String accessToken() throws OAuthSystemException, OAuthProblemException {

        String clientID = Credentials.getClientID();
        String clientSecret = Credentials.getClientSecret();
        String tokenURL = "https://api.petfinder.com/v2/oauth2/token";

        String finalToken;
        String bearerValue;
        String encodeValue = getBase64Encoded(clientID, clientSecret);

        // create client object
        OAuthClient client = new OAuthClient(new URLConnectionClient());

        // create access, request body
        OAuthClientRequest request = OAuthClientRequest.tokenLocation(tokenURL)
                .setGrantType(GrantType.CLIENT_CREDENTIALS)
                .buildBodyMessage();

        // add header to request
        request.addHeader("Authorization", "Basic " + encodeValue);

        // get response
        OAuthJSONAccessTokenResponse oAuthResponse = client.accessToken(request, OAuth.HttpMethod.POST, OAuthJSONAccessTokenResponse.class);

        // get token value from response
        finalToken = oAuthResponse.getAccessToken();
        bearerValue = "Bearer " + finalToken;

        return bearerValue;

    }

    // method to put input value
    public static String getBase64Encoded(String id, String password){
        return Base64.getEncoder().encodeToString((id + ":" + password).getBytes(StandardCharsets.UTF_8));
    }

}