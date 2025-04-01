package net.java.crud_application.utils;

import net.java.crud_application.model.Book;
import org.springframework.stereotype.Component;

@Component
public class ValidationUtil {
    public void validateBookRequest(Book book){
        if(book.getTitle() == null || book.getTitle().isEmpty()){
            throw  new CustomException("Book Title is required");
        }
        if(book.getAuthor() == null || book.getAuthor().isEmpty()){
            throw new CustomException("Book Author is Required");
        }
    }
}
