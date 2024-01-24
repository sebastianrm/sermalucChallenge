package com.srm.sermalucChallenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.srm.sermalucChallenge.dto.entities.LogUserEntity;

/**
 * Sermaluc Challenge
 * @author Sebastian Retamal Morales
 * @since 2024-01-24
 * @version 0.0.1-SNAPSHOT
 */
@Repository
public interface LogUserRepository extends JpaRepository<LogUserEntity,String> {

}
