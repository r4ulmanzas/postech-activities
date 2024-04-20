package com.challenge.fiap.challengeapi;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "book")
public class BookEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String authorName;
    private String publisherName;
}
