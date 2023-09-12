package com.jobapplicant.example.Frontendversion.RecomendedJobs.Controller;

import com.jobapplicant.example.Frontendversion.RecomendedJobs.Entity.Job;
import com.jobapplicant.example.Frontendversion.RecomendedJobs.Entity.SaveJobRequest;
import com.jobapplicant.example.Frontendversion.RecomendedJobs.Service.SavedJobsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/viewjobs/saved-jobs")
public class SavedJobController {

    @Autowired
    private SavedJobsService savedJobsService;
    @PostMapping("/save")
    public ResponseEntity<?> saveJob(@RequestBody SaveJobRequest request) throws Exception {
        // Implement code to save a job for the user
        savedJobsService.saveJob(request.getUserId(), request.getJobId());
        return ResponseEntity.ok("Job saved successfully");
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Job>> getSavedJobs(@RequestParam Long userId) throws Exception {
        // Implement code to retrieve a user's saved jobs
        List<Job> savedJobs = savedJobsService.getSavedJobs(userId);
        return ResponseEntity.ok(savedJobs);
    }

}
