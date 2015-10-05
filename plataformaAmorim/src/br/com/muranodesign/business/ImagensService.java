package br.com.muranodesign.business;

import java.util.List;

import br.com.muranodesign.dao.DAOFactory;
import br.com.muranodesign.dao.ImagensDAO;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.Imagens;

public class ImagensService {
	
	public List<Imagens> listarTodos() {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		ImagensDAO dao = DAOFactory.getImagensDAO(pc);
		List<Imagens> result = dao.listAll();
		pc.commitAndClose();
		return result;
	}
	
	public List<Imagens> listarkey(int key) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		ImagensDAO dao = DAOFactory.getImagensDAO(pc);
		List<Imagens> result = dao.listarKey(key);
		pc.commitAndClose();
		return result;
	}
	
	public Imagens criarImagens(Imagens p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		ImagensDAO dao = DAOFactory.getImagensDAO(pc);
		dao.criar(p);
		pc.commitAndClose();
		return p;
	}
	
	public Imagens atualizarImagens(Imagens p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		ImagensDAO dao = DAOFactory.getImagensDAO(pc);
		dao.atualizar(p);
		pc.commitAndClose();
		return p;
	}
	
	public Imagens deletarImagens(Imagens p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		ImagensDAO dao = DAOFactory.getImagensDAO(pc);
		dao.deletar(p);
		pc.commitAndClose();
		return p;
	}

}
