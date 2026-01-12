package com.example.kdubackendassessment1sarvesh.service;

import com.example.kdubackendassessment1sarvesh.model.Package;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PackageService {

    List<Package>packageList=new ArrayList<Package>();


    //@PreAuthorize("hasRole('MANAGER')")
//    public Package addPackage( Package p) throws InterruptedException {
//        p.setStatus("Pending");
//        Thread.sleep(3000);
//        p.setStatus("Sorted");
//        PackageList.add(p);
//        return p;
//    }

    public Package addPackage(Package p) throws InterruptedException{
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        p.setStatus("Pending");
        log.info(p.getStatus());
        scheduler.schedule(()->{
                    p.setStatus("Sorted");
                    log.info(p.getStatus());
                },
                3000,
                TimeUnit.MILLISECONDS
        );
        packageList.add(p);
        return p;
    }

    public double getAnalytics(){
        double revenue=packageList
                .stream()
                .filter(x->x.getStatus()=="Sorted")
                .mapToDouble(x->x.getWeight())
                .sum();
        return revenue;
    }
}
