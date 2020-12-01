package com.example.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.example.demo.repositories.MyData;

/*
 *  インターフェースをimplementするということはインターフェースに定義されている処理無しメソッドをこのクラスで
 *  実装すべきと暗示している。
 *  一方、継承(extends)は親クラスで定義されているメソッドを呼び出せば同じ処理を実行できる。
 *  C++ では複数のクラスを継承できたがJavaではできない。故に複数のクラスの処理を実装したいという意図で
 *  Javaでは継承とインターフェースの組み合わせが行われる。（実際インターフェースの抽象クラスは子クラスで定義する必要があるが。）
 */
@Repository
public class MyDataDaoImpl implements MyDataDao<MyData> {
	private static final long serialVersionUID = 1L;

	EntityManager entityManager; // エンティティを利用するために必要な機能を提供

	public MyDataDaoImpl() {
		super(); // 親クラスのコンストラクターメソッドを実行 ※書かなくても一緒。親クラスのコンストラクターズに引数を渡したい時には記述すると便利
	}

	public MyDataDaoImpl(EntityManager manager) {
		this();
		this.entityManager = manager;
	}

	@Override
	public List<MyData> getAll(){
		Query query = entityManager.createQuery("from MyData"); // query : sql文に相当するオブジェクト
		@SuppressWarnings("unchecked") // ビルド時に警告が出ないようにする。無くても構わない。
		List<MyData> list = query.getResultList();
		entityManager.clear();
		entityManager.close();
		return list;
	}

	@Override
	public void create(MyData data) {
		entityManager.persist(data);
		entityManager.flush();
	}

	@Override
	public void update(MyData data) {
		entityManager.merge(data);
		entityManager.flush();
	}

	@Override
	public void delete(MyData data) {
		entityManager.remove(data);
		entityManager.flush();
	}

	@Override
	public MyData findById(long id) {
		Query query = entityManager.createQuery("from MyData where id = " + id);
		return (MyData) query.getSingleResult();
	}
}