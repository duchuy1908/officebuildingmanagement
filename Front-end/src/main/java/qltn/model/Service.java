package qltn.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Service {
	private String madv;
	private String tendv;
	private String loaidv;
	private float dongia;
	private List<Company> companys; 
}
