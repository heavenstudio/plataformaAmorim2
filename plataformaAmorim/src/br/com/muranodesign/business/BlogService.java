package br.com.muranodesign.business;

import java.util.List;

import br.com.muranodesign.dao.BlogDAO;
import br.com.muranodesign.dao.DAOFactory;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.Blog;

public class BlogService {

	public List<Blog> listarTodos() {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		BlogDAO dao = DAOFactory.getBlogDAO(pc);
		List<Blog> result = dao.listAll();
		pc.commitAndClose();
		return result;
	}
	
	public List<Blog> listarkey(int key) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		BlogDAO dao = DAOFactory.getBlogDAO(pc);
		List<Blog> result = dao.listarKey(key);
		pc.commitAndClose();
		return result;
	}
	
	public Blog criarBlog(Blog p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		BlogDAO dao = DAOFactory.getBlogDAO(pc);
		dao.criar(p);
		pc.commitAndClose();
		return p;
	}
	
	public Blog atualizarBloga(Blog p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		BlogDAO dao = DAOFactory.getBlogDAO(pc);
		dao.atualizar(p);
		pc.commitAndClose();
		return p;
	}
	
	public Blog deletarBlog(Blog p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		BlogDAO dao = DAOFactory.getBlogDAO(pc);
		dao.deletar(p);
		pc.commitAndClose();
		return p;
	}
}
