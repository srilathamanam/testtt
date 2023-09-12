package com.jobapplicant.example.Frontendversion.RecomendedJobs.Repository;

import com.jobapplicant.example.Frontendversion.RecomendedJobs.Entity.SavedJobs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SavedJobsRepository extends JpaRepository<SavedJobs, Long> {
    List<SavedJobs> findByUserId(Long userId);
}
