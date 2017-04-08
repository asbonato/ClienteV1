package br.usjt.ftce.desmob.clientev1;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.TreeSet;

/**
 * Created by asbonato on 9/19/16.
 */
public class SectionIndexBuilder {
    //cria um array de cabecalhos unicos de secao; os dados devem estar ordenados por nome
    public static Object[] BuildSectionHeaders(Cliente[] clientes)
    {
        ArrayList<String> results = new ArrayList<>();
        TreeSet<String> used    = new TreeSet<>();
        if(clientes != null) {
            for (Cliente item : clientes) {
                String letter = item.getNome().substring(0, 1);

                if (!used.contains(letter))
                    results.add(letter);

                used.add(letter);
            }
        }
        return results.toArray(new Object[0]);
    }

    //cria um mapa para responder: posicao --> secao de dados ordenados pelo nome
    public static Hashtable<Integer, Integer> BuildSectionForPositionMap(Cliente[] clientes)
    {
        Hashtable results = new Hashtable<Integer, Integer>();
        TreeSet<String> used    = new TreeSet<>();
        int section = -1;

        if(clientes != null) {
            for (int i = 0; i < clientes.length; i++) {
                String letter = clientes[i].getNome().substring(0, 1);

                if (!used.contains(letter)) {
                    section++;
                    used.add(letter);
                }

                results.put(i, section);
            }
        }
        return results;
    }

    //cria um mapa para responder: secao --> posicao de dados ordenados pelo nome
    public static Hashtable<Integer, Integer> BuildPositionForSectionMap(Cliente[] clientes)
    {
        Hashtable results = new Hashtable<Integer, Integer>();
        TreeSet<String> used    = new TreeSet<>();
        int section = -1;

        if(clientes != null) {
            for (int i = 0; i < clientes.length; i++) {
                String letter = clientes[i].getNome().substring(0, 1);

                if (!used.contains(letter)) {
                    section++;
                    used.add(letter);
                    results.put(section, i);
                }
            }
        }
        return results;
    }
}
