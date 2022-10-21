package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.binding.Child;
import com.example.demo.binding.CitizenApp;
import com.example.demo.binding.DcSummary;
import com.example.demo.binding.Education;
import com.example.demo.binding.Income;
import com.example.demo.binding.PlanSelection;
import com.example.demo.entity.DcCaseEntity;
import com.example.demo.entity.DcChildrenEntity;
import com.example.demo.entity.DcEducationEntity;
import com.example.demo.entity.DcIncomeEntity;
import com.example.demo.entity.PlanEntity;
import com.example.demo.repository.DcCaseRepo;
import com.example.demo.repository.DcChildrenRepo;
import com.example.demo.repository.DcEducationRepo;
import com.example.demo.repository.DcIncomeRepo;
import com.example.demo.repository.PlanRepository;

public class DcServiceImpl implements DcService {
	
	@Autowired
	private DcCaseRepo dcCaseRepo;
	
	@Autowired
	private PlanRepository planRepo;
	
	@Autowired
	private DcIncomeRepo incomeRepo;
	
	@Autowired
	private DcEducationRepo eduRepo;
	
	@Autowired
	private DcChildrenRepo childRepo;

	@Override
	public Integer loadCaseNum(Integer appId) {
		
		DcCaseEntity findByAppId = dcCaseRepo.findByAppId(appId);
		if(findByAppId != null) {
			return findByAppId.getCaseNum();
		}
		
		return null;
	}

	@Override
	public List<String> getPlanNames() {
		List<PlanEntity> findAll = planRepo.findAll();
		
		List<String> plans = new ArrayList<>();
		
		for(PlanEntity entity : findAll) {
			plans.add(entity.getPlanName());
		}
		return plans;
	}
		
	@Override
	public Integer planSelection(PlanSelection planSelection) {
		DcCaseEntity entity = new DcCaseEntity();
		
		entity.setPlanId(planSelection.getPlanId());
		entity.setAppId(planSelection.getAppId());
		
		entity = dcCaseRepo.save(entity);
		
		if(entity.getCaseNum()!=null) {
			return entity.getCaseNum();
		}
		
		return null;
	}

	@Override
	public Integer saveIncomeData(Income income) {
		
		DcIncomeEntity entity = new DcIncomeEntity();
		BeanUtils.copyProperties(income, entity);
		incomeRepo.save(entity);
		return income.getCaseNum();
		
	}

	@Override
	public Integer saveEducation(Education education) {
		DcEducationEntity entity = new DcEducationEntity();
		BeanUtils.copyProperties(education, entity);
		eduRepo.save(entity);
		
		return education.getCaseNum();
	}

	@Override
	public Integer saveChildrens(List<Child> childs) {
		for(Child c : childs) {
			DcChildrenEntity entity =  new DcChildrenEntity();
			BeanUtils.copyProperties(c, entity);
			childRepo.save(entity);
		}
			
		return childs.get(0).getCaseNum();
	}

	@Override
	public DcSummary getSummary(Integer caseNumber) {
		
		DcIncomeEntity income = incomeRepo.findByCaseNum(caseNumber);
		DcEducationEntity education = eduRepo.findByCaseNum(caseNumber);
		DcEducationEntity childs = eduRepo.findByCaseNum(caseNumber);
		Optional<DcCaseEntity> dcCase = dcCaseRepo.findById(caseNumber);
		
		if(dcCase.isPresent()) {
			dcCase.get().getPlanId();
			Optional<PlanEntity>plan = planRepo.findById(caseNumber);
			if(plan.isPresent()) {
				plan.get().getPlanName();
			}
		}
		DcSummary summary = new DcSummary();
		
		summary.setPlanName(planName);
		
		Income income = new Income();
		BeanUtils.copyProperties(incomeEntity, income);
		
		Education edu = new Education();
		BeanUtils.copyProperties(EducationEntity, education);
		summary.setEducation(edu);
		
		List<Child> childs = new ArrayList();
		for(DcChildrenEntity c : childEntity) {
			child ch = new Child();
			BeanUtils.copyProperties(entity, ch);
			childs.add(ch);
			
		}
		summary.setChilds(childs);
		return summary;
	}

	

}
