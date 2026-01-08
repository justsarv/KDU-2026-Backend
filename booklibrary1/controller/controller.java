package com.example.booklibrary1.controller;

import com.example.booklibrary1.model.model;
import com.example.booklibrary1.service.bookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class controller {

        bookService service=new bookService();
        @GetMapping("/books")
        public ResponseEntity returnBooks(){
            List<model> bookList= service.getAll();
            return new ResponseEntity(bookList,HttpStatus.OK);
        }

        @PostMapping("/books")
        public ResponseEntity addBook(@RequestBody model book){
            model addedBook=service.addBook(book);
            if(addedBook.getId()!=null){
                return new ResponseEntity<>("added book"+addedBook,HttpStatus.OK);
            }
            else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        }

        @PutMapping("/books/{id}")
        public ResponseEntity updateBook(@RequestBody model book,@PathVariable long id){
            model updatedBook=service.updateBook(book,id);
            if(updatedBook!=null){
                return new ResponseEntity<>(updatedBook,HttpStatus.OK);
            }
            else return new ResponseEntity<>("Sorry book does not exist",HttpStatus.NOT_FOUND);
        }

        @DeleteMapping("/books/{id}")
        public ResponseEntity deleteBook(@PathVariable long id ){
            service.deleteBook(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

}
