package com.example.ant_track_sboot.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.ant_track_sboot.modelo.Gasto;
import com.example.ant_track_sboot.repositorio.IGastoRepositorio;

@Service
public class GastoServicio {

    @Autowired  //conecta la interface con el servicio
    private IGastoRepositorio gastoRepositorio;
  

    //implementacion
     //inyeccion CONSTRUCTOR
    public GastoServicio(IGastoRepositorio gastoRepositorio) {
        this.gastoRepositorio = gastoRepositorio;
    }
     

    //1. 1 guardar validadando gasto

    public Gasto guardarGasto(Gasto datosGasto){ 

        //validando descripcion vacio
        if(datosGasto.getDescripcion() == null || datosGasto.getDescripcion().isBlank() 
            || datosGasto.getDescripcion().isEmpty() ){

            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "la descripcion e necesaria"
            );
            
        }

        //validando numero
        if(datosGasto.getValor().isNaN()){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "debe se un numero, no letras"
            );
        }

    return gastoRepositorio.save(datosGasto);
        

    }

    // 2. BUSCAR TODOS
   
    public List<Gasto> buscarTodos() {
        return gastoRepositorio.findAll();
    }

    // 3. BUSCAR POR ID
     //indica que este método viene de la interfaz.
    public Gasto buscarPorId(Long id) {
        
        Optional<Gasto> gasto = gastoRepositorio.findById(id);
       
        if (!gasto.isPresent()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "no existe el gasto buscado"
            );
        }
        return gasto.get(); //funcion del optional

       
    }

    // 4. BUSCAR POR ATRIBUTO (nombre parcial)
   
    public List<Gasto> buscarPorNombre(String nombre) {
        return gastoRepositorio.findByDescripcionContaining(nombre);
    }

  

    // 5. EDITAR
   
    public Gasto editar(Long id, Gasto gastoActualizado) {
        Gasto gastoExistente = buscarPorId(id);

        gastoExistente.setDescripcion(gastoActualizado.getDescripcion());
        gastoExistente.setValor(gastoActualizado.getValor());
        gastoExistente.setFecha(gastoActualizado.getFecha());
       

        return gastoRepositorio.save(gastoExistente);
    }

    // 6. ELIMINAR
  
    public void eliminar(Long id) {
        Gasto gasto = buscarPorId(id);
        gastoRepositorio.delete(gasto);
    }
}


    

