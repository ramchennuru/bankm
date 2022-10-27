package com.virtusa.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtusa.bank.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User,Integer>{

	<Optional>User findByNameAndPwd(String name, String pwd);

	<Optional>User findByName(String name);

	<Optional>User findByPhone(long phone);

	

	//Object findByName(String name);

	

}
