package com.example.booklibrary1.service;


import org.springframework.data.domain.PageImpl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.example.booklibrary1.model.model;

import java.util.ArrayList;
import java.util.List;

public class bookService {
    List<model> books =new ArrayList<>(List.of(
            new model(1001L, "Effective Java", "Joshua Bloch"),
            new model(1002L, "Clean Code", "Robert C. Martin"),
            new model(1003L, "Java Concurrency in Practice", "Brian Goetz"),
            new model(1004L, "Head First Java", "Kathy Sierra"),
            new model(1005L, "Spring in Action", "Craig Walls"),
            new model(1006L, "Design Patterns", "Erich Gamma"),
            new model(1007L, "Refactoring", "Martin Fowler"),
            new model(1008L, "Microservices Patterns", "Chris Richardson"),
            new model(1009L, "Test Driven Development", "Kent Beck"),
            new model(1010L, "Domain-Driven Design", "Eric Evans")
    ));

//    public List<model> getAll(){
//        return books;
//    }

    public model addBook(model book){
        try{
            books.add(book);
            return book;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }


    }

    public Page<model> getAll(int pageNumber,int pageSize){
        Pageable page=PageRequest.of(pageNumber, pageSize);
        int start=(int)page.getOffset();
       int end=Math.min(start+pageSize,books.size());
       List<model>pageContent;
       if(start>=books.size()) pageContent=List.of();
       else {
           pageContent=books.subList(start,end);
       }
       return new PageImpl<>(pageContent,page,books.size());
    }

    public model updateBook(model book,long id){
        for(model booksL: books){
            if(booksL.getId()==id) {
                booksL.setId(book.getId());
                booksL.setAuthor(book.getAuthor());
                booksL.setTitle(book.getTitle());
                return booksL;
            }
        }
        return null;
    }

    public void deleteBook(long id){
        List<model> newBooks=new ArrayList<>();
        for(model bookL:books){
            if(bookL.getId()!=id)newBooks.add(bookL);
        }
        books=newBooks;
        return;
    }
}
