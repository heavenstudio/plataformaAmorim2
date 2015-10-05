package br.com.muranodesign.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.muranodesign.dao.TemplateDAO;
import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.model.Template;

public class TemplateDAOImpl extends AbstractHibernateDAO implements TemplateDAO{
	
	public TemplateDAOImpl(HibernatePersistenceContext persistenceContext) {
		super(persistenceContext);
		
	}
	
	public List<Template> listAll() {
		
		Criteria criteria = getSession().createCriteria(Template.class);
		List<Template> result = criteria.list();
		
		
		return result;
	} 
	
	public void criar(Template c) {
		synchronized (TemplateDAOImpl.class) {
			getSession().persist(c);
			getSession().flush();

		}
	}

	public void deletar(Template c) {
		getSession().delete(c);
		getSession().flush();
	}

	public void atualizar(Template p) {
		getSession().merge(p);
		getSession().flush();
	}
	
	
	public List<Template> listarKey(int key){
		Criteria criteria = getSession().createCriteria(Template.class);
		criteria.add(Restrictions.eq("Idtemplate", key));
		List<Template> result = criteria.list();
		return result;
	}

}
