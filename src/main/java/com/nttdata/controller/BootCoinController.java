package com.nttdata.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.nttdata.model.BootCoinTransaction;
import com.nttdata.model.BootCoinUser;
import com.nttdata.service.BootCoinService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/bootcoin")
public class BootCoinController {

    @Autowired
    BootCoinService bootCoinService;

    Logger log = LoggerFactory.getLogger(BootCoinController.class);

    @PostMapping("/createUser")
    public Mono<BootCoinUser> createBootCoinUser(@RequestBody BootCoinUser bootCoinUser){
        log.info("Calling service for creating a user for BootCoin");
        return bootCoinService.createBootCoinUser(bootCoinUser);
    }

    @PutMapping("/update")
    public Mono<BootCoinUser> updateBootCoinUser(@RequestBody BootCoinUser bootCoinUser){
        log.info("Calling service for updating user");
        return bootCoinService.updateBootCoinUser(bootCoinUser);
    }

    @GetMapping("/getUser/{id}")
    public Mono<BootCoinUser> getBootCoinUser(@PathVariable("id") String id){
        log.info("Calling service for getting a user");
        return bootCoinService.getBootCoinUser(id);
    }

    @PostMapping("/handletransaction")
    public Mono<BootCoinTransaction> handleTransaction(@RequestBody BootCoinTransaction bootCoinTransaction){
        log.info("Calling service for handling transaction");
        return bootCoinService.handleTransaction(bootCoinTransaction);
    }
}
