package net.java.crud_application.service;

import net.java.crud_application.model.Book;
import net.java.crud_application.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepo bookRepo;

    // Fetch all books
    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }
}
