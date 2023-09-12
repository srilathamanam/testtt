package com.jobapplicant.example.Frontendversion.RecomendedJobs.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaveJobRequest {
    private Long userId;
    private Long jobId;
}
