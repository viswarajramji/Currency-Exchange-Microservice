package com.ecb.exchangerate.application.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecb.exchangerate.application.entity.ReadAccess;

@Repository
public interface ReadAccessRepo extends JpaRepository<ReadAccess, String> {

}