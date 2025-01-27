package cheapSpring.cheapSpring;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;

public class EmagRequests extends BaseAbstractRequest {

    private static HttpURLConnection con;
    private static URI uri;
    private static String EMAG_SEARCH = "https://emag.ro/search/";
    private static String userInput = null;

    public EmagRequests() throws URISyntaxException {
    }

    public static URI buildUrl() throws URISyntaxException {
        StringBuilder input = new StringBuilder();
        if (userInput != null) {
            if (!userInput.isEmpty() && userInput.contains(" ")) {
                while (userInput.contains(" ")) {
                    input = input.append(userInput.replace(" ", "%20"));
                    userInput = userInput.substring(userInput.indexOf(" ") + 1, userInput.length());
                }
            } else {
                input = input.append(userInput);
            }
            EMAG_SEARCH = EMAG_SEARCH.concat(input.toString());
        }
        EMAG_SEARCH = EMAG_SEARCH.concat("?ref=effective_search");
        return new URI(EMAG_SEARCH);
    }

    public static void makeEmagCall() throws IOException, URISyntaxException {
        userInput = "ms.w aparat";
        uri = buildUrl();
        requestSearchItem(uri,"GET");
        getResponse();
    }

}
