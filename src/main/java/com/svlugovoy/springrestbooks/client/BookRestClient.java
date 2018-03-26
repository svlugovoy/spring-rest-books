package com.svlugovoy.springrestbooks.client;

import com.svlugovoy.springrestbooks.model.Book;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class BookRestClient {

    private final RestTemplate restTemplate = new RestTemplate();

    private final String url;

    public BookRestClient(String url) {
        this.url = url;
    }

    public List<Book> findBooks() {
        return restTemplate.getForObject(url, List.class);
    }

    public Book findBookById(int bookId) {
        return restTemplate.getForEntity(url + '/' + bookId, Book.class).getBody();
    }

    public void saveBook(Book book) {
        restTemplate.postForLocation(url, book);
    }

    public static void main(String[] args) {
        BookRestClient client = new BookRestClient("http://localhost:8080/api/book");
        Book book = new Book(4, "Test", "Test Test", 2014);
        client.saveBook(book);

        System.out.println(client.findBookById(1));
        System.out.println(client.findBookById(4));
        System.out.println(client.findBooks());
    }

}
