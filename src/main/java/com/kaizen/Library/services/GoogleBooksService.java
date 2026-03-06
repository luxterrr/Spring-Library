package com.kaizen.Library.services;

import com.kaizen.Library.domains.book.Book;
import com.kaizen.Library.domains.googlebook.GoogleBooksResponse;
import com.kaizen.Library.domains.googlebook.VolumeInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GoogleBooksService {

    @Value("${google.books.api-key}")
    private String apiKey; //https://www.googleapis.com/books/v1/volumes?q=isbn:9788535914849&key=AIzaSyDv4PCENZvll2cHXWEBYR9JKILBqKcJFVg

    private static RestTemplate restTemplate;
    private static final String API_URL = "https://www.googleapis.com/books/v1/volumes?q=isbn:";

    public GoogleBooksService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Book importByIsbn(String isbn) {
        String url = API_URL + isbn + "&key=" + apiKey;
        System.out.println(url);
        GoogleBooksResponse response = restTemplate.getForObject(url, GoogleBooksResponse.class);


        if (response != null && response.getGoogleBookItems() != null && !response.getGoogleBookItems().isEmpty()) {
            return response.getGoogleBookItems().getFirst().getBook();
        }
        return null;
    }
}
