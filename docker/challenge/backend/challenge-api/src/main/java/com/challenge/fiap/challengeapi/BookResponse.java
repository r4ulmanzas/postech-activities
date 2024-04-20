package com.challenge.fiap.challengeapi;

public record BookResponse(
        Long id,
        String title,
        String authorName,
        String publisherName
) {
}
