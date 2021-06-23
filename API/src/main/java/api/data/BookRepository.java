package api.data;

import org.springframework.data.repository.CrudRepository;

import api.model.Book;

public interface BookRepository extends CrudRepository<Book, String>{

}
