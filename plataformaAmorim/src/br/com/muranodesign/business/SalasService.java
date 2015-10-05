package br.com.muranodesign.business;

import java.util.List;

import br.com.muranodesign.dao.DAOFactory;
import br.com.muranodesign.dao.SalasDAO;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.Salas;

public class SalasService {
	
	public List<Salas> listarTodos() {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		SalasDAO dao = DAOFactory.getSalasDAO(pc);
		List<Salas> result = dao.listAll();
		pc.commitAndClose();
		return result;
	}
	
	public List<Salas> listarkey(int key) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		SalasDAO dao = DAOFactory.getSalasDAO(pc);
		List<Salas> result = dao.listarKey(key);
		pc.commitAndClose();
		return result;
	}
	
	public Salas criarSalas(Salas p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		SalasDAO dao = DAOFactory.getSalasDAO(pc);
		dao.criar(p);
		pc.commitAndClose();
		return p;
	}
	
	public Salas atualizarSalas(Salas p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		SalasDAO dao = DAOFactory.getSalasDAO(pc);
		dao.atualizar(p);
		pc.commitAndClose();
		return p;
	}
	
	public Salas deletarSalas(Salas p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		SalasDAO dao = DAOFactory.getSalasDAO(pc);
		dao.deletar(p);
		pc.commitAndClose();
		return p;
	}

}
