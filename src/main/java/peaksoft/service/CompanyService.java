package peaksoft.service;

import peaksoft.model.Company;

import java.util.List;

public interface CompanyService {
    void save(Company company);

    List<Company> getAllCompanies();

    void deleteCompanyById(Long id);

    void update( Company company );

    Company getById(Long id);
}
