package com.curso.proyect.controller;

import com.curso.proyect.dao.UsuarioDao;
import com.curso.proyect.model.UsuarioModel;
import com.curso.proyect.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    protected UsuarioDao usuarioDao;
    @Autowired
    protected JWTUtil jwtUtil;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody UsuarioModel usuario) {

        UsuarioModel usuarioLogueado = usuarioDao.obtenerUsuarioPorCredenciales(usuario);
        if (usuarioLogueado != null) {
            String tokenJwt = jwtUtil.create(String.valueOf(usuarioLogueado.getId()), usuarioLogueado.getEmail());
            return tokenJwt;
        }
        return "FAIL";
    }
}
