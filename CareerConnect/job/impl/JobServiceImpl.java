package com.CareerConnect.CareerConnect.job.impl;

import com.CareerConnect.CareerConnect.job.Job;
import com.CareerConnect.CareerConnect.job.JobRepository;
import com.CareerConnect.CareerConnect.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
    //private List<Job> jobs = new ArrayList<>();
    JobRepository jobRepository;
    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {


        jobRepository.save(job);

    }

    @Override
    public Job getJobById(Long id) {
       return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJobById(Long id) {
        Optional<Job> optionalJob = jobRepository.findById(id);
        if (optionalJob.isPresent()) {
            jobRepository.deleteById(id);
            return true; // Job found and deleted
        } else {
            return false; // Job not found
        }
    }


    @Override
    public boolean updateJob(Long id, Job updatedJob) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();
            //job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setLocation(updatedJob.getLocation());

            // Save the updated job back to the repository
            jobRepository.save(job);

            return true; // Job updated successfully
        }

        return false; // Job not found
    }



}
