package com.GestorTareaApi.app.controllers;

import com.GestorTareaApi.app.dtos.ResponseCustom;
import com.GestorTareaApi.app.entities.Tarea;
import com.GestorTareaApi.app.entities.Usuario;
import com.GestorTareaApi.app.exceptions.TareaNoEncontradaException;
import com.GestorTareaApi.app.exceptions.UsuarioNoEncontradoException;
import com.GestorTareaApi.app.services.ITareaService;
import com.GestorTareaApi.app.services.IUsuarioService;
import jakarta.validation.Valid;
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
    public ResponseEntity<?> buscarTarea(@PathVariable Long id){
        Optional<Tarea> tareaOptional = tareaService.buscarTareaId(id);

        if(tareaOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseCustom("success" ,  "" , HttpStatus.OK.value() , tareaOptional.get(),null));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseCustom("info" , "tarea no encontrada" , HttpStatus.NOT_FOUND.value(), null , null));

    }

    @PostMapping("/")
    public ResponseEntity<?> crearTarea(@Valid @RequestBody Tarea tarea , BindingResult result){

        if (result.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body( new ResponseCustom("error" , "revisar campos nulos", HttpStatus.BAD_REQUEST.value(), null, validar(result)));
        }

        if (tareaService.buscarTareaPortitulo(tarea.getTitulo())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseCustom("error" , "ya existe tarea" , HttpStatus.BAD_REQUEST.value(), null , null));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseCustom("info" , "tarea creada con éxito" , HttpStatus.CREATED.value(),tareaService.guardarTarea(tarea),null));
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> modificarTarea(@Valid @RequestBody Tarea tarea , BindingResult result , @PathVariable Long id){

        if (result.hasErrors()){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body( new ResponseCustom("error" , "revisar campos nulos", HttpStatus.BAD_REQUEST.value(), null, validar(result)));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseCustom("success" , "tarea modificada" , HttpStatus.OK.value() , tareaService.modificarTarea(id , tarea),null));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarTarea(@PathVariable Long id){
        tareaService.borrarTarea(id);
       return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/usuario/{usuarioId}/tarea/{tareaId}")
    public ResponseEntity<?> asignarUsuarioTarea(@PathVariable Long usuarioId ,  @PathVariable Long tareaId){

        Usuario usuario = usuarioService.buscarUsuario(usuarioId).orElseThrow(()->new UsuarioNoEncontradoException(usuarioId));

        Tarea tarea  = tareaService.buscarTareaId(tareaId).orElseThrow(()->new TareaNoEncontradaException(tareaId));

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseCustom("success" , tareaService.asignarUsuarioTarea(usuario , tarea) , HttpStatus.OK.value(),null , null));
    }

    @DeleteMapping("/usuario/{usuarioId}/tarea/{tareaId}")
    public ResponseEntity<?> eliminarUsuarioTarea(@PathVariable Long usuarioId ,  @PathVariable Long tareaId){

        Usuario usuario = usuarioService.buscarUsuario(usuarioId).orElseThrow(()-> new UsuarioNoEncontradoException(usuarioId));

        Tarea tarea = tareaService.buscarTareaId(tareaId).orElseThrow(()-> new TareaNoEncontradaException(tareaId));

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseCustom("success" , tareaService.eliminarUsuarioTarea(usuario , tarea) , HttpStatus.OK.value() , null , null));
    }

    private  Map<String, String>  validar(BindingResult result){
        Map<String, String> errores = new HashMap<>();

        result.getFieldErrors().forEach(err ->  {
            errores.put(err.getField() , err.getDefaultMessage());
        });
        return errores;
    }




}

