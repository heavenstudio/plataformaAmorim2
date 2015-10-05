package br.com.muranodesign.business;

import java.util.List;

import br.com.muranodesign.dao.DAOFactory;
import br.com.muranodesign.dao.OficinaDAO;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.Oficina;

public class OficinaService {
	
	public List<Oficina> listarTodos() {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		OficinaDAO dao = DAOFactory.getOficinaDAO(pc);
		List<Oficina> result = dao.listAll();
		pc.commitAndClose();
		return result;
	}
	
	public List<Oficina> listarkey(int key) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		OficinaDAO dao = DAOFactory.getOficinaDAO(pc);
		List<Oficina> result = dao.listarKey(key);
		pc.commitAndClose();
		return result;
	}
	
	public Oficina criarOficina(Oficina p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		OficinaDAO dao = DAOFactory.getOficinaDAO(pc);
		dao.criar(p);
		pc.commitAndClose();
		return p;
	}
	
	public Oficina atualizarOficina(Oficina p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		OficinaDAO dao = DAOFactory.getOficinaDAO(pc);
		dao.atualizar(p);
		pc.commitAndClose();
		return p;
	}
	
	public Oficina deletarOficina(Oficina p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		OficinaDAO dao = DAOFactory.getOficinaDAO(pc);
		dao.deletar(p);
		pc.commitAndClose();
		return p;
	}

	public List<Oficina> listarIdCiclo(int id, int ciclo){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		OficinaDAO dao = DAOFactory.getOficinaDAO(pc);
		List<Oficina> result = dao.listarIdCiclo(id, ciclo);
		pc.commitAndClose();
		return result;
	}
}
