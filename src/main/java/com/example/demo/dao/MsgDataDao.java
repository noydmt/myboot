package com.example.demo.dao;

import java.io.Serializable;
import java.util.List;

import com.example.demo.repositories.MsgData;

public interface MsgDataDao<T> extends Serializable {

	public List<MsgData> getAll();
	public MsgData findById(long id);
}
