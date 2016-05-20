import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Jessie on 20/05/2016.
 */
public class HttpRequestHelper {

    // string for URL(hier komt de zoekopdracht achter)
    private static final String url1 = "https://www.goodreads.com/search.xml?key=ZZh7kngcTINCg4tjAG0GDQ&q=";

    // method to download from server
    protected static synchronized String downloadFromServer(String... params) {

        // declare return string result
        String result = "''";

        // get chosen tag from argument
        String chosenTag = params[0];

        // complete string for URL
        String complete_URL_string = url1 + chosenTag;

        // turn string into URL
        URL url = null;

        try {
            url = new URL(complete_URL_string);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // make the connection
        HttpURLConnection connection;
        if (url != null) {
            try {
                connection = (HttpURLConnection) url.openConnection();

                // open connection, set request method
                connection.setRequestMethod("GET");

                // get response code
                Integer response_code = connection.getResponseCode();

                // if 200-300, read inputstream
                if (200 <= response_code && response_code <= 299){
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        result = result + line;
                    }
                }
                else {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                    // communicate error
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // return result
        return result;
    }
}
