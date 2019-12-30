package cheapSpring.cheapSpring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.xml.ws.http.HTTPException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Map;

@SpringBootApplication
public class CheapSpringApplication {

	private static HttpURLConnection con;
	private static URL url;
	private static String EMAG_SEARCH = "https://emag.ro/search";


	private static void buildUrl(String input) {
		if (input != null) {
			EMAG_SEARCH = EMAG_SEARCH.concat("/" + input);
		}
		EMAG_SEARCH = EMAG_SEARCH.concat("?ref=effective_search");
	}

	public static void setConnection() throws HTTPException, IOException {

		url = new URL(EMAG_SEARCH);
		con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");

	}

	private static String getParamsString(Map<String, String> params)
			throws UnsupportedEncodingException {
		StringBuilder result = new StringBuilder();

		for (Map.Entry<String, String> entry : params.entrySet()) {
			result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
			result.append("=");
			result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
			result.append("&");
		}

		String resultString = result.toString();
		return resultString.length() > 0
				? resultString.substring(0, resultString.length() - 1)
				: resultString;
	}

	public static void requestSearchItem() throws IOException {
		con.setDoOutput(true);

		System.out.println(con.toString());
		DataOutputStream out = new DataOutputStream(con.getOutputStream());

		out.flush();
		out.close();
	}

	public static void getResponse() throws IOException {
		BufferedReader in = new BufferedReader(
				new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer content = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			content.append(inputLine);
		}
		System.out.println(content);
		in.close();
	}

	public static void main(String[] args) {
		SpringApplication.run(CheapSpringApplication.class, args);
		buildUrl("xiaomi");
		try {
			setConnection();
			requestSearchItem();
			getResponse();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			System.out.println("Let's inspect the beans provided by Spring Boot:");

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				System.out.println(beanName);
			}

		};
	}

}
