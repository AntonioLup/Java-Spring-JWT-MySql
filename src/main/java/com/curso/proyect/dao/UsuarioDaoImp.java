package com.curso.proyect.dao;

import com.curso.proyect.model.UsuarioModel;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UsuarioDaoImp implements UsuarioDao {
    @PersistenceContext
     EntityManager entityManager;
    @Override
    @Transactional
    public List<UsuarioModel> getUsuarios() {
        String query = "FROM UsuarioModel";
        return entityManager.createQuery(query).getResultList();


    }

    @Override
    public void eliminar(Long id) {
        UsuarioModel usuarioeliminar = entityManager.find(UsuarioModel.class, id);
        entityManager.remove(usuarioeliminar);
    }

    @Override
    public void registrar(UsuarioModel usuario) {
        entityManager.merge(usuario);
    }

    @Override
    public UsuarioModel obtenerUsuarioPorCredenciales(UsuarioModel usuario) {
        String query = "FROM Usuario WHERE email = :email";
        List<UsuarioModel> lista = entityManager.createQuery(query)
                .setParameter("email", usuario.getEmail())
                .getResultList();

        if (lista.isEmpty()) {
            return null;
        }

        String passwordHashed = lista.get(0).getPassword();

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if (argon2.verify(passwordHashed, usuario.getPassword())) {
            return lista.get(0);
        }
        return null;
    }
}
