package jstam.jessiestam_pset5;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * SecondActivity.java
 *
 * Jessie Stam
 *
 * This activity gets the data from the Goodreads site and puts it into the listview.
 */
public class SecondActivity extends MainActivity {

    String author;
    String title;
    ListView result_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // get extras from MainActivity
        Bundle extraInfo = getIntent().getExtras();

        author = extraInfo.getString("author");
        title = extraInfo.getString("title");
        result_list = (ListView) findViewById(R.id.result_list);

        // make TagAsyncTask get the data
        TagAsyncTask asyncTask = new TagAsyncTask(this);
        asyncTask.execute(author, title);
    }

    // set data, is called from TagAsyncTask when done getting data
    public void setData(ArrayList<TrackData> book_data_list) {

        // create a new adapter and fill in the listview
        BookAdapter adapter = new BookAdapter(this, book_data_list);
        result_list.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

}
