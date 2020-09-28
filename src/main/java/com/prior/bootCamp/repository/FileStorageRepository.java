package com.prior.bootCamp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prior.bootCamp.entity.FileStorage;

@Repository
public interface FileStorageRepository extends JpaRepository<FileStorage, Long>{
}