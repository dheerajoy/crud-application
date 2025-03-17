package net.java.crud_application.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ResponseUtil {

    public ResponseEntity<Object> customSuccessResponse(Object data){
        Map<String, Object> response = new HashMap<>();
        response.put("Success", true);
        response.put("Message", "Data fetched Successfully");
        response.put("Data", createDataResponse(data));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<Object> customFailureResponse(String message){
        Map<String, Object> response = new HashMap<>();

        response.put("Success", false);
        response.put("message", message);
        response.put("data", new Object());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    public Map<String, Object> createDataResponse(Object data){
        Map<String, Object> dataResponse = new HashMap<>();

        if (data instanceof Iterable) {
            dataResponse.put("list", data);  // if it's a list, wrap it in "list" key
        } else {
            dataResponse.put("object", data);  // otherwise, just return it as an object
        }

        return dataResponse;
    }
}
