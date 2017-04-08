package br.usjt.ftce.desmob.clientev1;

import java.io.Serializable;

/**
 * Created by asbonato on 03/03/17.
 */

public class Cliente implements Serializable, Comparable<Cliente> {
    private int id;
    private String nome;
    private String fone;
    private String email;

    public Cliente(int id, String nome, String fone, String email) {
        this.id = id;
        this.nome = nome;
        this.fone = fone;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImagem(){
        String foto = this.email.replace('@', '_');
        return foto.replace('.', '_');
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cliente cliente = (Cliente) o;

        if (id != cliente.id) return false;
        if (!nome.equals(cliente.nome)) return false;
        if (!fone.equals(cliente.fone)) return false;
        return email.equals(cliente.email);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + nome.hashCode();
        result = 31 * result + fone.hashCode();
        result = 31 * result + email.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", fone='" + fone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public int compareTo(Cliente cliente) {
        return this.nome.compareTo(cliente.getNome());
    }
}
