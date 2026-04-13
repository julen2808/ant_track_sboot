package com.example.ant_track_sboot.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.ant_track_sboot.modelo.Comercio;
import com.example.ant_track_sboot.repositorio.IComercioRepositorio;

@Service
public class ComercioServicio {

    @Autowired
    private IComercioRepositorio comercioRepositorio;

    public ComercioServicio(IComercioRepositorio comercioRepositorio) {
        this.comercioRepositorio = comercioRepositorio;
    }

    // SERVICIO PARA GUARDAR USUARIO (Nombre Comercio)
    public Comercio guardar_Comercio(Comercio nombreComercio) {
        if (nombreComercio.getNombreComercio() == null || nombreComercio.getNombreComercio().isBlank()
                || nombreComercio.getNombreComercio().isEmpty()) {

            // para mensaje de error en caso de que el campo este vacio o nulo, se puede
            // usar la clase ResponseStatusException para lanzar una excepción con un
            // mensaje personalizado y un código de estado HTTP adecuado (por ejemplo, 400
            // Bad Request).
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El nombre del comercio no puede estar vacío o nulo, por favor ingrese un nombre válido.    ");

        }

        if (nombreComercio.getNit().length() < 9) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El documento no puede estar vacío o nulo, por favor ingrese un documento válido.");

        }

        // Despues de las validaciones intento guardar los datos que me enviaron, si
        // todo esta bien se guardan los datos y se retorna true, si no se guarda nada y
        // se retorna false
        return comercioRepositorio.save(nombreComercio);

    }

    // SERVICIO PARA LISTAR TODOS LOS USUARIOS EN BD
    public List<Comercio> listar_comercios() {
        return comercioRepositorio.findAll();
    }

    // SERVICIO PARA ELIMINAR UN USUARIO EN BD
    // SERVICIO PARA MODIFICAR UN USUARIO EN BD
    // SERVICIO PARA BUSCAR UN USUARIO POR ID EN BD

}