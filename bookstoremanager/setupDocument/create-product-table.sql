CREATE SEQUENCE book_seq
    START WITH 1
    INCREMENT BY 1
    NOMAXVALUE;

CREATE TABLE product (
    id NUMBER PRIMARY KEY,
    bookTitle VARCHAR2(255),
    bookPrice NUMBER(10, 2),
    bookQuantity NUMBER
);

ALTER TABLE product MODIFY id DEFAULT book_seq.NEXTVAL;
