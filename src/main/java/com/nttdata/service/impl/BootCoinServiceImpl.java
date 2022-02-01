package com.nttdata.service.impl;

import com.netflix.discovery.converters.Auto;
import com.nttdata.model.BootCoinTransaction;
import com.nttdata.model.BootCoinUser;
import com.nttdata.repository.BootCoinRepository;
import com.nttdata.service.BootCoinService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BootCoinServiceImpl implements BootCoinService {
    @Autowired
    BootCoinRepository bootCoinRepository;

    @Autowired
    WebClient webClientYanki;

    @Autowired
    WebClient webClientAccount;

    Logger log = LoggerFactory.getLogger(BootCoinServiceImpl.class);

    @Override
    public Mono<BootCoinUser> createBootCoinUser(BootCoinUser bootCoinUser) {
        log.info("Creating user: "+bootCoinUser);
        return bootCoinRepository.insert(bootCoinUser);
    }

    @Override
    public Mono<BootCoinUser> updateBootCoinUser(BootCoinUser bootCoinUser) {
        return bootCoinRepository.save(bootCoinUser);
    }

    @Override
    public Mono<BootCoinUser> getBootCoinUser(String id) {
        log.info("Searching for user with Id: "+id);
        return bootCoinRepository.findById(id);
    }

    @Override
    public Mono<BootCoinTransaction> handleTransaction(BootCoinTransaction bootCoinTransaction) {
        Mono<BootCoinUser> origin=this.getBootCoinUser(bootCoinTransaction.getBuyer()).flatMap(e->{
          if (e.getMoney()<bootCoinTransaction.getPrice()) return Mono.empty();
          return Mono.just(e);
        }).switchIfEmpty(Mono.empty());

        Mono<BootCoinUser> destiny=this.getBootCoinUser(bootCoinTransaction.getSeller()).flatMap(e->{
            if (e.getAmount()<bootCoinTransaction.getBcAmount()) return Mono.empty();
            return Mono.just(e);
        }).switchIfEmpty(Mono.empty());

        return Flux.zip(origin,destiny, (a,b)->{
            a.setAmount(a.getAmount()+bootCoinTransaction.getBcAmount());
            a.setMoney(a.getMoney()-bootCoinTransaction.getPrice());
            b.setAmount(b.getAmount()-bootCoinTransaction.getBcAmount());
            b.setMoney(b.getMoney()+bootCoinTransaction.getPrice());
            this.updateBootCoinUser(a);
            this.updateBootCoinUser(b);
            bootCoinTransaction.setStatus("Completed");
            return bootCoinTransaction;
            } ).next();
    }
}
