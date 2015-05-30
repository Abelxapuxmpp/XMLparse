package com.example.abel.xmlparse;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends ListActivity {

    //URL del archivo almacenado en un servidor online
    static final String URL = "http://iin8.szhernandez.dx.am/bbddabel.xml";

    //Nodos XML del archivo y tags utilizados en la aplicacion
    static final String KEY_TAG = "prestamos"; // Nodo padre
    static final String KEY_CLAVE = "clave_prestamo"; // Nodos de los atributos
    static final String KEY_FECHA = "fecha";
    static final String KEY_NOMBRE = "nombre_sol";
    static final String KEY_AREA = "area_sol";
    static final String KEY_DESCRIPCION = "descripcion";
    static final String KEY_RECIBIDO = "recibido";
    static final String KEY_ENTREGADO = "entregado";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Arreglo que almacenara los datos
        ArrayList<HashMap<String, String>> menuItems = new ArrayList<HashMap<String, String>>();

        final XMLParser parser = new XMLParser();
        String xml = parser.getXmlFromUrl(URL);
        Document doc = parser.getDomElement(xml);

        //Identifica el nodo padre de los regisros del archivo
        NodeList nl = doc.getElementsByTagName(KEY_TAG);
        //Contador para leer cada uno de los registros
        for (int i = 1; i < nl.getLength(); i++) {
            //Creando nuevo arreglo
            HashMap<String, String> map = new HashMap<String, String>();
            Element e = (Element) nl.item(i);
            //Agregando cada uno de los registros en el arreglo
            map.put(KEY_CLAVE, parser.getValue(e, KEY_CLAVE));
            map.put(KEY_FECHA, parser.getValue(e, KEY_FECHA));
            map.put(KEY_NOMBRE, parser.getValue(e, KEY_NOMBRE));
            map.put(KEY_AREA, parser.getValue(e, KEY_AREA));
            map.put(KEY_DESCRIPCION, parser.getValue(e, KEY_DESCRIPCION));
            map.put(KEY_RECIBIDO, parser.getValue(e, KEY_RECIBIDO));
            map.put(KEY_ENTREGADO, parser.getValue(e, KEY_ENTREGADO));
            menuItems.add(map);
        }

        //Agregando arreglo al ListView
        ListAdapter adapter = new SimpleAdapter(this, menuItems, R.layout.list_prestamo, new String[]{ KEY_CLAVE, KEY_FECHA, KEY_NOMBRE, KEY_AREA, KEY_DESCRIPCION, KEY_RECIBIDO, KEY_ENTREGADO}, new int[]{ R.id.clave2_2, R.id.fecha2_2, R.id.nombre2_2, R.id.area2_2, R.id.descripcion2_2, R.id.recibido2_2, R.id.entregado2_2});

        setListAdapter(adapter);

        //Identificar cuando se le de clic a un elemento del list view
        ListView lv = getListView();

        lv.setOnItemClickListener(new OnItemClickListener() {

            //Metodo para identificar el clic en el listView
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Variables y sus respectivos elementos graficos
                String clv = ((TextView) view.findViewById(R.id.clave2_2)).getText().toString();
                String fec = ((TextView) view.findViewById(R.id.fecha2_2)).getText().toString();
                String nom = ((TextView) view.findViewById(R.id.nombre2_2)).getText().toString();
                String are = ((TextView) view.findViewById(R.id.area2_2)).getText().toString();
                String des = ((TextView) view.findViewById(R.id.descripcion2_2)).getText().toString();
                String rec = ((TextView) view.findViewById(R.id.recibido2_2)).getText().toString();
                String ent = ((TextView) view.findViewById(R.id.entregado2_2)).getText().toString();

                //Iniciando Intent para enviar los datos a otra actividad
                Intent in = new Intent(getApplicationContext(), individual_prestamo.class);
                in.putExtra(KEY_CLAVE, clv);
                in.putExtra(KEY_FECHA, fec);
                in.putExtra(KEY_NOMBRE, nom);
                in.putExtra(KEY_AREA, are);
                in.putExtra(KEY_DESCRIPCION, des);
                in.putExtra(KEY_RECIBIDO, rec);
                in.putExtra(KEY_ENTREGADO, ent);
                startActivity(in);

            }
        });
    }
}
