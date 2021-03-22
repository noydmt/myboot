package com.example.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.example.demo.repositories.MsgData;

@Repository
public class MsgDataDaoImpl implements MsgDataDao<MsgData> {
	// インターフェースjava.io.Serializableを実装した場合に必要。
	private static final long serialVersionUID = 1L;

	private EntityManager em;

	public MsgDataDaoImpl() {
		super();
	}
	public MsgDataDaoImpl(EntityManager em) {
		this.em = em;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MsgData> getAll(){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<MsgData> query = cb.createQuery(MsgData.class);
		Root<MsgData> root = query.from(MsgData.class);
		List<MsgData> list = (List<MsgData>) query.select(root);
		return list;
	}

	@Override
	public MsgData findById(long id) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<MsgData> query = cb.createQuery(MsgData.class);
		Root<MsgData> root = query.from(MsgData.class);
		query.select(root).where(cb.equal(root.get("id"), id));
		MsgData list = (MsgData)em.createQuery(query).getSingleResult();
		return list;
	}
}
