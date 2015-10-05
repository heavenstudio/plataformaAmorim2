package br.com.muranodesign.business;

import java.util.List;

import br.com.muranodesign.dao.CicloDAO;
import br.com.muranodesign.dao.DAOFactory;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.Ciclos;

public class CiclosService {
	
	public List<Ciclos> listarTodos() {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		CicloDAO dao = DAOFactory.getCicloDAO(pc);
		List<Ciclos> result = dao.listAll();
		pc.commitAndClose();
		return result;
	}
	
	public List<Ciclos> listarkey(int key) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		CicloDAO dao = DAOFactory.getCicloDAO(pc);
		List<Ciclos> result = dao.listarKey(key);
		pc.commitAndClose();
		return result;
	}
	
	public Ciclos criarCiclos(Ciclos p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		CicloDAO dao = DAOFactory.getCicloDAO(pc);
		dao.criar(p);
		pc.commitAndClose();
		return p;
	}
	
	public Ciclos atualizarCiclos(Ciclos p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		CicloDAO dao = DAOFactory.getCicloDAO(pc);
		dao.atualizar(p);
		pc.commitAndClose();
		return p;
	}
	
	public Ciclos deletarCiclos(Ciclos p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		CicloDAO dao = DAOFactory.getCicloDAO(pc);
		dao.deletar(p);
		pc.commitAndClose();
		return p;
	}

}
