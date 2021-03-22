package com.example.demo.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import com.example.demo.dao.MyDataRestDaoImpl;
import com.example.demo.repositories.RestData;

@Service
public class MyDataRestService {

	@PersistenceContext
	EntityManager entityManager;

	MyDataRestDaoImpl daoImpl;

	@PostConstruct
	public void init() {
		daoImpl = new MyDataRestDaoImpl(entityManager);
	}

	public List<RestData> getAll(){
		return daoImpl.getAll();
	}

	public RestData getFind(int num){
		return daoImpl.getFind(num);
	}
}
