package com.osorio.springboot.backend.apirest.controllers;


import com.osorio.springboot.backend.apirest.models.entity.Cliente;
import com.osorio.springboot.backend.apirest.models.services.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ClienteRestController {

    private final IClienteService clienteService;

    @Autowired
    public ClienteRestController(IClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping(value = "/clientes")
    @ResponseStatus(HttpStatus.OK )
    public List<Cliente> index(){
      return clienteService.findAll();
    }

    @GetMapping(value = "cliente/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cliente show(@PathVariable Long id){
        return clienteService.findById(id);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/clientes")
    public Cliente create( @RequestBody Cliente cliente){
      return  clienteService.save(cliente);
    }

    @PutMapping("/editar/{id}")
    public Cliente editarCliente( @RequestBody Cliente cliente, @PathVariable Long id  ){
      return clienteService.save(cliente);
    }


    @DeleteMapping(value = "eliminar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete( @PathVariable Long id ){
        clienteService.delete(id);
    }


}
