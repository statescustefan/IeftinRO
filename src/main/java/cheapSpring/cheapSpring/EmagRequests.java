package cheapSpring.cheapSpring;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;

public class EmagRequests {

    private static HttpURLConnection con;
    private static URI uri;
    private static String EMAG_SEARCH = "https://emag.ro/search/";
    private static String userInput= null;

    private static URI buildUrl() throws URISyntaxException {
        StringBuilder input = new StringBuilder();
        if (userInput != null) {
            if(!userInput.isEmpty() && userInput.contains(" ")) {
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

    public static void setUpConnection() throws IOException, URISyntaxException {
        uri = buildUrl();
        con = (HttpURLConnection) uri.toURL().openConnection();
        con.setRequestMethod("GET");
    }

    public static void requestSearchItem() throws IOException, URISyntaxException {
        setUpConnection();
        con.setDoOutput(true);
        System.out.println(con.toString());
        DataOutputStream out = new DataOutputStream(con.getOutputStream());

        out.flush();
        out.close();
    }

    public static void getResponse() throws IOException {
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String userInputLine;
        StringBuffer content = new StringBuffer();
        while ((userInputLine = in.readLine()) != null) {
            content.append(userInputLine);
        }
        System.out.println(content);
        in.close();
    }

    public static void makeEmagCall() throws IOException, URISyntaxException {
        userInput = "ms.w aparat";
        requestSearchItem();
        getResponse();
    }

}
