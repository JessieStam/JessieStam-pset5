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
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


/**
 * Created by Jessie on 20/05/2016.
 */
public class TagAsyncTask extends AsyncTask<String, Integer, String> {

    Context context;
    SecondActivity activity_second;

    // don't use name spaces
    final String ns = null;

    public static Document loadXMLFromString(String result) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource input_source = new InputSource(new StringReader(result));
        return builder.parse(input_source);
    }

    // constructor
    public TagAsyncTask(SecondActivity activity_second) {
        this.activity_second = activity_second;
        this.context = this.activity_second.getApplicationContext();
    }

    @Override
    protected void onPreExecute() {
        // inform user
        Toast.makeText(context, "Getting data from server", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected String doInBackground(String... params) {
        return HttpRequestHelper.downloadFromServer(params);
    }

    // Parses the contents of an entry. If it encounters a title, summary, or link tag, hands them off
    // to their respective "read" methods for processing. Otherwise, skips the tag.
    private TrackData readList(XmlPullParser parser) throws XmlPullParserException, IOException {

        parser.require(XmlPullParser.START_TAG, ns, "book");
        String title = null;
        String author = null;
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            switch (name) {
                case "title":
                    title = readTitle(parser);
                    break;
                case "author":
                    author = readAuthor(parser);
                    break;
                default:
                    skip(parser);
                    break;
            }
        }
        return new TrackData(title, author);
    }

    /*
     * Processes the book titles
     */
    private String readTitle(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "title");
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "title");
        return title;
    }

    /*
     * Processes the book authors
     */
    private String readAuthor(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "author");
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "author");
        return title;
    }

    /*
     * For the title and author, extracts their text values.
     */
    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result_xml = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result_xml = parser.getText();
            parser.nextTag();
        }
        return result_xml;
    }

    /*
     * Skip tags that are not important for this app
     */
    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }


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
                // make new parser
                JSONObject response_object = new JSONObject(readText);
                JSONObject book_title_object = response_object.getJSONObject("book");
                JSONArray titles = book_title_object.getJSONArray("title");
                // JSONArray description = book_title_object.getJSONArray("description");
                JSONArray authors = book_title_object.getJSONArray("name");


                for (int i = 0; i < titles.length(); i++) {
                    JSONObject title = titles.getJSONObject(i);
                    String title_name = title.getString("title");
                    JSONObject author = authors.getJSONObject(i);
                    String author_name = author.getString("name");

                    book_data_list.add(new TrackData(title_name, author_name));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            this.activity_second.setData(book_data_list);
        }
    }
}
