package com.example.demo.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.MsgDataDaoImpl;
import com.example.demo.repositories.MsgData;
import com.example.demo.repositories.MsgDataRepository;

@Service
@Transactional
public class MsgDataService {

	@Autowired
	MsgDataRepository repository;
	
	@Autowired
	MsgDataDaoImpl dao;
	
	@PersistenceContext
	EntityManager em;
	
	@PostConstruct
	public void init() {
		dao = new MsgDataDaoImpl(em);
	}
	
	public List<MsgData> getMsgDataList(long id){
		
		return null;
	}
	
}
