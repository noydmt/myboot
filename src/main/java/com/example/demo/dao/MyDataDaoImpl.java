package com.example.demo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
		List<MyData> list = null;
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<MyData> query = builder.createQuery(MyData.class);
		Root<MyData> root = query.from(MyData.class);
		query.select(root);
		list = (List<MyData>)entityManager.createQuery(query).getResultList();
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
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<MyData> query = builder.createQuery(MyData.class);
		Root<MyData> root = query.from(MyData.class);
		query.select(root).where(builder.equal(root.get("id"), id));
		MyData list = (MyData)entityManager.createQuery(query).getSingleResult();
		return list;
	}

	@Transactional(readOnly=true)
	@Override
	public List<MyData> findByName(String kwName,String kwMail,
			String kwMinAge, String kwMaxAge){
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<MyData> query = builder.createQuery(MyData.class);
		Root<MyData> root = query.from(MyData.class);
		List<Predicate> predicates = new ArrayList<Predicate>();
		// 参照：https://www.it-swarm-ja.tech/ja/java/%E8%A4%87%E6%95%B0%E3%81%AE%E3%83%91%E3%83%A9%E3%83%A1%E3%83%BC%E3%82%BF%E3%83%BC%E3%82%92%E6%8C%81%E3%81%A4jpa%E5%9F%BA%E6%BA%96api/1069474853/

		boolean andFlg = false;

		if(!"".equals(kwName)) {
			String likeName = "%"+kwName+"%";
			andFlg = true;
			predicates.add(builder.like(root.get("name"), likeName));
		}

		if(!"".equals(kwMail)) {
			String likeMail = "%"+kwMail+"%";
			if(andFlg) {
				predicates.add(builder.and(builder.like(root.get("mail"), likeMail)));
			} else {
				predicates.add(builder.like(root.get("mail"), likeMail));
				andFlg = true;
			}
		}

		if(!"".equals(kwMinAge)&&!"".equals(kwMaxAge)) {
			if(andFlg) {
				predicates.add(builder.and(builder.between(root.get("age"), kwMinAge, kwMaxAge)));
			} else {
				predicates.add(builder.between(root.get("age"), kwMinAge, kwMaxAge));
				andFlg = true;
			}
		} else if(!"".equals(kwMinAge)) {
			if(andFlg) {
				predicates.add(builder.and(builder.greaterThanOrEqualTo(root.get("age"), kwMinAge)));
			} else {
				predicates.add(builder.greaterThanOrEqualTo(root.get("age"), kwMinAge));
				andFlg = true;
			}
		} else if(!"".equals(kwMaxAge)) {
			if(andFlg) {
				predicates.add(builder.and(builder.lessThanOrEqualTo(root.get("age"), kwMaxAge)));
			} else {
				predicates.add(builder.lessThanOrEqualTo(root.get("age"), kwMaxAge));
				andFlg = true;
			}
		}
		query.select(root).where(predicates.toArray(new Predicate[]{}));
		List<MyData> list = entityManager.createQuery(query).getResultList();
		return list;
	}
}