package com.jobapplicant.example.Frontendversion.RecomendedJobs.Repository;

import com.jobapplicant.example.Frontendversion.RecomendedJobs.Entity.Job;
import org.springframework.boot.autoconfigure.jackson.JacksonProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepositoty extends JpaRepository<Job, Long> {

}
