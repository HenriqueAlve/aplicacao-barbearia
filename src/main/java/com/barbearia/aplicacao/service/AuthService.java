package com.barbearia.aplicacao.service;

import com.barbearia.aplicacao.dto.request.CreateBarbeiroDTO;
import com.barbearia.aplicacao.dto.request.CreateClienteDTO;
import com.barbearia.aplicacao.dto.request.LoginDTO;
import com.barbearia.aplicacao.dto.response.TokenResponse;
import com.barbearia.aplicacao.model.entity.Barbeiro;
import com.barbearia.aplicacao.model.entity.Cliente;
import com.barbearia.aplicacao.model.entity.Usuario;
import com.barbearia.aplicacao.model.enums.Role;
import com.barbearia.aplicacao.repository.BarbeiroRepository;
import com.barbearia.aplicacao.repository.ClienteRepository;
import com.barbearia.aplicacao.repository.UsuarioRepository;
import com.barbearia.aplicacao.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {


    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private BarbeiroRepository barbeiroRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public TokenResponse loginCliente(LoginDTO dto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.email(), dto.senha())
        );

        Usuario usuario = usuarioRepository.findByEmail(dto.email())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return new TokenResponse(jwtService.gerarToken(usuario));
    }

    public TokenResponse registerCliente(CreateClienteDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setEmail(dto.email());
        usuario.setSenha(passwordEncoder.encode(dto.senha()));
        usuario.setRole(Role.CLIENTE);
        usuarioRepository.save(usuario);

        Cliente cliente = new Cliente();
        cliente.setNome(dto.nome());
        cliente.setTelefone(dto.telefone());
        cliente.setUsuario(usuario);
        clienteRepository.save(cliente);

        return new TokenResponse(jwtService.gerarToken(usuario));
    }

    public TokenResponse registerBarbeiro(CreateBarbeiroDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setEmail(dto.email());
        usuario.setSenha(passwordEncoder.encode(dto.senha()));
        usuario.setRole(Role.BARBEIRO);
        usuarioRepository.save(usuario);

        Barbeiro barbeiro = new Barbeiro();
        barbeiro.setNome(dto.nome());
        barbeiro.setTelefone(dto.telefone());
        barbeiro.setAtivo(true);
        barbeiro.setUsuario(usuario);
        barbeiroRepository.save(barbeiro);

        return new TokenResponse(jwtService.gerarToken(usuario));
    }
}
