package com.nttdata.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.model.BootCoinUser;
import com.nttdata.service.BootCoinService;

@Repository
public interface BootCoinRepository extends ReactiveMongoRepository<BootCoinUser, String> {
}
