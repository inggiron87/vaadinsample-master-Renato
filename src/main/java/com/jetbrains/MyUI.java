package com.jetbrains;

import javax.servlet.annotation.WebServlet;
import javax.xml.soap.Text;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class MyUI extends UI {
    Profesor p = new Profesor();

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout mainLayout = new VerticalLayout();
        final HorizontalLayout profesorLayout = new HorizontalLayout();

        final TextField nombreProfesorTXT = new TextField("Nombre: ");
        final TextField especialidadProfesorTXT = new TextField("Especialidad: ");
        final Button agregarProfesorBTN = new Button("Agrega profesor");

        profesorLayout.addComponents(nombreProfesorTXT,especialidadProfesorTXT,agregarProfesorBTN);

        final HorizontalLayout estudianteLayout = new HorizontalLayout();
        final TextField nombreEstudianteTXT = new TextField("Nombre: ");
        final TextField edadEstudianteTXT = new TextField("Edad: ");
        final Button agregarEstudianteBTN = new Button("Agrega estudiante");
        final Grid<Estudiante> listaEGrid = new Grid<>();


        estudianteLayout.addComponents(nombreEstudianteTXT,edadEstudianteTXT,agregarEstudianteBTN,listaEGrid);


        final HorizontalLayout asignaturasLayout = new HorizontalLayout();
        final TextField nombreAsignaturaTXT = new TextField("Asigatura: ");
        final TextField notaAsignaturaTXT = new TextField("Nota: ");
        final TextField cantevalAsignaturaTXT = new TextField("Cantidad de examenes: ");
        final Button agregarAsignaturaBTN = new Button("Agrega asignatura");
        final Grid listadoAsignaturas = new Grid();




        asignaturasLayout.addComponents(nombreAsignaturaTXT,notaAsignaturaTXT,cantevalAsignaturaTXT,
                agregarAsignaturaBTN,listadoAsignaturas);


        mainLayout.addComponents(new Label("Información del profesor"),profesorLayout,
                                 new Label("Información del estudiante"),estudianteLayout,
                                 new Label("Información de las asignaturas"),asignaturasLayout);

        // Aciones de los botones "Listeners"

        agregarProfesorBTN.addClickListener( e -> {
            p.setNombre(nombreProfesorTXT.getValue());
            p.setEspecialidad(especialidadProfesorTXT.getValue());


            Notification.show(p.getNombre()+" con especialidad "+ p.getEspecialidad() + " ha sido agregado");

        });

        agregarEstudianteBTN.addClickListener( e-> {
            Estudiante e1 = new Estudiante();

            e1.setNombre(nombreEstudianteTXT.getValue());
            e1.setEdad(Integer.parseInt(edadEstudianteTXT.getValue()));

            p.addEstudiante(e1);

            listaEGrid.removeAllColumns();
            listaEGrid.setItems(p.getEstudiantes());
            listaEGrid.addColumn(Estudiante::getNombre).setCaption("Nombre");
            listaEGrid.addColumn(Estudiante::getEdad).setCaption("Edad");

            Notification.show(e1.getNombre()+" de "+e1.getEdad()+" años ha sido agregado");

            nombreEstudianteTXT.setValue("");
            edadEstudianteTXT.setValue("");
        });

        listaEGrid.addSelectionListener( e -> {
            Set<Estudiante> selE = e.getAllSelectedItems();

            if (selE.size()>=0) {
                Notification.show("Ha seleccionado");
                Notification.show(String.valueOf(selE.size()));
            }
        });





        setContent(mainLayout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
