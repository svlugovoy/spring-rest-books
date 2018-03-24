package com.svlugovoy.springrestbooks.monitoring;

import com.svlugovoy.springrestbooks.repository.BookRepository;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class BookStorageIndicator implements HealthIndicator {

    private final BookRepository bookRepository;

    public BookStorageIndicator(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Health health() {
        if(bookRepository.findAll().size() <= 2) {
            return Health.down().withDetail("reason", "Warning! Less than 2 books present").build();
        }
        return Health.up().build();
    }
}
