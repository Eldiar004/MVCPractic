package peaksoft.repository;
import org.springframework.stereotype.Repository;
import peaksoft.model.Company;
import java.util.List;

@Repository
public interface CompanyRepository {
    void save(Company company);

    List<Company> getAllCompanies();

    void deleteCompanyById(Long id);

    void update( Company company );

    Company getById(Long id);
}
