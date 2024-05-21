package com.example.springBoot.infra.security;

import com.example.springBoot.models.ClienteModel;
import com.example.springBoot.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    ClienteRepository clienteRepository;
    @Override
    public UserDetails loadUserByUsername(String usuario)throws UsernameNotFoundException{
        ClienteModel cliente = clienteRepository.findByEmail(usuario).orElseThrow(()-> new RuntimeException());
        return new org.springframework.security.core.userdetails.User(cliente.getCpfCliente(),cliente.getSenha(), new ArrayList<>());
    }
}
