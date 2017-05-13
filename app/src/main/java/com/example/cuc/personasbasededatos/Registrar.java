package com.example.cuc.personasbasededatos;


import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;


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
                pasatiempo=pasatiempo+getResources().getString(R.string.bailar);
            }

            //Le quita el espacio y la "," al final
            pasatiempo=pasatiempo.substring(pasatiempo.length()-1);
            p = new Persona(foto,cedula,nombre,apellido,sexo,pasatiempo);
            p.guardar(getApplicationContext());

            new AlertDialog.Builder(this).setMessage(getResources().getString(R.string.mensaje2)).setCancelable(true).show();
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



}
