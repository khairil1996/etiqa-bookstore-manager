package com.etiqa.bookstoremanager.entity;

import jakarta.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOOK_SEQ_GEN")
    @SequenceGenerator(name = "BOOK_SEQ_GEN", sequenceName = "book_seq", allocationSize = 1)
    private Long id;

    @Column(name = "BOOKTITLE")
    private String bookTitle;
    @Column(name = "BOOKPRICE")
    private Double bookPrice;
    @Column(name = "BOOKQUANTITY")
    private Integer bookQuantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public Double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(Double bookPrice) {
        this.bookPrice = bookPrice;
    }

    public Integer getBookQuantity() {
        return bookQuantity;
    }

    public void setBookQuantity(Integer bookQuantity) {
        this.bookQuantity = bookQuantity;
    }
}

