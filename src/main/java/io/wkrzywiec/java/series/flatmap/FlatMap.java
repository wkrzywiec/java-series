/* Wojciech Krzywiec 2022 */
package io.wkrzywiec.java.series.flatmap;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class FlatMap {

  public record Book(String title) {}

  public record Author(String name, List<Book> books) {}

  List<String> getAllBookTitles(List<Author> authors) {
    return authors.stream().map(Author::books).flatMap(List::stream).map(Book::title).toList();
  }

  List<String> mergeLists(List<String> left, List<String> right) {
    return Stream.of(left, right).flatMap(List::stream).toList();
  }

  public record Address(String street, String buildingNo, Optional<String> apartmentNo) {}

  String extractApartmentNo(Optional<Address> address) {
    return address.flatMap(Address::apartmentNo).orElse("");
  }
}
