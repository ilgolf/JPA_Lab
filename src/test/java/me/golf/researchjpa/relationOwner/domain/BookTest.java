package me.golf.researchjpa.relationOwner.domain;

import me.golf.researchjpa.realationOwner.domain.Book;
import me.golf.researchjpa.realationOwner.domain.BookRepository;
import me.golf.researchjpa.realationOwner.domain.BookStore;
import me.golf.researchjpa.realationOwner.domain.BookStoreRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookTest {

    @Autowired BookRepository bookRepository;

    @Autowired BookStoreRepository bookStoreRepository;

    @Test
    void contextLoad() {
        BookStore bookStore = new BookStore();
        bookStore.setName("시애틀 책방");
        bookStoreRepository.save(bookStore);

        Book book = new Book();
        book.setTitle("연관관계를 잘 공부하고 JPA를 사용하자");
        bookStore.add(book);
        bookRepository.save(book);
    }
}
