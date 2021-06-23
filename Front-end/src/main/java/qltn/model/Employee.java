package qltn.model;

import java.sql.Date;
import lombok.Data;

@Data
public class Employee {
	private String manv;
	private String cmt;
	private String ten;
	private Date ngaysinh;
	private String sdt;
}
