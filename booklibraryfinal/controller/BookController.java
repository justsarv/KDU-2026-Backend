package com.example.booklibraryfinal.controller;

//import com.example.booklibraryfinal.service.BookService;
import com.example.booklibraryfinal.exception.BookNotFoundException;
import com.example.booklibraryfinal.model.Book;
import com.example.booklibraryfinal.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    BookService bookService=new BookService();

    @GetMapping("/books")
    public ResponseEntity returnAllBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,asc") String[] sort,
            PagedResourcesAssembler<Book> assembler // spring injects this and this will handle the hateoas aspect on its own
    ){
        Sort sorted= sort[1].equalsIgnoreCase("desc") ? Sort.by(sort[0]).descending() : Sort.by(sort[0]).ascending();
        Pageable pageable= PageRequest.of(page,size,sorted);
        Page<Book> paged=bookService.getAll(pageable);

        if(paged==null){
            return new ResponseEntity("The item does not exist",HttpStatus.NOT_FOUND);
        }
        PagedModel<EntityModel<Book>> model=assembler.toModel(paged);
        return new ResponseEntity(model, HttpStatus.OK);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity returnBookById(
            @PathVariable long id
    ){
        Book book=bookService.getById(id);

        if(book==null){
            throw new BookNotFoundException(id);
        }
        //PageModel<EntityModel<Book>> model=assembler.toModel(book,) for single page i dont need ot use pagination so i can directly use EntityModel
        Link selfLink= WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).returnAllBooks(0,10,null,null)).withRel("All-books").expand();
        Link  AllBookLink=WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).returnBookById(id)).withSelfRel();
        EntityModel<Book>model=EntityModel.of(book,selfLink,AllBookLink);
        return new ResponseEntity(model,HttpStatus.OK);
    }


}
