package com.bibleplangenerator;

import com.bibleplangenerator.response.Book;
import com.bibleplangenerator.response.ObjectMapper;
import com.bibleplangenerator.response.Response;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.IntStream;

public class Application {

    private static final int TOTAL_WORDS_IN_THE_BIBLE = 783137;
    private static final int DAYS = 365;
    private static final int WORDS_PER_DAY = 783137 / 365;

    private static final List<Book> books = List.of(
            Book.of("Genesis", 50),
            Book.of("Exodus", 40),
            Book.of("Leviticus", 27),
            Book.of("Numbers", 36),
            Book.of("Deuteronomy", 34),
            Book.of("Joshua", 24),
            Book.of("Judges", 21),
            Book.of("Ruth", 4),
            Book.of("1 Samuel", 31),
            Book.of("2 Samuel", 24),
            Book.of("1 Kings", 22),
            Book.of("2 Kings", 25),
            Book.of("1 Chronicles", 29),
            Book.of("2 Chronicles", 36),
            Book.of("Ezra", 10),
            Book.of("Nehemiah", 13),
            Book.of("Esther", 10),
            Book.of("Job", 42),
            Book.of("Psalms", 150),
            Book.of("Proverbs", 31),
            Book.of("Ecclesiastes", 12),
            Book.of("Song of Solomon", 8),
            Book.of("Isaiah", 66),
            Book.of("Jeremiah", 52),
            Book.of("Lamentations", 5),
            Book.of("Ezekiel", 48),
            Book.of("Daniel", 12),
            Book.of("Hosea", 14),
            Book.of("Joel", 3),
            Book.of("Amos", 9),
            Book.of("Obadiah", 1),
            Book.of("Jonah", 4),
            Book.of("Micah", 7),
            Book.of("Nahum", 3),
            Book.of("Habakkuk", 3),
            Book.of("Zephaniah", 3),
            Book.of("Haggai", 2),
            Book.of("Zechariah", 14),
            Book.of("Malachi", 4),
            Book.of("Matthew", 28),
            Book.of("Mark", 16),
            Book.of("Luke", 24),
            Book.of("John", 21),
            Book.of("Acts", 28),
            Book.of("Romans", 16),
            Book.of("1 Corinthians", 16),
            Book.of("2 Corinthians", 13),
            Book.of("Galatians", 6),
            Book.of("Ephesians", 6),
            Book.of("Philippians", 4),
            Book.of("Colossians", 4),
            Book.of("1 Thessalonians", 5),
            Book.of("2 Thessalonians", 3),
            Book.of("1 Timothy", 6),
            Book.of("2 Timothy", 4),
            Book.of("Titus", 3),
            Book.of("Philemon", 1),
            Book.of("Hebrews", 13),
            Book.of("James", 5),
            Book.of("1 Peter", 5),
            Book.of("2 Peter", 3),
            Book.of("1 John", 5),
            Book.of("2 John", 1),
            Book.of("3 John", 1),
            Book.of("Jude", 1),
            Book.of("Revelation", 22)
    );


    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException, ExecutionException {

        for (var book : books) {
            for (int chapter : IntStream.rangeClosed(1, book.getNumberOfChapters()).toArray()) {
                var request = HttpRequest.newBuilder()
                        .uri(new URI(String.format("https://bible-api.com/%s+%d?translation=kjv", book.getName().replaceAll(" ", "+"), chapter)))
                        .GET()
                        .build();

                var client = HttpClient.newHttpClient();

                Response response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                        .thenApply(HttpResponse::body)
                        .thenApply(ObjectMapper::readValue)
                        .get();

                System.out.println(String.format("%s %d | %d", book.getName(), chapter, countWords(response.getText())));
            }
        }
    }

    public static int countWords(String verse) {
        String trim = verse.replaceAll("\\n"," ").trim();
        if (trim.isEmpty())
            return 0;
        return trim.split("\\s+").length;
    }
}
