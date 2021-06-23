package api.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import antlr.DocBookCodeGenerator;
import lombok.Data;

@Data
@Entity
@Table(name= "book")
public class Book {
	@Id
	private String bookcode ; 
	private String tittle ; 
	private String author ; 
	private String category ; 
	private int approved ; 
}
