package jstam.jessiestam_pset5;

/**
 * TrackData.java
 *
 * Jessie Stam
 *
 * A TrackData object that stores the author name and book title
 */
public class TrackData {

    // create field
    private String title;
    private String author;

    // constructor
    public TrackData(String new_title, String new_author) {
        title = new_title;
        author = new_author;
    }

    //methods for author
    public String getAuthor() {return author;}
    public void setAuthor(String new_author) {author = new_author;}

    // methods for title
    public String getTitle() {return title;}
    public void setTitle(String new_title) {title = new_title;}
}
