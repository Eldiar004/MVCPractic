package peaksoft.api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Company;
import peaksoft.service.serviceImpl.CompanyServiceImpl;
@Controller
public class CompanyController {
    private final CompanyServiceImpl companyService;

    @Autowired
    public CompanyController(CompanyServiceImpl companyService) {
        this.companyService = companyService;
    }
    @GetMapping("/getAllCompanies")
    public String getAllCompany(Model model) {
        model.addAttribute("companies", companyService.getAllCompanies());
        return "company/allCompanies";
    }

    @GetMapping("/newCompany")
    public String newCompany(Model model){
        model.addAttribute("company",new Company());
        return "company/newCompany";
    }

    @PostMapping("/saveCompany")
    public String save(@ModelAttribute("companyId")Company company){
        companyService.save(company);
        return "redirect:/getAllCompanies";
    }
    @GetMapping("/updateCompany/{id}")
    public String updateCompany(@PathVariable("id") Long id, Model model) {
        Company company = companyService.getById(id);
        model.addAttribute("company", company);
        return "company/updateCompany";
    }

    @PostMapping("/saveUpdateCompany")
    public String saveUpdateCompany(@ModelAttribute("company") Company company) {
        companyService.update(company);
        return "redirect:/getAllCompanies";
    }


    @RequestMapping("/deleteCompany")
    public String deleteCompany(@RequestParam("companyId")Long id){
        companyService.deleteCompanyById(companyService.getById(id).getId());
        return "redirect:/getAllCompanies";
    }
}

