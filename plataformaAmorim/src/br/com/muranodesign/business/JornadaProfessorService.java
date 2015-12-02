package br.com.muranodesign.business;

import java.util.List;

import br.com.muranodesign.dao.DAOFactory;
import br.com.muranodesign.dao.JornadaProfessorDAO;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.JornadaProfessor;

//Alteração que pode mudar
public class JornadaProfessorService {

	public List<JornadaProfessor> listarTodos() {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		JornadaProfessorDAO dao = DAOFactory.getJornadaProfessorDAO(pc);
		List<JornadaProfessor> result = dao.listAll();
		pc.commitAndClose();
		return result;
	}
	
	public List<JornadaProfessor> listarkey(int key) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		JornadaProfessorDAO dao = DAOFactory.getJornadaProfessorDAO(pc);
		List<JornadaProfessor> result = dao.listarKey(key);
		pc.commitAndClose();
		return result;
	}
	
	public JornadaProfessor criarJornadaProfessor(JornadaProfessor p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		JornadaProfessorDAO dao = DAOFactory.getJornadaProfessorDAO(pc);
		dao.criar(p);
		pc.commitAndClose();
		return p;
	}
	
	public JornadaProfessor atualizarJornadaProfessor(JornadaProfessor p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		JornadaProfessorDAO dao = DAOFactory.getJornadaProfessorDAO(pc);
		dao.atualizar(p);
		pc.commitAndClose();
		return p;
	}
	
	public JornadaProfessor deletarJornadaProfessor(JornadaProfessor p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		JornadaProfessorDAO dao = DAOFactory.getJornadaProfessorDAO(pc);
		dao.deletar(p);
		pc.commitAndClose();
		return p;
	}
	
	public long Total(){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		JornadaProfessorDAO dao = DAOFactory.getJornadaProfessorDAO(pc);
		long result = dao.Total();
		pc.commitAndClose();
		return result;
	}
	
	public long Disponivel(){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		JornadaProfessorDAO dao = DAOFactory.getJornadaProfessorDAO(pc);
		long result = dao.Disponivel();
		pc.commitAndClose();
		return result;
	}
	
	public long extra(){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		JornadaProfessorDAO dao = DAOFactory.getJornadaProfessorDAO(pc);
		long result = dao.extra();
		pc.commitAndClose();
		return result;
	}
}
