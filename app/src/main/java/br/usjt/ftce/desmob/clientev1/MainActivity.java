package br.usjt.ftce.desmob.clientev1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends Activity {
    EditText textNome;
    ArrayList<Cliente> lista;
    ClienteRequester requester;
    Intent intent;
    String chave;
    Context contexto;

    //padrao
    public static final String SERVIDOR = "http://192.168.43.248:8080";
    //casa
    //public static final String SERVIDOR = "http://192.168.0.12:8080";
    //faculdade
    //public static final String SERVIDOR = "http://10.70.9.176:8080";
    public static final String APLICACAO = "/poetas";
    public static final String RECURSO = "/cliente";
    public static final String LISTA = "br.usjt.ftce.desmob.clientev1.lista";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contexto = this;
        textNome = (EditText) findViewById(R.id.buscar_clientes);
    }

    public void buscarCliente(View view) {
        intent = new Intent(this, ListarClientesActivity.class);
        chave = textNome.getText().toString();
        requester = new ClienteRequester();
        if (requester.isConnected(this)) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        lista = requester.get(SERVIDOR + APLICACAO + RECURSO, chave);
                        ClientesDb banco = new ClientesDb(contexto);
                        banco.insereCliente(lista);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Lista: " + lista);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            intent.putExtra(LISTA, lista);
                            startActivity(intent);
                        }
                    });

                }
            }).start();

        } else {
            Toast toast = Toast.makeText(this,
                    "Rede indisponível. Carregando clientes armazenados localmente.",
                    Toast.LENGTH_LONG);
            toast.show();
            // TODO Carregar clientes do banco
            new CarregaClientesDoBanco().execute(ClientesDb.CLIENTE);

        }

        //startActivity(intent);

    }
    private class CarregaClientesDoBanco extends AsyncTask<String, Void, ArrayList<Cliente>> {

        @Override
        protected ArrayList<Cliente> doInBackground(String... strings) {
            //teste rapido
            //Cliente teste = new Cliente(0, "Teste sem Conexao", "123456", "teste@conexao.sem");
            //ArrayList<Cliente> testes = new ArrayList<>();
            //testes.add(teste);
            ClientesDb banco = new ClientesDb(contexto); //nao apagar junto com o teste
            //banco.insereCliente(testes);
            //fim teste
            ArrayList<Cliente> clientes = banco.selecionaClientes();
            return clientes;
        }

        public void onPostExecute(ArrayList<Cliente> result) {
            intent.putExtra(LISTA, result);
            startActivity(intent);
        }
    }
}
