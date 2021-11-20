package sqltutorial.evms;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sqltutorial.evms.model.VaccineType;
import sqltutorial.evms.repository.VaccineTypeRepository;

@SpringBootTest
class EvmsApplicationTests {

	@Autowired
	VaccineTypeRepository vaccineTypeRepository;
	
	private void showVaccineTypes(String message) {
		System.out.println();
		System.out.println(message);
		List<VaccineType> list = vaccineTypeRepository.findAll();
		
		for (VaccineType vaccineType: list) {
			System.out.println(vaccineType);
		}	
	}
	
	@Test
	void testRepository() {
		showVaccineTypes("Showing all list of vaccine types ...");
		
		VaccineType vaccineType = new VaccineType();
		vaccineType.setName("POLIO");
		vaccineType.setDescription("POLIO - This may need one vaccine");
		vaccineType.setCreatedBy("Mohammed Monirul Islam");
		vaccineType.setLastUpdatedBy("Mohammed Monirul Islam");
		vaccineTypeRepository.save(vaccineType);
		
		showVaccineTypes("After adding a new showing all list of vaccine types ...");
		
		vaccineType = vaccineTypeRepository.findById("POLIO").get();
		vaccineType.setDescription("POLIO - This may need unknown amount of vaccines");
		vaccineTypeRepository.save(vaccineType);
		
		showVaccineTypes("After updating the data showing all list of vaccine types ...");
				
		vaccineTypeRepository.delete(vaccineType);
		showVaccineTypes("After deleting the data showing all list of vaccine types ...");
		System.out.println();
	}

}
