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
            
            CursoListar telaListar = new CursoListar();
            
            telaListar.setSize(700, 550);
            
            telaListar.setLocationRelativeTo(null);
            
            telaListar.setVisible(true);
            
        });
    }
}