package me.golf.researchjpa.realationOwner.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter
public class BookStore {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "bookStore") // 연관관계를 단뱡향 -> 양방향으로 바꿔줌, Book이 관계의 주인
    private Set<Book> books = new HashSet<>();

    public void add(Book book) {
        book.setBookStore(this); // 자기 자신에게만 변경을 주는 것이 아닌 주인인 book에도 변경이 들어가야 데이터에 변화가 생김
        this.books.add(book);
    }
}
