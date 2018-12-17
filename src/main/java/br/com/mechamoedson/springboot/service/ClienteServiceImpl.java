package br.com.mechamoedson.springboot.service;

import java.util.List;

import br.com.mechamoedson.springboot.model.Cliente;
import br.com.mechamoedson.springboot.repositories.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service("clienteService")
@Transactional
public class ClienteServiceImpl implements ClienteService{

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente findById(Long id) {
		return clienteRepository.findOne(id);
	}

	public Cliente findByNome(String nome) {
		return clienteRepository.findByNome(nome);
	}

	public void saveCliente(Cliente cliente) {
		
		/* 
		 * 
		 * - Se o risco for do tipo A manter os dados informados 
		 * - Se o risco for do tipo B, a taxa de juros deve ser de 10%
		 * - Se o risco for do tipo C, a taxa de juros deve ser de 20%
		 * 
		 * */
		
		if("B".equalsIgnoreCase(cliente.getRisco())) {
			cliente.setTaxa(0.1);
		}else if("C".equalsIgnoreCase(cliente.getRisco())) {
			cliente.setTaxa(0.2);
		}else {
			cliente.setTaxa(0.0);
		}
		
		clienteRepository.save(cliente);
	}

	public void updateCliente(Cliente cliente){
		saveCliente(cliente);
	}

	public void deleteClienteById(Long id){
		clienteRepository.delete(id);
	}

	public void deleteAllClientes(){
		clienteRepository.deleteAll();
	}

	public List<Cliente> findAllClientes(){
		return clienteRepository.findAll();
	}

	public boolean isClienteExist(Cliente cliente) {
		return findById(cliente.getId()) != null;
	}

}
