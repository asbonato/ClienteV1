package br.usjt.ftce.desmob.clientev1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListarClientesActivity extends Activity {
    ListView listView;
    ArrayList<String> lista;
    public static final String NOME = "br.usjt.ftce.desmob.clientev1.nome";
    Activity atividade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_clientes);
        atividade = this;
        listView = (ListView) findViewById(R.id.lista_de_clientes);
        Intent intent = getIntent();
        String chave = intent.getStringExtra(MainActivity.CHAVE);
        lista = buscarCliente(chave);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
// manda para a tela de detalhe
                Intent intent = new Intent(atividade, DetalheClienteActivity.class);
                intent.putExtra(NOME, lista.get(position));
                startActivity(intent); }
        });
    }

    private ArrayList<String> listaDeClientes() {
        ArrayList<String> clientes = new ArrayList<>();
        clientes.add("Carlos Drummond de Andrade");
        clientes.add("Manuel Bandeira");
        clientes.add("Olavo Bilac");
        clientes.add("Vinícius de Moraes");
        clientes.add("Cecília Meireles");
        clientes.add("Castro Alves");
        clientes.add("Gonçalves Dias");
        clientes.add("Ferreira Gullar");
        clientes.add("Machado de Assis");
        clientes.add("Mário de Andrade");
        clientes.add("Cora Coralina");
        clientes.add("Manoel de Barros");
        clientes.add("João Cabral de Melo Neto");
        clientes.add("Casimiro de Abreu");
        clientes.add("Paulo Leminski");
        clientes.add("Álvares de Azevedo");
        clientes.add("Guilherme de Almeida");
        clientes.add("Alphonsus de Guimarães");
        clientes.add("Mário Quintana");
        clientes.add("Gregório de Matos");
        clientes.add("Augusto dos Anjos");

        return clientes;
    }

    private ArrayList<String> buscarCliente(String chave){
        ArrayList<String> clientes = listaDeClientes();
        ArrayList<String> resultado = new ArrayList<>();
        if(chave == null || chave.length()==0){
            return clientes;
        }
        for(String nome:clientes){
            if(nome.toUpperCase().contains(chave.toUpperCase())){
                resultado.add(nome);
            }
        }
        return resultado;
    }
}
