package br.com.muranodesign.business;

import java.util.List;

import br.com.muranodesign.dao.DAOFactory;
import br.com.muranodesign.dao.MuralAlunoDAO;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.MuralAluno;


public class MuralAlunoService {

	public List<MuralAluno> listarall() {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		MuralAlunoDAO dao = DAOFactory.geMuralAlunoDAO(pc);
		List<MuralAluno> result = dao.listarAll();
		pc.commitAndClose();
		return result;
	}
	
	public List<MuralAluno> listarkey(int id) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		MuralAlunoDAO dao = DAOFactory.geMuralAlunoDAO(pc);
		List<MuralAluno> result = dao.listarKey(id);
		pc.commitAndClose();
		return result;
	}
	
	public MuralAluno deletarMuralAluno(MuralAluno muralAluno) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		MuralAlunoDAO dao = DAOFactory.geMuralAlunoDAO(pc);
		dao.deletar(muralAluno);
		pc.commitAndClose();
		return muralAluno;
	}

	public MuralAluno criarMuralAluno(MuralAluno muralAluno) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		MuralAlunoDAO dao = DAOFactory.geMuralAlunoDAO(pc);
		dao.criar(muralAluno);
		pc.commitAndClose();
		return muralAluno;
	}

	

	public List<MuralAluno> listarAluno(int idaluno) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		MuralAlunoDAO dao = DAOFactory.geMuralAlunoDAO(pc);
		List<MuralAluno> result = dao.listarAluno(idaluno);
		pc.commitAndClose();
		return result;
	}

}
