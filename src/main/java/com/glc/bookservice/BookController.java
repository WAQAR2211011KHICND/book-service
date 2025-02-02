package com.glc.bookservice;

import java.io.Console;
import java.util.Collection;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/book") //https:locahost:8080/book
public class BookController {
    
    private final BookRepository repository;

    public BookController(BookRepository repository){
        this.repository = repository;        
    } 

     // (POST) https:locahost:8080/book
    @PostMapping
    public void createBook(@RequestBody Book book){
        repository.save(book);
    }

    
     // (GET) https:locahost:8080/book/all
    @GetMapping("/all") //GET https://Lo..../books/all
    public Collection<Book> getAllBooks(){
        return this.repository.getAllBooks();
    }


    @GetMapping("/") //  (GET) https:locahost:8080/book/?id=1
    public Book getBook(@RequestParam int id){
       return this.repository.getBook(id);
    }
    
    @DeleteMapping("/delete/") //  (GET) https:locahost:8080/book/?id=1
    public String deleteBook(@RequestParam int id){
         return this.repository.deleteBook(id);
    }

    @PutMapping
    public ResponseEntity<Book> updateBook(@RequestBody Book book){

        Book reponseBook = this.repository.updateBook(book.getId().intValue(), book);

        if(reponseBook==null){
            return ResponseEntity.status(406).build(); 
        }else{
            return ResponseEntity.ok().body(reponseBook);
        }
      
    }
     

}

