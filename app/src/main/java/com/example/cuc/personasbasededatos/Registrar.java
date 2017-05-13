package com.example.cuc.personasbasededatos;


import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;


public class Registrar extends AppCompatActivity {
    private EditText cajaCedula;
    private EditText cajaNombre;
    private EditText cajaApellido;
    private RadioButton rMasculino;
    private RadioButton rFemenino;
    private CheckBox chkProgramar;
    private CheckBox chkLeer;
    private CheckBox chkBailar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        cajaCedula=(EditText)findViewById(R.id.txtCedula);
        cajaNombre=(EditText)findViewById(R.id.txtNombre);
        cajaApellido=(EditText)findViewById(R.id.txtApellido);

        rMasculino=(RadioButton)findViewById(R.id.r1);
        rFemenino=(RadioButton)findViewById(R.id.r2);

        chkProgramar=(CheckBox)findViewById(R.id.chkProgramar);
        chkLeer=(CheckBox)findViewById(R.id.chkLeer);
        chkBailar=(CheckBox)findViewById(R.id.chkBailar);

    }

    public boolean validarTodo(){
        if (cajaCedula.getText().toString().isEmpty()){
            cajaCedula.setError(getResources().getString(R.string.error1));
            cajaCedula.requestFocus();
            return false;
        }
        if (cajaNombre.getText().toString().isEmpty()){
            cajaNombre.setError(getResources().getString(R.string.error2));
            cajaNombre.requestFocus();
            return false;
        }
        if (cajaApellido.getText().toString().isEmpty()){
            cajaApellido.setError(getResources().getString(R.string.error3));
            cajaApellido.requestFocus();
            return false;
        }
        //Escoger por lo menos 1 pasatiempo
        if ((!chkProgramar.isChecked())&&(!chkLeer.isChecked())&&(!chkBailar.isChecked())){
            new AlertDialog.Builder(this).setMessage(getResources().getString(R.string.mensaje1)).setCancelable(true).show();
            return false;
        }
        return true;
    }

    public void guardar(View v){
        String foto,cedula,nombre,apellido,sexo,pasatiempo="";
        Persona p;
        if (validarTodo()){
            cedula=cajaCedula.getText().toString();
            foto= String.valueOf(fotoaleatoria());
            nombre=cajaNombre.getText().toString();
            apellido=cajaApellido.getText().toString();
            if (rMasculino.isChecked()) sexo=getResources().getString(R.string.masculino);
            else sexo=getResources().getString(R.string.femenino);

            if (chkProgramar.isChecked()){
                pasatiempo=getResources().getString(R.string.programar)+",";
            }
            if (chkLeer.isChecked()){
                pasatiempo=pasatiempo+getResources().getString(R.string.leer)+",";
            }
            if (chkBailar.isChecked()){
                pasatiempo=pasatiempo+getResources().getString(R.string.bailar)+", ";
            }

            //Le quita el espacio y la "," al final
            pasatiempo=pasatiempo.substring(0,pasatiempo.length()-2);
            p = new Persona(foto,cedula,nombre,apellido,sexo,pasatiempo);
            p.guardar(getApplicationContext());

            new AlertDialog.Builder(this).setMessage(getResources().getString(R.string.mensaje2)).setCancelable(true).show();
            limpiar();
        }
    }

    public int fotoaleatoria(){
        int foto[]={R.drawable.images,R.drawable.images2,R.drawable.images3};
        int numero=(int)(Math.random()*3);
        return foto[numero];
    }

    public boolean validarCedula(){
        if (cajaCedula.getText().toString().isEmpty()){
            cajaCedula.setError(getResources().getString(R.string.error1));
            cajaCedula.requestFocus();
            return false;
        }
        return true;
    }

    public void limpiar(){
        cajaCedula.setText("");
        cajaNombre.setText("");
        cajaApellido.setText("");
        rMasculino.setChecked(true);
        chkProgramar.setChecked(false);
        chkLeer.setChecked(false);
        chkBailar.setChecked(false);
        cajaCedula.requestFocus();
    }


    public void borrar(View v){limpiar();}

    public void buscar (View v){
        Persona p;
        String pasatiempos;
        if (validarCedula()){
            p=Datos.buscarPersona(getApplicationContext(),cajaCedula.getText().toString());
            if (p!=null){
                cajaNombre.setText(p.getNombre());
                cajaApellido.setText(p.getApellido());
                if (p.getSexo().equalsIgnoreCase(getResources().getString(R.string.masculino)))rMasculino.setChecked(true);
                else rFemenino.setChecked(true);

               pasatiempos = p.getPasatiempo();
                if (pasatiempos.contains(getResources().getString(R.string.programar)))chkProgramar.setChecked(true);
                if (pasatiempos.contains(getResources().getString(R.string.leer)))chkLeer.setChecked(true);
                if (pasatiempos.contains(getResources().getString(R.string.bailar)))chkBailar.setChecked(true);
            }
        }
    }

    public void eliminar (View v){
        Persona p;
        String pasatiempos;
        if (validarCedula()){
            p=Datos.buscarPersona(getApplicationContext(),cajaCedula.getText().toString());
            if (p!=null){

                AlertDialog.Builder ventana=new AlertDialog.Builder(this);
                ventana.setTitle(getResources().getString(R.string.confirmacion));
                ventana.setMessage(getResources().getString(R.string.mensaje4));
                ventana.setPositiveButton(getResources().getString(R.string.confirmar), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int witch) {
                        Persona p;
                        p=Datos.buscarPersona(getApplicationContext(), cajaCedula.getText().toString());

                        p.eliminar(getApplicationContext());
                        limpiar();
                        Toast.makeText(getApplicationContext(),getResources().getString(R.string.mensaje3),Toast.LENGTH_SHORT).show();
                    }
                });

                ventana.setNegativeButton(getResources().getString(R.string.cancelar), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        cajaCedula.requestFocus();
                    }
                });
                ventana.show();

            }
        }
    }

    public void modificar (View v){
        Persona p,p2;
        String nombre,apellido,sexo,pasatiempo="";
        if (validarCedula()){
            p=Datos.buscarPersona(getApplicationContext(),cajaCedula.getText().toString());
            if (p!=null){

                nombre=cajaNombre.getText().toString();
                apellido=cajaApellido.getText().toString();
                if (rMasculino.isChecked()) sexo=getResources().getString(R.string.masculino);
                else sexo=getResources().getString(R.string.femenino);

                if (chkProgramar.isChecked()){
                    pasatiempo=getResources().getString(R.string.programar)+",";
                }
                if (chkLeer.isChecked()){
                    pasatiempo=pasatiempo+getResources().getString(R.string.leer)+",";
                }
                if (chkBailar.isChecked()){
                    pasatiempo=pasatiempo+getResources().getString(R.string.bailar)+", ";
                }

                //Le quita el espacio y la "," al final
                pasatiempo=pasatiempo.substring(0,pasatiempo.length()-2);
                p2 = new Persona(p.getFoto(),p.getCedula(),nombre,apellido,sexo,pasatiempo);
                p2.modificar(getApplicationContext());

                Toast.makeText(getApplicationContext(),getResources().getString(R.string.mensaje5),Toast.LENGTH_SHORT).show();
                limpiar();


            }
        }
    }



}
