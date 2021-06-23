package api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;

import api.data.CompanyRepository;
import api.data.Company_serviceRepository;
import api.data.EmployeeRepositoty;
import api.data.FeeRepository;
import api.model.Company_service;
import api.model.Fee;

@RestController
@RequestMapping(path = "/fees" , produces = "application/json")
public class FeeController {
	private FeeRepository feeRepository ; 
	private Company_serviceRepository company_serviceRepository ; 
	@Autowired
	public FeeController(FeeRepository feeRepository,Company_serviceRepository company_serviceRepository) {
		this.feeRepository = feeRepository ;
		this.company_serviceRepository = company_serviceRepository ; 
	}
	@GetMapping("/recent")
	public List<Fee> show() { 
		List<Company_service> company_services = (List<Company_service>) company_serviceRepository.findAll();
		List<Fee> fees = new ArrayList<>();
		for (Company_service company_service : company_services) {
			int ok = 0 ; 
			if(company_service.getCompany().getSonv()<=10 && company_service.getCompany().getDientich()<=100) {
				float phidv = company_service.getService().getDongia() ; 
				if(fees.isEmpty()==false) {
				for (Fee fee : fees) {
					if(company_service.getCompany().getMacty()==fee.getCompany().getMacty()) {
						fee.setPhidv(fee.getPhidv()+company_service.getService().getDongia());
						fee.setPhimatbang(company_service.getCompany().getDientich()*1000000);
						ok = 1 ; 
						break ; 
					}
				}
				}
				if(ok==0) {
					Fee fee = new Fee();
					fee.setCompany(company_service.getCompany());
					fee.setPhidv(phidv);
					fee.setPhimatbang(company_service.getCompany().getDientich()*1000000);
					fees.add(fee);
				}
			}
			if (company_service.getCompany().getSonv()>10) {
				float bac = (company_service.getCompany().getSonv()-10)/5;
				int du = (company_service.getCompany().getSonv()-10)%5;
				float phidv = company_service.getService().getDongia() ; 
				float phidt = phidv ; 
				if(du != 0) {
					bac +=2 ; 
				}
				else bac += 1 ; 
				for(int i=1;i<=bac;i++) {
					phidv += phidv*0.05 ; 
				}
				float laidv = phidv-company_service.getService().getDongia();
				float laidt = 0 ; 
				if(company_service.getCompany().getDientich()>100) {
					int bac1 = (int) ((company_service.getCompany().getDientich()-100)/5) ; 
					int du1 = (int) ((company_service.getCompany().getDientich()-100)%5) ; 
					if(du1 != 0) {
						bac1 +=2 ; 
					}
					else bac1 += 1 ; 
					for(int i=1;i<=bac1;i++) {
						phidt = (float) (phidt+ phidt*0.05) ; 
					}
					 laidt = phidt-company_service.getService().getDongia();
					float phitong = (float)(company_service.getService().getDongia()+laidt+laidv);
					for (Fee fee : fees) {
						if(fees.isEmpty()==false) {
						if(company_service.getCompany().getMacty()==fee.getCompany().getMacty()) {
							fee.setPhidv(fee.getPhidv()+phitong);
							fee.setPhimatbang(company_service.getCompany().getDientich()*1000000);
							ok = 1 ; 
							break ; 
						}
						}
					}
					if(ok==0) {
						Fee fee = new Fee();
						fee.setCompany(company_service.getCompany());
						fee.setPhimatbang(company_service.getCompany().getDientich()*1000000);
						fee.setPhidv(phitong);
						fees.add(fee);
					}
				}
				else {
					float phitong1 = (float)(company_service.getService().getDongia()+laidt+laidv);
					for (Fee fee : fees) {
						if(fees.isEmpty()==false) {
						if(company_service.getCompany().getMacty()==fee.getCompany().getMacty()) {
							fee.setPhidv(fee.getPhidv()+phitong1);
							fee.setPhimatbang(company_service.getCompany().getDientich()*1000000);
							ok = 1 ; 
							break ; 
						}
						}
					}
					if(ok==0) {
						Fee fee = new Fee();
						fee.setCompany(company_service.getCompany());
						fee.setPhidv(phitong1);
						fee.setPhimatbang(company_service.getCompany().getDientich()*1000000);
						fees.add(fee);
					}
				}
				
				
			}
			
		}
		return fees ; 
	}
}
