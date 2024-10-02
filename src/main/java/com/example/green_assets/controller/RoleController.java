package com.example.green_assets.controller;

import com.example.green_assets.model.Role;
import com.example.green_assets.repo.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/role")
public class RoleController {
    private final RoleRepo repo;
    @GetMapping("/{id}")
    public List<Role> getItems() {
        return repo.findAll();
    }
}
