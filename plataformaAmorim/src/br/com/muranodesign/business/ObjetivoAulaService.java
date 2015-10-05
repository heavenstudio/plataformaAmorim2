package br.com.muranodesign.business;

import java.util.List;

import br.com.muranodesign.dao.DAOFactory;
import br.com.muranodesign.dao.ObjetivoAulaDAO;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.ObjetivoAula;

public class ObjetivoAulaService {
	public List<ObjetivoAula> listarTodos() {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		ObjetivoAulaDAO dao = DAOFactory.getObjetivoAulaDAO(pc);
		List<ObjetivoAula> result = dao.listAll();
		pc.commitAndClose();
		return result;
	}
	
	public List<ObjetivoAula> listarkey(int key) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		ObjetivoAulaDAO dao = DAOFactory.getObjetivoAulaDAO(pc);
		List<ObjetivoAula> result = dao.listarKey(key);
		pc.commitAndClose();
		return result;
	}
	
	public ObjetivoAula criarObjetivoAula(ObjetivoAula p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		ObjetivoAulaDAO dao = DAOFactory.getObjetivoAulaDAO(pc);
		dao.criar(p);
		pc.commitAndClose();
		return p;
	}
	
	public ObjetivoAula atualizarObjetivoAula(ObjetivoAula p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		ObjetivoAulaDAO dao = DAOFactory.getObjetivoAulaDAO(pc);
		dao.atualizar(p);
		pc.commitAndClose();
		return p;
	}
	
	public ObjetivoAula deletarObjetivoAula(ObjetivoAula p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		ObjetivoAulaDAO dao = DAOFactory.getObjetivoAulaDAO(pc);
		dao.deletar(p);
		pc.commitAndClose();
		return p;
	}
	
	public List<ObjetivoAula> listarPorRoteiro(int idroteiro){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		ObjetivoAulaDAO dao = DAOFactory.getObjetivoAulaDAO(pc);
		List<ObjetivoAula> result = dao.listarPorRoteiro(idroteiro);
		pc.commitAndClose();
		return result;
	}
}
