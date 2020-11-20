package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.MyData;
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

	// 全レコード取得
	public List<MyData> selectAll() {
		List<MyData> list = repository.findAll();
		return list;
	}

	// 登録
	public void create(MyData data) {
		repository.saveAndFlush(data);
	}

	// 一件、レコード取得
	public MyData findById(long id) {
		Optional<MyData> data = repository.findById(id);
		return data.get();
	}

	// 一件、レコード更新
	public void update(MyData data) {
		repository.saveAndFlush(data);
	}

	// 一件、レコード削除
	public void delete(int id) {
		long longId = id;
		repository.deleteById(longId);
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
