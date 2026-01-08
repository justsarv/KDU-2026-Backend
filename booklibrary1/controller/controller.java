package com.example.booklibrary1.controller;

import com.example.booklibrary1.model.model;
import com.example.booklibrary1.service.bookService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class controller {

        bookService service=new bookService();
        @GetMapping("/books")
        public ResponseEntity returnBooks(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue="3") int pageSize){
//            List<model> bookList= service.getAll();
            Page<model> ListofPageable=service.getAll(page,pageSize);
            return new ResponseEntity(ListofPageable,HttpStatus.OK);
        }

        @GetMapping("/books/hateoas")
        public EntityModel<model>  getBooks(){

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
