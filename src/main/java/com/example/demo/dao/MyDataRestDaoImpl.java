package com.example.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.example.demo.repositories.RestData;

@Repository
public class MyDataRestDaoImpl implements MyDataRestDao<RestData> {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	public EntityManager em;

	public MyDataRestDaoImpl(EntityManager em) {
		this.em = em;
	}

	@Override
	public List<RestData> getAll() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<RestData> query = builder.createQuery(RestData.class);
		Root<RestData> root = query.from(RestData.class);
		query.select(root);
		List<RestData> list = (List<RestData>)em.createQuery(query).getResultList();
		return list;
	}

	@Override
	public RestData getFind(int num) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<RestData> query = builder.createQuery(RestData.class);
		Root<RestData> root = query.from(RestData.class);
		query.select(root).where(builder.equal(root.get("id"), num));
		RestData data = (RestData)em.createQuery(query).getSingleResult();
		return data;
	}
}
