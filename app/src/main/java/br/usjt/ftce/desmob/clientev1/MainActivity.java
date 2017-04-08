package br.usjt.ftce.desmob.clientev1;

import android.app.Activity;
import android.content.Intent;
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

    //padrao
    public static final String SERVIDOR = "http://10.0.2.2:8080";
    //casa
    //public static final String SERVIDOR = "http://192.168.0.12:8080";
    //faculdade
    //public static final String SERVIDOR = "http://10.70.9.176:8080";
    public static final String APLICACAO = "/arqdesis_poetas";
    public static final String RECURSO = "/cliente";
    public static final String LISTA = "br.usjt.ftce.desmob.clientev1.lista";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textNome = (EditText)findViewById(R.id.buscar_clientes);
    }

    public void buscarCliente(View view){
        intent = new Intent(this, ListarClientesActivity.class);
        chave = textNome.getText().toString();
        requester = new ClienteRequester();
        if(requester.isConnected(this)){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        lista = requester.get(SERVIDOR+APLICACAO+RECURSO, chave);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Lista: "+lista);
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
            Toast toast = Toast.makeText(this, "Rede indisponível", Toast.LENGTH_LONG);
            toast.show();
        }

        //startActivity(intent);
    }
}
