package com.srm.sermalucChallenge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.srm.sermalucChallenge.dto.ParentUser;


/**
 * Sermaluc Challenge
 * @author Sebastian Retamal Morales
 * @since 2024-01-24
 * @version 0.0.1-SNAPSHOT
 */
@Repository
public interface UserRepository extends JpaRepository<ParentUser,String>{

	/**
	 * @param email
	 */	
	List<ParentUser> findByEmail(String email);
	

}
