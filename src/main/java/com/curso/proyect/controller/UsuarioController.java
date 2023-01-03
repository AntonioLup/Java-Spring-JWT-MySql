package com.curso.proyect.controller;

import com.curso.proyect.dao.UsuarioDaoImp;
import com.curso.proyect.model.UsuarioModel;
import com.curso.proyect.util.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UsuarioController  {
    @Autowired
    protected JWTUtil jwtUtil;
    @Autowired
    protected UsuarioDaoImp  usuarioDaoImp;

    @RequestMapping(value = "api/usuarios" , method = RequestMethod.GET)
    public List<UsuarioModel> getUsuarios(@RequestHeader(value="Authorization") String token){
//        if (!validarToken(token)) { return null; }

        return  usuarioDaoImp.getUsuarios();
    }
    @RequestMapping(value = "api/usuarios", method = RequestMethod.POST)
    public void registrarUsuario(@RequestBody UsuarioModel usuario){
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, usuario.getPassword());
        usuario.setPassword(hash);
        usuarioDaoImp.registrar(usuario);
    }

    private boolean validarToken(String token) {
        String usuarioId = jwtUtil.getKey(token);
        return usuarioId != null;
    }

    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.DELETE)
    public void eliminar(
                         @PathVariable Long id){

        usuarioDaoImp.eliminar(id);
    }



}
