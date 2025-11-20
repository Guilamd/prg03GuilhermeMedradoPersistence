package br.com.ifba.curso.entity;

// Importa nossa nova classe-mãe
import br.com.ifba.infrastructure.entity.PersistenceEntity; 
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
// herda de PersistenceEntity 
public class Curso extends PersistenceEntity {

    //O ID, getId() e setId() FORAM REMOVIDOS
    //Eles agora são herdados automaticamente da PersistenceEntity.

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(length = 50, nullable = false, unique = true)
    private String codigo;
    
    // Construtores
    public Curso() {
    }

    public Curso(String nome, String codigo) {
        this.nome = nome;
        this.codigo = codigo;
    }

    // --- Getters e Setters (apenas de nome e codigo) ---
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        // Usamos o getId() que foi herdado!
        return "Curso{" + "id=" + this.getId() + ", nome=" + nome + ", codigo=" + codigo + '}';
    }
}