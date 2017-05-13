package br.usjt.ftce.desmob.clientev1;

import android.provider.BaseColumns;

/**
 * Created by asbonato on 5/12/17.
 */
public class ClientesContract {
    public ClientesContract(){}

    public static abstract class ClienteEntry implements BaseColumns{
        public static final String TABLE_NAME = "cliente";
        public static final String COLUMN_NAME_CLIENTE_NOME = "nome";
        public static final String COLUMN_NAME_CLIENTE_FONE = "fone";
        public static final String COLUMN_NAME_CLIENTE_EMAIL = "email";
    }


}
