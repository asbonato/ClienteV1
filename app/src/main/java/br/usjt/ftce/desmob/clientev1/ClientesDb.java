package br.usjt.ftce.desmob.clientev1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by asbonato on 5/12/17.
 */
public class ClientesDb {
    ClientesDbHelper dbHelper;
    public static final String CLIENTE = "cliente";

    public ClientesDb(Context context){
        dbHelper = new ClientesDbHelper(context);
    }

    public void insereCliente(ArrayList<Cliente> clientes){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.delete(ClientesContract.ClienteEntry.TABLE_NAME, null, null );

        for(Cliente cliente:clientes) {
            ContentValues values = new ContentValues();
            values.put(ClientesContract.ClienteEntry.COLUMN_NAME_CLIENTE_NOME, cliente.getNome());
            values.put(ClientesContract.ClienteEntry.COLUMN_NAME_CLIENTE_FONE, cliente.getFone());
            values.put(ClientesContract.ClienteEntry.COLUMN_NAME_CLIENTE_EMAIL, cliente.getEmail());
            db.insert(ClientesContract.ClienteEntry.TABLE_NAME, null, values);
        }
    }

    public ArrayList<Cliente>  selecionaClientes(){
        ArrayList<Cliente> lista = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] colunas = {ClientesContract.ClienteEntry._ID,
                ClientesContract.ClienteEntry.COLUMN_NAME_CLIENTE_NOME,
                ClientesContract.ClienteEntry.COLUMN_NAME_CLIENTE_FONE,
                ClientesContract.ClienteEntry.COLUMN_NAME_CLIENTE_EMAIL};

        String ordem = ClientesContract.ClienteEntry.COLUMN_NAME_CLIENTE_NOME;

        Cursor c = db.query(ClientesContract.ClienteEntry.TABLE_NAME, colunas, null, null, ordem, null, null );

        while(c.moveToNext()){
            int id = c.getInt(c.getColumnIndex(ClientesContract.ClienteEntry._ID));
            String nome = c.getString(c.getColumnIndex(ClientesContract.ClienteEntry.COLUMN_NAME_CLIENTE_NOME));
            String fone = c.getString(c.getColumnIndex(ClientesContract.ClienteEntry.COLUMN_NAME_CLIENTE_FONE));
            String email = c.getString(c.getColumnIndex(ClientesContract.ClienteEntry.COLUMN_NAME_CLIENTE_EMAIL));
            Cliente cliente = new Cliente(id, nome, fone, email);

            lista.add(cliente);
        }

        c.close();

        return lista;
    }

}
