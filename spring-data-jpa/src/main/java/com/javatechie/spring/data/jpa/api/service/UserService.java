package com.javatechie.spring.data.jpa.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.javatechie.spring.data.jpa.api.dao.ProductRepository;
import com.javatechie.spring.data.jpa.api.dao.UserRepository;
import com.javatechie.spring.data.jpa.api.model.Product;
import com.javatechie.spring.data.jpa.api.model.User;


@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private ProductRepository productrepo;
	
	@PostConstruct
	public void initDB() {
		List<User> users=new ArrayList<>();
		users.add(new User(111,"x","IT",23));
		users.add(new User(121,"y","EC",22));
		users.add(new User(131,"z","EE",21));
		users.add(new User(141,"p","MEC",24));
		users.add(new User(151,"q","MET",25));
		repository.saveAll(users);

	}
	
  @PostConstruct
  public void initDB2() {
      List<Product> products = IntStream.rangeClosed(1, 200)
              .mapToObj(i -> new Product("product" + i, new Random().nextInt(100), new Random().nextInt(50000)))
              .collect(Collectors.toList());
      productrepo.saveAll(products);
  }
	
	public List<User> getUsers(){
		return repository.findAll();
	}
	
	public List<User> getUserByProfession(String profession){
		return repository.findByProfession(profession);
	}
	
	public long getCountByAge(int age) {
		return repository.countByAge(age);
	}
	
	public List<User> deleteUserByName(String name){
		return repository.deleteByName(name);
	}
	
	public List<User> findByProfessionAndAge(String name, int age){
		return repository.findByProfessionAndAge(name, age);
	}
	
	public List<User> findByMultiCondition(String profession, int age) {
		return repository.findByProfessionAndAge(profession, age);
	}

	public List<User> getUsersIgnoreCase(String profession) {
		return repository.findByProfessionIgnoreCase(profession);
	}

	// sort
//	public List<User> getUserSort(List<Order> field) {
//		return repository.findAll(new Sort(field));
//	}
//
//	// pagination
//	public Page<User> getPaginatedUser() {
//		return repository.findAll(new PageRequest(0, 3));
//	}
//
	
	public List<User> findUsersWithSorting(String field){
        return  repository.findAll(Sort.by(Sort.Direction.ASC,field));
    }
	
	public Page<Product> findProductsWithPagination(int offset,int pageSize){
        Page<Product> products = productrepo.findAll(PageRequest.of(offset, pageSize));
        return  products;
    }
	
	public Page<Product> findProductsWithPaginationAndSorting(int offset,int pageSize,String field){
        Page<Product> products = productrepo.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
        return  products;
    }
	
	public List<User> getUserCustomQuery(){
		return repository.getUserCustomQuery();
	}

	
 }
