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

    // All static variables
    static final String URL = "http://iin8.szhernandez.dx.am/bbddabel.xml";

    //http://iin8.szhernandez.dx.am/bbddabel.xml

    // XML node keys
    static final String KEY_TAG = "prestamos"; // parent node
    static final String KEY_CLAVE = "clave_prestamo"; // parent node
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

        ArrayList<HashMap<String, String>> menuItems = new ArrayList<HashMap<String, String>>();

        final XMLParser parser = new XMLParser();
        String xml = parser.getXmlFromUrl(URL); // getting XML
        Document doc = parser.getDomElement(xml); // getting DOM element

        NodeList nl = doc.getElementsByTagName(KEY_TAG);
        // looping through all item nodes <item>
        for (int i = 1; i < nl.getLength(); i++) {
            // creating new HashMap
            HashMap<String, String> map = new HashMap<String, String>();
            Element e = (Element) nl.item(i);
            // adding each child node to HashMap key => value
            map.put(KEY_CLAVE, parser.getValue(e, KEY_CLAVE));
            map.put(KEY_FECHA, parser.getValue(e, KEY_FECHA));
            map.put(KEY_NOMBRE, parser.getValue(e, KEY_NOMBRE));
            map.put(KEY_AREA, parser.getValue(e, KEY_AREA));
            map.put(KEY_DESCRIPCION, parser.getValue(e, KEY_DESCRIPCION));
            map.put(KEY_RECIBIDO, parser.getValue(e, KEY_RECIBIDO));
            map.put(KEY_ENTREGADO, parser.getValue(e, KEY_ENTREGADO));

            // adding HashList to ArrayList
            menuItems.add(map);
        }

        // Adding menuItems to ListView
        ListAdapter adapter = new SimpleAdapter(this, menuItems, R.layout.list_prestamo, new String[]{ KEY_CLAVE, KEY_FECHA, KEY_NOMBRE, KEY_AREA, KEY_DESCRIPCION, KEY_RECIBIDO, KEY_ENTREGADO}, new int[]{ R.id.clave2_2, R.id.fecha2_2, R.id.nombre2_2, R.id.area2_2, R.id.descripcion2_2, R.id.recibido2_2, R.id.entregado2_2});

        setListAdapter(adapter);

        // selecting single ListView item
        ListView lv = getListView();

        lv.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // getting values from selected ListItem
                String clv = ((TextView) view.findViewById(R.id.clave2_2)).getText().toString();
                String fec = ((TextView) view.findViewById(R.id.fecha2_2)).getText().toString();
                String nom = ((TextView) view.findViewById(R.id.nombre2_2)).getText().toString();
                String are = ((TextView) view.findViewById(R.id.area2_2)).getText().toString();
                String des = ((TextView) view.findViewById(R.id.descripcion2_2)).getText().toString();
                String rec = ((TextView) view.findViewById(R.id.recibido2_2)).getText().toString();
                String ent = ((TextView) view.findViewById(R.id.entregado2_2)).getText().toString();

                // Starting new intent
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
