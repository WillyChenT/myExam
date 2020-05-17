package com.java.exam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.java.exam.data.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{
	
	@Query(value = "select c.* from Client c where 1=1  "
			+ "AND (:id is null or c.id =:id)"
			+ "AND (:company_Id is null or c.company_Id =:company_Id)"
			+ "AND (:name is null or c.NAME =:name)"
			+ "AND (:email is null or c.email =:email)"
			+ "AND (:phone is null or c.phone =:phone)"
			,nativeQuery = true)
	List<Client> query(@Param("id")int id,	@Param("company_Id")String company_Id, @Param("name")String name
			,	@Param("email")String email, @Param("phone")String phone);
	
	List<Client> queryById(@Param("id")int id);
	List<Client> queryByName(@Param("name")String name);
	
	@Query(value = "select c.* from Client c where 1=1  "
			+ "AND (:company_Id is null or c.company_Id =:company_Id)"
			+ "AND (:name is null or c.NAME =:name)"
			+ "AND (:email is null or c.email =:email)"
			+ "AND (:phone is null or c.phone =:phone)"
			,nativeQuery = true)
	List<Client> query(@Param("company_Id")String company_Id, @Param("name")String name
			,	@Param("email")String email, @Param("phone")String phone);
}