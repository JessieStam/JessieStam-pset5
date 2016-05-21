package jstam.jessiestam_pset5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jessie on 21/05/2016.
 */
public class BookAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<TrackData> books;

    public BookAdapter (Context context, ArrayList<TrackData> data) {

        this.context = context;
        this.books = data;
    }

    @Override
    public int getCount() {return this.books.size();}

    @Override
    public Object getItem(int arg0) {return null;}

    @Override
    public long getItemId(int pos) {return pos;}

    @Override
    public View getView(int pos, View view, ViewGroup parent) {

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.row_layout, parent, false);
        }
        TrackData track = books.get(pos);
        TextView author = (TextView) view.findViewById(R.id.book_name);
        TextView title = (TextView) view.findViewById(R.id.author_name);
        book_name.setText(track.getTitle());
        author_name.setText(track.getAuthor());
        return view;
    }




}
