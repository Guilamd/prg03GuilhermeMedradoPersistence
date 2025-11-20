package br.com.ifba.curso.controller;

import br.com.ifba.curso.entity.Curso;
import br.com.ifba.curso.service.CursoIService;
import br.com.ifba.curso.service.CursoService;
import java.util.List;

/**
 * Controlador que intermedia a View e o Service.
 */
public class CursoController implements CursoIController {

    // O Controller "conversa" com o Service
    private final CursoIService cursoService = new CursoService();

    @Override
    public Curso save(Curso curso) {
        return cursoService.save(curso);
    }

    @Override
    public Curso update(Curso curso) {
        return cursoService.update(curso);
    }

    @Override
    public void delete(Curso curso) {
        cursoService.delete(curso);
    }

    @Override
    public List<Curso> findAll() {
        return cursoService.findAll();
    }

    @Override
    public List<Curso> findByName(String nome) {
        return cursoService.findByName(nome);
    }
}