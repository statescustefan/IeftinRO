package cheapSpring.cheapSpring;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;

public class AmazonRequests extends BaseAbstractRequest{
    private static HttpURLConnection con;
    private static URI uri;
    private static String EMAG_SEARCH = "https://www.amazon.com/s?k=";
    private static String userInput = null;

    public AmazonRequests() throws URISyntaxException {
    }

    public static URI buildUrl() throws URISyntaxException {
        StringBuilder input = new StringBuilder();
        if (userInput != null) {
            if (!userInput.isEmpty() && userInput.contains(" ")) {
                while (userInput.contains(" ")) {
                    input = input.append(userInput.replace(" ", "+"));
                    userInput = userInput.substring(userInput.indexOf(" ") + 1, userInput.length());
                }
            } else {
                input = input.append(userInput);
            }
            EMAG_SEARCH = EMAG_SEARCH.concat(input.toString());
        }
        EMAG_SEARCH = EMAG_SEARCH.concat("&ref=nb_sb_noss");
        return new URI(EMAG_SEARCH);
    }

    public static void makeAmazonCall() throws IOException, URISyntaxException {
        userInput = "xiaomi";
        uri = buildUrl();
        requestSearchItem(uri, "GET");
        getResponse();
    }


}
