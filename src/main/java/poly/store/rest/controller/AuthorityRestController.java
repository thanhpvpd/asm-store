package poly.store.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import poly.store.entity.Authority;
import poly.store.service.AuthorityService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest")
public class AuthorityRestController {
	@Autowired
	AuthorityService authorityService;

	@GetMapping("/authorities")
	public List<Authority> findAll(@RequestParam("admin") Optional<Boolean> admin) {
		if (admin.orElse(false)) {
			return authorityService.getAuthoritiesOfAdministrators();
		}
		return authorityService.findAll();
	}
	
	@PostMapping("/authorities")
	public Authority post(@RequestBody Authority auth) {
		return authorityService.create(auth);
	}
	
	@DeleteMapping("/authorities/{id}")
	public void delete(@PathVariable("id") Integer id) {
		authorityService.delete(id);
	}
}
