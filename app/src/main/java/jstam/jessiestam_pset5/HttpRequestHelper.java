package jstam.jessiestam_pset5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * HttpRequestHelper.java
 *
 * Jessie Stam
 *
 * This activity forms the URL that is used to get the data and connects to the internet.
 */
public class HttpRequestHelper {

    //static String develop_key = "ZZh7kngcTINCg4tjAG0GDQ";

    // strings for URL
    private static final String url1 = "https://www.goodreads.com/book/title.json?author=";
    private static final String url2 = "&key=ZZh7kngcTINCg4tjAG0GDQ&title=";

    /*
     * Downloads the information from the server and puts it into a string object.
     */
    protected static synchronized String downloadFromServer(String... params) {

        // declare return string result
        String result = "";

        // get chosen tag from argument
        String author = params[0];
        String title = params[1];

        // complete string for URL
        String complete_URL_string = url1 + author + url2 + title;

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
                // when error occures, read errorstream
                else {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // return the result string
        return result;
    }
}
