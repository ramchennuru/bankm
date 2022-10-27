package com.virtusa.bank.controller;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.virtusa.bank.entity.Bank;
import com.virtusa.bank.entity.Statement;
import com.virtusa.bank.entity.User;
import com.virtusa.bank.repository.BankRepo;
import com.virtusa.bank.repository.StatementRepo;
import com.virtusa.bank.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bankcontroller")
public class BankController {
	@Autowired
	UserRepo userRepo;
	@Autowired
	BankRepo bankRepo;
	@Autowired
	StatementRepo statementRepo;

	@GetMapping(value = "/sendSMS")
	public String sendSMS() {

		String TWILIO_ACCOUNT_SID="AC7e8ba02807ed926d076bf94eb56361b1";
		String TWILIO_AUTH_TOKEN="f91128c9ca5d1845643e75040f63a931";
		Twilio.init(TWILIO_ACCOUNT_SID,TWILIO_AUTH_TOKEN);
		//Twilio.init(System.getenv("TWILIO_ACCOUNT_SID"), System.getenv("TWILIO_AUTH_TOKEN"));

		Message.creator(new PhoneNumber("8466816189"),
				new PhoneNumber("+13854255387"), "Hello from Twilio 📞").create();

		return "index";
		//return new ResponseEntity<String>("Message sent successfully", HttpStatus.OK);
	}


	@PostMapping("/deposit/{depoamount}")
	public Bank deposit(@RequestBody Bank bank,@PathVariable("depoamount") double depoamount)
	{
		Bank bankdeposit=bankRepo.findAll().stream().filter(x->x.getAccountno().equals(bank.getAccountno())).findFirst().orElse(null);
		System.out.println(bankdeposit);
		if(depoamount>0)
		{
			//Bank bank1=bankRepo.findByUname(bank.getUname());
			bankdeposit.setBalance(bankdeposit.getBalance()+depoamount);
			//bankdeposit.setAccountno(bank.getAccountno());
			//bank1.setUname(bank.getUname());
			Statement stmt=new Statement();
			stmt.setAccountno(bankdeposit.getAccountno());
			stmt.setBalance(bankdeposit.getBalance());
			stmt.setAvailbal(bankdeposit.getBalance());
			stmt.setType("credit");
			statementRepo.save(stmt);
//			String TWILIO_ACCOUNT_SID="AC7e8ba02807ed926d076bf94eb56361b1";
//			String TWILIO_AUTH_TOKEN="f91128c9ca5d1845643e75040f63a931";
//			Twilio.init(TWILIO_ACCOUNT_SID,TWILIO_AUTH_TOKEN);
//
//				//Twilio.init(System.getenv("AC7e8ba02807ed926d076bf94eb56361b1"), System.getenv("f91128c9ca5d1845643e75040f63a931"));
//			bankRepo.save(bankdeposit);
//			Message message=Message.creator(new PhoneNumber("9014822833"),
//					new PhoneNumber("9676835670"), "Hello from Twilio 📞").create();
//			//return new ResponseEntity<Object>("sent successfully",HttpStatus.OK);
			return bankRepo.save(bankdeposit);
		}
		return null;
	}
	@PutMapping("/withdraw/{depoamount}")
	public Bank withdraw(@RequestBody Bank bank,@PathVariable("depoamount") double depoamount)
	{
		Bank bankwith=bankRepo.findAll().stream().filter(x->x.getUname().equals(bank.getUname())).findFirst().orElse(null);


		//Bank bankwith=bankRepo.findByUname(bank.getUname());
		if(bankwith.getBalance()>=depoamount) {
			bankwith.setBalance(bankwith.getBalance() - depoamount);
			Statement stmt = new Statement();
			stmt.setAccountno(bankwith.getAccountno());
			stmt.setBalance(bankwith.getBalance());
			stmt.setAvailbal(bankwith.getBalance());
			stmt.setType("debit");
			statementRepo.save(stmt);

		}
		else
		{
			return null;
		}
		return bankRepo.save(bankwith);
	}
	
