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

public class ActivityActualizarPersona extends AppCompatActivity {


    EditText NOMBRES, APELLIDOS, EDAD, CORREO, DIRECCION, CODIGO;
    Button ACTUALIZAR_DATOS, ELIMINAR_DATOS, REGRESAR_A_REGISTROS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_persona);

        CODIGO = (EditText) findViewById(R.id.txt_codigo);
        NOMBRES = (EditText) findViewById(R.id.txt_actualizar_nombre);
        APELLIDOS = (EditText) findViewById(R.id.txt_actualizar_apellidos);
        EDAD = (EditText) findViewById(R.id.txt_actualizar_edad);
        CORREO = (EditText) findViewById(R.id.txt_actualizar_correo);
        DIRECCION = (EditText) findViewById(R.id.txt_actualizar_direccion);

        ACTUALIZAR_DATOS = (Button) findViewById(R.id.btn_actualizar_datos);
        ELIMINAR_DATOS = (Button) findViewById(R.id.btn_eliminar_datos);
        REGRESAR_A_REGISTROS = (Button) findViewById(R.id.btn_regresar_a_registros) ;

        CODIGO.setText(getIntent().getStringExtra("codigo"));
        NOMBRES.setText(getIntent().getStringExtra("nombre"));
        APELLIDOS.setText(getIntent().getStringExtra("apellidos"));
        EDAD.setText(getIntent().getStringExtra("edad"));
        CORREO.setText(getIntent().getStringExtra("correo"));
        DIRECCION.setText(getIntent().getStringExtra("direccion"));

        ACTUALIZAR_DATOS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                actualizarPersona();


            }
        });


        ELIMINAR_DATOS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarPersona();
            }
        });

        REGRESAR_A_REGISTROS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //REGRESAR AL MENU PRINCIPAL
                Intent intent = new Intent(getApplicationContext(),ActivityVerRegistrosPersonas.class);
                startActivity(intent);
            }
        });
    }
    private void actualizarPersona() {
        SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();
        String obtenerCodigo = CODIGO.getText().toString();

        ContentValues valores = new ContentValues();

        valores.put(Transacciones.nombres, NOMBRES.getText().toString());
        valores.put(Transacciones.apellidos, APELLIDOS.getText().toString());
        valores.put(Transacciones.edad, EDAD.getText().toString());
        valores.put(Transacciones.correo, CORREO.getText().toString());
        valores.put(Transacciones.direccion, DIRECCION.getText().toString());

        db.update(Transacciones.tablapersonas, valores , Transacciones.id +" = "+obtenerCodigo, null);
        db.close();

        Intent intent = new Intent(getApplicationContext(),ActivityVerRegistrosPersonas.class);
        startActivity(intent);


    }

    private void eliminarPersona() {
        SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();
        String obtenerCodigo = CODIGO.getText().toString();

        db.delete(Transacciones.tablapersonas,Transacciones.id +" = "+ obtenerCodigo, null);

        Toast.makeText(getApplicationContext(), "Registro eliminado con exito, Codigo " + obtenerCodigo
                ,Toast.LENGTH_LONG).show();
        db.close();



        //REGRESAR AL MENU PRINCIPAL
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);



    }
}