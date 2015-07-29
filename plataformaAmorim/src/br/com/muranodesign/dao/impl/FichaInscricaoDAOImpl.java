package br.com.muranodesign.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.muranodesign.business.RoteiroService;
import br.com.muranodesign.dao.FichaInscricaoDAO;
import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.model.FichaInscricao;

public class FichaInscricaoDAOImpl extends AbstractHibernateDAO implements FichaInscricaoDAO {
	
	public FichaInscricaoDAOImpl(HibernatePersistenceContext persistenceContext) {
		super(persistenceContext);
		
	}

	public List<FichaInscricao> listAll() {
		
		Criteria criteria = getSession().createCriteria(FichaInscricao.class);
		List<FichaInscricao> result = criteria.list();
		
		
		return result;
	} 
	
	public void criar(FichaInscricao c) {
		synchronized (FichaInscricaoDAOImpl.class) {
			getSession().persist(c);
			getSession().flush();

		}
	}

	public void deletar(FichaInscricao c) {
		getSession().delete(c);
		getSession().flush();
	}

	public void atualizar(FichaInscricao p) {
		getSession().merge(p);
		getSession().flush();
	}
	
	
	public List<FichaInscricao> listarKey(int key){
		Criteria criteria = getSession().createCriteria(FichaInscricao.class);
		criteria.add(Restrictions.eq("roteiro", new RoteiroService().listarkey(key).get(0)));
		List<FichaInscricao> result = criteria.list();
		return result;
	}
	
}
