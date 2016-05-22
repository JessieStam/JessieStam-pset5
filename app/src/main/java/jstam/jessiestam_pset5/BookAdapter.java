package jstam.jessiestam_pset5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * BookAdapter.java
 *
 * Jessie Stam
 *
 * An adapter that help putting the downloaded data into a listview.
 */

public class BookAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<TrackData> books_list;

    public BookAdapter (Context context, ArrayList<TrackData> data) {
        this.context = context;
        this.books_list = data;
    }

    /**
     * Get length of books list.
     */
    @Override
    public int getCount() {return this.books_list.size();}

    /**
     * Get item at first position.
     */
    @Override
    public Object getItem(int arg0) {return null;}

    /**
     * Get item position.
     */
    @Override
    public long getItemId(int pos) {return pos;}

    /**
     * Gets information from TrackData object on position, puts text into view.
     */
    @Override
    public View getView(int pos, View view, ViewGroup parent) {

        // check if view is occupied, inflate view
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.row_layout, parent, false);
        }
        // get TrackData object for position
        TrackData book = books_list.get(pos);

        // find textviews for author and title
        TextView author = (TextView) view.findViewById(R.id.title);
        TextView title = (TextView) view.findViewById(R.id.author);

        // put text into view and return view
        title.setText(book.getTitle());
        author.setText(book.getAuthor());
        return view;
    }
}
