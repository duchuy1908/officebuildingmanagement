package api.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="staff")
public class Staff {
	@Id
@Column(name = "manv") 
   private String manv  ; 
	@Column(name = "hoten") 
   private String hoten ; 
	@Column(name = "ngaysinh") 
   private Date ngaysinh ; 
	@Column(name = "diachi") 
   private String diachi ; 
	@Column(name = "sdt") 
   private String sdt ; 
	@Column(name = "bac") 
   private String bac ; 
	@Column(name = "vitri") 
   private String vitri;
}
