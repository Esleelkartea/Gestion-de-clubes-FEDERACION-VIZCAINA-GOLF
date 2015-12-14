/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.complementos;

import com.Negocio.Curso;
import com.Negocio.InstalacionHorario;
import com.Negocio.ProfesorHorario;
import com.Negocio.Reserva;
import com.Negocio.ReservaProfesor;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Julen
 */
public class Buscador {

   //Constructor
    public Buscador()
    {

    }

    //Otros metodos

    //Metodo para la busqueda de Reservas de un profesor
   public boolean buscarFechasReservaProfesor(ArrayList<ReservaProfesor> listaReservas, String pFecha, String pHora) throws ParseException
    {

        String fecha;

        int i = 0;
        while(i<listaReservas.size())
        {
            fecha = String.valueOf(listaReservas.get(i).getFecha());
            SimpleDateFormat sdfi=new SimpleDateFormat("yyyy-MM-dd");
            Calendar c1=Calendar.getInstance();
            c1.setTime(sdfi.parse(pFecha));
            Calendar c2=Calendar.getInstance();
            c2.setTime(sdfi.parse(fecha));

            if (c1.compareTo(c2)==0)
            {
                String hora2 = String.valueOf(listaReservas.get(i).getHoraInicio());
                int l = hora2.indexOf(":");
                String horaI = hora2.substring(0, l);
                String hora3 = String.valueOf(listaReservas.get(i).getHoraFin());

                l = hora3.indexOf(":");
                String horaF = hora3.substring(0, l);
                l = pHora.indexOf(":");
                String hora = pHora.substring(0, l);
                int hI = Integer.parseInt(horaI);
                int hF = Integer.parseInt(horaF);
                hF -= 1;
                int h = Integer.parseInt(hora);
                if(h<= hF && h>= hI)
                     return true;
            }
            i++;
        }
        return false;
    }


   //Metodo para la busqueda de horarios de un profesor
   public boolean buscarFechasProfesor(ArrayList<ProfesorHorario> listaProfesorHorario, String pFecha, String pHora) throws ParseException
    {

        int i = 0;
        while(i<listaProfesorHorario.size())
        {
            String fechaInicio = String.valueOf(listaProfesorHorario.get(i).getFechaInicio());
            String fechaFin = String.valueOf(listaProfesorHorario.get(i).getFechaFin());
            SimpleDateFormat sdfi=new SimpleDateFormat("yyyy-MM-dd");
            Calendar c1=Calendar.getInstance();
            c1.setTime(sdfi.parse(pFecha));
            Calendar c2=Calendar.getInstance();
            c2.setTime(sdfi.parse(fechaInicio));
            Calendar c3=Calendar.getInstance();
            c3.setTime(sdfi.parse(fechaFin));

            if (c1.compareTo(c2)>=0 && c1.compareTo(c3)<=0)
            {
                String hora2 = String.valueOf(listaProfesorHorario.get(i).getHoraInicio());
                int l = hora2.indexOf(":");
                String horaI = hora2.substring(0, l);
                String hora3 = String.valueOf(listaProfesorHorario.get(i).getHoraFin());
                l = hora3.indexOf(":");
                String horaF = hora3.substring(0, l);
                l = pHora.indexOf(":");
                String hora = pHora.substring(0, l);
                int hI = Integer.parseInt(horaI);
                int hF = Integer.parseInt(horaF);
                hF -= 1;
                int h = Integer.parseInt(hora);
                if(h<= hF && h>= hI)
                {
                   return true;
                }
            }
            i++;
        }
        return false;
    }



    public boolean buscarFechasReserva(ArrayList<Reserva> listaReservas, String pFecha, String pHora) throws ParseException
    {
        
        String fecha; 

        int i = 0;
        while(i<listaReservas.size())
        {
            fecha = String.valueOf(listaReservas.get(i).getFecha());
            SimpleDateFormat sdfi=new SimpleDateFormat("yyyy-MM-dd");
            Calendar c1=Calendar.getInstance();
            c1.setTime(sdfi.parse(pFecha));
            Calendar c2=Calendar.getInstance();
            c2.setTime(sdfi.parse(fecha));
            
            if (c1.compareTo(c2)==0)
            {
                String hora2 = String.valueOf(listaReservas.get(i).getHoraInicio());
                int l = hora2.indexOf(":");
                String horaI = hora2.substring(0, l);
                String hora3 = String.valueOf(listaReservas.get(i).getHoraFin());
                
                l = hora3.indexOf(":");
                String horaF = hora3.substring(0, l);
                l = pHora.indexOf(":");
                String hora = pHora.substring(0, l);
                int hI = Integer.parseInt(horaI);
                int hF = Integer.parseInt(horaF);
                hF -= 1;
                int h = Integer.parseInt(hora);
                if(h<= hF && h>= hI)
                     return true;
            }
            i++;
        }
        return false;
    }



    public boolean buscarFechasReserva2(ArrayList<Reserva> listaReservas, String pFecha, String pHora) throws ParseException
    {

        String fecha;

        int i = 0;
        while(i<listaReservas.size())
        {
            fecha = String.valueOf(listaReservas.get(i).getFecha());
            SimpleDateFormat sdfi=new SimpleDateFormat("yyyy-MM-dd");
            Calendar c1=Calendar.getInstance();
            c1.setTime(sdfi.parse(pFecha));
            Calendar c2=Calendar.getInstance();
            c2.setTime(sdfi.parse(fecha));

            if (c1.compareTo(c2)==0)
            {
                String hora2 = String.valueOf(listaReservas.get(i).getHoraInicio());
                int l = hora2.indexOf(":");
                String horaI = hora2.substring(0, l);
                String hora3 = String.valueOf(listaReservas.get(i).getHoraFin());

                l = hora3.indexOf(":");
                String horaF = hora3.substring(0, l);
                l = pHora.indexOf(":");
                String hora = pHora.substring(0, l);
                int hI = Integer.parseInt(horaI);
                int hF = Integer.parseInt(horaF);

                String arrHora[] = pHora.split(":");
                int pMinuto = Integer.parseInt(arrHora[1]);

                String arrHora2[] = hora2.split(":");
                int min2 = Integer.parseInt(arrHora2[1]);

                int h = Integer.parseInt(hora);
                if(h == hI)
                {
                    if(pMinuto == min2)
                    {
                        return true;
                    }
                }
            }
            i++;
        }
        return false;
    }


