package br.com.ifba.curso.service;

/**
 *
 * @author guilhermeAmedrado
 */
import br.com.ifba.curso.entity.Curso;
import java.util.List;

/**
 * Interface que define o contrato para a camada de serviço do Curso.
 */
public interface CursoIService {
    public abstract Curso save(Curso curso);
    public abstract Curso update(Curso curso);
    public abstract void delete(Curso curso);
    public abstract List<Curso> findAll();
    public abstract List<Curso> findByName(String nome);
}
