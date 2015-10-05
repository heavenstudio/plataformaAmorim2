package br.com.muranodesign.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.muranodesign.dao.SemanaDAO;
import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.model.Semana;

public class SemanaDAOImpl extends AbstractHibernateDAO implements SemanaDAO{
	
	public SemanaDAOImpl(HibernatePersistenceContext persistenceContext) {
		super(persistenceContext);
		
	}
	
	public List<Semana> listAll() {
		
		Criteria criteria = getSession().createCriteria(Semana.class);
		List<Semana> result = criteria.list();
		
		
		return result;
	} 
	
	public void criar(Semana c) {
		synchronized (SemanaDAOImpl.class) {
			getSession().persist(c);
			getSession().flush();

		}
	}
	
	public void deletar(Semana c) {
		getSession().delete(c);
		getSession().flush();
	}
	
	public void atualizar(Semana p) {
		getSession().merge(p);
		getSession().flush();
	}
	
	public List<Semana> listarKey(int key){
		Criteria criteria = getSession().createCriteria(Semana.class);
		criteria.add(Restrictions.eq("Idsemana", key));
		List<Semana> result = criteria.list();
		return result;
	}

}
