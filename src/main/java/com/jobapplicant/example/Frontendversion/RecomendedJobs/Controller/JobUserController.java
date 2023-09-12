package com.jobapplicant.example.Frontendversion.RecomendedJobs.Controller;

import com.jobapplicant.example.Frontendversion.RecomendedJobs.Entity.JobUser;
import com.jobapplicant.example.Frontendversion.RecomendedJobs.Service.JobUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/viewjobs/user")
public class JobUserController {
    private final JobUserService userService;

    @Autowired
    public JobUserController(JobUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public JobUser getUser(@PathVariable Long userId) throws Exception {


        return userService.getUserById(userId);
    }

    @PostMapping("/create")
    public JobUser createUser(@RequestBody JobUser user) {
        // You can add validation and other checks before creating a user
        return userService.createUser(user);
    }

}
