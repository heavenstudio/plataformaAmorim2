package br.com.muranodesign.business;

import java.util.List;

import br.com.muranodesign.dao.DAOFactory;
import br.com.muranodesign.dao.SessaoForumGeralDAO;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.SessaoForumGeral;

public class SessaoForumGeralService {
	
	public List<SessaoForumGeral> listarTodos() {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		SessaoForumGeralDAO dao = DAOFactory.getSessaoForumGeralDAO(pc);
		List<SessaoForumGeral> result = dao.listAll();
		pc.commitAndClose();
		return result;
	}
	
	public List<SessaoForumGeral> listarkey(int key) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		SessaoForumGeralDAO dao = DAOFactory.getSessaoForumGeralDAO(pc);
		List<SessaoForumGeral> result = dao.listarKey(key);
		pc.commitAndClose();
		return result;
	}
	
	public SessaoForumGeral criarSessao(SessaoForumGeral p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		SessaoForumGeralDAO dao = DAOFactory.getSessaoForumGeralDAO(pc);
		dao.criar(p);
		pc.commitAndClose();
		return p;
	}
	
	public SessaoForumGeral atualizarSessao(SessaoForumGeral p){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		SessaoForumGeralDAO dao = DAOFactory.getSessaoForumGeralDAO(pc);
		dao.atualizar(p);
		pc.commitAndClose();
		return p;
	}
	
	public SessaoForumGeral deletarSessao(SessaoForumGeral p){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		SessaoForumGeralDAO dao = DAOFactory.getSessaoForumGeralDAO(pc);
		dao.deletar(p);
		pc.commitAndClose();
		return p;
	}
}
