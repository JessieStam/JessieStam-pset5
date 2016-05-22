package jstam.jessiestam_pset5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Onlinebrary
 *
 * Jessie Stam
 *
 * This application was supposed to let the user input an author and/or a book title in order to
 * look up information about the book via the Goodreads API. The user would then see a list of
 * books, which should have been clickable and in turn should have displayed a description of the
 * book. I think this app could actually have worked, if the app recognized the input stream as a
 * json file. According to the instructions on the website, this should have been possible, but
 * unfortunately it didn't. I spent a lot time trying to parse the inputstream as xml file, but
 * without any luck. I left the code I have written for the xml in comments, since there's a lot of
 * work in there. The code that's not in comments should work for a json inputstream.
 */

public class MainActivity extends AppCompatActivity {

    EditText author_input;
    EditText title_input;

    String author;
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        author_input = (EditText) findViewById(R.id.search_author);
        title_input = (EditText) findViewById(R.id.search_book);

        author = author_input.toString();
        title = title_input.toString();
    }

    /*
     * When button is clicked, move to second activity and get the data from the Goodreads website.
     */
    public void get_data(View view) {

        // move to SecondActivity
        Intent getInfo = new Intent(this, SecondActivity.class);

        // move extras to SecondActivity
        getInfo.putExtra("author", author);
        getInfo.putExtra("title", title);

        // clear the EditTexts
        author_input.getText().clear();
        title_input.getText().clear();

        startActivity(getInfo);
    }
}
