package api.model;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name ="company_service")
public class Company_service {
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
		@GenericGenerator(name ="native",strategy = "native")
	    private int idRegister ;
	    @ManyToOne
	    private Company company;
	    @ManyToOne
	    private Service service ; 
	    private Date registerTime;
}
