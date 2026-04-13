package com.example.ant_track_sboot.servicio;

import com.example.ant_track_sboot.modelo.Categoria;
import com.example.ant_track_sboot.repositorio.ICategoriaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service //le dice a Spring que esta clase es un servicio.
public class CategoriaServicio{

    @Autowired //anotación para inyección de dependencias.
    private ICategoriaRepositorio categoriaRepositorio;

    //inyeccion CONSTRUCTOR
    public CategoriaServicio(ICategoriaRepositorio categoriaRepository) {
        this.categoriaRepositorio = categoriaRepository;
    }

    // 4. BUSCAR TODOS
   
    public List<Categoria> buscarTodos() {
        
        return categoriaRepositorio.findAll();
    }

    // 1. GUARDAR
   
    public Categoria guardar(Categoria categoria) {
        if(categoria.getNombre()==null || categoria.getNombre().isBlank()|| categoria.getNombre().isEmpty()){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "El nombre de la categoria es obligatorio, revisa por favor"
            );
        }
        if (categoria.getPresupuestoMaximoMensual() == null || categoria.getPresupuestoMaximoMensual()<=0) {
            throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "El presupuesto mensual debe ser mayor a 0"
            );
        }
        return categoriaRepositorio.save(categoria);
    }





    // 2. BUSCAR POR ID
     //indica que este método viene de la interfaz.
    public Categoria buscarPorId(Long id) {
        Optional<Categoria> categoria = categoriaRepositorio.findById(id);
        if(! categoria.isPresent()){
             throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "La categoria no existe, ingrese una valida"
        );

        }
        return categoria.get();
    }

    // 3. BUSCAR POR ATRIBUTO (nombre parcial)
   /* 
    public List<Categoria> buscarPorNombre(String nombre) {
        return categoriaRepositorio.findByNombreContaining(nombre);
    }*/

  

    // 5. EDITAR
   
    public Categoria editar(Long id, Categoria categoriaActualizada) {
        Categoria categoriaExistente = buscarPorId(id);

        categoriaExistente.setNombre(categoriaActualizada.getNombre());
        categoriaExistente.setDescripcion(categoriaActualizada.getDescripcion());
        categoriaExistente.setPresupuestoMaximoMensual(categoriaActualizada.getPresupuestoMaximoMensual());
        categoriaExistente.setEsNecesaria(categoriaActualizada.isEsNecesaria());
        categoriaExistente.setPrioridad(categoriaActualizada.getPrioridad());
        categoriaExistente.setActiva(categoriaActualizada.isActiva());
        categoriaExistente.setAlertaActiva(categoriaActualizada.isAlertaActiva());
        categoriaExistente.setGastoMensual(categoriaActualizada.getGastoMensual());

        return categoriaRepositorio.save(categoriaExistente);
    }

    // 6. ELIMINAR
  
    public void eliminar(Long id) {
        Categoria categoria = buscarPorId(id);
        categoriaRepositorio.delete(categoria);
    }
}