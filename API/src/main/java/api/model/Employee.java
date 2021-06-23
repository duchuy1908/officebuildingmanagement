package api.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "employee")
public class Employee {
	   @Id
	   private String manv  ; 
	   private String cmt ; 
	   private String ten ; 
	   private Date ngaysinh ; 
	   private String sdt ; 
}
