package api.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.*;

import lombok.Data;

import java.util.*;
@Data
@Entity
@Table(name = "company")
public class Company {
	@Id
	private String macty;
	private String ten;
	private String msthue;
	private float vondieule;
	private String linhvuc;
	private int sonv;
	private String diachi;
	private String sdt;
	private float dientich;
//	@ManyToMany(targetEntity = Service.class)
//	 @JoinTable(
//			  name = "company_service", 
//			  joinColumns = @JoinColumn(name = "company_id"), 
//			  inverseJoinColumns = @JoinColumn(name = "service_id"))
//	private List<Service> services ; 
}
