package br.com.ifba.infrastructure.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

/**
 * Esta é uma classe-mãe para todas as nossas entidades.
 * Ela não é uma tabela, mas "doa" seus campos (o ID)
 * para todas as classes que a herdarem.
 */
@MappedSuperclass
public class PersistenceEntity {

    /**
     * Campo que representa a chave primária (Primary Key) da entidade.
     * @Id -> Diz ao JPA que este é o identificador único.
     * @GeneratedValue(strategy = GenerationType.IDENTITY)
     * -> Indica que o banco (MySQL) será responsável por gerar
     * automaticamente o valor do ID (AUTO_INCREMENT).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Getter e Setter (necessários para o JPA)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}