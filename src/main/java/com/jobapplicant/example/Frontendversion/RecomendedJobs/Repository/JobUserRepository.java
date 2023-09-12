package com.jobapplicant.example.Frontendversion.RecomendedJobs.Repository;

import com.jobapplicant.example.Frontendversion.RecomendedJobs.Entity.JobUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface JobUserRepository extends JpaRepository<JobUser, Long> {
    Optional<JobUser> findByEmail(String email);
}
