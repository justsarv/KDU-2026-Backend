package com.example.booklibraryfinal.exception;

public class BookNotFoundException extends RuntimeException{
    public long BookId;

    public BookNotFoundException(long BookId){
        super("Book with id: "+BookId+"Not Found");
        this.BookId=BookId;
    }
}
