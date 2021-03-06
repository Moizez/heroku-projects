package com.producersapi.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.producersapi.model.Manager;
import com.producersapi.service.ManagerService;
import com.producersapi.util.EntityResource;
import com.producersapi.util.Response;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/managers")
public class ManagerResource extends Response<Manager> implements EntityResource<Manager> {

	@Autowired
	private ManagerService service;
	
	@Autowired
	private PasswordResource res;

	@Override
	public ResponseEntity<Manager> save(Manager entity) {
		service.save(entity);
		if (entity.getId() > 0) {
			try {
				res.SendEmail(entity.getEmail());
			} catch ( Exception e ) {
				System.out.println(e);
			};
		};		
		return new ResponseEntity<Manager>(entity, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<List<Manager>> findAll() {
		return findAll(service);
	}

	@Override
	public ResponseEntity<Manager> findById(Integer id) {
		return findById(service, id);
	}

	@Override
	public ResponseEntity<Manager> updateById(Integer id, Manager entity) {
		Optional<Manager> manager = service.findById(id);

		if (manager.isPresent()) {
			BeanUtils.copyProperties(entity, manager.get(), "id", "password");

			service.save(manager.get());
			return ResponseEntity.ok(manager.get());
		}

		return ResponseEntity.notFound().build();
	}

	@Override
	public ResponseEntity<Manager> deleteById(Integer id) {
		Optional<Manager> manager = service.findById(id);

		if (manager.isPresent()) {
			service.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/findByEmail/{email}")
	public ResponseEntity<Manager> findByEmail(@PathVariable("email") String email) {
		Manager manager = service.findByEmail(email);
		if(manager !=null) {
			return ResponseEntity.ok(manager);
		} else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
}