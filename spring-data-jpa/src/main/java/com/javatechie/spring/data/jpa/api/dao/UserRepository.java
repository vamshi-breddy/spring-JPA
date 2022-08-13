package com.javatechie.spring.data.jpa.api.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.javatechie.spring.data.jpa.api.model.Product;
import com.javatechie.spring.data.jpa.api.model.User;

public interface UserRepository extends JpaRepository<User,Integer> {

	public List<User> findByProfession(String profession);
	
	public long countByAge(int age);
	
	public List<User> deleteByName(String name);
	
	public List<User> findByProfessionAndAge(String profession,int age);
	
	public List<User> findByProfessionIgnoreCase(String profession);

	
	@Query("select u from User u where u.age>22")
	public List<User> getUserCustomQuery();	
}
