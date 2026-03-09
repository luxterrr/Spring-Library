package com.kaizen.Library.services;

import com.kaizen.Library.DTOS.BookDTO;
import com.kaizen.Library.DTOS.GoogleBookDTO;
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

    public BookDTO importByIsbn(String isbn, BookDTO item) {
        String url = API_URL + isbn + "&key=" + apiKey;
        System.out.println(url);
        GoogleBookDTO response = restTemplate.getForObject(url, GoogleBookDTO.class);

        if (response == null && response.items() == null && response.items().isEmpty()) {
            return null;
        }
        GoogleBookDTO.Item info = response.items().getFirst();

        String title = info.volumeInfo().title();
        String author = String.valueOf(info.volumeInfo().authors());
        String category = info.volumeInfo().mainCategory();

        return new BookDTO(author, title, category, 1);
    }
}
