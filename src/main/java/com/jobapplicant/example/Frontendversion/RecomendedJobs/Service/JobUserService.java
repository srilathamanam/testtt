package com.jobapplicant.example.Frontendversion.RecomendedJobs.Service;

import com.jobapplicant.example.Frontendversion.RecomendedJobs.Entity.JobUser;
import com.jobapplicant.example.Frontendversion.RecomendedJobs.Repository.JobUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobUserService {
    private final JobUserRepository userRepository;

    @Autowired
    public JobUserService(JobUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public JobUser createUser(JobUser user) {
        return userRepository.save(user);
    }
    public JobUser getUserById(Long userId) throws Exception {
        return userRepository.findById(userId)
                .orElseThrow(() -> new Exception("User not found"));
    }
    public JobUser getUserByEmail(String email) throws Exception {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new Exception("User not found"));
    }
}
