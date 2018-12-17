package br.com.mechamoedson.springboot.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.mechamoedson.springboot.model.Cliente;
import br.com.mechamoedson.springboot.service.ClienteService;
import br.com.mechamoedson.springboot.util.CustomErrorType;

@RestController
@RequestMapping("/api")
public class RestApiController {

	public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

	@Autowired
	ClienteService clienteService; 

	// ------------------- Todos Clientes--------------------------------------------- >>

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/cliente/", method = RequestMethod.GET)
	public ResponseEntity<List<Cliente>> listAllClientes() {
		List<Cliente> clientes = clienteService.findAllClientes();
		if (clientes.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Cliente>>(clientes, HttpStatus.OK);
	}

	// -------------------Retorna Cliente------------------------------------------

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/cliente/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getCliente(@PathVariable("id") long id) {
		logger.info("Consultando Cliente com id {}", id);
		Cliente cliente = clienteService.findById(id);
		if (cliente == null) {
			logger.error("Cliente com id {} não encontrado.", id);
			return new ResponseEntity(new CustomErrorType("Cliente com id " + id 
					+ " não encontrado"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}

	// -------------------Grava um Cliente-------------------------------------------

	@RequestMapping(value = "/cliente/", method = RequestMethod.POST)
	public ResponseEntity<?> createCliente(@RequestBody Cliente cliente, UriComponentsBuilder ucBuilder) {
		logger.info("Criando um Cliente : {}", cliente);

//		if (clienteService.isClienteExist(cliente)) {
//			logger.error("Erro. Um Cliente com o nome {} já existe", cliente.getNome());
//			return new ResponseEntity(new CustomErrorType("Não foi possível gravar o cliente: " + 
//			cliente.getNome() + " já existe."),HttpStatus.CONFLICT);
//		}
		clienteService.saveCliente(cliente);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/cliente/{id}").buildAndExpand(cliente.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// ------------------- Atualiza um Cliente ------------------------------------------------

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/cliente/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateCliente(@PathVariable("id") long id, @RequestBody Cliente cliente) {
		logger.info("Atuaizando cliente id {}", id);

		Cliente currentCliente = clienteService.findById(id);

		if (currentCliente == null) {
			logger.error("Não foi possível atualizar. Cliente com id {} não encontrado.", id);
			return new ResponseEntity(new CustomErrorType("Não foi possível atualizar. Cliente com id " + id + " não encontrado."),
					HttpStatus.NOT_FOUND);
		}

		currentCliente.setNome(cliente.getNome());
		currentCliente.setLimiteCredito(cliente.getLimiteCredito());
		currentCliente.setRisco(cliente.getRisco());

		clienteService.updateCliente(currentCliente);
		return new ResponseEntity<Cliente>(currentCliente, HttpStatus.OK);
	}

	// ------------------- Delete a Cliente-----------------------------------------

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/cliente/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteCliente(@PathVariable("id") long id) {
		logger.info("Consultando cliente com id {} para exclusão", id);

		Cliente cliente = clienteService.findById(id);
		if (cliente == null) {
			logger.error("Não foi possível deletar Cliente com id {} não encontrado.", id);
			return new ResponseEntity(new CustomErrorType("Não foi possível deletar Cliente com id " + id + " não encontrado."),
					HttpStatus.NOT_FOUND);
		}
		clienteService.deleteClienteById(id);
		return new ResponseEntity<Cliente>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All Clientes-----------------------------

	@RequestMapping(value = "/cliente/", method = RequestMethod.DELETE)
	public ResponseEntity<Cliente> deleteAllClientes() {
		logger.info("Deletando todos os Clientes");

		clienteService.deleteAllClientes();
		return new ResponseEntity<Cliente>(HttpStatus.NO_CONTENT);
	}

}