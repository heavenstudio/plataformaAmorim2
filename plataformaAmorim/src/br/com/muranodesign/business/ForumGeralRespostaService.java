package br.com.muranodesign.business;

import java.util.List;

import br.com.muranodesign.dao.DAOFactory;
import br.com.muranodesign.dao.ForumGeralRespostaDAO;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.ForumGeralResposta;

public class ForumGeralRespostaService {
	public List<ForumGeralResposta> listarTodos(){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		ForumGeralRespostaDAO dao = DAOFactory.getForumGeralRespostaDAO(pc);
		List<ForumGeralResposta> result = dao.listAll();
		pc.commitAndClose();
		return result;
	}
	
	public List<ForumGeralResposta> listarkey(int key){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		ForumGeralRespostaDAO dao = DAOFactory.getForumGeralRespostaDAO(pc);
		List<ForumGeralResposta> result = dao.listarKey(key);
		pc.commitAndClose();
		return result;
	}
	
	public ForumGeralResposta criarForumQuestao(ForumGeralResposta p){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		ForumGeralRespostaDAO dao = DAOFactory.getForumGeralRespostaDAO(pc);
		dao.criar(p);
		pc.commitAndClose();
		return p;
	}
	
	public ForumGeralResposta atualizarForumQuestao(ForumGeralResposta p){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		ForumGeralRespostaDAO dao = DAOFactory.getForumGeralRespostaDAO(pc);
		dao.atualizar(p);
		pc.commitAndClose();
		return p;
	}
	
	public ForumGeralResposta deletarForumQuestao(ForumGeralResposta p){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		ForumGeralRespostaDAO dao = DAOFactory.getForumGeralRespostaDAO(pc);
		dao.deletar(p);
		pc.commitAndClose();
		return p;
	}

}
