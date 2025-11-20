package br.com.ifba.curso.dao;

import br.com.ifba.curso.entity.Curso;
import br.com.ifba.infrastructure.dao.GenericDao; // Importa o PAI
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

/**
 * DAO específico para a entidade Curso.
 * Herda todo o CRUD básico de GenericDao.
 */
// 1. HERDA de GenericDao, especificando que é para "Curso"
public class CursoDAO extends GenericDao<Curso> {
    
    //SALVAR, ATUALIZAR, EXCLUIR, LISTAR_TODOS e BUSCAR_ID
    
    //Mantemos APENAS os métodos que são ÚNICOS do Curso:
    
    /**
     * Busca e retorna todos os Cursos cujo NOME contenha o texto informado.
     * @param nome O texto a ser buscado no nome do curso.
     * @return Uma Lista (List) de objetos Curso que correspondem à busca.
     */
    public List<Curso> buscarPorNome(String nome) {
        
        // Pego EntityManagerFactory (herdado)
        EntityManager em = entityManagerFactory.createEntityManager();
        
        List<Curso> cursos = null;

        try {
            String jpql = "FROM Curso c WHERE c.nome LIKE :nome"; 
            TypedQuery<Curso> query = em.createQuery(jpql, Curso.class);
            query.setParameter("nome", "%" + nome + "%");
            cursos = query.getResultList();
            
        } catch (Exception e) {
            System.err.println("Erro ao buscar os cursos: " + e.getMessage());
        } finally {
            em.close();
        }
        
        return cursos;
    }
}