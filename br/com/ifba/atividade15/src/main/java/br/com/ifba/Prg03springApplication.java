package br.com.ifba;

import br.com.ifba.curso.view.CursoListar;
import br.com.ifba.curso.view.CursoListar;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = "br.com.ifba")
public class Prg03springApplication {

    public static void main(String[] args) {
        //Inicia o Spring configurado para tela gráfica (headless=false)
        ConfigurableApplicationContext context = 
                new SpringApplicationBuilder(Prg03springApplication.class)
                .headless(false)
                .run(args);

        //Pede ao Spring a tela principal já "montada"
        CursoListar telaPrincipal = context.getBean(CursoListar.class);
        
        //Configura e mostra
        telaPrincipal.setSize(700, 550); // Tamanho inicial
        telaPrincipal.setLocationRelativeTo(null); // Centraliza
        telaPrincipal.setVisible(true);
    }
}