package br.com.muranodesign.dao;

import java.util.Date;
import java.util.List;

import br.com.muranodesign.model.PlanoAula;

public interface PlanoAulaDAO {

	/**
	 * List all.
	 *
	 * @return the list
	 */
	public List<PlanoAula> listAll();
	
	/**
	 * Criar.
	 *
	 * @param p the p
	 */
	public void criar(PlanoAula p);
	
	/**
	 * Deletar.
	 *
	 * @param p the p
	 */
	public void deletar(PlanoAula p);
	
	/**
	 * Atualizar.
	 *
	 * @param p the p
	 */
	public void atualizar(PlanoAula p);
	
	/**
	 * Listar key.
	 *
	 * @param key the key
	 * @return the list
	 */
	public List<PlanoAula> listarKey(int key);
	
	/**
	 * 
	 * @param data
	 * @return
	 */
	public List<PlanoAula> range(Date data);
	
	/**
	 * 
	 * @return
	 */
	public List<PlanoAula> listarUltimo(int idProfessor);
	
	/**
	 * Listar por id de professor
	 * @param idProfessor
	 * @return
	 */
	public List<PlanoAula> listarProfessor(int idProfessor, Date data);

	/**
	 * Listar por data e oficina
	 * @param idOficina
	 * @param data
	 * @return
	 */
	public List<PlanoAula> listarOficinaData(int idOficina, Date data);

	/**
	 * Listar plano de aula mais recente do professor
	 * @param idProfessor
	 * @param idOficina
	 * @return
	 */
	public PlanoAula listarProfessorOficinaRecente(int idProfessor,
			int idOficina);

	public List<PlanoAula> verificarData(Date inicio, Date fim, int idOficina);
}
