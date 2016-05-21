package jstam.jessiestam_pset5;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

import TrackData.TrackData;

/**
 * Created by Jessie on 21/05/2016.
 */
public class SecondActivity extends MainActivity {

    String author;
    String title;
    ListView result_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Bundle extraInfo = getIntent().getExtras();

        author = extraInfo.getString("author");
        title = extraInfo.getString("title");
        result_list = (ListView) findViewById(R.id.result_list);

        // print hier nog je tekstje voor boven de resultaten
    }

    // set data - called from async when ready?
    public void setData(ArrayList<TrackData> track_data) {

    }

}
