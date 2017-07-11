package com.jetbrains;

import java.util.*;


public class Estudiante {
    private String Nombre;
    private int Edad;
    private List<Asignatura> Materias;

    public Estudiante() {
        Materias = new ArrayList<>();
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getEdad() {
        return Edad;
    }

    public void setEdad(int edad) {
        Edad = edad;
    }

    public List<Asignatura> getMaterias() {
        return Materias;
    }

    public void setMaterias(List<Asignatura> materias) {
        this.Materias = materias;
    }

    public void addMateria(Asignatura a){
        Materias.add(a);
    }

    public float promedioNotas(){
        int sumaNotas=0;

        for (Asignatura a: Materias
             ) {
            sumaNotas+=a.getNota();
        }

        return sumaNotas/ Materias.size();
    }
}
