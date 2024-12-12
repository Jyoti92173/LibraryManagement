package com.app.librarymanagement.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "book")
public class Book {

    @Id                                //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id", length = 11)
    private int bookId;

    @Column(name = "book_title", length = 45)
    private String title;

    @Column(name = "cost",length = 1000)
    private int cost;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    public Book(int bookId, int cost, Author author, Publisher publisher, String title) {
        this.bookId = bookId;
        this.cost = cost;
        this.author = author;
        this.publisher = publisher;
        this.title = title;
    }

    public Book(String title, int cost, Publisher publisher, Author author) {
        this.title = title;
        this.cost = cost;
        this.publisher = publisher;
        this.author = author;
    }

    public Book() {
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }


    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }


    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return bookId == book.bookId && cost == book.cost && Objects.equals(title, book.title) && Objects.equals(author, book.author) && Objects.equals(publisher, book.publisher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, title, cost, author, publisher);
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", cost=" + cost +
                ", author=" + author +
                ", publisher=" + publisher +
                '}';
    }
}
