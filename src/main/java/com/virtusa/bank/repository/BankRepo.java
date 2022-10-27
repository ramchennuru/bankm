package com.virtusa.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtusa.bank.entity.Bank;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface BankRepo extends JpaRepository<Bank,Long>{

	<Optional>Bank findByUname(String uname);

	<Optional>Bank findByAccountno(long accountno);

	<Optional>Bank deleteByAccountno(long accountno);

	Bank findByDebitPin(Integer debitPin);

	

	Bank findByCreditcardPin(Integer creditcardPin);

	Bank findByCreditcardNo(long creditcardno);

}
