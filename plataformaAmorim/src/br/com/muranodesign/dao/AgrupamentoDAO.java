package br.com.muranodesign.dao;

import java.util.List;

import br.com.muranodesign.model.Agrupamento;

public interface AgrupamentoDAO {

	/**
	 * List all.
	 *
	 * @return the list
	 */
	public List<Agrupamento> listAll();
	
	/**
	 * Criar.
	 *
	 * @param p the p
	 */
	public void criar(Agrupamento p);
	
	/**
	 * Deletar.
	 *
	 * @param p the p
	 */
	public void deletar(Agrupamento p);
	
	/**
	 * Atualizar.
	 *
	 * @param p the p
	 */
	public void atualizar(Agrupamento p);
	
	/**
	 * Listar key.
	 *
	 * @param key the key
	 * @return the list
	 */
	public List<Agrupamento> listarKey(int key);
	
	

}
