package com.example.ejercicio13_informacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ejercicio13_informacion.configuraciones.SQLiteConexion;
import com.example.ejercicio13_informacion.configuraciones.Transacciones;

public class ActivityAgregarPersonas extends AppCompatActivity {
    EditText NOMBRES, APELLIDOS, EDAD, CORREO, DIRECCION;
    Button GUARDAR, REGRESAR;

    @Override public void onBackPressed() { }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_personas);

        // inicializamos las variables
        NOMBRES = (EditText) findViewById(R.id.txt_nombres);
        APELLIDOS = (EditText) findViewById(R.id.txt_apellidos);
        EDAD = (EditText) findViewById(R.id.txt_edad);
        CORREO = (EditText) findViewById(R.id.txt_correo);
        DIRECCION = (EditText) findViewById(R.id.txt_direccion);
        GUARDAR = (Button) findViewById(R.id.btn_guardar_datos);
        REGRESAR = (Button) findViewById(R.id.btn_regresar);


        GUARDAR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarPersonas();
            }
        });

        REGRESAR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //REGRESAR AL MENU PRINCIPAL
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void agregarPersonas() {

        SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);

        SQLiteDatabase db = conexion.getWritableDatabase();


        ContentValues valores = new ContentValues();

        valores.put(Transacciones.nombres, NOMBRES.getText().toString());
        valores.put(Transacciones.apellidos, APELLIDOS.getText().toString());
        valores.put(Transacciones.edad, EDAD.getText().toString());
        valores.put(Transacciones.correo, CORREO.getText().toString());
        valores.put(Transacciones.direccion, DIRECCION.getText().toString());

        Long resultado = db.insert(Transacciones.tablapersonas, Transacciones.id, valores);

        Toast.makeText(getApplicationContext(), "Registro ingreso con exito, Codigo " + resultado.toString()
                ,Toast.LENGTH_LONG).show();

        db.close();

        //despues de guardar se procede a limpiar las cajas de texto
        limpiarPantalla();


        //REGRESAR AL MENU PRINCIPAL
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);


    }

    private void limpiarPantalla() {
        NOMBRES.setText("");
        APELLIDOS.setText("");
        EDAD.setText("");
        CORREO.setText("");
        DIRECCION.setText("");
    }
}