package br.com.muranodesign.business;

import java.util.List;

import br.com.muranodesign.dao.CicloAnoEstudoDAO;
import br.com.muranodesign.dao.DAOFactory;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.CicloAnoEstudo;

public class CicloAnoEstudoService {

	public List<CicloAnoEstudo> listarTodos() {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		CicloAnoEstudoDAO dao = DAOFactory.getCicloAnoEstudoDAO(pc);
		List<CicloAnoEstudo> result = dao.listAll();
		pc.commitAndClose();
		return result;
	}
	
	public List<CicloAnoEstudo> listarkey(int key) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		CicloAnoEstudoDAO dao = DAOFactory.getCicloAnoEstudoDAO(pc);
		List<CicloAnoEstudo> result = dao.listarKey(key);
		pc.commitAndClose();
		return result;
	}
	
	public CicloAnoEstudo criarCicloAnoEstudo(CicloAnoEstudo p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		CicloAnoEstudoDAO dao = DAOFactory.getCicloAnoEstudoDAO(pc);
		dao.criar(p);
		pc.commitAndClose();
		return p;
	}
	
	public CicloAnoEstudo atualizarCicloAnoEstudo(CicloAnoEstudo p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		CicloAnoEstudoDAO dao = DAOFactory.getCicloAnoEstudoDAO(pc);
		dao.atualizar(p);
		pc.commitAndClose();
		return p;
	}
	
	public CicloAnoEstudo deletarCicloAnoEstudo(CicloAnoEstudo p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		CicloAnoEstudoDAO dao = DAOFactory.getCicloAnoEstudoDAO(pc);
		dao.deletar(p);
		pc.commitAndClose();
		return p;
	}
}
