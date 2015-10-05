package br.com.muranodesign.business;

import java.util.List;

import br.com.muranodesign.dao.DAOFactory;
import br.com.muranodesign.dao.HistoricoEventosDAO;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.historicoEventos;

public class HistoricoEventosService {
	
	public List<historicoEventos> listarTodos() {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		HistoricoEventosDAO dao = DAOFactory.getHistoricoEventosDAO(pc);
		List<historicoEventos> result = dao.listAll();
		pc.commitAndClose();
		return result;
	}
	
	public List<historicoEventos> listarkey(int key) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		HistoricoEventosDAO dao = DAOFactory.getHistoricoEventosDAO(pc);
		List<historicoEventos> result = dao.listarKey(key);
		pc.commitAndClose();
		return result;
	}
	
	public historicoEventos criarhistoricoEventos(historicoEventos p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		HistoricoEventosDAO dao = DAOFactory.getHistoricoEventosDAO(pc);
		dao.criar(p);
		pc.commitAndClose();
		return p;
	}
	
	public historicoEventos atualizarhistoricoEventos(historicoEventos p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		HistoricoEventosDAO dao = DAOFactory.getHistoricoEventosDAO(pc);
		dao.atualizar(p);
		pc.commitAndClose();
		return p;
	}
	
	public historicoEventos deletarhistoricoEventos(historicoEventos p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		HistoricoEventosDAO dao = DAOFactory.getHistoricoEventosDAO(pc);
		dao.deletar(p);
		pc.commitAndClose();
		return p;
	}

}
