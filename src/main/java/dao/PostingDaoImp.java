/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Posting;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class PostingDaoImp implements PostingDao {

    private static PostingDaoImp instance = null;
    private ConcurrentHashMap<Long, Posting> postings;
    private AtomicLong nextId = new AtomicLong(0L);

    public static synchronized PostingDao getPostingDao() {
        if (instance == null) {
            instance = new PostingDaoImp();
        }

        return instance;
    }

    private PostingDaoImp() {
        this.initWebBlog();
    }

    public void initWebBlog() {
        postings = new ConcurrentHashMap<>();

        create(new Posting("Student 1", "Title 1", "Content 1"));
        create(new Posting("Student 1", "Title 2", "Content 2"));
        create(new Posting("Student 1", "Title 3", "Content 3"));
    }

    @Override
    public Posting create(Posting p) {
        if (p == null) {
            throw new IllegalArgumentException("Posting is null");
        }
        p.setId(nextId.getAndIncrement());
        postings.put(p.getId(), p);
        return p;
    }

    @Override
    public Posting update(Long id, String author, String title, String content) {
        if (author == null || title == null || content == null) {
            throw new IllegalArgumentException("Author, Title or Content is null");
        }
        if (!postings.containsKey(id)) {
            throw new IllegalArgumentException("Id not found: " + id);
        }

        Posting p = postings.get(id);
        p.setAuthor(author);
        p.setTitle(title);
        p.setContent(content);

        return p;
    }

    @Override
    public void delete(Long id) {
        if (!postings.containsKey(id)) {
            throw new IllegalArgumentException("Id not found: " + id);
        }

        postings.remove(id);
    }

    @Override
    public List<Posting> findAll() {
        return new ArrayList(postings.values());
    }

    @Override
    public Posting find(Long id) {
        if (!postings.containsKey(id)) {
            throw new IllegalArgumentException("Id not found: " + id);
        }
        return postings.get(id);
    }
}
