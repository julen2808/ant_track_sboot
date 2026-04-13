package com.example.ant_track_sboot.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.ant_track_sboot.modelo.Usuario;
import com.example.ant_track_sboot.repositorio.IUsuarioRepositorio;

@Service
public class UsuarioServicio {

    private final IUsuarioRepositorio usuarioRepositorio;

    // Constructor para conectar el repositorio
    public UsuarioServicio(IUsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    

    // GUARDAR USUARIO
    public Usuario guardar_usuario(Usuario datosUsario){
        // Validaciones manuales
        if(datosUsario.getNombre() == null || datosUsario.getNombre().isBlank()){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "El nombre es obligatorio"
            );
        }

        if(datosUsario.getDocumento() == null || datosUsario.getDocumento().length() < 5){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "El documento es inválido"
            );
        }

        // Guardamos el objeto que recibimos por parámetro
        return usuarioRepositorio.save(datosUsario);
    }

    // LISTAR TODO
    public List<Usuario> buscarTodos() {
        // Simplemente pedimos todo  datos al repositorio
        return usuarioRepositorio.findAll();
    }

    // BUSCAR POR ID
    public Usuario buscarPorId(Integer id) {
        
        Optional<Usuario> usuario = usuarioRepositorio.findById(id);

        if(!usuario.isPresent()){

            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "No existe el usuario buscado"
            );

        }

        return usuario.get();
    }

    // EDITAR
    public Usuario editar(Integer id, Usuario usuarioActualizado) {
        Usuario usuarioExistente = buscarPorId(id);

        // Actualización de campos
        usuarioExistente.setNombre(usuarioActualizado.getNombre());
        usuarioExistente.setTipoDocumento(usuarioActualizado.getTipoDocumento());
        usuarioExistente.setDocumento(usuarioActualizado.getDocumento());
        usuarioExistente.setEdad(usuarioActualizado.getEdad());
        usuarioExistente.setGenero(usuarioActualizado.getGenero());
        usuarioExistente.setCorreo(usuarioActualizado.getCorreo());
        usuarioExistente.setContacto(usuarioActualizado.getContacto());
        usuarioExistente.setPresupMensual(usuarioActualizado.getPresupMensual());
        usuarioExistente.setFechaRegistro(usuarioActualizado.getFechaRegistro());
        usuarioExistente.setGastos(usuarioActualizado.getGastos());
        usuarioExistente.setMetodosPago(usuarioActualizado.getMetodosPago());

    return usuarioRepositorio.save(usuarioExistente);
    }

    // ELIMINAR
    public void eliminar(Integer id) {
        Usuario usuario = buscarPorId(id);
        usuarioRepositorio.delete(usuario);
    }
}


