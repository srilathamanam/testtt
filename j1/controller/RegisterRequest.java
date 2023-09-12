package com.example.j1.j1.controller;

import com.example.j1.j1.User.Role;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private  String firstname;
    private  String lastname;
    private  String email;
    private String password;
    private Role role;


}
