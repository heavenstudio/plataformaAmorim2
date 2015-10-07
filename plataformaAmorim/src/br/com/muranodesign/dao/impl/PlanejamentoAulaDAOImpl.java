package br.com.muranodesign.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.muranodesign.dao.PlanejamentoAulaDAO;
import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.model.PlanejamentoAula;

public class PlanejamentoAulaDAOImpl extends AbstractHibernateDAO implements PlanejamentoAulaDAO{

	public PlanejamentoAulaDAOImpl(HibernatePersistenceContext persistenceContext) {
		super(persistenceContext);
		
	}
	
	public List<PlanejamentoAula> listAll() {
		
		Criteria criteria = getSession().createCriteria(PlanejamentoAula.class);
		List<PlanejamentoAula> result = criteria.list();
		
		
		return result;
	} 

	public void criar(PlanejamentoAula c) {
		synchronized (PlanejamentoAulaDAOImpl.class) {
			getSession().persist(c);
			getSession().flush();

		}
	}
	
	public void deletar(PlanejamentoAula c) {
		getSession().delete(c);
		getSession().flush();
	}
	
	public void atualizar(PlanejamentoAula p) {
		getSession().merge(p);
		getSession().flush();
	}
	
	public List<PlanejamentoAula> listarKey(int key){
		Criteria criteria = getSession().createCriteria(PlanejamentoAula.class);
		criteria.add(Restrictions.eq("Idplanejamento_aula", key));
		List<PlanejamentoAula> result = criteria.list();
		return result;
	}
	
	public List<PlanejamentoAula> listarProfessor(int id){
		Criteria criteria = getSession().createCriteria(PlanejamentoAula.class);
		criteria.createAlias("professor", "professor");
		criteria.add(Restrictions.eq("professor.idprofessorFuncionario", id));
		List<PlanejamentoAula> result = criteria.list();
		return result;
	}

	public List<PlanejamentoAula> listarPlanoAula(int id){
		Criteria criteria = getSession().createCriteria(PlanejamentoAula.class);
		criteria.createAlias("planoAula", "planoAula");
		criteria.add(Restrictions.eq("planoAula.Idplano_aula", id));
		List<PlanejamentoAula> result = criteria.list();
		return result;
	}
	
	public List<PlanejamentoAula> listarProfessorObjetivoAula(int idProfessor, int idObjetivoAula){
		Criteria criteria = getSession().createCriteria(PlanejamentoAula.class);
		criteria.createAlias("professor", "professor");
		criteria.add(Restrictions.eq("professor.idprofessorFuncionario", idProfessor));
		criteria.createAlias("objetivoAula", "objetivoAula");
		criteria.add(Restrictions.eq("objetivoAula.Idobjetivo_aula", idObjetivoAula));
		List<PlanejamentoAula> result = criteria.list();
		return result;
	}
}
