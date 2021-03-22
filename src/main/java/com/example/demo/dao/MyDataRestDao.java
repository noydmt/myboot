package com.example.demo.dao;

import java.io.Serializable;
import java.util.List;

import com.example.demo.repositories.RestData;

public interface MyDataRestDao <T> extends Serializable {

	public List<T> getAll();
	public RestData getFind(int num);
}
