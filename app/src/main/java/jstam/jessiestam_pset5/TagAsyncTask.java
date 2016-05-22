package jstam.jessiestam_pset5;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Xml;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * TagAsyncTask.java
 *
 * Jessie Stam
 *
 * This activity processes the data and creates TrackData objects for each Listview item.
 */
public class TagAsyncTask extends AsyncTask<String, Integer, String> {

    Context context;
    SecondActivity activity_second;

    // MEANT FOR XML. name spaces aren't used
    // final String ns = null;

    /**
     * MEANT FOR XML. Transforms the HttpRequestHelper datastring back into xml
     */
//    public static Document loadXMLFromString(String result) throws Exception {
//        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder builder = factory.newDocumentBuilder();
//        InputSource input_source = new InputSource(new StringReader(result));
//        return builder.parse(input_source);
//    }

    // constructor
    public TagAsyncTask(SecondActivity activity_second) {
        this.activity_second = activity_second;
        this.context = this.activity_second.getApplicationContext();
    }

    // lets the user know the data is being downloaded
    @Override
    protected void onPreExecute() {
        // inform user
        Toast.makeText(context, "Getting data from server", Toast.LENGTH_SHORT).show();
    }

    // download the data
    @Override
    protected String doInBackground(String... params) {
        return HttpRequestHelper.downloadFromServer(params);
    }

    /*
     * MEANT FOR XML. Parses the contents of book tags. When title or author is found, read them
     * using methods below.
     */
//    private TrackData readList(XmlPullParser parser) throws XmlPullParserException, IOException {
//
//        parser.require(XmlPullParser.START_TAG, ns, "book");
//        String title = null;
//        String author = null;
//        while (parser.next() != XmlPullParser.END_TAG) {
//            if (parser.getEventType() != XmlPullParser.START_TAG) {
//                continue;
//            }
//            String name = parser.getName();
//            switch (name) {
//                case "title":
//                    title = readTitle(parser);
//                    break;
//                case "author":
//                    author = readAuthor(parser);
//                    break;
//                default:
//                    skip(parser);
//                    break;
//            }
//        }
//        return new TrackData(title, author);
//    }

    /*
     * MEANT FOR XML. Processes the book titles.
     */
//    private String readTitle(XmlPullParser parser) throws IOException, XmlPullParserException {
//        parser.require(XmlPullParser.START_TAG, ns, "title");
//        String title = readText(parser);
//        parser.require(XmlPullParser.END_TAG, ns, "title");
//        return title;
//    }

    /*
     * MEANT FOR XML. Processes the book authors.
     */
//    private String readAuthor(XmlPullParser parser) throws IOException, XmlPullParserException {
//        parser.require(XmlPullParser.START_TAG, ns, "author");
//        String title = readText(parser);
//        parser.require(XmlPullParser.END_TAG, ns, "author");
//        return title;
//    }

    /*
     * MEANT FOR XML. For the title and author, extract their text values.
     */
//    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
//        String result_xml = "";
//        if (parser.next() == XmlPullParser.TEXT) {
//            result_xml = parser.getText();
//            parser.nextTag();
//        }
//        return result_xml;
//    }

    /*
     * MEANT FOR XML. Skip tags that are not important for this app
     */
//    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
//        if (parser.getEventType() != XmlPullParser.START_TAG) {
//            throw new IllegalStateException();
//        }
//        int depth = 1;
//        while (depth != 0) {
//            switch (parser.next()) {
//                case XmlPullParser.END_TAG:
//                    depth--;
//                    break;
//                case XmlPullParser.START_TAG:
//                    depth++;
//                    break;
//            }
//        }
//    }

    // read the data, create TrackData objects and put them in a list
    @Override
    protected void onPostExecute(String readText) {
        super.onPostExecute(readText);

        // if nothing was found, inform user
        if (readText.length() == 0) {
            Toast.makeText(context, "No data was found", Toast.LENGTH_SHORT).show();
        }
        // else parse json
        else {
            ArrayList<TrackData> book_data_list = new ArrayList<>();
            Toast.makeText(context, "Data was found", Toast.LENGTH_SHORT).show();

            try {
                // create new JSONObject
                JSONObject response_object = new JSONObject(readText);
                JSONObject book_title_object = response_object.getJSONObject("book");

                // get titles and authors from JSONObject
                JSONArray titles = book_title_object.getJSONArray("title");
                JSONArray authors = book_title_object.getJSONArray("name");

                // iterate over JSONArray list, and put titles and authors in TrackData object list
                for (int i = 0; i < titles.length(); i++) {
                    JSONObject title = titles.getJSONObject(i);
                    String title_name = title.getString("title");
                    JSONObject author = authors.getJSONObject(i);
                    String author_name = author.getString("name");

                    // add new TrackData object to Trackdata object list
                    book_data_list.add(new TrackData(title_name, author_name));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            // set the data to the TrackData list
            this.activity_second.setData(book_data_list);
        }
    }
}
