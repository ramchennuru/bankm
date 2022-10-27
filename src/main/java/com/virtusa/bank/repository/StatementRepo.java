package com.virtusa.bank.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtusa.bank.entity.Statement;

@Repository
public interface StatementRepo extends JpaRepository<Statement,Long>{

	Optional<List<Statement>> findAllByAccountno(long accountno);

}
