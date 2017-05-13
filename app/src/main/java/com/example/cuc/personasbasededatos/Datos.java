package com.example.cuc.personasbasededatos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by CUC on 13/05/2017.
 */

public class Datos {
    public static ArrayList<Persona> traerPersonas(Context contexto){
        //Declaro variables
        ArrayList<Persona> personas = new ArrayList<>();

        SQLiteDatabase db;
        String sql,foto,cedula,nombre,apellido,sexo,pasatiempo;
        Persona p;

        //Abrir conexion
        PersonasSQLiteOpenHelper aux=new PersonasSQLiteOpenHelper(contexto,"DBPersonas",null,1);
        db=aux.getReadableDatabase();

        //Cursor
        sql="select * from Personas";
        Cursor c=db.rawQuery(sql,null);

        //Recorrido del cursor
        if (c.moveToFirst()){
            do{
                foto=c.getString(0);
                cedula=c.getString(1);
                nombre=c.getString(2);
                apellido=c.getString(3);
                sexo=c.getString(4);
                pasatiempo=c.getString(5);
                p=new Persona(foto,cedula,nombre,apellido,sexo,pasatiempo);
                personas.add(p);

            }while(c.moveToNext());
        }

        db.close();

        return personas;

    }

    public static Persona buscarPersona(Context contexto, String ced){
        //Declaro variables

        SQLiteDatabase db;
        String sql,foto,cedula,nombre,apellido,sexo,pasatiempo;
        Persona p=null;

        //Abrir conexion
        PersonasSQLiteOpenHelper aux=new PersonasSQLiteOpenHelper(contexto,"DBPersonas",null,1);
        db=aux.getReadableDatabase();

        //Cursor
        sql="select * from Personas where cedula='"+ced+"'";
        Cursor c=db.rawQuery(sql,null);

        //Recorrido del cursor
        if (c.moveToFirst()){

            foto=c.getString(0);
            cedula=c.getString(1);
            nombre=c.getString(2);
            apellido=c.getString(3);
            sexo=c.getString(4);
            pasatiempo=c.getString(5);
            p=new Persona(foto,cedula,nombre,apellido,sexo,pasatiempo);



        }

        db.close();

        return p;
    }




}
