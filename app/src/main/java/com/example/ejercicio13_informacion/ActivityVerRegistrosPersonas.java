package com.example.ejercicio13_informacion;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.ejercicio13_informacion.configuraciones.SQLiteConexion;
import com.example.ejercicio13_informacion.configuraciones.Transacciones;
import com.example.ejercicio13_informacion.configuraciones.tablas.Persona;

import java.util.ArrayList;

public class ActivityVerRegistrosPersonas extends AppCompatActivity {

    SQLiteConexion conexion;
    ListView lista;
    ArrayList<Persona> listaPersona;
    ArrayList <String> ArregloPersona;
    Button REGRESAR_A_MENU;

    
    
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_registros_personas);

        lista = (ListView) findViewById(R.id.registros);
        REGRESAR_A_MENU = (Button) findViewById(R.id.btn_regresar_a_menu) ;
        conexion = new SQLiteConexion(this, Transacciones.NameDatabase,null,1);

        obtenerlistaPeersona();
        //llenar grip con datos empleado
        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_list_item_1,ArregloPersona);
        lista.setAdapter(adp);


        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                ObtenerPersona(i);


            }
        });


        REGRESAR_A_MENU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });


    }
    private void ObtenerPersona(int id) {
        Persona persona = listaPersona.get(id);

        Intent intent = new Intent(getApplicationContext(),ActivityActualizarPersona.class);

        intent.putExtra("codigo", persona.getId()+"");
        intent.putExtra("nombre", persona.getNombres());
        intent.putExtra("apellidos", persona.getApellidos());
        intent.putExtra("edad", persona.getEdad()+"");
        intent.putExtra("correo", persona.getCorreo());
        intent.putExtra("direccion", persona.getDireccion());

        startActivity(intent);
    }

    private void obtenerlistaPeersona() {
        //conexion a la BD modo lectura
        SQLiteDatabase db = conexion.getReadableDatabase();

        //clase empleados
        Persona list_person = null;

        //inicializar array empleados con la clase
        listaPersona = new ArrayList<Persona>();

        //consulta BD directa
        Cursor cursor = db.rawQuery("SELECT * FROM "+ Transacciones.tablapersonas, null);

        //RECORRER LA TABLA MOVIENDONOS SOBRE EL CURSOR
        while (cursor.moveToNext())
        {
            list_person = new Persona();
            list_person.setId(cursor.getInt(0));
            list_person.setNombres(cursor.getString(1));
            list_person.setApellidos(cursor.getString(2));
            list_person.setEdad(cursor.getString(3));
            list_person.setCorreo(cursor.getString(4));
            list_person.setDireccion(cursor.getString(5));
            listaPersona.add(list_person);
        }
        cursor.close();
        //metodo para llenar lista
        llenarlista();
    }
    private void llenarlista()
    {
        ArregloPersona = new ArrayList<String>();

        for (int i=0; i<listaPersona.size();i++)
        {
            ArregloPersona.add(listaPersona.get(i).getId()+"|"+
                    listaPersona.get(i).getNombres()+"|"+
                    listaPersona.get(i).getApellidos()+"|"+
                    listaPersona.get(i).getEdad()+"|"+
                    listaPersona.get(i).getCorreo()+"|"+
                    listaPersona.get(i).getDireccion());

        }
    }

}