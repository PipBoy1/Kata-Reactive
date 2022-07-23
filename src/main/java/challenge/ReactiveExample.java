package challenge;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import java.util.Enumeration;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;


public class ReactiveExample {

    public static final int VALOR_PERMITIDO = 15;
    private  Flux<Estudiante> estudianteList;

    public ReactiveExample() {
        //TODO: convertir los estudiantes a un Flux

        List<Estudiante> estudianteList1 = List.of(
                new Estudiante("raul", 30, List.of(1, 2, 1, 4, 5)),
                new Estudiante("andres", 35, List.of(4, 2, 4, 3, 5)),
                new Estudiante("juan", 75, List.of(3, 2, 4, 5, 5)),
                new Estudiante("pedro", 80, List.of(5, 5, 4, 5, 5)),
                new Estudiante("santiago", 40, List.of(4, 5, 4, 5, 5))
        );
        estudianteList = Flux.fromIterable(estudianteList1);

    }

    //TODO: suma de puntajes
    public Mono<Integer> sumaDePuntajes() {
        return estudianteList
                .map(this.mapeoDeEstudianteAPuntaje())
                .reduce(0, Integer::sum);
    }

    private Function<Estudiante, Integer> mapeoDeEstudianteAPuntaje() {
        return Estudiante::getPuntaje;
    }

    //TODO: mayor puntaje de estudiante
    public Flux<Estudiante> mayorPuntajeDeEstudiante(int limit) {
        /*return estudianteList
                .sort(Estudiante::compareTo)
                .flatMap(estudiante -> estudiante));*/
        return null;
    }

    //TODO: total de asisntencias de estudiantes con mayor puntaje basado en un  valor
    public Mono<Integer> totalDeAsisntenciasDeEstudiantesConMayorPuntajeDe(int valor) {
        return estudianteList
                .filter(estudiante -> estudiante.getPuntaje() >= valor)
                .map(estudiante -> estudiante.getAsistencias().stream().reduce(0, Integer::sum))
                .reduce(0, Integer::sum);
    }

    //TODO: el estudiante tiene asistencias correctas
    public Mono<Boolean> elEstudianteTieneAsistenciasCorrectas(Estudiante estudiante) {
        return Mono.just(estudiante)
                .filter(est -> est.getAsistencias().stream().reduce(0, Integer::sum) >= VALOR_PERMITIDO)
                .map(estudiante1 -> true);
    }

    //TODO: promedio de puntajes por estudiantes
    public Mono<Double> promedioDePuntajesPorEstudiantes() {
        cantidadEst = 0;
        Mono<Double> total = estudianteList
                .map(estudiante -> {
                    cantidadEstudiantes();
                    return estudiante.getPuntaje();
                })
                .reduce(0, Integer::sum)
                .map(Integer::doubleValue);
        return total.map(aDouble -> aDouble / cantidadEst);
    }

    private Integer cantidadEst=0;
    public void cantidadEstudiantes(){
        cantidadEst++;
    }


    //TODO: los nombres de estudiante con puntaje mayor a un valor
    public Flux<String> losNombresDeEstudianteConPuntajeMayorA(int valor) {
        return estudianteList
                .filter(estudiante -> estudiante.getPuntaje() >= valor)
                .map(Estudiante::getNombre);
    }



    //TODO: estudiantes aprobados
    public Flux<String> estudiantesAprobados(){
        return estudianteList
                .filter(estudiante -> estudiante.getPuntaje() >= 75)
                //.map(estudiante -> estudiante.setAprobado(true))
                .map(this::aprobar)
                .map(Estudiante::getNombre);
    }
    private Estudiante aprobar(Estudiante estudiante){
        estudiante.setAprobado(true);
        return estudiante;
    }

    public Predicate<Estudiante> asistenciasPemitidas() {
        return estudiante -> estudiante.getAsistencias()
                .stream()
                .reduce(0, Integer::sum) >= VALOR_PERMITIDO;
    }


}
