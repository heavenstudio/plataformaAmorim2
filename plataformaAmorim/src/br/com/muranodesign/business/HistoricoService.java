package br.com.muranodesign.business;

import java.util.List;

import br.com.muranodesign.dao.DAOFactory;
import br.com.muranodesign.dao.HistoricoDAO;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.Historico_conexao;
import br.com.muranodesign.model.SO;



public class HistoricoService {
	
	public Historico_conexao criarHistorico(Historico_conexao p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		HistoricoDAO dao = DAOFactory.getNHistoricoDAO(pc);
		dao.criar(p);
		pc.commitAndClose();
		return p;
	}
	
	public List<Historico_conexao> listarQtdAcessoEscola(String data){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		HistoricoDAO dao = DAOFactory.getNHistoricoDAO(pc);
		List<Historico_conexao> result = dao.listarQtdAcessoEscola(data);
		return result;
	}
	
	public List<Historico_conexao> listarQtdAcessoExterno(String data){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		HistoricoDAO dao = DAOFactory.getNHistoricoDAO(pc);
		List<Historico_conexao> result = dao.listarQtdAcessoExterno(data);
		return result;
	}
	
	public List<Historico_conexao> listarSo(SO so){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		HistoricoDAO dao = DAOFactory.getNHistoricoDAO(pc);
		List<Historico_conexao> result = dao.listarSo(so);
		return result;
	}

}
