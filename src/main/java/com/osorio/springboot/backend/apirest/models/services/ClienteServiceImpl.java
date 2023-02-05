package com.osorio.springboot.backend.apirest.models.services;

import com.osorio.springboot.backend.apirest.models.dao.IClienteDAO;
import com.osorio.springboot.backend.apirest.models.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;


@Service("ClienteServiceImpl")
public class ClienteServiceImpl implements IClienteService  {


    private final IClienteDAO clienteDAO;

    @Autowired
    public ClienteServiceImpl( IClienteDAO clienteDAO){
        this.clienteDAO = clienteDAO;
    }

    @Override
    @Transactional( readOnly = true )
    public List<Cliente> findAll() {
        return clienteDAO.findAll() ;
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente findById(Long id) {
        return clienteDAO.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Cliente save(Cliente cliente) {
        return clienteDAO.save(cliente);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        clienteDAO.deleteById(id);
    }

    @Transactional
    @Override
    public Cliente update(Cliente cliente, Long id) {

        Cliente clienteAEditar = clienteDAO.findById(id).get() ;

        clienteAEditar.setNombre(cliente.getNombre());
        clienteAEditar.setApellido(cliente.getApellido());
        clienteAEditar.setEmail(cliente.getEmail());
        clienteAEditar.setCreateAt(cliente.getCreateAt());

        return save(clienteAEditar);
    }


}
