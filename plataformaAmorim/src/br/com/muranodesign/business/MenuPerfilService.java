package br.com.muranodesign.business;

import java.util.List;

import br.com.muranodesign.dao.DAOFactory;
import br.com.muranodesign.dao.MenuPerfilDAO;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.MenuPerfil;

public class MenuPerfilService {

	public List<MenuPerfil> listarTodos() {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		MenuPerfilDAO dao = DAOFactory.getMenuPerfilDAO(pc);
		List<MenuPerfil> result = dao.listAll();
		pc.commitAndClose();
		return result;
	}
	
	public List<MenuPerfil> listarkey(int key) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		MenuPerfilDAO dao = DAOFactory.getMenuPerfilDAO(pc);
		List<MenuPerfil> result = dao.listarKey(key);
		pc.commitAndClose();
		return result;
	}
	
	public MenuPerfil criarMenuPerfil(MenuPerfil p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		MenuPerfilDAO dao = DAOFactory.getMenuPerfilDAO(pc);
		dao.criar(p);
		pc.commitAndClose();
		return p;
	}
	
	public MenuPerfil atualizarMenuPerfil(MenuPerfil p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		MenuPerfilDAO dao = DAOFactory.getMenuPerfilDAO(pc);
		dao.atualizar(p);
		pc.commitAndClose();
		return p;
	}
	
	public MenuPerfil deletarMenuPerfil(MenuPerfil p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		MenuPerfilDAO dao = DAOFactory.getMenuPerfilDAO(pc);
		dao.deletar(p);
		pc.commitAndClose();
		return p;
	}
	
	
	public List<MenuPerfil> listarUser(int id) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		MenuPerfilDAO dao = DAOFactory.getMenuPerfilDAO(pc);
		List<MenuPerfil> result = dao.listarUser(id);
		pc.commitAndClose();
		return result;
	}
}
