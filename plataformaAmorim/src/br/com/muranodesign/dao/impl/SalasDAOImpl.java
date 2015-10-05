package br.com.muranodesign.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.muranodesign.dao.SalasDAO;
import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.model.Salas;

public class SalasDAOImpl extends AbstractHibernateDAO implements SalasDAO{
	
	public SalasDAOImpl(HibernatePersistenceContext persistenceContext) {
		super(persistenceContext);
		
	}
	
	public List<Salas> listAll() {
		
		Criteria criteria = getSession().createCriteria(Salas.class);
		List<Salas> result = criteria.list();
		
		
		return result;
	}
	
	public void criar(Salas c) {
		synchronized (SalasDAOImpl.class) {
			getSession().persist(c);
			getSession().flush();

		}
	}
	
	public void deletar(Salas c) {
		getSession().delete(c);
		getSession().flush();
	}
	
	public void atualizar(Salas p) {
		getSession().merge(p);
		getSession().flush();
	}
	

	public List<Salas> listarKey(int key){
		Criteria criteria = getSession().createCriteria(Salas.class);
		criteria.add(Restrictions.eq("Idsalas", key));
		List<Salas> result = criteria.list();
		return result;
	}
	
}
