package br.com.muranodesign.business;

import java.util.List;

import br.com.muranodesign.dao.DAOFactory;
import br.com.muranodesign.dao.SODAO;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.SO;



public class SOService {
	
	public SO criarSO(SO p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		SODAO dao = DAOFactory.getSodao(pc);
		dao.criar(p);
		pc.commitAndClose();
		return p;
	}
	//era int
	public List<SO> listarSO(String so){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		SODAO dao = DAOFactory.getSodao(pc);
		List<SO> result = dao.listarSo(so);
		return result;
	}
	
	public List<SO> listarBySO(int idso){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		SODAO dao = DAOFactory.getSodao(pc);
		List<SO> result = dao.listarid(idso);
		return result;
	}
	
	
}
