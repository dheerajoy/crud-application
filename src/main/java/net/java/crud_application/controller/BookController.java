package net.java.crud_application.controller;

import net.java.crud_application.model.Book;
import net.java.crud_application.repo.BookRepo;
import net.java.crud_application.service.BookService;
import net.java.crud_application.utils.ResponseUtil;
import net.java.crud_application.utils.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class BookController {

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private BookService bookService;

    @Autowired
    private ResponseUtil responseUtil;

    @Autowired
    private ValidationUtil validationUtil;

    @GetMapping("/getAllBooks")
    public ResponseEntity<Map<String, Object>> getAllBooks(){
        try {
            return responseUtil.customSuccessResponse(bookService.getAllBooks());
        } catch (Exception ex) {
            return responseUtil.customFailureResponse(ex.getMessage());
        }
    }

    @GetMapping("/getBookById/{id}")
    public ResponseEntity<Map<String, Object>> getBookById(@PathVariable Long id){
        try{
            return responseUtil.customSuccessResponse(bookService.getBookById(id));
        } catch (Exception e) {
            return responseUtil.customFailureResponse(e.getMessage());
        }
    }

    @PostMapping("/addBook")
    public ResponseEntity<Map<String, Object>> addBook(@RequestBody Book book){
        try{
            validationUtil.validateBookRequest(book);
            return responseUtil.customSuccessResponse(bookService.addBook(book));
        } catch (Exception e) {
            return responseUtil.customFailureResponse(e.getMessage());
        }
    }

    @PostMapping("/updateBookById/{id}")
    public ResponseEntity<Map<String, Object>> updateBookById(@PathVariable long id, @RequestBody Book newBookData){
        try{
            validationUtil.validateBookRequest(newBookData);
            return responseUtil.customSuccessResponse(bookService.updateBookById(id, newBookData));
        } catch (Exception e) {
            return responseUtil.customFailureResponse(e.getMessage());
        }
    }

    @DeleteMapping("/deleteBookById/{id}")
    public ResponseEntity<Map<String,Object>> deleteBookById(@PathVariable long id){
        try{
            bookService.deleteBookById(id);
            return responseUtil.customSuccessResponse("Deleted Book Successfully");
        } catch (Exception e) {
            return responseUtil.customFailureResponse(e.getMessage());
        }
    }
}
