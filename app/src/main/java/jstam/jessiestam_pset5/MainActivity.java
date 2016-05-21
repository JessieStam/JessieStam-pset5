package jstam.jessiestam_pset5;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import TrackData.TrackData;

public class MainActivity extends AppCompatActivity {

    EditText author_input;
    EditText title_input;

    String author;
    String title;

    // add listview to second activity
    //ListView books_listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        author_input = (EditText) findViewById(R.id.search_author);
        title_input = (EditText) findViewById(R.id.search_book);

        author = author_input.toString();
        title = title_input.toString();
    }

    public void get_data(View view) {

        // make asynctask get the data
        TagAsyncTask asyncTask = new TagAsyncTask(this);
        asyncTask.execute(author, title);

        // move to SecondActivity
        Intent getInfo = new Intent(this, SecondActivity.class);

        getInfo.putExtra("author", author);
        getInfo.putExtra("title", title);

        startActivity(getInfo);
    }
}
