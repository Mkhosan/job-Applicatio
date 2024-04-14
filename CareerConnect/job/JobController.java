package com.CareerConnect.CareerConnect.job;

import com.CareerConnect.CareerConnect.company.Company;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/jobs")
public class JobController {
    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    @ResponseBody
    public List<Job> findAll() {
        return jobService.findAll();
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<String> createJobs(@RequestBody Job job) {
        try {
            jobService.createJob(job);

            return ResponseEntity.status(HttpStatus.CREATED).body("Job added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding job: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getJobById(@PathVariable Long id) {
        Job job = jobService.getJobById(id);
        if (job != null) {
            return ResponseEntity.ok(job);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Job with ID " + id + " not found");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id){
        boolean deleted =jobService.deleteJobById(id);
        if(deleted)
            return new ResponseEntity<>("Job deleted successfully", HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job updatedJob) {
        boolean updated = jobService.updateJob(id, updatedJob);
        if (updated) {
            return ResponseEntity.ok("Job updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Job with ID " + id + " not found or not updated");
        }
    }

}
