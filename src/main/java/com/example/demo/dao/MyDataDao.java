package com.example.demo.dao;

import java.io.Serializable;
import java.util.List;

import com.example.demo.repositories.MyData;

/* 継承されているのでSerialize クラスのメソッド処理を子クラスに記述しなくても使用可能。
 * 一方、インターフェースはクラスでなくインスタンス化できない。インターフェースに記述されているメソッドを
 * 子クラスで定義しろと暗示することしかできない。
 */
public interface MyDataDao <T> extends Serializable {
	public List<T> getAll();
	public void create(T data);
	public void update(T data);
	public void delete(T data);
	public T findById(long id);
	public List<T> findByName(String name);
}
