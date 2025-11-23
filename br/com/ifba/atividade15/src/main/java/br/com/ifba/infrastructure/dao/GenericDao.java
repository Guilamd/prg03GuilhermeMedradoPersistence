package br.com.ifba.infrastructure.dao;

import br.com.ifba.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.springframework.transaction.annotation.Transactional; // Importante!

public class GenericDao<Entity extends PersistenceEntity> implements GenericIDao<Entity> {

    // MÁGICA DO SPRING: Ele injeta o gerenciador de banco aqui
    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    @Transactional // O Spring abre, salva e fecha a transação sozinho
    public Entity salvar(Entity entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    @Transactional
    public Entity atualizar(Entity entity) {
        return entityManager.merge(entity);
    }

    @Override
    @Transactional
    public void excluir(Entity entity) {
        // Garante que o objeto está "conectado" antes de remover
        Entity entityAnexada = entityManager.merge(entity);
        entityManager.remove(entityAnexada);
    }

    @Override
    public List<Entity> listarTodos() {
        return entityManager.createQuery("FROM " + getTypeClass().getName()).getResultList();
    }

    @Override
    public Entity buscarId(Long id) {
        return (Entity) entityManager.find(getTypeClass(), id);
    }

    protected Class<?> getTypeClass() {
        return (Class<?>) ((ParameterizedType) getClass()
                .getGenericSuperclass())
                .getActualTypeArguments()[0];
    }
}