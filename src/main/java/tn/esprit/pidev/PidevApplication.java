package tn.esprit.pidev;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tn.esprit.pidev.model.Role;
import tn.esprit.pidev.repository.RoleRepository;

import java.util.ArrayList;

@SpringBootApplication
public class PidevApplication implements CommandLineRunner {

	@Autowired
	RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(PidevApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Role admin = new Role("ADMIN");
		Role operateur = new Role("OPERATEUR");
		Role acheteur = new Role("ACHETEUR");
		Role fournisseur = new Role("FOURNISSEUR");

		ArrayList<Role> roles = new ArrayList<>();

		if(!roleRepository.existsByName(admin.getName())) {
			roles.add(admin);
		}
		if(!roleRepository.existsByName(operateur.getName())) {
			roles.add(operateur);
		}
		if(!roleRepository.existsByName(acheteur.getName())) {
			roles.add(acheteur);
		}
		if(!roleRepository.existsByName(fournisseur.getName())) {
			roles.add(fournisseur);
		}

		roleRepository.saveAll(roles);
	}

}
