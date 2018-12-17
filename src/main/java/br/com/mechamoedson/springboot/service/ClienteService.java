package br.com.mechamoedson.springboot.service;


import java.util.List;

import br.com.mechamoedson.springboot.model.Cliente;

public interface ClienteService {
	
	Cliente findById(Long id);

	Cliente findByNome(String name);

	void saveCliente(Cliente cliente);

	void updateCliente(Cliente cliente);

	void deleteClienteById(Long id);

	void deleteAllClientes();

	List<Cliente> findAllClientes();

	boolean isClienteExist(Cliente cliente);
}