package com.example.kdubackendassessment1sarvesh.controller;

import com.example.kdubackendassessment1sarvesh.model.Package;
import com.example.kdubackendassessment1sarvesh.service.PackageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PackageController {
    PackageService packageService=new PackageService();

    @PostMapping("/package")
    public ResponseEntity addPack(@RequestBody Package x) throws InterruptedException {
            Package pa=packageService.addPackage(x);
            return new ResponseEntity(pa, HttpStatus.ACCEPTED);
    }

    @GetMapping("/analytics/revenue")
    public ResponseEntity analytics(){
        double revenue=packageService.getAnalytics();
        return new ResponseEntity(revenue,HttpStatus.OK);
    }

}
