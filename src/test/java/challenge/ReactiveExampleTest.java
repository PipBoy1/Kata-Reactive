package challenge;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.test.StepVerifier;

import java.util.List;


@ExtendWith(MockitoExtension.class)
class ReactiveExampleTest {

    @InjectMocks
    ReactiveExample reactiveExample;


    @Test
    void sumaDePuntajes(){
        StepVerifier.create(reactiveExample.sumaDePuntajes())
                .expectNext(260)
                .verifyComplete();
    }

    @Test
    void mayorPuntajeDeEstudiante(){

    }

    @Test
    void totalDeAsisntenciasDeEstudiantesComMayorPuntajeDe(){
        StepVerifier.create(reactiveExample.totalDeAsisntenciasDeEstudiantesConMayorPuntajeDe(75))
                .expectNext(43)
                .verifyComplete();
    }

    @Test
    void elEstudianteTieneAsistenciasCorrectas(){
        StepVerifier.create(reactiveExample.elEstudianteTieneAsistenciasCorrectas(
                new Estudiante("raul", 30, List.of(5,2,1,4,5))
        )).expectNext(true)
                .verifyComplete();
    }

    @Test
    void promedioDePuntajesPorEstudiantes(){
        StepVerifier.create(reactiveExample.promedioDePuntajesPorEstudiantes())
                .expectNext(Double.valueOf(52))
                .verifyComplete();
    }

    @Test
    void estudiantesAprobados(){
        StepVerifier.create(reactiveExample.estudiantesAprobados())
                .expectNext("juan", "pedro")
                .expectComplete()
                .verify();
    }

    @Test
    void losNombresDeEstudianteConPuntajeMayorA(){
        StepVerifier.create(reactiveExample.losNombresDeEstudianteConPuntajeMayorA(60))
                .expectNext("juan", "pedro")
                .expectComplete()
                .verify();
    }
}