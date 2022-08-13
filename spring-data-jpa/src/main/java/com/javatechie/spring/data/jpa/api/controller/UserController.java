package com.javatechie.spring.data.jpa.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javatechie.spring.data.jpa.api.model.APIResponse;
import com.javatechie.spring.data.jpa.api.model.Product;
import com.javatechie.spring.data.jpa.api.model.User;
import com.javatechie.spring.data.jpa.api.service.UserService;

@RestController
@RequestMapping("/spring-data-jpa")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/getUsers")
	public List<User> getAllUsers(){
		return userService.getUsers();
	}
	
	@GetMapping("/getByProfession/{profession}")
	public List<User> findByProfession(@PathVariable String profession){
		return userService.getUserByProfession(profession);
	}
	
	@GetMapping("/getUserCount/{age}")
	public String getCountByAge(@PathVariable int age) {
		long count=userService.getCountByAge(age);
		return "total no of records"+count;
	}
	
	@DeleteMapping("/deleteUser/{name}")
	public List<User> deleteUserByName(@PathVariable String name){
		return userService.deleteUserByName(name);
	}
	
	@GetMapping("/findMultiCondition/{profession}/{age}")
	public List<User> getUsersByProfessionAndAge(@PathVariable String profession, @PathVariable int age) {
		return userService.findByMultiCondition(profession, age);
	}

	@GetMapping("/getUsersIgnoreCase")
	public List<User> getUsersByprofession(@RequestParam("profession") String profession) {
		return userService.getUsersIgnoreCase(profession);
	}

//	@GetMapping("/getSort/{field}")
//	public List<User> getSortedUsers(@PathVariable String field) {
//		return userService.getUserSort(field);
//	}
//
//	@GetMapping("/getPaginatedData")
//	public Page<User> getPaginatedRecords() {
//		return userService.getPaginatedUser();
//	}
//
//	@GetMapping("/getRecordsByCustomQuery")
//	public List<User> getUsersByCustomQuery() {
//		return userService.getUsersCustomQuery();
//	}
	
	
	@GetMapping("/getUsersWithSort/{field}")
    private APIResponse<List<User>> getUsersWithSort(@PathVariable String field) {
        List<User> allUsers = userService.findUsersWithSorting(field);
        return new APIResponse<>(allUsers.size(), allUsers);
    }
	
	
	@GetMapping("/pagination/{offset}/{pageSize}")
    private APIResponse<Page<Product>> getProductsWithPagination(@PathVariable int offset, @PathVariable int pageSize) {
        Page<Product> productsWithPagination = userService.findProductsWithPagination(offset, pageSize);
        return new APIResponse<>(productsWithPagination.getSize(), productsWithPagination);
    }
	
	@GetMapping("/paginationAndSort/{offset}/{pageSize}/{field}")
    private APIResponse<Page<Product>> getProductsWithPaginationAndSort(@PathVariable int offset, @PathVariable int pageSize,@PathVariable String field) {
        Page<Product> productsWithPagination = userService.findProductsWithPaginationAndSorting(offset, pageSize, field);
        return new APIResponse<>(productsWithPagination.getSize(), productsWithPagination);
    }
	
	@GetMapping("/getCustomQuery")
	public List<User> getCustomQuery(){
		return userService.getUserCustomQuery();
	}
}
