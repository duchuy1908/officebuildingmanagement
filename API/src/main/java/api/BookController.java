package api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.data.BookRepository;
import api.model.Book;
import api.model.Company;
@RestController
@RequestMapping(path = "/book" , produces = "application/json")
@CrossOrigin(origins = "*")
public class BookController {
	private BookRepository bookRepository ; 
	@Autowired
	public BookController(BookRepository bookRepository) {
		this.bookRepository = bookRepository ; 
	}
	@GetMapping("/recent")
	public List<Book> showAll(){
		List<Book> list = (List<Book>) bookRepository.findAll();
		List<Book> list1 = new ArrayList<>();
		for (Book book : list) {
			if(book.getApproved()==0) {
				list1.add(book) ; 
			}
		}
		return list1 ; 
	}
	@PostMapping("/approved/{id}") 
	public Book approved(@PathVariable String id) {
		Optional<Book> book = bookRepository.findById(id) ; 
		Book b1 = new Book() ; 
		if(book.isPresent()) {
			b1 = book.get(); 
			b1.setApproved(1);
		}
		return bookRepository.save(b1);
	}

	@GetMapping("/{id}")
	public Book getBookById(@PathVariable("id") String id) {
	Optional<Book> book = bookRepository.findById(id);
	if (book.isPresent()) {
		return book.get();
	}
	return null;
}
	@DeleteMapping("/delete/{id}")
	public void deleteBook(@PathVariable("id")String id) {
		bookRepository.deleteById(id);
	}
}
