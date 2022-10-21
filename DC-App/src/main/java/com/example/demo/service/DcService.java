package com.example.demo.service;

import java.util.List;

import com.example.demo.binding.Child;
import com.example.demo.binding.CitizenApp;
import com.example.demo.binding.DcSummary;
import com.example.demo.binding.Education;
import com.example.demo.binding.Income;
import com.example.demo.binding.PlanSelection;

public interface DcService {
	
	public Integer findAppById(Integer appId);
	
	public List <String> getPlanNames();
	
	public Integer planSelection(PlanSelection planSelection);
	
	public Integer saveIncomeData(Income income);
	
	public Integer saveEducation(Education education);
	
	public Integer saveChildrens(List<Child> childs);
	
	public DcSummary getSummary(Integer caseNumber);

	Integer loadCaseNum(Integer appId);
	
	
	}
