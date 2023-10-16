# TSI-CardiologyReportManagementSystem

Pequeno sistema de laudos de cardiologia.

- Java versão 19

- Apache Tomcat versão 10.1

- Dynamic Web Module versão 6.0

<hr>
 
1) O arquivo de backup do banco de dados (cardiology-system_backup.sql) e arquivo com os códigos SQL 
   (cardiology-system_text-sql-codes.txt) estão no diretório "DataBase".
	> É necessário restaurar o backup ou executar o código txt para a gerar das tabelas.

<hr>

2) Lembre-se de alterar os dados necessários para a execução da aplicação.
   
   O arquivo de configuração é: src/main/java/br/vjps/tsi/crms/system/SystemSettings.java

   O que deverá/poderá ser alterado:

	- O nome do banco, usuário e senha (caso necessário).
	
	- O modo de envio de e-mails:
		- Enviar para um e-mail de testes. (habilitado)
		- Enviar para os e-mails dos pacientes cadastrados.
		- Não enviar nenhum e-mail.
	
	- E-mail (gmail) capaz de integrar com aplicações web, utilizado para efetuar o envio dos e-mails.
		- Altere também a sua respectiva senha.
	
	- Caminho absoluto para o diretório do projeto.
		- Utilize a barra padrão '/' e não a contra barra '\' (arrab) como delimitador de caminho.

Obs.: Todas as informações necessárias estão comentadas no arquivo SystemSettings.java.

<hr>
	
3) O login no sistema se dá por meio do CRM e da senha dos respectivos médicos, residentes e docentes.
   Há 9 (nove) usuários cadastrados, conforme a tabela:

<p>
 
	+---------------------------------+-----------+-------------+--------+
	|              Nome               |   Papel   | CRM (login) |  Senha |
	+---------------------------------+-----------+-------------+--------+
	| Dra Ana Paula da Silva          | Médico    | 111111      | 111111 |
	+---------------------------------+-----------+-------------+--------+
	| Dr João Carlos Santos           | Médico    | 222222      | 222222 |
	+---------------------------------+-----------+-------------+--------+
	| Dra Fernanda Oliveira           | Médico    | 333333      | 333333 |
	+---------------------------------+-----------+-------------+--------+
	| Professor Marcos Vinícius Costa | Docente   | 444444      | 444444 |
	+---------------------------------+-----------+-------------+--------+
	| Professora Isabela Souza        | Docente   | 555555      | 555555 |
	+---------------------------------+-----------+-------------+--------+
	| Professor Rafael Pereira        | Docente   | 666666      | 666666 |
	+---------------------------------+-----------+-------------+--------+
	| Residente Juliana Ramos         | Residente | 777777      | 777777 |
	+---------------------------------+-----------+-------------+--------+
	| Residente André Luiz Ferreira   | Residente | 888888      | 888888 |
	+---------------------------------+-----------+-------------+--------+
	| Residente Camila Alves          | Residente | 999999      | 999999 |
	+---------------------------------+-----------+-------------+--------+
 
</p>

5) Há 10 (dez) clientes cadastrados no banco de dados, os mesmos podem ser utilizandos para geração de 
   exames e laudos de exames.
	> Os clientes cadastrados possuem e-mails fictícios, portanto lembre-se de habilitar a opção de
	  envio de e-mails para o e-mail de teste ou desabilitar o envio de e-mails, conforme o passo 2.
   
   Tabela dos clientes e seus respectivos CPFs:

<p>
 
	+-------------+------------------+
	|     CPF     |       Nome       |
	+-------------+------------------+
	| 12345678901 | Ana Silva        |
	+-------------+------------------+
	| 23456789012 | João Santos      |
	+-------------+------------------+
	| 34567890123 | Maria Oliveira   |
	+-------------+------------------+
	| 45678901234 | Pedro Costa      |
	+-------------+------------------+
	| 56789012345 | Laura Souza      |
	+-------------+------------------+
	| 67890123456 | Marcelo Pereira  |
	+-------------+------------------+
	| 78901234567 | Camila Ramos     |
	+-------------+------------------+
	| 89012345678 | Rodrigo Ferreira |
	+-------------+------------------+
	| 90123456789 | Paula Alves      |
	+-------------+------------------+
	| 01234567890 | Lucas Vieira     |
	+-------------+------------------+

 </p>
 
 <hr>
