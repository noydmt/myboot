package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.MyDataDaoImpl;
import com.example.demo.repositories.MyData;
import com.example.demo.repositories.MyDataRepository;

@Service
@Transactional
public class MyDataService {
	/*
	 * 1, @Repository がつけられたインターフェースを検索し、自動的にそのクラスとインスタンスが作成され、インスタンスがBeanとしてアプリに登録される
	 * 2, Controller クラスがロードされる際に@Autowiredが指定されているフィールドが存在する時、登録済みのBeanから同じクラスのものを検索し、自動的に
	 * 　　そのBeanのインスタンスをフィールドに割り当てる
	 */
	@Autowired // 自動的にインスタンスが生成 => MyDataRepositoryってインターフェースだよ？ => Spring MVC により無名クラスのインスタンスが作成
	MyDataRepository repository; // => MyDataRepository(インターフェース) => 無名クラス => インスタンス => Bean => Beanのインスタンス

	/*
	 * entityManager の Bean を取得してフィールドに設定。springBoot では自動的にインスタンスがBeanとして登録されている
	 * @Autowiredとの違いがわからない
	 */
	@PersistenceContext
	EntityManager entityManager;

	@PostConstruct
	public void init() {
		dao = new MyDataDaoImpl(entityManager);
	}
	MyDataDaoImpl dao;

	// 全レコード取得
	public List<MyData> selectAll() {
		List<MyData> list = dao.getAll();
		return list;
	}

	// 登録
	public void create(MyData data) {
		dao.create(data);
	}

	// 一件、レコード取得
	public MyData findById(long id) {
		MyData data = dao.findById(id);
		return data;
	}

	// 一件、レコード更新
	public void update(MyData data) {
		dao.update(data);
	}

	// 一件、レコード削除
	public void delete(int id) {
		MyData data = dao.findById(id);
		dao.delete(data);
	}

	// 検索
	public List<MyData> search(String kwName, String kwMail, String kwMinAge, String kwMaxAge){
		List<MyData> list = new ArrayList<MyData>();
		if("".equals(kwName) && "".equals(kwMail) && "".equals(kwMinAge) && "".equals(kwMaxAge)) {
			list = dao.getAll();
		} else {
			list = dao.findByName(kwName,kwMail,kwMinAge,kwMaxAge);
		}
		return list;
	}

	// 存在チェック
	public boolean exist(MyData data) {
		long id = data.getId();
		if(!repository.existsById(id)) {
			return false;
		}
		return true;
	}

	// 存在チェック
	public boolean exist(int id) {
		long longId = id;
		if(!(repository.existsById(longId))) {
			return false;
		}
		return true;
	}
}
