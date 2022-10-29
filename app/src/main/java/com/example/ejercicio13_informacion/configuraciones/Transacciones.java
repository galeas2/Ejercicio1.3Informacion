package com.example.ejercicio13_informacion.configuraciones;

public class Transacciones {
    //Nombre de la base de datos
    public static final String NameDatabase = "PM01DB";

    //Creacion de las tablas de la base de datos
    public static final String tablapersonas = "personas";
       //Creacion de los atributos
        public static final String id = "id";
        public static final String nombres = "nombres";
        public static final String apellidos = "apellidos";
        public static final String edad = "edad";
        public static final String correo = "correo";
        public static final String direccion = "direccion";



        //Transacciones DDL
        public static final String createTablePersonas = "CREATE TABLE " + tablapersonas +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombres TEXT, apellidos TEXT, edad INTEGER, correo TEXT, direccion TEXT)";

        public static final String dropTablePersonas = "DROP TABLE IF EXIST" + tablapersonas;
}
