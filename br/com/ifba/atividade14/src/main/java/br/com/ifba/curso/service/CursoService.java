/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.curso.service;

/**
 *
 * @author guilhermeAmedrado
 */
import br.com.ifba.curso.dao.CursoDAO;
import br.com.ifba.curso.entity.Curso;
import br.com.ifba.infrastructure.util.StringUtil; 
import java.util.List;

/**
 * Implementação da camada de serviço.
 * Aqui ficam as regras de negócio e validações.
 */
public class CursoService implements CursoIService {

    // O Service "conversa" com o DAO
    private final CursoDAO cursoDao = new CursoDAO();

    @Override
    public Curso save(Curso curso) {
        // Regra de Negócio: Validar campos obrigatórios
        if (StringUtil.isNullOrEmpty(curso.getNome())) {
            throw new RuntimeException("O nome do curso é obrigatório.");
        }
        if (StringUtil.isNullOrEmpty(curso.getCodigo())) {
            throw new RuntimeException("O código do curso é obrigatório.");
        }
        // Se passou na validação, manda o DAO salvar
        return cursoDao.salvar(curso);
    }

    @Override
    public Curso update(Curso curso) {
        if (StringUtil.isNullOrEmpty(curso.getNome())) {
            throw new RuntimeException("O nome do curso é obrigatório.");
        }
        if (StringUtil.isNullOrEmpty(curso.getCodigo())) {
            throw new RuntimeException("O código do curso é obrigatório.");
        }
        return cursoDao.atualizar(curso);
    }

    @Override
    public void delete(Curso curso) {
        if (curso == null || curso.getId() == null) {
            throw new RuntimeException("Curso inválido para exclusão.");
        }
        cursoDao.excluir(curso);
    }

    @Override
    public List<Curso> findAll() {
        return cursoDao.listarTodos();
    }

    @Override
    public List<Curso> findByName(String nome) {
        if (StringUtil.isNullOrEmpty(nome)) {
            return cursoDao.listarTodos();
        }
        return cursoDao.buscarPorNome(nome);
    }
}