    public boolean buscarFechasInstalacion(ArrayList<InstalacionHorario> listaInstalacionHorario, String pFecha, String pHora) throws ParseException
    {

        int i = 0;
        while(i<listaInstalacionHorario.size())
        {
            String fechaInicio = String.valueOf(listaInstalacionHorario.get(i).getFechaInicio());
            String fechaFin = String.valueOf(listaInstalacionHorario.get(i).getFechaFin());
            SimpleDateFormat sdfi=new SimpleDateFormat("yyyy-MM-dd");
            Calendar c1=Calendar.getInstance();
            c1.setTime(sdfi.parse(pFecha));
            Calendar c2=Calendar.getInstance();
            c2.setTime(sdfi.parse(fechaInicio));
            Calendar c3=Calendar.getInstance();
            c3.setTime(sdfi.parse(fechaFin));

            if (c1.compareTo(c2)>=0 && c1.compareTo(c3)<=0)
            {
                String hora2 = String.valueOf(listaInstalacionHorario.get(i).getHoraInicio());
                int l = hora2.indexOf(":");
                String horaI = hora2.substring(0, l);
                String hora3 = String.valueOf(listaInstalacionHorario.get(i).getHoraFin());
                l = hora3.indexOf(":");
                String horaF = hora3.substring(0, l);
                l = pHora.indexOf(":");
                String hora = pHora.substring(0, l);
                int hI = Integer.parseInt(horaI);
                int hF = Integer.parseInt(horaF);
                hF -= 1;
                int h = Integer.parseInt(hora);
                if(h<= hF && h>= hI)
                {
                    int diaSemana = c1.get(Calendar.DAY_OF_WEEK);
                    switch(diaSemana)
                    {
                         case 1:
                            if(listaInstalacionHorario.get(i).isDomingo())
                                return true;
                            else
                                return false;
                         case 2:
                            if(listaInstalacionHorario.get(i).isLunes())
                                return true;
                            else
                                return false;
                         case 3:
                            if(listaInstalacionHorario.get(i).isMartes())
                                return true;
                            else
                                return false;
                         case 4:
                            if(listaInstalacionHorario.get(i).isMiercoles())
                                return true;
                            else
                                return false;
                         case 5:
                            if(listaInstalacionHorario.get(i).isJueves())
                                return true;
                            else
                                return false;
                         case 6:
                            if(listaInstalacionHorario.get(i).isViernes())
                                return true;
                            else
                                return false;
                         case 7:
                            if(listaInstalacionHorario.get(i).isSabado())
                                return true;
                            else
                                return false;
                    }
                }
            }
            i++;
        }
        return false;

    }






    //Añadido
    public boolean buscarFechasCurso(ArrayList<Curso> listaCursos, String pFecha, String pHora) throws ParseException
    {

        int i = 0;
        while(i<listaCursos.size())
        {
            String fechaInicio = String.valueOf(listaCursos.get(i).getFechaInicio());
            String fechaFin = String.valueOf(listaCursos.get(i).getFechaFin());
            SimpleDateFormat sdfi=new SimpleDateFormat("yyyy-MM-dd");
            Calendar c1=Calendar.getInstance();
            c1.setTime(sdfi.parse(pFecha));
            Calendar c2=Calendar.getInstance();
            c2.setTime(sdfi.parse(fechaInicio));
            Calendar c3=Calendar.getInstance();
            c3.setTime(sdfi.parse(fechaFin));

            if (c1.compareTo(c2)>=0 && c1.compareTo(c3)<=0)
            {
                String hora2 = String.valueOf(listaCursos.get(i).getHoraInicio());
                int l = hora2.indexOf(":");
                String horaI = hora2.substring(0, l);
                String hora3 = String.valueOf(listaCursos.get(i).getHoraFin());
                l = hora3.indexOf(":");
                String horaF = hora3.substring(0, l);
                l = pHora.indexOf(":");
                String hora = pHora.substring(0, l);
                int hI = Integer.parseInt(horaI);
                int hF = Integer.parseInt(horaF);
                hF -= 1;
                int h = Integer.parseInt(hora);
                if(h<= hF && h>= hI)
                {
                    int diaSemana = c1.get(Calendar.DAY_OF_WEEK);
                    switch(diaSemana)
                    {
                         case 1:
                            if(listaCursos.get(i).isDomingo())
                                return true;
                            else
                                return false;
                         case 2:
                            if(listaCursos.get(i).isLunes())
                                return true;
                            else
                                return false;
                         case 3:
                            if(listaCursos.get(i).isMartes())
                                return true;
                            else
                                return false;
                         case 4:
                            if(listaCursos.get(i).isMiercoles())
                                return true;
                            else
                                return false;
                         case 5:
                            if(listaCursos.get(i).isJueves())
                                return true;
                            else
                                return false;
                         case 6:
                            if(listaCursos.get(i).isViernes())
                                return true;
                            else
                                return false;
                         case 7:
                            if(listaCursos.get(i).isSabado())
                                return true;
                            else
                                return false;
                    }
                }
            }
            i++;
        }
        return false;

    }




}
