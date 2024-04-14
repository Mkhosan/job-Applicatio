package com.CareerConnect.CareerConnect.company;

import com.CareerConnect.CareerConnect.job.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> companies = companyService.getAllCompanies();
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Company> addCompany(@RequestBody Company company) {
        Company createdCompany = companyService.createCompany(company);
        if (createdCompany != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCompany);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }




   /*
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id, @RequestBody Company company) {
        try {
            companyService.updateCompany(company, id);
            return ResponseEntity.ok("Company updated successfully");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating company");
        }
    }
*/
   @PutMapping("/{id}")
   public ResponseEntity<String> updateCompany(@PathVariable Long id, @RequestBody Company company) {
       try {
           boolean updated = companyService.updateCompany(company, id);
           if (updated) {
               return ResponseEntity.ok("Company updated successfully");
           } else {
               return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company not found");
           }
       } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating company");
       }
   }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id) {
        boolean isDeleted = companyService.deleteCompanyById(id);
        if (isDeleted) {
            return ResponseEntity.ok("Company successfully deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company not found or could not be deleted");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompany(@PathVariable Long id) {
        Company company = companyService.getCompanyById(id);
        if (company != null) {
            return new ResponseEntity<>(company, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
