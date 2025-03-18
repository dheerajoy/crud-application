package net.java.crud_application.controller;

import net.java.crud_application.model.Book;
import net.java.crud_application.repo.BookRepo;
import net.java.crud_application.service.BookService;
import net.java.crud_application.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class BookController {

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private BookService bookService;

    @Autowired
    private ResponseUtil responseUtil;

    @GetMapping("/getAllBooks")
    public ResponseEntity<Map<String, Object>> getAllBooks(){
        try {
            List<Book> books = bookService.getAllBooks();

            if(books.isEmpty()){
                return responseUtil.customFailureResponse("No data available");
            }

            return responseUtil.customSuccessResponse(books);
        } catch(Exception ex){
            return responseUtil.customFailureResponse(ex.getMessage());
        }
    }

    @GetMapping("/getBookById/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id){
        Optional<Book> bookData = bookRepo.findById(id);

        if(bookData.isPresent()){
        return new ResponseEntity<>(bookData.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/addBook")
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        try{

          Book bookObj = bookRepo.save(book);
          return new ResponseEntity<>(bookObj,HttpStatus.OK);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/updateBookById/{id}")
    public ResponseEntity<Book> updateBookById(@PathVariable long id, @RequestBody Book newBookData){
        Optional<Book> oldBookData = bookRepo.findById(id);
        if (oldBookData.isPresent()){
            Book updatedBookData = oldBookData.get();
            updatedBookData.setAuthor(newBookData.getAuthor());
            updatedBookData.setTitle(newBookData.getTitle());

            Book bookObj =  bookRepo.save(updatedBookData);
            return new ResponseEntity<>(bookObj, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteBookById/{id}")
    public ResponseEntity<HttpStatus> deleteBookById(@PathVariable long id){
        Optional<Book> oldBookData = bookRepo.findById(id);

        if(oldBookData.isPresent()){
            bookRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
