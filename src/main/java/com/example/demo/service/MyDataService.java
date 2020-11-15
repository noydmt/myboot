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

	@Autowired // 自動的にインスタンスが生成 => MyDataRepositoryってインターフェースだよ？ => Spring MVC により無名クラスのインスタンスが作成
	MyDataRepository repository; // => MyDataRepository(インターフェース) => 無名クラス => インスタンス => Bean => Beanのインスタンス

	public List<MyData> selectAll() {
		List<MyData> list = repository.findAll();
		return list;
	}

	public void create(MyData data) {
		repository.saveAndFlush(data);
	}

	public MyData findById(long id) {
		Optional<MyData> data = repository.findById(id);
		return data.get();
	}

	public void update(MyData data) {
		repository.saveAndFlush(data);
	}

	public boolean exist(MyData data) {
		long id = data.getId();
		if(!(repository.existsById(id))) {
			return false;
		}
		return true;
	}
}
