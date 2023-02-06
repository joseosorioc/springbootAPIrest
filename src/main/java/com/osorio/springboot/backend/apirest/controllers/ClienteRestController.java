package com.osorio.springboot.backend.apirest.controllers;


import com.osorio.springboot.backend.apirest.models.entity.Cliente;
import com.osorio.springboot.backend.apirest.models.services.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<?> show(@PathVariable Long id){

        Cliente cliente = null;

        Map<String, Object> response = new HashMap<>();

        try{
         cliente = clienteService.findById(id);
        }catch (DataAccessException ex){
            response.put("mensaje", "Error al realizar la consulta en Base de datos");
            response.put("error", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()) );

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(cliente == null ){
            response.put("mensaje", "El cliente ID: ".concat(id.toString().concat(" no existe en la bd.")) );
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/clientes")
    public ResponseEntity<?> create( @RequestBody Cliente cliente){

        Cliente clienteNew = null;
        Map<String, Object> response = new HashMap<>();

        try{
            clienteNew = clienteService.save(cliente);

        }catch ( DataAccessException ex){
            response.put("mensaje", "Error al realizar el insert");
            response.put("error", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()) );
            return  new ResponseEntity<Cliente>(clienteNew, HttpStatus.INTERNAL_SERVER_ERROR );
        }

        response.put("mensaje", "cliente fue creado satisfactoriamente");
        response.put("cliente", clienteNew);
      return  new ResponseEntity<Cliente>(clienteNew, HttpStatus.CREATED);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarCliente( @RequestBody Cliente cliente, @PathVariable Long id  ){

        Map<String, Object> response = new HashMap<>();

        try{
           Cliente clienteActualizado =  clienteService.update(cliente,id);
           response.put("mensaje", "cliente ha sido actualizado correctamente");
           response.put("cliente Actualizado", clienteActualizado );

           return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

        }catch (DataAccessException ex){

            response.put("mensaje", "No se pudo editar cliente correctamente.");
            response.put("error", ex.getMessage());
            return  new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @DeleteMapping(value = "eliminar/{id}")
    public ResponseEntity<?> delete( @PathVariable Long id ){

        Map<String, Object> response = new HashMap<>();

        try{
            clienteService.delete(id);
        }catch(Exception e){
            response.put("mensaje", "Error al eliminar cliente en base de datos.");
            response.put("error", e.getMessage());
            return  new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put( "mensaje" , "Cliente eliminado con éxito.");
        return new ResponseEntity<>(response, HttpStatus.OK);

    }


}
