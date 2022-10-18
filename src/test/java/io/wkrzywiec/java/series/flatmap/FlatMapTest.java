/* Wojciech Krzywiec 2022 */
package io.wkrzywiec.java.series.flatmap;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.wkrzywiec.java.series.flatmap.FlatMap.Author;
import io.wkrzywiec.java.series.flatmap.FlatMap.Book;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FlatMapTest {

  FlatMap flatMap;

  @BeforeEach
  public void setup() {
    flatMap = new FlatMap();
  }

  @Test
  @DisplayName("Two lists are merged")
  void twoListsAreMerged() {
    // given
    var sciFiBooks =
        List.of(
            "Dune, by Frank Herbert",
            "Solaris, by Stanisław Lem",
            "A Scanner Darkly, by Philip K Dick");
    var fantasyBooks =
        List.of(
            "The Lord of the Rings, by J.R.R. Tolkien",
            "A Game of Thrones, by George R.R. Martin",
            "Sourcery, by Terry Pratchett");

    // when
    var books = flatMap.mergeLists(sciFiBooks, fantasyBooks);

    // then
    assertEquals(
        List.of(
            // sci-fi books
            "Dune, by Frank Herbert",
            "Solaris, by Stanisław Lem",
            "A Scanner Darkly, by Philip K Dick",
            // fantasy books
            "The Lord of the Rings, by J.R.R. Tolkien",
            "A Game of Thrones, by George R.R. Martin",
            "Sourcery, by Terry Pratchett"),
        books);
  }

  @Test
  @DisplayName("All books from each author are listed")
  void allBooksAreListed() {
    // given
    var authors =
        List.of(
            new Author("Frank Herbert", List.of(new Book("Dune"))),
            new Author(
                "Stanisław Lem", List.of(new Book("Solaris"), new Book("Fables for Robots"))));

    // when
    var books = flatMap.getAllBooks(authors);

    // then
    assertEquals(List.of("Dune", "Solaris", "Fables for Robots"), books);
  }
}
