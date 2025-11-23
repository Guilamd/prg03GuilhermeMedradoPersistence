package br.com.ifba.curso.controller;

import br.com.ifba.curso.entity.Curso;
import br.com.ifba.curso.service.CursoIService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired; // <--- TEM QUE TER
import org.springframework.stereotype.Controller;

@Controller
public class CursoController implements CursoIController {

    @Autowired // <--- ESSA LINHA É OBRIGATÓRIA!
    private CursoIService cursoService;

    @Override
    public Curso save(Curso curso) {
        return cursoService.save(curso); // <--- O erro deu aqui
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