/* Wojciech Krzywiec 2022 */
package io.wkrzywiec.java.series.flatmap;

import java.util.List;
import java.util.stream.Stream;

public class FlatMap {

  public record Author(String name, List<Book> books) {}

  public record Book(String title) {}

  List<String> mergeLists(List<String> left, List<String> right) {
    return Stream.of(left, right).flatMap(List::stream).toList();
  }

  List<String> getAllBooks(List<Author> authors) {
    return authors.stream()
        .flatMap(author -> author.books().stream())
        .map(book -> book.title)
        .toList();
  }
}
