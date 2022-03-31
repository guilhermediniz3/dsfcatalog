package com.devsuperior.dsfcatalog.services;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsfcatalog.dto.ClientDTO;
import com.devsuperior.dsfcatalog.entities.Client;
import com.devsuperior.dsfcatalog.repositories.ClientRepository;
import com.devsuperior.dsfcatalog.services.exceptions.ResourceNotFoundException;

@Service
public class ClientService {
	@Autowired
	private ClientRepository repository;
	@Transactional(readOnly =true)
	public Page<ClientDTO> findAllPaged(PageRequest pageRequest) {
	Page<Client> list = repository.findAll(pageRequest);
	
		return list.map(x -> new ClientDTO(x));
	}
	@Transactional(readOnly =true)
	public ClientDTO findById(Long id) {
	Optional<Client> obj = repository.findById(id);
	Client entity = obj.orElseThrow(()-> new ResourceNotFoundException("Entity not Found"));
	return new ClientDTO(entity);
	}
}
