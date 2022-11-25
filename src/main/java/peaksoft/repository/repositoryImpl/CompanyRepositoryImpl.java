package peaksoft.repository.repositoryImpl;
import org.springframework.stereotype.Repository;
import peaksoft.model.Company;
import peaksoft.repository.CompanyRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
@Repository
@Transactional
public class CompanyRepositoryImpl implements CompanyRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void save(Company company) {
        entityManager.persist(company);
    }

    @Override
    public List getAllCompanies() {
        return entityManager.createQuery("select c from Company c").getResultList();
    }

    @Override
    public void deleteCompanyById(Long id) {
        entityManager.remove(entityManager.find(Company.class, id));
    }

    @Override
    public void update(Company company) {
        entityManager.merge(company);
    }

    @Override
    public Company getById(Long id) {
        return entityManager.find(Company.class,id);
    }
}

