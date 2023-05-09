package fr.techies.lock.service.controller;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import fr.techies.lock.service.register.Client;
import fr.techies.lock.service.register.ClientRegister;
import fr.techies.lock.service.register.MemoryLockRegister;

@Controller
@RequestMapping("/client")
public class ClientController {

	@Autowired
	private ClientRegister clientRegister;
	
	@Autowired 
	private MemoryLockRegister<?> memoryLockRegister;
	
	@GetMapping("/showAll")
	public ModelAndView showAll() {
	   
		ModelAndView modelAndView = new ModelAndView("showAll");
		Collection<Client> clients = this.clientRegister.getAll();
		
	    modelAndView.addObject("clients", clients);
	    
	    return modelAndView;
	}
	
	@GetMapping("/show/{serviceUUID}")
	public ModelAndView show(@PathVariable String serviceUUID) {
	   
		ModelAndView modelAndView = new ModelAndView("show");
		Client client = new Client(UUID.fromString(serviceUUID));
		
		modelAndView.addObject("client", client);
	    modelAndView.addObject("locksHeld", this.memoryLockRegister.getLocksHeldByClient(client));
	    
	    return modelAndView;
	}
}
