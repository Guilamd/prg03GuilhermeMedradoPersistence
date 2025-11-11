package br.com.ifba.curso.dao;

import br.com.ifba.curso.entity.Curso;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceException; // Usaremos para tratar exceções
import java.util.List;
import jakarta.persistence.TypedQuery;

/**
 *
 * @author guilhermeAmedrado
 */
public class CursoDAO {

    private static final EntityManagerFactory emf = 
            Persistence.createEntityManagerFactory("prg03-persistence-unit");

    /**
     * Salva um novo curso no banco de dados ou atualiza um existente.
     * @param curso O objeto Curso preenchido que vem da tela.
     * @throws PersistenceException Se ocorrer um erro ao salvar (ex: código duplicado).
     */
    public void salvar(Curso curso) throws PersistenceException {
        
        // É o objeto que de fato "conversa" com o banco (salva, remove, etc.)
        EntityManager em = emf.createEntityManager();

        try {
            // Qualquer operação que MUDA o banco (INSERT, UPDATE, DELETE)
            // precisa estar dentro de uma transação.
            em.getTransaction().begin();

            // Usamos 'merge' pois ele serve tanto para INSERIR (novo curso)
            // quanto para ATUALIZAR (um curso que já existe).
            em.merge(curso);

            // Se chegou até aqui sem erros, o 'commit' grava os dados
            // permanentemente no banco.
            em.getTransaction().commit();
            
        } catch (PersistenceException e) {
            //Tratamento de Exceção 
            System.err.println("Erro ao salvar o curso: " + e.getMessage());
            
            // Se a transação estiver ativa (ex: deu erro antes do commit),
            //desfaz (rollback) para não deixar o banco "sujo".
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            
            // Relança a exceção para que a classe que chamou (a tela)
            // saiba que deu erro.
            throw e; 
            
        } finally {
            //fechar o entity
            // Isso "devolve" a conexão com o banco, liberando recursos.
            // O 'finally' garante que isso aconteça mesmo se der erro.
            em.close();
        }
    }
    
    /**
     * Busca e retorna todos os Cursos do banco de dados.
     * @return Uma Lista (List) de objetos Curso.
     * @throws PersistenceException Se ocorrer um erro na consulta.
     */
    public List<Curso> listarTodos() throws PersistenceException {
        // 1. Pega um EntityManager (conexão)
        EntityManager em = emf.createEntityManager();
        
        List<Curso> cursos = null;

        try {
            //Cria a consulta (Query)
            // "FROM Curso" - O "Curso" aqui é a classe @Entity,
            // e não o nome da tabela no banco. O JPA traduz sozinho.
            String jpql = "FROM Curso"; // JPQL (Java Persistence Query Language)
            
            //Prepara a consulta para retornar objetos do tipo "Curso"
            TypedQuery<Curso> query = em.createQuery(jpql, Curso.class);

            //Executa a consulta e pega o resultado
            cursos = query.getResultList();
            
        } catch (PersistenceException e) {
            System.err.println("Erro ao listar os cursos: " + e.getMessage());
            throw e; // Relança a exceção para a tela
        } finally {
            //Fecha o EntityManager
            em.close();
            }
        return cursos;
        }
        

    /**
     * Remove um curso do banco de dados, baseado no seu ID.
     * @param id O ID (Long) do curso a ser removido.
     * @throws PersistenceException Se ocorrer um erro na remoção.
     */
    public void remover(Long id) throws PersistenceException {
        //Pega um EntityManager
        EntityManager em = emf.createEntityManager();

        try {
            //Inicia a transação
            em.getTransaction().begin();

            //Busca o objeto no banco ANTES de remover
            //    Precisamos que o objeto esteja "gerenciado" pelo JPA.
            Curso cursoParaRemover = em.find(Curso.class, id);

            //Se ele encontrou o curso, remove
            if (cursoParaRemover != null) {
                em.remove(cursoParaRemover);
            } else {
                // (Opcional) Lança um erro se o curso já foi removido
                throw new PersistenceException("Curso com ID " + id + " não encontrado.");
            }

            //Confirma a transação
            em.getTransaction().commit();
            
        } catch (PersistenceException e) {
            //Tratamento de Exceção
            System.err.println("Erro ao remover o curso: " + e.getMessage());
            // Desfaz a transação se deu erro
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e; // Relança para a tela
            
        } finally {
            //Fecha o EntityManager
            em.close();
        }
    }  
    
    /**
     * Busca e retorna todos os Cursos cujo NOME contenha o texto informado.
     * @param nome O texto a ser buscado no nome do curso.
     * @return Uma Lista (List) de objetos Curso que correspondem à busca.
     * @throws PersistenceException Se ocorrer um erro na consulta.
     */
    public List<Curso> buscarPorNome(String nome) throws PersistenceException {
        //Pega um EntityManager
        EntityManager em = emf.createEntityManager();
        
        List<Curso> cursos = null;

        try {
            //Criar a consulta (Query)
            //    "FROM Curso c" -> 'c' é um "apelido" para Curso
            //    "WHERE c.nome LIKE :nome" -> Onde o nome for "parecido" com o parâmetro :nome
            String jpql = "FROM Curso c WHERE c.nome LIKE :nome"; 
            
            //Prepara a consulta
            TypedQuery<Curso> query = em.createQuery(jpql, Curso.class);

            // Define o parâmetro :nome
            //    Uso "%" como um coringa. "%" + nome + "%" significa:
            //    "qualquer coisa, depois o texto do nome, depois qualquer coisa"
            //    Ex: buscar por "sis" acha "Sistemas de Informação"
            query.setParameter("nome", "%" + nome + "%");

            //Executa a consulta e pega o resultado
            cursos = query.getResultList();
            
        } catch (PersistenceException e) {
            System.err.println("Erro ao buscar os cursos: " + e.getMessage());
            throw e; // Relança a exceção
        } finally {
            //Fecha o EntityManager
            em.close();
        }
        
        // Retorna a lista encontrada
        return cursos;
    }
}