/*
*   Custom Exception - Provides a mechanism to throw custom exceptions and handle errors consistently.
*/

package net.java.crud_application.utils;

public class CustomException extends RuntimeException {

    public CustomException(String message) {
        super(message);
    }
}