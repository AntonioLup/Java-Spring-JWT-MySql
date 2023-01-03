package com.curso.proyect.dao;

import com.curso.proyect.model.UsuarioModel;

import java.util.List;

public interface UsuarioDao {
    List<UsuarioModel> getUsuarios();

    void eliminar(Long id);

    void registrar(UsuarioModel usuario);
    UsuarioModel obtenerUsuarioPorCredenciales(UsuarioModel usuario);
}
