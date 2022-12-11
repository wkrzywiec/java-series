/* Wojciech Krzywiec 2022 */
package io.wkrzywiec.java.series.flatmap;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.wkrzywiec.java.series.flatmap.FlatMap.Address;
import io.wkrzywiec.java.series.flatmap.FlatMap.Author;
import io.wkrzywiec.java.series.flatmap.FlatMap.Book;
import java.util.List;
import java.util.Optional;
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
  @DisplayName("Book titles are extracted from each author")
  void allBooksAreListed() {
    // given
    var authors =
        List.of(
            new Author("Frank Herbert", List.of(new Book("Dune"))),
            new Author(
                "Stanisław Lem", List.of(new Book("Solaris"), new Book("Fables for Robots"))));

    // when
    var books = flatMap.getAllBookTitles(authors);

    // then
    assertEquals(List.of("Dune", "Solaris", "Fables for Robots"), books);
  }

  @Test
  @DisplayName("Apartment number is extracted")
  void apartmentNoExtracted() {
    // given
    var optAddress = Optional.of(new Address("Diagonal Street", "21A", Optional.of("787")));

    // when
    var apartmentNo = flatMap.extractApartmentNo(optAddress);

    // then
    assertEquals("787", apartmentNo);
  }

  @Test
  @DisplayName("Empty apartment number is extracted if it's not available")
  void emptyApartmentNoIfNotPresent() {
    // given
    var optAddress = Optional.of(new Address("Diagonal Street", "21A", Optional.empty()));

    // when
    var apartmentNo = flatMap.extractApartmentNo(optAddress);

    // then
    assertEquals("", apartmentNo);
  }

  @Test
  @DisplayName("Empty apartment number is extracted if address is empty")
  void emptyApartmentNoIfAddressNotPresent() {
    // given
    Optional<Address> optAddress = Optional.empty();

    // when
    var apartmentNo = flatMap.extractApartmentNo(optAddress);

    // then
    assertEquals("", apartmentNo);
  }
}
