package br.com.muranodesign.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.muranodesign.dao.MenuDAO;
import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.model.Menu;

public class MenuDAOImpl extends AbstractHibernateDAO implements MenuDAO {

	public MenuDAOImpl(HibernatePersistenceContext persistenceContext) {
		super(persistenceContext);
		
	}
	
	public List<Menu> listAll() {
			
			Criteria criteria = getSession().createCriteria(Menu.class);
			List<Menu> result = criteria.list();
			
			
			return result;
		} 
	
	public void criar(Menu c) {
		synchronized (MenuDAOImpl.class) {
			getSession().persist(c);
			getSession().flush();
	
		}
	}

	public void deletar(Menu c) {
		getSession().delete(c);
		getSession().flush();
	}
	
	public void atualizar(Menu p) {
		getSession().merge(p);
		getSession().flush();
	}
	
	public List<Menu> listarKey(int key){
		Criteria criteria = getSession().createCriteria(Menu.class);
		criteria.add(Restrictions.eq("Idmenu", key));
		List<Menu> result = criteria.list();
		return result;
	}

}