	@GetMapping("/statements")
	public Optional<List<Statement>> statement(@RequestBody Bank bank)
	{

		//LoginController loginc=new LoginController();
		//System.out.println(LoginController.getCustomername());

		Bank bankst=bankRepo.findAll().stream().filter(x->x.getAccountno().equals(bank.getAccountno())).findFirst().orElse(null);


		//Bank bankst=bankRepo.findByUname(bank.getUname());
		
		//System.out.println(bankst);
		//return bankServices.getTransactions(bank);
		String filepath="C:\\Users\\ramprasadchennuru\\Desktop\\simple.pdf";

//		try {
//			Document document=new Document(PageSize.A4.rotate());
//			PdfWriter writer= PdfWriter.getInstance(document,new FileOutputStream(filepath));
//			document.open();
//			Paragraph p=new Paragraph();
//			p.setFont(new Font(Font.FontFamily.HELVETICA,20));
//			Chunk c=new Chunk("AccountHolderName:"+bankst.getUname());
//			p.add(c);
//			p.add(String.valueOf(statementRepo.findAllByAccountno(bankst.getAccountno())));
//			document.add(p);
//			document.close();
//		} catch (FileNotFoundException | DocumentException e) {
//			e.printStackTrace();
//		}
		return  statementRepo.findAllByAccountno(bankst.getAccountno());
	}
	@DeleteMapping("/delete/{accountno}")
	public ResponseEntity<String>delete(@RequestBody User user,@PathVariable long accountno)
	{
		// bankServices.getDelete(bank,name,pwd);
		
		if(user.getName().equals("adminramya") && user.getPwd().equals("Admin!@#123"))
		{
			//Bank sender=bankRepo.findByUname(LoginController.getCustomername());
			//if(sender.getUname().equals())
			Bank bank3=bankRepo.findAll().stream().filter(x->x.getAccountno().equals(accountno)).findFirst().orElse(null);
			//Bank bank3=bankRepo.findByAccountno(accountno);
			User user1=userRepo.findAll().stream().filter(x->x.getName().equals(bank3.getUname())).findFirst().orElse(null);
			//User user1=userRepo.findByName(bank3.getUname());
			userRepo.delete(user1);
			bankRepo.delete(bank3);
			return new ResponseEntity<>("account successfully deleted", HttpStatus.OK);
			
		}
		
		return new ResponseEntity<>("account  doesn't deleted", HttpStatus.FORBIDDEN);
		
		
	}
	@PutMapping("/updatePhonenName/{accountno}/{phoneno}")
	public User update(@RequestBody User user,@PathVariable long accountno,@PathVariable long phoneno)
	{
		if(user.getName().equals("adminramya") && user.getPwd().equals("Admin!@#123"))
		{
			Bank bankupdate=bankRepo.findAll().stream().filter(x->x.getAccountno().equals(accountno)).findFirst().orElse(null);
			//Bank bankupdate=bankRepo.findByAccountno(accountno);
			//bankupdate.setUname(bank.getUname());
			User user1=userRepo.findAll().stream().filter(x->x.getName().equals(bankupdate.getUname())).findFirst().orElse(null);
			//long a=user.getBa().getAccountno();
			bankupdate.setUname(user.getName());
			user1.setName(user.getName());
			//long p=user.getPhone();
			user1.setPhone(phoneno);
			bankRepo.save(bankupdate);
			return userRepo.save(user1);
		}
		return null;
	}
	@PutMapping("/updatePhone/{account}/{phone}")
	public User updatephone(@RequestBody User user,@PathVariable("account") long accountno,@PathVariable("phone") long phone)
	{
		if(user.getName().equals("adminramya") && user.getPwd().equals("Admin!@#123"))
		{
			User userp=userRepo.findByName(bankRepo.findByAccountno(accountno).getUname());
			userp.setPhone(phone);
			return userRepo.save(userp);
		}
		return null;
		
		
	}
	@PutMapping("updateName/{account}/{name}")
	public User updatename(@RequestBody User user,@PathVariable("account") long accountno,@PathVariable("name") String name)
	{
		if(user.getName().equals("adminramya") && user.getPwd().equals("Admin!@#123"))
		{
			User usern=userRepo.findByName(bankRepo.findByAccountno(accountno).getUname());
			usern.setName(name);
			Bank bankn=bankRepo.findByAccountno(accountno);
			bankn.setUname(name);
			bankRepo.save(bankn);
			return userRepo.save(usern);
		}
		return null;
		
		
	}
	@PutMapping("/transfer/{accountno}/{amount}")
	public Bank transfer(@RequestBody Bank bank,@PathVariable("accountno") long accountno,@PathVariable("amount") double amount)
	{	
		
		//if(bank.getUname().equals(username))
		//{
		//return bankServices.getTransfer(bank,accountno);
		Bank sender=bankRepo.findAll().stream().filter(x->x.getUname().equals(bank.getUname())).findFirst().orElse(null);
		Bank receiver=bankRepo.findAll().stream().filter(x->x.getAccountno().equals(accountno)).findFirst().orElse(null);
		//bankRepo.findByAccountno(accountno);
		if(sender.getBalance()>=amount && amount>0)
		{
			sender.setBalance(sender.getBalance()-amount);
			bankRepo.save(sender);
			receiver.setBalance(amount+receiver.getBalance());
			
			
			Statement stmt=new Statement();
			stmt.setAccountno(sender.getAccountno());
			stmt.setBalance(amount);
			stmt.setAvailbal(sender.getBalance());
			stmt.setType("debit");
			statementRepo.save(stmt); 
			
			
			
			Statement stmt1=new Statement();
			stmt1.setAccountno(receiver.getAccountno());
			stmt1.setBalance(amount);
			stmt1.setAvailbal(receiver.getBalance());
			stmt1.setType("credit");
			statementRepo.save(stmt1); 
			return bankRepo.save(receiver);
			
		}
		
		return null;
	}
	@PutMapping("/create_debitcard")
	public Bank craetedebitcard(@RequestBody Bank bank)
	{
		Bank bankdebitcre=bankRepo.findAll().stream().filter(x->x.getUname().equals(bank.getUname())).findFirst().orElse(null);
		//bankRepo.findByUname(bank.getUname());
		//if(bank.getUname().equals(username))
		//{
		if(bankdebitcre.getDebitcardno()==0)
		{
			Random ran=new Random();
			long debitcardno=1000000000+ran.nextInt(9999999);
			int debit_pin=1000+ran.nextInt(999);
			//Bank bank3=bankRepo.findByAccountno(bankdebitcre.getAccountno());
			//bankdebitcre.setAccountno(a);
			bankdebitcre.setDebitcardno(debitcardno);
			bankdebitcre.setDebitPin(debit_pin);
			return bankRepo.save(bankdebitcre);
		}
		return null;
		
		
	}
	@PutMapping("/debitby_debitcard")
	public Bank debit(@RequestBody Bank bank)
	{	
		
		
		//System.out.println(LoginController.getCustomername());
		Bank bankcredit=bankRepo.findAll().stream().filter(x->x.getUname().equals(bank.getUname())).findFirst().orElse(null);
		//bankRepo.findByUname(bank.getUname());
		System.out.println(bankcredit.getDebitPin());
		System.out.println(bank.getDebitPin());
		System.out.println(bank.getBalance());
		System.out.println(bankcredit.getBalance());
		int c=bankcredit.getDebitPin();
		int d=bank.getDebitPin();
			
		if(bankcredit.getBalance()>= bank.getBalance()&& c==d)
		{
			//System.out.println("stage1");
			
			
			System.out.println("reached");
			bankcredit.setBalance(bankcredit.getBalance()-bank.getBalance());
			
			Statement stmt1=new Statement();
			stmt1.setAccountno(bankcredit.getAccountno());
			stmt1.setAvailbal(bankcredit.getBalance());
			stmt1.setBalance(bankcredit.getBalance());
			stmt1.setType("debitby_debitcard");
			statementRepo.save(stmt1);
			return bankRepo.save(bankcredit);
			
		}
		return null;
		
	}
	@PutMapping("/creditby_debitcard")
	public Bank credit(@RequestBody Bank bank)
	{
		
		//if(bank.getUname().equals(username))
		//{
		
		Bank bankcredit=bankRepo.findAll().stream().filter(x->x.getUname().equals(bank.getUname())).findFirst().orElse(null);
		//bankRepo.findByUname(bank.getUname());
		int a=bankcredit.getDebitPin();
		int b=bank.getDebitPin();
		System.out.println(a+","+b);
		
		if(a==b)
		{
		bankcredit.setBalance(bank.getBalance()+bankcredit.getBalance());
		Statement st=new Statement();
		st.setAccountno(bankcredit.getAccountno());
		st.setBalance(bankcredit.getBalance());
		st.setAvailbal(bankcredit.getBalance());
		st.setType("creditedby_debitcard");
		statementRepo.save(st);
		return bankRepo.save(bankcredit);
		}
		return null;
	}
	@PutMapping("/upi_transaction/{phone}/{amount}")
	public Bank upi(@RequestBody Bank bank,@PathVariable("phone") long phone,@PathVariable("amount") double amount)
	{	

		
		Bank sender=bankRepo.findAll().stream().filter(x->x.getUname().equals(bank.getUname())).findFirst().orElse(null);
		//bankRepo.findByUname(bank.getUname());
		//Bank receiver=bankRepo.findByPhone(phone);
		List<User> obj =userRepo.findAll();
		List<Long> phoneno=obj.stream().map( p -> p.getPhone()).collect(Collectors.toList());
		User receiver=userRepo.findByPhone(phone);
		long a=receiver.getBa().getAccountno();
		//User rec=bankRepo.findByUname(receiver.get);
		Bank b=bankRepo.findByAccountno(a);
		if(sender.getBalance()>=amount &&phoneno.contains(phone))
		{
			sender.setBalance(sender.getBalance()-amount);
			bankRepo.save(sender);
			b.setBalance(receiver.getBa().getBalance()+amount);
			 Bank bm=bankRepo.save(b);
			Statement str=new Statement();
			str.setAccountno(a);
			str.setAvailbal(b.getBalance());
			str.setBalance(amount);
			str.setType("upi credit");
			
			Statement sts=new Statement();
			sts.setAccountno(sender.getAccountno());
			sts.setAvailbal(sender.getBalance());
			sts.setBalance(amount);  
			sts.setType("upi debit");
			return bm;
			
		}
		return null;
	}
	@PutMapping("/create_creditcard/{accountno}")
	public Bank creditcard(@RequestBody Bank bank,@PathVariable("accountno") long accountno)
	{	
		
		//if(bank.getUname().equals(username))
		//{
		
		Bank bankcreatecredit=bankRepo.findAll().stream().filter(x->x.getUname().equals(bank.getUname())).findFirst().orElse(null);
		//bankRepo.findByUname(bank.getUname());
		if(bankcreatecredit.getCreditcardNo()==0)
		{
		Random ran=new Random();
		long creditcardno=1000000000+ran.nextInt(9999999);
		int credit_pin=1000+ran.nextInt(999);
		//Bank bank3=bankRepo.findByAccountno(bank.getAccountno());
		//bankcreatecredit.
		bankcreatecredit.setCreditcardBal(bankcreatecredit.getBalance()*2);
		bankcreatecredit.setCreditcardNo(creditcardno);
		bankcreatecredit.setCreditcardPin(credit_pin);
		return bankRepo.save(bankcreatecredit);
		}
		//}
		return null;
	}
	@PutMapping("/creditcard_transaction")
	public Bank credit_transaction(@RequestBody Bank bank)
	{	
		
		
		Bank existingBank=bankRepo.findAll().stream().filter(x->x.getUname().equals(bank.getUname())).findFirst().orElse(null);
		//bankRepo.findByUname(bank.getUname());
		int bc=existingBank.getCreditcardPin();
		int dc=bank.getCreditcardPin();
		if(existingBank.getCreditcardBal()>=bank.getCreditcardBal() && bc==dc)
		{
			existingBank.setCreditcardBal(existingBank.getCreditcardBal()-bank.getCreditcardBal());
			return bankRepo.save(existingBank);
			
		}
		return null;
		
	}
	
	


}

