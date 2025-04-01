package net.java.crud_application.service;

import net.java.crud_application.model.Book;
import net.java.crud_application.repo.BookRepo;
import net.java.crud_application.utils.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepo bookRepo;

    // Fetch all books
    public List<Book> getAllBooks() {
        List<Book> books = bookRepo.findAll();
        if (books.isEmpty()) {
            throw new CustomException("No books found");
        }
        return books;
    }

    // Get a Book by Id
    public Book getBookById(Long id){
        Optional<Book> book = bookRepo.findById(id);
        if (!book.isPresent()) {
            throw new CustomException("Book not found");
        }
        return book.get();
    }

    // Add book
    public Book addBook(Book book){
        return bookRepo.save(book);
    }

    // Update Book
    public Book updateBookById(Long id, Book newBookData){
        Optional<Book> oldBookData = bookRepo.findById(id);
        if(oldBookData.isPresent()){
            Book updateBookData = oldBookData.get();
            updateBookData.setAuthor(newBookData.getAuthor());
            updateBookData.setTitle(newBookData.getTitle());
            return bookRepo.save(updateBookData);
        }else{
            throw new CustomException("Book cannot be updated");
        }
    }

    public void deleteBookById(Long id){
        Optional<Book> book = bookRepo.findById(id);
        if (book.isPresent()){
            bookRepo.deleteById(id);
        }else{
            throw new CustomException("Cannot Delete the book");
        }
    }
}
