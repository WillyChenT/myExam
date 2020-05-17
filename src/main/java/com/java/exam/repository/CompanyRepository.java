package com.java.exam.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.java.exam.data.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>{
	
	@Query(value = "select c.* from COMPANY c where 1=1  "
			+ "AND (:id is null or c.id =:id)"
			+ "AND (:name is null or c.NAME =:name)"
			+ "AND (:address is null or c.ADDRESS =:address)"
			,nativeQuery = true)
	List<Company> query(@Param("id")int id,	@Param("name")String name, @Param("address")String address);
	
	List<Company> queryById(@Param("id")int id);
	List<Company> queryByName(@Param("name")String name);

	
	@Query(value = "select c.* from COMPANY c where 1=1  "
			+ "AND (:name is null or c.NAME =:name)"
			+ "AND (:address is null or c.ADDRESS =:address)"
			,nativeQuery = true)
	List<Company> query(@Param("name")String name, @Param("address")String address);
	
	@Query(value = "select c.* from COMPANY c where 1=1  "
			+ "AND (:id is null or c.id =:id)"
			+ "AND (:name is null or c.NAME =:name)"
			,nativeQuery = true)
	List<Company> queryCompany(@Param("id")int id, @Param("name")String name);
	
	
}
