package br.com.muranodesign.business;

import java.util.List;

import br.com.muranodesign.dao.DAOFactory;
import br.com.muranodesign.dao.OficinaProfessorDAO;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.OficinaProfessor;

public class OficinaProfessorService {
	
	public List<OficinaProfessor> listarTodos() {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		OficinaProfessorDAO dao = DAOFactory.getOficinaProfessorDAO(pc);
		List<OficinaProfessor> result = dao.listAll();
		pc.commitAndClose();
		return result;
	}
	
	public List<OficinaProfessor> listarkey(int key) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		OficinaProfessorDAO dao = DAOFactory.getOficinaProfessorDAO(pc);
		List<OficinaProfessor> result = dao.listarKey(key);
		pc.commitAndClose();
		return result;
	}
	
	public OficinaProfessor criarOficinaProfessor(OficinaProfessor p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		OficinaProfessorDAO dao = DAOFactory.getOficinaProfessorDAO(pc);
		dao.criar(p);
		pc.commitAndClose();
		return p;
	}
	
	public OficinaProfessor atualizarOficinaProfessor(OficinaProfessor p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		OficinaProfessorDAO dao = DAOFactory.getOficinaProfessorDAO(pc);
		dao.atualizar(p);
		pc.commitAndClose();
		return p;
	}
	
	public OficinaProfessor deletarOficinaProfessor(OficinaProfessor p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		OficinaProfessorDAO dao = DAOFactory.getOficinaProfessorDAO(pc);
		dao.deletar(p);
		pc.commitAndClose();
		return p;
	}
	
	public List<OficinaProfessor> listarProfessor(int id){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		OficinaProfessorDAO dao = DAOFactory.getOficinaProfessorDAO(pc);
		List<OficinaProfessor> result = dao.listarProfessor(id);
		pc.commitAndClose();
		return result;
	}
	
	public List<OficinaProfessor> listarOficina(int idOficina, int idProfessor){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		OficinaProfessorDAO dao = DAOFactory.getOficinaProfessorDAO(pc);
		List<OficinaProfessor> result = dao.listarOficina(idOficina, idProfessor);
		pc.commitAndClose();
		return result;
	}

}
