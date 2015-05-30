package com.example.abel.xmlparse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class individual_prestamo extends Activity {

    //Nodos utilizados en la aplicacion
    static final String KEY_CLAVE = "clave_prestamo";
    static final String KEY_FECHA = "fecha";
    static final String KEY_NOMBRE = "nombre_sol";
    static final String KEY_AREA = "area_sol";
    static final String KEY_DESCRIPCION = "descripcion";
    static final String KEY_RECIBIDO = "recibido";
    static final String KEY_ENTREGADO = "entregado";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.individual_prestamo);

        //Obteniendo el Intent
        Intent in = getIntent();

        //Variables y su tag respectivo
        String clave_prestamo = in.getStringExtra(KEY_CLAVE);
        String fecha = in.getStringExtra(KEY_FECHA);
        String nombre_sol = in.getStringExtra(KEY_NOMBRE);
        String area_sol = in.getStringExtra(KEY_AREA);
        String descripcion = in.getStringExtra(KEY_DESCRIPCION);
        String recibido = in.getStringExtra(KEY_RECIBIDO);
        String entregado = in.getStringExtra(KEY_ENTREGADO);

        //Variables y sus elementos graficos respectivamente
        TextView clv = (TextView) findViewById(R.id.clave2);
        TextView fec = (TextView) findViewById(R.id.fecha2);
        TextView nom = (TextView) findViewById(R.id.nombre2);
        TextView are = (TextView) findViewById(R.id.area2);
        TextView des = (TextView) findViewById(R.id.descripcion2);
        TextView rec = (TextView) findViewById(R.id.recibido2);
        TextView ent = (TextView) findViewById(R.id.entregado2);

        //Mostrar informacion obenida del Intent
        clv.setText(clave_prestamo);
        fec.setText(fecha);
        nom.setText(nombre_sol);
        are.setText(area_sol);
        des.setText(descripcion);
        rec.setText(recibido);
        ent.setText(entregado);
    }
}
