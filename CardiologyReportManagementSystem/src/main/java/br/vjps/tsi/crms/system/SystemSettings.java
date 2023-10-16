package br.vjps.tsi.crms.system;

/**
 * Classe responsável por agrupar todas as constantes de pré-configurações do sistema, 
 * incluindo informações de conexão com o banco de dados, configurações de envio de e-mail, 
 * caminhos de diretórios e outras configurações importantes.
 * 
 * @author Vinicius J P Silva
 */
public final class SystemSettings {
	
	private SystemSettings() {}
	
	/**
	 * Dados para a conexão com o Banco de Dados.
	 */
	public static final String 
							// Nome do Banco de Dados.
							DB_NAME = "cardiology-system",
							
							// Usuário.
							DB_USER = "aluno",
							
							// Senha.
							DB_PASSWD = "aluno";
	
	
	// Define os modos de envio dos e-mail.
	public static enum EMAIL_SENDING_MODE {
		// MODO PADRÂO: efetua o envio para o e-mail do paciente, cadastrado no banco de dados.
		SEND_TO_PACIENT,
		
		// MODO DE TESTE: não efetua o envio de nenhum e-mail.
		DO_NOT_SEND,
		
		// MODO DE TESTE: efetua o envio para o e-mail de teste - TEST_EMAIL - definido abaixo.
		SEND_TO_TEST
	};

	/**
	 *  Define se os e-mails serão enviados aos pacientes, ao e-mail de teste ou não serão enviados.
	 *  Utilize os valores disponíveis em EMAIL_SENDING_MODE.
	 */
	public static final EMAIL_SENDING_MODE 
							EMAIL_MODE = EMAIL_SENDING_MODE.SEND_TO_TEST;	
	
	
	// Constantes do Sistema de Laudos.
	public static final String 
							
							// E-mail e senha utilizandos para efetuar o envio dos pedidos de exame.
							SENDER_EMAIL = "", 
							SENDER_EMAIL_PASSWD = "",
							
							/* 
							 * E-mail de teste, os pedidos de exame serão enviados 
							 * para o mesmo caso o modo de teste esteja ativado.
							 * (EMAIL_SENDING_MODE.SEND_TO_TEST)
							 */
							TEST_EMAIL = "",
							
							/*
							 *  Caminho absoluto para o diretório do projeto.
							 *  É essencial para que os PDF's dos resultados dos exames sejam salvos no diretório '/src/main/resources/pdfs' 
							 *  e para que o sistema seja capaz de encontrar as imagens de exames no diretório '/src/main/resources/images'.
							 *  
							 *  Exemplo:
							 *  "C:/Users/MeuUsuario/MeuWorkspace/CardiologyReportManagementSystem"
							 *  
							 *  O caminho deve ser completo e finalizar com o nome do diretório do projeto,
							 *  ou seja, '/CardiologyReportManagementSystem'.
							 */
							WEB_APP_ROOT_PATH = "C:/Users/Vinícius/Documents/CardiologyReportManagementSystem".replace('\\', '/');
							; 
	
	/**
	 * Define o tempo limite de inatividade (em minutos) dos usuários.
	 * Após o tempo determinado o usuário será desconectados automaticamente pelo sistema.
	 */
	public static final int INACTIVITY_TIMEOUT = 2;

}
