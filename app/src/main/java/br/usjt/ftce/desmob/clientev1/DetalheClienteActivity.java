package br.usjt.ftce.desmob.clientev1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetalheClienteActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_cliente);
        TextView nome = (TextView)findViewById(R.id.nome_do_cliente);
        Intent intent = getIntent();
        String nomeCliente = intent.getStringExtra(ListarClientesActivity.NOME);
        nome.setText(nomeCliente);
    }
}
