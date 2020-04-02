package dmacc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dmacc.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>{

}
