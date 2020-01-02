package cheapSpring.cheapSpring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;

public abstract class BaseAbstractRequest {

    public static HttpURLConnection con;
    public static URI uri;
    private static final Logger logger = LoggerFactory.getLogger(BaseAbstractRequest.class);

    static {
        try {
            uri = new URI("www.dummy.com");
        } catch (URISyntaxException e) {
            logger.info("cannot instantiate URI from " + BaseAbstractRequest.class);
        }
    }

    public static String EMAG_SEARCH = "https://emag.ro/search/";
    public static String userInput = null;

    public BaseAbstractRequest() throws URISyntaxException {
    }

    public static void setUpConnection(URI uri, String requestMethod) throws IOException, URISyntaxException {
        con = (HttpURLConnection) uri.toURL().openConnection();
        con.setRequestMethod(requestMethod);
    }

    public static void requestSearchItem(URI uri,String requestMethod) throws IOException, URISyntaxException {
        setUpConnection(uri, requestMethod);
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
}
