package br.com.ifba.curso.service;

import br.com.ifba.curso.dao.CursoDAO;
import br.com.ifba.curso.entity.Curso;
import br.com.ifba.infrastructure.util.StringUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoService implements CursoIService {

    @Autowired
    private CursoDAO cursoDao;

    @Override
    public Curso save(Curso curso) {
        // Regra de Negócio: Validação
        if (StringUtil.isNullOrEmpty(curso.getNome())) {
            throw new RuntimeException("O nome do curso é obrigatório.");
        }
        if (StringUtil.isNullOrEmpty(curso.getCodigo())) {
            throw new RuntimeException("O código do curso é obrigatório.");
        }
        
        // Manda o DAO salvar
        return cursoDao.salvar(curso);
    }

    @Override
    public Curso update(Curso curso) {
        if (StringUtil.isNullOrEmpty(curso.getNome())) {
            throw new RuntimeException("O nome do curso é obrigatório.");
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