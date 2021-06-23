package qltn.model;

import java.sql.Date;

import lombok.Data;

@Data

public class Company_service {
		private int idRegister ;
	    private Company company;
	    private Service service ; 
	    private Date registerTime;
}
