package jstam.jessiestam_pset5;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;


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

        // make asynctask get the data
        TagAsyncTask asyncTask = new TagAsyncTask(this);
        asyncTask.execute(author, title);

        // print hier nog je tekstje voor boven de resultaten
    }

    // set data - called from async when ready?
    public void setData(ArrayList<TrackData> book_data_list) {

        BookAdapter adapter = new BookAdapter(this, book_data_list);
        result_list.setAdapter(adapter);

        // print data to the screen?
        adapter.notifyDataSetChanged();
    }

}
