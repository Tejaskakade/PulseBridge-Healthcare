package com.hms.doclogin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.AttributeNotFoundException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.doclogin.entity.Medicine;
import com.hms.doclogin.repository.MedicineRepository;
import com.hms.entity.Patient;

@RestController
@RequestMapping("/api/v3")
@CrossOrigin(origins = "http://localhost:4200")
public class MedicineController {
	
	private MedicineRepository medicineRepository;

	public MedicineController(MedicineRepository medicineRepository) {
		super();
		this.medicineRepository = medicineRepository;
	}
	
	
	@PostMapping("/medicines")
	public Medicine createMedicine( @RequestBody Medicine medicine) {
		
		return medicineRepository.save(medicine);
	}
	
	@GetMapping("/medicines")
	public List<Medicine> getAllMedicines(){
		return medicineRepository.findAll();
	}
	
	
	@GetMapping("/medicines/{id}")
	public ResponseEntity<Medicine> getMedicineById(@PathVariable long id) throws AttributeNotFoundException{
		Medicine medicine = medicineRepository.findById(id).orElseThrow(()-> new AttributeNotFoundException("Medicine not found with id :" +id));
		
		return ResponseEntity.ok(medicine);
	}
	
	
	@PutMapping("/medicines/{id}")
	public ResponseEntity<Medicine> updatePatientById( @PathVariable long id, @RequestBody Medicine medicineDetails) throws AttributeNotFoundException{
		
		
		Medicine medicine = medicineRepository.findById(id).orElseThrow(()-> new AttributeNotFoundException("Medicine not found with id :" +id));

		medicine.setDrugName(medicineDetails.getDrugName());
		medicine.setStock(medicineDetails.getStock());
		
		
		Medicine savedMedicine = medicineRepository.save(medicine);
		
		return ResponseEntity.ok(savedMedicine);
	}
	
	
	@DeleteMapping("/medicines/{id}")
	public ResponseEntity<Map<String , Boolean>> delete(@PathVariable long id) throws AttributeNotFoundException{
		
		Medicine medicine = medicineRepository.findById(id).orElseThrow(()-> new AttributeNotFoundException("Medicine not found with id :" +id));
	
	 medicineRepository.delete(medicine);
	 
	 Map<String, Boolean> response= new HashMap<String, Boolean>();
	 response.put("Deleted", Boolean.TRUE);
	 
	 return ResponseEntity.ok(response);
	}

}
