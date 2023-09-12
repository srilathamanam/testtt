package com.jobapplicant.example.Frontendversion.RecomendedJobs.Entity;

import com.jobapplicant.example.Frontendversion.RecomendedJobs.Entity.Job;
import com.jobapplicant.example.Frontendversion.RecomendedJobs.Entity.JobUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SavedJobs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn
    private JobUser user;

    @ManyToOne
    @JoinColumn
    private Job job;

    private Date dateSaved;
}
