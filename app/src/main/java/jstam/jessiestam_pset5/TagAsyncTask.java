package jstam.jessiestam_pset5;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Xml;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;

import java.net.HttpURLConnection;
import java.util.ArrayList;

import TrackData.TrackData;
import jstam.jessiestam_pset5.MainActivity;

/**
 * Created by Jessie on 20/05/2016.
 */
public class TagAsyncTask extends AsyncTask<String, Integer, String> {

    Context context;
    MainActivity activity;
    SecondActivity activity_second;

   // XmlPullParser parser = Xml.newPullParser();

    // constructor
    public TagAsyncTask(MainActivity activity) {
        this.activity = activity;
        this.context = this.activity.getApplicationContext();
    }


    @Override
    protected void onPreExecute() {
        // inform user
        Toast.makeText(context, "Getting data from server", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected String doInBackground(String... params) {
        // het result hiervan wordt automatisch doorgegeven aan on post execute
        return HttpRequestHelper.downloadFromServer(params);
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        // if nothing was found, inform user
        if (result.length() == 0) {
            Toast.makeText(context, "No data was found", Toast.LENGTH_SHORT).show();
        }
        // else parse json
        else {
            ArrayList<TrackData> track_data = new ArrayList<>();
            try {
                JSONObject response_object = new JSONObject(result);
                JSONObject book_title_object = response_object.getJSONObject("book");
                JSONArray titles = book_title_object.getJSONArray("title");
                // JSONArray description = book_title_object.getJSONArray("description");
                JSONArray authors = book_title_object.getJSONArray("name");

                for (int i = 0; i < titles.length(); i++) {
                    JSONObject title = titles.getJSONObject(i);
                    String title_name = title.getString("title");
                    JSONObject author = authors.getJSONObject(i);
                    String author_name = author.getString("name");

                    track_data.add(new TrackData(title_name, author_name));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            this.activity_second.setData(track_data);
        }
    }
}
