package br.com.ifba.infrastructure.dao;

import br.com.ifba.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Classe genérica que realiza operações básicas de CRUD.
 * @param <Entity> entidade que estende PersistenceEntity
 */
public class GenericDao<Entity extends PersistenceEntity> implements GenericIDao<Entity> {

    protected static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("prg03-persistence-unit");

    // salva um novo registro no banco (para entidades NOVAS)
    @Override
    public Entity salvar(Entity entity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(entity); // 'persist' é para salvar novos
            entityManager.getTransaction().commit();
            return entity;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.err.println("Erro ao salvar entidade: " + e.getMessage());
            throw e;
        } finally {
            entityManager.close();
        }
    }

    // atualiza um registro existente (para entidades que já têm ID)
    @Override
    public Entity atualizar(Entity entity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(entity); // 'merge' é para atualizar existentes
            entityManager.getTransaction().commit();
            return entity;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.err.println("Erro ao atualizar entidade: " + e.getMessage());
            throw e;
        } finally {
            entityManager.close();
        }
    }

    // exclui um registro do banco.
    @Override
    public void excluir(Entity entity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            // Precisamos "anexar" a entidade antes de removê-la
            Entity entityAnexada = entityManager.merge(entity);
            entityManager.remove(entityAnexada);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.err.println("Erro ao excluir entidade: " + e.getMessage());
            throw e;
        } finally {
            entityManager.close();
        }
    }

    // retorna todos os registros da entidade.
    @Override
    @SuppressWarnings("unchecked")
    public List<Entity> listarTodos() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.createQuery("FROM " + getTypeClass().getName()).getResultList();
        } catch (Exception e) {
            System.err.println("Erro ao listar entidades: " + e.getMessage());
            return null;
        } finally {
            entityManager.close();
        }
    }

    // busca um registro pelo ID.
    @Override
    @SuppressWarnings("unchecked")
    public Entity buscarId(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return (Entity) entityManager.find(getTypeClass(), id);
        } finally {
            entityManager.close();
        }
    }

    // método mágico que descobre o tipo da classe (ex: Curso.class)
    protected Class<?> getTypeClass() {
        return (Class<?>) ((ParameterizedType) getClass()
                .getGenericSuperclass())
                .getActualTypeArguments()[0];
    }
}