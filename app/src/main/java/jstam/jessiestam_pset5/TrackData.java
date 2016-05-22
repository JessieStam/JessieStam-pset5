package jstam.jessiestam_pset5;

/**
 * Created by Jessie on 21/05/2016.
 */
public class TrackData {

    // field
    private String title;
    private String author;
    // private String description;

    // constructor
    public TrackData(String new_title, String new_author) {
        title = new_title;
        author = new_author;
        // description = new_description;
    }

    //methods for author
    public String getAuthor() {return author;}
    public void setAuthor(String new_author) {author = new_author;}

    // methods for title
    public String getTitle() {return title;}
    public void setTitle(String new_title) {title = new_title;}

//    // methods for description
//    public String getDescription() {return description;}
//    public void setDescription(String new_description) {description = new_description;}
}
