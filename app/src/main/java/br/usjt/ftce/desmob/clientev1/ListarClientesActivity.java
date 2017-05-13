package br.usjt.ftce.desmob.clientev1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListarClientesActivity extends Activity {
    ListView listView;
    Cliente[] lista;
    public static final String CLIENTE = "br.usjt.ftce.desmob.clientev1.cliente";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_clientes);
        listView = (ListView) findViewById(R.id.lista_de_clientes);
        Intent intent = getIntent();
        ArrayList<Cliente> clientes = (ArrayList<Cliente>)intent.getSerializableExtra(MainActivity.LISTA);

        //TODO Salvar clientes no banco

        System.out.println("Clientes: "+clientes);
        lista = clientes.toArray(new Cliente[0]);

        BaseAdapter adapter = new ClienteAdapter(this, lista);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent1 = new Intent(ListarClientesActivity.this, DetalheClienteActivity.class);
                intent1.putExtra(CLIENTE, lista[i]);
                startActivity(intent1);
            }
        });

    }

}
