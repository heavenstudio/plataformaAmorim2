package br.com.muranodesign.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.muranodesign.dao.SessaoForumGeralDAO;
import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.model.SessaoForumGeral;

public class SessaoForumGeralDAOImpl extends AbstractHibernateDAO implements
		SessaoForumGeralDAO {

	public SessaoForumGeralDAOImpl(
			HibernatePersistenceContext persistenceContext) {
		super(persistenceContext);

	}

	public List<SessaoForumGeral> listAll() {

		Criteria criteria = getSession().createCriteria(SessaoForumGeral.class);
		List<SessaoForumGeral> result = criteria.list();

		return result;
	}

	public void criar(SessaoForumGeral c) {
		synchronized (SessaoForumGeralDAOImpl.class) {
			getSession().persist(c);
			getSession().flush();

		}
	}

	public void deletar(SessaoForumGeral c) {
		getSession().delete(c);
		getSession().flush();
	}

	public void atualizar(SessaoForumGeral p) {
		getSession().merge(p);
		getSession().flush();
	}

	public List<SessaoForumGeral> listarKey(int key) {
		Criteria criteria = getSession().createCriteria(SessaoForumGeral.class);
		criteria.add(Restrictions.eq("idSessaoForum", key));
		List<SessaoForumGeral> result = criteria.list();
		return result;
	}

}
