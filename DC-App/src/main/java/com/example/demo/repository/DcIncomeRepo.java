package com.example.demo.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.DcIncomeEntity;

public interface DcIncomeRepo extends JpaRepository<DcIncomeRepo, Serializable> {

	public Integer save(DcIncomeEntity entity);
	
	public DcIncomeEntity findByCaseNum(Integer caseNumber);

	

}
