package com.jobapplicant.example.Frontendversion.RecomendedJobs.Service;

import com.jobapplicant.example.Frontendversion.RecomendedJobs.Entity.Job;
import com.jobapplicant.example.Frontendversion.RecomendedJobs.Entity.JobUser;
import com.jobapplicant.example.Frontendversion.RecomendedJobs.Entity.SavedJobs;
import com.jobapplicant.example.Frontendversion.RecomendedJobs.Repository.JobRepositoty;
import com.jobapplicant.example.Frontendversion.RecomendedJobs.Repository.JobUserRepository;
import com.jobapplicant.example.Frontendversion.RecomendedJobs.Repository.SavedJobsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SavedJobsService {
    private final JobRepositoty jobRepository;
    private final JobUserRepository userRepository;
    private final SavedJobsRepository savedJobRepository;

    @Autowired
    public SavedJobsService(JobRepositoty jobRepository, JobUserRepository userRepository, SavedJobsRepository savedJobRepository) {
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
        this.savedJobRepository = savedJobRepository;
    }

    public void saveJob(Long userId, Long jobId) throws Exception {
        // Check if the user and job exist
       JobUser user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("User not found"));

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new Exception("Job not found"));


        SavedJobs savedJob = new SavedJobs();
        savedJob.setUser(user);
        savedJob.setJob(job);

        // Save the association in the database
        savedJobRepository.save(savedJob);
    }

    public List<Job> getSavedJobs(Long userId) throws Exception {

        JobUser user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("User not found"));


        List<SavedJobs> savedJobs = savedJobRepository.findByUserId(userId);


        return savedJobs.stream()
                .map(SavedJobs::getJob)
                .toList();
    }

}

