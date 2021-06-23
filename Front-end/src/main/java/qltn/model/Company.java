package qltn.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Company {
	private String macty;
	private String ten;
	private String msthue;
	private float vondieule;
	private String linhvuc;
	private int sonv;
	private String diachi;
	private String sdt;
	private float dientich;
	private List<Service> services ; 
}
