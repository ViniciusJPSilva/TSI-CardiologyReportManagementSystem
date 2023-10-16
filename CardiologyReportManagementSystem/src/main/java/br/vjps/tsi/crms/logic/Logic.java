package br.vjps.tsi.crms.logic;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * A interface Logic define um contrato para classes que implementam a lógica de controle
 * em uma aplicação web. Classes que implementam esta interface devem fornecer uma
 * implementação para o método execute, que é usado para processar solicitações HTTP
 * e retornar uma resposta como uma string.
 */
public interface Logic {
	
    /**
     * Executa a lógica de controle para processar uma solicitação HTTP.
     *
     * @param request  O objeto HttpServletRequest contendo os dados da solicitação.
     * @param response O objeto HttpServletResponse usado para enviar a resposta.
     * @return Uma string que representa o resultado da execução da lógica.
     * 
     * @throws ServletException Se ocorrer um erro relacionado ao servlet.
     * @throws IOException      Se ocorrer um erro de entrada/saída.
     */
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
