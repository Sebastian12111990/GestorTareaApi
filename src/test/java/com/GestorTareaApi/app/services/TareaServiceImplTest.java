package com.GestorTareaApi.app.services;

import com.GestorTareaApi.app.entities.Tarea;
import com.GestorTareaApi.app.mothers.TareaMother;
import com.GestorTareaApi.app.repositories.TareaRepository;
import com.GestorTareaApi.app.repositories.UsuarioRepository;
import com.GestorTareaApi.app.repositories.UsuarioTareaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TareaServiceImplTest {

    @Mock
    private TareaRepository tareaRepositoryMock;
    @Mock
    private UsuarioRepository usuarioRepositoryMock;
    @Mock
    private UsuarioTareaRepository usuarioTareaRepositoryMock;
    @InjectMocks
    private TareaServiceImpl tareaService;
    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testGuardarTareaConExito(){

        Tarea tareaMock = TareaMother.tareaPredeterminada();
        when(tareaRepositoryMock.save(any(Tarea.class))).thenReturn(tareaMock);
        Tarea result = tareaService.guardarTarea(new Tarea());
        assertNotNull(result);
        assertEquals(tareaMock.getTitulo() , result.getTitulo());
        verify(tareaRepositoryMock , times(1)).save(any(Tarea.class));

    }
    @Test
    void testModificarTareConExito(){


        var idModificar = 1L;

        Tarea tareaModificar =   TareaMother.tareaPredeterminada();

        when(tareaRepositoryMock.findById(idModificar)).thenReturn(Optional.of(tareaModificar));
        when(tareaRepositoryMock.save(tareaModificar)).thenReturn(tareaModificar);

        tareaModificar.setTitulo("Tarea Modificada");

        Tarea resultado = tareaService.modificarTarea(idModificar, tareaModificar);

        assertNotNull(resultado);
        assertEquals("Tarea Modificada", resultado.getTitulo());
        verify(tareaRepositoryMock, times(1)).findById(idModificar);
        verify(tareaRepositoryMock, times(1)).save(tareaModificar);

    }

    @Test
    void testModificarTareConExito2(){


        var idModificar = 1L;

        Tarea tareaOriginal  = TareaMother.tareaPredeterminada();

        Tarea tareaModificar = TareaMother.tareaModificada();

        when(tareaRepositoryMock.findById(idModificar)).thenReturn(Optional.of(tareaOriginal));

        when(tareaRepositoryMock.save(any(Tarea.class))).thenReturn(tareaModificar);

        Tarea resultado = tareaService.modificarTarea(idModificar, tareaModificar);

        assertNotNull(resultado);
        assertEquals("Tarea Modificada", resultado.getTitulo());
        verify(tareaRepositoryMock, times(1)).findById(idModificar);
        verify(tareaRepositoryMock, times(1)).save(any(Tarea.class));


    }


    @Test
    public void testListarTarea(){

        List<Tarea> tareasMock = TareaMother.tareaListaPredeterminada();

        when(tareaRepositoryMock.findAll()).thenReturn(tareasMock);

        List<Tarea> result = tareaService.listarTareas();

        assertNotNull(result);
        assertEquals(2 , result.size());
        assertEquals("Tarea de Ejemplo", result.get(0).getTitulo());
        assertEquals("Otra Tarea"      , result.get(1).getTitulo());
    }
    @Test
    public void testBuscarTareaPorTitulo(){
        when(tareaRepositoryMock.countBuscarTareaPorTitulo("Otra Tarea")).thenReturn(1L);
        Boolean result = tareaService.buscarTareaPortitulo("Otra Tarea");
        assertTrue(result);
    }
    @Test
    public void testBuscarTareaPorTituloNoExiste(){
        when(tareaRepositoryMock.countBuscarTareaPorTitulo("Otra Tarea 3")).thenReturn(0L);
        Boolean result = tareaService.buscarTareaPortitulo("Otra Tarea 3");
        assertFalse(result);
    }

    @Test
    public void testBuscarTareaPorTituloNoExisteErrorInesperado(){
        when(tareaRepositoryMock.countBuscarTareaPorTitulo("Otra Tarea 3")).thenThrow(new RuntimeException("Error inesperado"));
        assertThrows(RuntimeException.class , ()->{
            tareaService.buscarTareaPortitulo("Otra Tarea 3");
        });
        verify(tareaRepositoryMock, times(1)).countBuscarTareaPorTitulo("Otra Tarea 3");
    }
    @Test
    public void testBuscarTareaID(){
        Long idBuscado = 1L;
        Tarea tareaMock = TareaMother.tareaPredeterminada();
        when(tareaRepositoryMock.findById(idBuscado)).thenReturn(Optional.of(tareaMock));
        Optional<Tarea> result = tareaService.buscarTareaId(idBuscado);
        assertTrue(result.isPresent(), "La tarea debería estar presente");
        assertEquals(tareaMock.getTitulo(), result.get().getTitulo(), "Los títulos de las tareas deben coincidir");
        verify(tareaRepositoryMock, times(1)).findById(idBuscado);
    }

    @Test
    public void testBuscarTareaPorIdConIdInvalido(){
        Long idInexistente = 999L;
        when(tareaRepositoryMock.findById(idInexistente)).thenReturn(Optional.empty());
        Optional<Tarea> result = tareaService.buscarTareaId(idInexistente);
        assertFalse(result.isPresent());
        verify(tareaRepositoryMock , times(1)).findById(idInexistente);

    }
    @Test
    public void testBuscarTareaPorIdConErrorInesperado() {
        Long idCualquier = 5L;
        when(tareaRepositoryMock.findById(idCualquier)).thenThrow(new RuntimeException("Error inesperado"));
        assertThrows(RuntimeException.class, () -> {
            tareaService.buscarTareaId(idCualquier);
        }, "Debería lanzar una excepción al ocurrir un error inesperado");
        verify(tareaRepositoryMock, times(1)).findById(idCualquier);
    }





}
