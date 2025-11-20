package br.com.ifba.infrastructure.dao;

import br.com.ifba.infrastructure.entity.PersistenceEntity;
import java.util.List;

/**
 * Interface genérica (Contrato) para operações de CRUD.
 * @param <Entity> representa qualquer entidade que herde de PersistenceEntity
 */
public interface GenericIDao<Entity extends PersistenceEntity> {

    // salva um novo objeto no banco.
    public abstract Entity salvar(Entity obj);

    // atualiza um objeto existente.
    public abstract Entity atualizar(Entity obj);

    // exclui um objeto do banco.
    public abstract void excluir(Entity obj);

    // retorna todos os registros da entidade.
    public abstract List<Entity> listarTodos();

    // busca um registro pelo ID.
    public abstract Entity buscarId(Long id);
}