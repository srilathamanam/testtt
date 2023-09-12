package com.jobapplicant.example.Frontendversion.RecomendedJobs.Service;

import com.jobapplicant.example.Frontendversion.RecomendedJobs.Entity.Job;
import com.jobapplicant.example.Frontendversion.RecomendedJobs.Repository.JobRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class JobService {
    private final JobRepositoty jobRepository;

    @Autowired
    public JobService(JobRepositoty jobRepository) {
        this.jobRepository = jobRepository;
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public Optional<Job> getJobById(Long jobId) {
        return jobRepository.findById(jobId);
    }

    public Job createJob(Job job) {
       job.setDatePosted(new Date()); // Set the current date when creating a job
        return jobRepository.save(job);
    }

        public void deleteJob(Long jobId) throws Exception {
        Optional<Job> existingJob = jobRepository.findById(jobId);
        if (existingJob.isPresent()) {
            jobRepository.deleteById(jobId);
        } else {
            throw new Exception("Job not found");
        }
    }
}
