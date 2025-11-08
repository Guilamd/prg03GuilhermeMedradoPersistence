package br.com.ifba;

import br.com.ifba.view.CursoAdicionar;
import br.com.ifba.view.CursoEditar;
import br.com.ifba.view.CursoListar;

/**
 *
 * @author guilhermeAmedrado
 */
public class CursoSave {
    
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new CursoListar().setVisible(true);
    });
    }
}
