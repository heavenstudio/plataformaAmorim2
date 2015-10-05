package br.com.muranodesign.business;

import java.util.List;

import br.com.muranodesign.dao.DAOFactory;
import br.com.muranodesign.dao.MuralDAO;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.Mural;

public class MuralService {
	
	public List<Mural> listarTodos() {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		MuralDAO dao = DAOFactory.getMuralDAO(pc);
		List<Mural> result = dao.listAll();
		pc.commitAndClose();
		return result;
	}
	
	public List<Mural> listarkey(int key) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		MuralDAO dao = DAOFactory.getMuralDAO(pc);
		List<Mural> result = dao.listarKey(key);
		pc.commitAndClose();
		return result;
	}
	
	public Mural criarMural(Mural p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		MuralDAO dao = DAOFactory.getMuralDAO(pc);
		dao.criar(p);
		pc.commitAndClose();
		return p;
	}
	
	public Mural atualizarMural(Mural p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		MuralDAO dao = DAOFactory.getMuralDAO(pc);
		dao.atualizar(p);
		pc.commitAndClose();
		return p;
	}
	
	public Mural deletarMural(Mural p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		MuralDAO dao = DAOFactory.getMuralDAO(pc);
		dao.deletar(p);
		pc.commitAndClose();
		return p;
	}
	
	public List<Mural>Range(String data2, String data, int idAluno){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		MuralDAO dao = DAOFactory.getMuralDAO(pc);
		List<Mural> result = dao.Range(data2, data,idAluno);
		pc.commitAndClose();
		return result;
		
	}


}
