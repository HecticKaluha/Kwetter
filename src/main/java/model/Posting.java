package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Posting {

    private Long id;
    private String author;
    private String title;
    private String content;
    private Date date;
    private List<Comment> comments;
    private Long nextCommentId;

    public Posting(String author, String title, String content) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.date = new Date();
        this.comments = new ArrayList<Comment>();
        this.nextCommentId = 1L;
    }

    public Posting(Long id, String author, String title, String content) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
        this.date = new Date();
        this.comments = new ArrayList<Comment>();
        this.nextCommentId = 1L;
    }

    public List<Comment> getComments() {
        return comments;
    }
    
    public void addComment(String message) {
        Comment comment = new Comment(this.nextCommentId++, message);
        this.comments.add(comment);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
