package com.virtusa.bank.controller;

import java.util.Random;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.bank.entity.Bank;
import com.virtusa.bank.entity.User;
import com.virtusa.bank.repository.BankRepo;
import com.virtusa.bank.repository.UserRepo;

@RestController
@RequestMapping("/usercontroller")
public class UserController {
	@Autowired
	private BankRepo bankRepo;
	
	@Autowired
	private UserRepo userRepo;
	@PostMapping("/register")
	public User register(@RequestBody User user)
	{
		//return userServices.setRegister(user);

		/*String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";
		 Pattern p = Pattern.compile(regex);
		 Matcher m = p.matcher(user.getPwd());*/
		 
		if(String.valueOf(user.getPhone()).length()==10 && String.valueOf(user.getAdhar()).length()==12 &&!(userRepo.findByName(user.getName())!=null))
		{
			Bank bank=new Bank();
			//System.out.println(user.getAdhar());
			//System.out.println(user.getPhone());
			Random r=new Random();
			bank.setAccountno((long) (100000000 + r.nextInt(99999999)));
			
			
			bank.setBalance(0);
			bank.setUname(user.getName());
			//bankRepo.save(bank);
			//return userRepo.save(user);
			user.setBa(bank);
			return userRepo.save(user);
			
		}
		return null;
	}
	/*@GetMapping("/login")
	public User login(@RequestBody User user)
	{
		//return userServices.setLogin(user);
		return (userRepo.findByNameAndPwd(user.getName(),user.getPwd()));
	}*/


}
