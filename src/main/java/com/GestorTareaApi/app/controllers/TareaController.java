package com.GestorTareaApi.app.controllers;


import com.GestorTareaApi.app.entities.Tarea;
import com.GestorTareaApi.app.entities.Usuario;
import com.GestorTareaApi.app.services.ITareaService;
import com.GestorTareaApi.app.services.IUsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("tarea/")
public class TareaController {

    @Autowired
    private ITareaService iTareaService;

    @Autowired
    private IUsuarioService iUsuarioService;

    @GetMapping("/")
    public ResponseEntity<?> listarTareas(){
        List<Tarea> tareaList = iTareaService.listarTareas();
       return ResponseEntity.status(HttpStatus.OK).body(tareaList);
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> buscarTarea(@PathVariable Long id){

        Optional<Tarea> listarTareaOpt = iTareaService.buscarTareaId(id);

        if (listarTareaOpt.isPresent()){

            return ResponseEntity.status(HttpStatus.OK).body(listarTareaOpt.get());

        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/")
    private ResponseEntity<?> crearTarea(@Valid @RequestBody Tarea tarea , BindingResult result){

        if (tarea.getEstado().getId() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("se necesita estado id");
        }

        if (result.hasErrors()){
            Map<String, String> errores =  validar(result);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        }

        Boolean existe = iTareaService.buscarTareaPortitulo(tarea.getTitulo());

        if (existe){
            return new ResponseEntity<>("ya existe tarea", HttpStatus.OK);
        }



        return ResponseEntity.status(HttpStatus.OK).body(iTareaService.guardarTarea(tarea));
    }

    @PutMapping("/{id}")
    private ResponseEntity<?> modificarTarea(@Valid @RequestBody Tarea tarea , BindingResult result , @PathVariable Long id){

        if (tarea.getEstado().getId() == null){
            return new ResponseEntity<>("Estado no encontrado", HttpStatus.BAD_REQUEST);
        }

        if (result.hasErrors()){
            Map<String, String> errores =  validar(result);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        }

        Optional<Tarea> tareaOptional = iTareaService.buscarTareaId(id);

        if (tareaOptional.isPresent()){
            Tarea tareaModificar = tareaOptional.get();

            tareaModificar.setFechaDevencimiento(tarea.getFechaDevencimiento());
            tareaModificar.setTitulo(tarea.getTitulo());
            tareaModificar.setEstado(tarea.getEstado());

            iTareaService.guardarTarea(tareaModificar);

            return ResponseEntity.status(HttpStatus.OK).body(tareaModificar);

        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> eliminarTarea(@PathVariable Long id){
        iTareaService.borrarTarea(id);
       return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/usuario/{usuarioId}/tarea/{tareaId}")
    private ResponseEntity<?> asignarUsuarioTarea(@PathVariable Long usuarioId ,  @PathVariable Long tareaId){

        Optional<Usuario> usuarioOptional = iUsuarioService.buscarUsuario(usuarioId);

        if (!usuarioOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("usuario no encontrado");
        }

        Optional<Tarea> tareaOptional = iTareaService.buscarTareaId(tareaId);

        if (!tareaOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("tarea no encontrada");
        }

        String mensaje = iTareaService.asignarUsuarioTarea(usuarioOptional.get() , tareaOptional.get());


        return ResponseEntity.status(HttpStatus.OK).body(mensaje);
    }

    @DeleteMapping("/usuario/{usuarioId}/tarea/{tareaId}")
    private ResponseEntity<?> eliminarUsuarioTarea(@PathVariable Long usuarioId ,  @PathVariable Long tareaId){

        Optional<Usuario> usuarioOptional = iUsuarioService.buscarUsuario(usuarioId);

        if (!usuarioOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("usuario no encontrado");
        }

        Optional<Tarea> tareaOptional = iTareaService.buscarTareaId(tareaId);

        if (!tareaOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("tarea no encontrada");
        }

        String mensaje = iTareaService.eliminarUsuarioTarea(usuarioOptional.get() , tareaOptional.get());


        return ResponseEntity.status(HttpStatus.OK).body(mensaje);
    }

    private  Map<String, String>  validar(BindingResult result){
        Map<String, String> errores = new HashMap<>();

        result.getFieldErrors().forEach(err ->  {
            errores.put(err.getField() , err.getDefaultMessage());
        });
        return errores;
    }




}

