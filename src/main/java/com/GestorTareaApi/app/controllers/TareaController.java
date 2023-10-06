package com.GestorTareaApi.app.controllers;

import com.GestorTareaApi.app.entities.Tarea;
import com.GestorTareaApi.app.entities.Usuario;
import com.GestorTareaApi.app.services.ITareaService;
import com.GestorTareaApi.app.services.IUsuarioService;
import jakarta.validation.Valid;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("tarea/")
public class TareaController {
    private final ITareaService tareaService;
    private final IUsuarioService usuarioService;
    public TareaController(ITareaService tareaService, IUsuarioService usuarioService) {
        this.tareaService = tareaService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/")
    public ResponseEntity<?> listarTareas(){
       return ResponseEntity.status(HttpStatus.OK).body(tareaService.listarTareas());
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> buscarTarea(@PathVariable Long id){

        return tareaService.buscarTareaId(id).map(tarea ->
                        ResponseEntity.status(HttpStatus.OK).body(tarea)).
                orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }

    @PostMapping("/")
    private ResponseEntity<?> crearTarea(@Valid @RequestBody Tarea tarea , BindingResult result){

        if (result.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validar(result));
        }

        if (tarea.getEstado().getId() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("se necesita estado id");
        }

        if (tareaService.buscarTareaPortitulo(tarea.getTitulo())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ya existe tarea");
        }

        return ResponseEntity.status(HttpStatus.OK).body(tareaService.guardarTarea(tarea));
    }

    @PutMapping("/{id}")
    private ResponseEntity<?> modificarTarea(@Valid @RequestBody Tarea tarea , BindingResult result , @PathVariable Long id){

        if (result.hasErrors()){
            Map<String, String> errores =  validar(result);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        }

        if (tarea.getEstado().getId() == null){
            return new ResponseEntity<>("Estado no encontrado", HttpStatus.BAD_REQUEST);
        }

        return tareaService.buscarTareaId(id).map(tareaExistente -> {
            tareaExistente.setFechaDevencimiento(tarea.getFechaDevencimiento());
            tareaExistente.setEstado(tarea.getEstado());
            tareaExistente.setTitulo(tarea.getTitulo());
            return ResponseEntity.status(HttpStatus.CREATED).body(tareaService.guardarTarea(tareaExistente));
        }).orElseGet(() -> ResponseEntity.badRequest().build());

    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> eliminarTarea(@PathVariable Long id){
        tareaService.borrarTarea(id);
       return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/usuario/{usuarioId}/tarea/{tareaId}")
    private ResponseEntity<?> asignarUsuarioTarea(@PathVariable Long usuarioId ,  @PathVariable Long tareaId){

        Optional<Usuario> usuarioOptional = usuarioService.buscarUsuario(usuarioId);

        if (!usuarioOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("usuario no encontrado");
        }

        Optional<Tarea> tareaOptional = tareaService.buscarTareaId(tareaId);

        if (!tareaOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("tarea no encontrada");
        }

        String mensaje = tareaService.asignarUsuarioTarea(usuarioOptional.get() , tareaOptional.get());


        return ResponseEntity.status(HttpStatus.OK).body(mensaje);
    }

    @DeleteMapping("/usuario/{usuarioId}/tarea/{tareaId}")
    private ResponseEntity<?> eliminarUsuarioTarea(@PathVariable Long usuarioId ,  @PathVariable Long tareaId){

        Optional<Usuario> usuarioOptional = usuarioService.buscarUsuario(usuarioId);

        if (!usuarioOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("usuario no encontrado");
        }

        Optional<Tarea> tareaOptional = tareaService.buscarTareaId(tareaId);

        if (!tareaOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("tarea no encontrada");
        }

        String mensaje = tareaService.eliminarUsuarioTarea(usuarioOptional.get() , tareaOptional.get());


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

