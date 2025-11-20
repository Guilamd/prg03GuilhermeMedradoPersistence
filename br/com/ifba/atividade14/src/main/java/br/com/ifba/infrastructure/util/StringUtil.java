package br.com.ifba.infrastructure.util;

/**
 * Classe utilitária para operações com Strings.
 * Usada para validações nas regras de negócio.
 */
public class StringUtil {
    
    // Verifica se uma string é nula ou vazia
    public static boolean isNullOrEmpty(String text) {
        return text == null || text.trim().isEmpty();
    }
    
    // Verifica se uma string NÃO é nula nem vazia
    public static boolean isNotNullOrEmpty(String text) {
        return !isNullOrEmpty(text);
    }
}