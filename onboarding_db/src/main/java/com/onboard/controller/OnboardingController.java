package com.onboard.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.onboard.entity.EntityCategory;
import com.onboard.entity.EntityDetails;

import com.onboard.service.OnboardingService;
import com.onboard.util.EntityStatus;
import com.onboard.util.Response;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/api/onboard/v1")
@Slf4j
public class OnboardingController {

    private OnboardingService onboardingService;

    @Autowired
    public OnboardingController(OnboardingService onboardingService) {
        this.onboardingService = onboardingService;
    }

    @PostMapping("/create")
    public ResponseEntity<Response> registerEntity(@RequestBody EntityDetails entity) {
    	log.info("Started executing Create...");
        return new ResponseEntity<>(Response.builder()
                .data(onboardingService.registerEntity(entity))
                .statusCode(HttpStatus.CREATED.value())
                .statusMsg("Data Stored Successfully.")
                .build(), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getEntityById(@PathVariable("id") Integer id) {
    	log.info("Started execution of GetById...");
        return new ResponseEntity<>(Response.builder()
                .data(onboardingService.getEntityById(id))
                .statusCode(HttpStatus.FOUND.value())
                .statusMsg(" Successfully Fetched from database by id " + id)
                .build(), HttpStatus.FOUND);
    }

    @GetMapping
    public ResponseEntity<Response> getAllEntities() {
        return new ResponseEntity<>(Response.builder()
                .datas(onboardingService.getAllEntitys())
                .statusCode(HttpStatus.FOUND.value())
                .statusMsg("All data is Successfully Fetched from Database.")
                .build(), HttpStatus.FOUND);
    }

    @PutMapping
    public ResponseEntity<Response> updateEntity(@RequestBody EntityDetails entity) {
        return new ResponseEntity<>(Response.builder()
                .data(onboardingService.updateEntity(entity))
                .statusCode(HttpStatus.OK.value())
                .statusMsg("data Successfully Updated.")
                .build(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateEntityStatus(@PathVariable("id") Integer id, @RequestParam EntityStatus status) {
        return new ResponseEntity<>(Response.builder()
                .data(onboardingService.updateEntityStatus(id, status))
                .statusCode(HttpStatus.OK.value())
                .statusMsg("Status Successfully Set...")
                .build(), HttpStatus.OK);
    }

    // New endpoints for EntityCategory
    @PostMapping("/category")
    public ResponseEntity<Response> createCategory(@RequestBody EntityCategory category) {
        return new ResponseEntity<>(Response.builder()
                .cdata(onboardingService.createCategory(category))
                .statusCode(HttpStatus.CREATED.value())
                .statusMsg("Category Created Successfully.")
                .build(), HttpStatus.CREATED);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<Response> getCategoryById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(Response.builder()
                .cdata(onboardingService.getCategoryById(id))
                .statusCode(HttpStatus.FOUND.value())
                .statusMsg("Category Successfully Fetched.")
                .build(), HttpStatus.FOUND);
    }

    @GetMapping("/category")
    public ResponseEntity<Response> getAllCategories() {
        return new ResponseEntity<>(Response.builder()
                .catdatas(onboardingService.getAllCategories())
                .statusCode(HttpStatus.FOUND.value())
                .statusMsg("All Categories Successfully Fetched.")
                .build(), HttpStatus.FOUND);
    }


}
