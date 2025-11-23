package br.com.ifba.curso.dao;

import br.com.ifba.curso.entity.Curso;
import br.com.ifba.infrastructure.dao.GenericDao;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.springframework.stereotype.Repository; // Importante

@Repository // <--- ETIQUETA DE DAO
public class CursoDAO extends GenericDao<Curso> {

    public List<Curso> buscarPorNome(String nome) {
        // Usamos o entityManager que herdamos do GenericDao
        String jpql = "FROM Curso c WHERE c.nome LIKE :nome";
        TypedQuery<Curso> query = entityManager.createQuery(jpql, Curso.class);
        query.setParameter("nome", "%" + nome + "%");
        return query.getResultList();
    }
}