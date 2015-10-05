package br.com.muranodesign.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.muranodesign.dao.CicloAnoEstudoDAO;
import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.model.CicloAnoEstudo;

public class CicloAnoEstudoDAOImpl extends AbstractHibernateDAO implements CicloAnoEstudoDAO{
	
	public CicloAnoEstudoDAOImpl(HibernatePersistenceContext persistenceContext) {
		super(persistenceContext);
		
	}

	public List<CicloAnoEstudo> listAll() {
		
		Criteria criteria = getSession().createCriteria(CicloAnoEstudo.class);
		List<CicloAnoEstudo> result = criteria.list();
		
		
		return result;
	} 
	
	public void criar(CicloAnoEstudo c) {
		synchronized (CicloAnoEstudoDAOImpl.class) {
			getSession().persist(c);
			getSession().flush();

		}
	}
	
	public void deletar(CicloAnoEstudo c) {
		getSession().delete(c);
		getSession().flush();
	}
	
	public void atualizar(CicloAnoEstudo p) {
		getSession().merge(p);
		getSession().flush();
	}
	
	public List<CicloAnoEstudo> listarKey(int key){
		Criteria criteria = getSession().createCriteria(CicloAnoEstudo.class);
		criteria.add(Restrictions.eq("Idciclo_anoEstudo", key));
		List<CicloAnoEstudo> result = criteria.list();
		return result;
	}
}
