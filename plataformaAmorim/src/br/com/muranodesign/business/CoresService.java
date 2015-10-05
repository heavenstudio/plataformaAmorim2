package br.com.muranodesign.business;

import java.util.List;

import br.com.muranodesign.dao.CoresDAO;
import br.com.muranodesign.dao.DAOFactory;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.Cores;

public class CoresService {
	
	public List<Cores> listarTodos() {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		CoresDAO dao = DAOFactory.getCorDAO(pc);
		List<Cores> result = dao.listAll();
		pc.commitAndClose();
		return result;
	}
	
	public List<Cores> listarkey(int key) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		CoresDAO dao = DAOFactory.getCorDAO(pc);
		List<Cores> result = dao.listarKey(key);
		pc.commitAndClose();
		return result;
	}
	
	public Cores criarCores(Cores p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		CoresDAO dao = DAOFactory.getCorDAO(pc);
		dao.criar(p);
		pc.commitAndClose();
		return p;
	}
	
	public Cores atualizarCores(Cores p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		CoresDAO dao = DAOFactory.getCorDAO(pc);
		dao.atualizar(p);
		pc.commitAndClose();
		return p;
	}
	
	public Cores deletarCores(Cores p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		CoresDAO dao = DAOFactory.getCorDAO(pc);
		dao.deletar(p);
		pc.commitAndClose();
		return p;
	}

}
