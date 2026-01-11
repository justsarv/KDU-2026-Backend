package com.example.booklibraryfinal.service;

import com.example.booklibraryfinal.model.Book;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.min;

@Service
public class BookService {
    List<Book> bookList= new ArrayList<Book>(List.of(
            new Book(1001L, "Effective Java", "Joshua Bloch"),
            new Book(1002L, "Clean Code", "Robert C. Martin"),
            new Book(1003L, "Java Concurrency in Practice", "Brian Goetz"),
            new Book(1004L, "Head First Java", "Kathy Sierra"),
            new Book(1005L, "Spring in Action", "Craig Walls"),
            new Book(1006L, "Design Patterns", "Erich Gamma"),
            new Book(1007L, "Refactoring", "Martin Fowler"),
            new Book(1008L, "Microservices Patterns", "Chris Richardson"),
            new Book(1009L, "Test Driven Development", "Kent Beck"),
            new Book(1010L, "Domain-Driven Design", "Eric Evans")
    ));

    public Page<Book> getAll(Pageable pageable){
        //return bookList;
        List<Book> sortedBooks=new ArrayList<>(bookList);
        if(pageable.getSort().isSorted()){
            Sort.Order order = pageable.getSort().iterator().next();
            String property = order.getProperty();
            sortedBooks.sort((b1, b2) -> {
                // Get the first sort instruction (e.g., "title" or "id")

                int result = 0;
                if ("title".equalsIgnoreCase(property)) {
                    result = b1.getName().compareTo(b2.getName());
                } else if ("id".equalsIgnoreCase(property)) {
                    result = Long.compare(b1.getId(),(b2.getId()));
                }

                return order.isDescending() ? -result : result;
            });
        }
        int start= (int) pageable.getOffset();
        int end=Math.min((start+pageable.getPageSize()),sortedBooks.size());

        if(start>sortedBooks.size()){
            return new PageImpl<>(Collections.emptyList(),pageable,sortedBooks.size());
        }
        List<Book>contentPage=sortedBooks.subList(start,end);
        return new PageImpl<>(contentPage,pageable,sortedBooks.size());
    }

    public Book getById(long id){
        for(Book i: bookList){
            if(i.getId()==id){
                return i;
            }
        }
        return null;
    }
}
