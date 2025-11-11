package br.com.ifba.curso.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;

/**
 *
 * @author guilhermeAmedrado
 */

// @Entity -> Avisa ao JPA que esta classe representa uma tabela no banco
@Entity 
public class Curso implements Serializable {

    // @Id  Marca este atributo como a Chave Primária (Primary Key)
    @Id 
    // @GeneratedValue  O valor será gerado automaticamente pelo banco
    // (strategy = GenerationType.IDENTITY)  Usa o "auto-incremento" do banco
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    // @Column  Mapeia para uma coluna (opcional se o nome for igual)
    // "length = 100"  Define o tamanho (ex: VARCHAR(100))
    // "nullable = false"  Não pode ser nulo (NOT NULL)
    @Column(length = 100, nullable = false)
    private String nome;

    // "unique = true"  Garante que não haverá dois cursos com o mesmo código
    @Column(length = 50, nullable = false, unique = true) 
    private String codigo;

    

    public Curso() {
    }

    public Curso(String nome, String codigo) {
        this.nome = nome;
        this.codigo = codigo;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
        return "Curso{" + "id=" + id + ", nome=" + nome + ", codigo=" + codigo + '}';
    }
}