package sqltutorial.evms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sqltutorial.evms.model.VaccineType;

public interface VaccineTypeRepository extends JpaRepository<VaccineType, String> {

}
