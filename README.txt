***************************** SISTEMA CRUD - TELECON ***************************** 

Sistema foi projetado em Java e Banco de dados MySQL com controle do XAMPP

Modelado em um Padrão de Arquitetura MVC (Model-View-Controller), facilitando a manutenção, visualização e otimização de códigos.

IDE utilizada - NetBeans IDE 8.2 (Build 201609300101).

O projeto tem 7 Pacotes, sendo eles:
  * BD - Aonde foi armazenado o arquivo .RAR com o banco de dados;
  * Classes - Aonde foram usadas 2 para fazer alterações em campos que o usuário só pode digitar letras Maiúsculas ou Números;
  * Controller - Aonde ficam as classes de controle entre a View e Model;
  * Model - Que são as classes de conexão com o Banco de Dados e todas suas interações;
  * Tabelas - Aonde foram implementadas tabelas do tipo AbstracttableModel aonde a classe faz toda as interações com as tabelas dos pacotes da View;
  * View - Aonde fica a classe de visualização que o cliente interage, é nela que ficam toda a interação do usuário com o cliente;
  * Icons - Neste pacote ficam todos os ícones utilizados no projeto.

# Em sua tela principal tem um Painel de Guias com 2 Guias distintas, uma para cadastro de clientes e a outra de relatório para pesquisa avançada de clientes conforme a necessidade do usuário.

# A mesma tem botões principais aonde o usuário pode fazer um CRUD completo dos dados.

# Na Guia de relatório tem a pesquisa avançada de ações e também uma opção de gerar PDF dos dados que são impressos na sua tabela.


###############################################
** EXECUÇÃO DO PROJETO **

# No seu "DISCO C", crie uma pasta chamada de "Relatorios", aonde os arquivos de PDF serão salvos para eventuais consultas.

# Na pasta do Projeto tem o arquivo do tipo sql que pode ser introduzido ao XAMPP com o mesmo nome de destino (telecon.sql), havendo todas as informações solicitadas pelo Cliente ("Telecon").

# Na pasta também tem o arquivo de extensão .MSI aonde pode ser executado, em seguida pedirá autorização do Cliente, após a autorização o mesmo será instalado na Programs Files (Arquivos de Programas), sendo necessário somente executar o arquivo "CRUD Clientes.exe" ícone do Java.

# Ao ser aberto o sistema, a tela principal de cadastro foi desenvolvida para atender aos requisitos de software que o cliente solicitou.
  * Busca de códigos automática do cadastro de cliente com o botão "BUSCAR";
  * Inserção de diversos telefones em uma tabela própria, podendo ser alteradas ou excluídas ao ser selecionada a linha na tabela;
  * Busca de dados de cliente através de Código ou Nome;
  * Alteração de dados do cliente com o botão de "ALTERAR";
  * Exclusão de dados do cliente com o botão de "EXCLUIR";
  * Cancelar ações do usuário para limpar a tela com o botão "CANCELAR";
  * Sair do sistema de forma fácil com o botão "SAIR"

# Na Guia de Relatórios, temos 4 tipos de ações que o cliente pode escolher entre uma delas, Busca por "Código", Busca por "Nome" com auto complemento de nome, busca por "Cidade" que tem busca personalizada de palavras contendo letras EX(digitado: "por" ele vai buscar todas as cidade que tem POR entre seu campo) e também uma Busca de "Todos" aonde vem todas as informações de todos os clientes, após a seleção e a inserção de dados é só clicar no botão da lupa para pesquisar.
  * Após ter sido feita a busca dos dados e quiser gerar o PDF, basta clicar no botão a baixo da pesquisa que o seu PDF será salvo na pasta Relatórios do disco C e será aberta automaticamente em sua tela, podendo visualizar todos os dados dos clientes.

# Sistema foi projetado para atender as exigências do CLiente e cada ação tem suas respectivas informações deixando o cliente sempre a par do que está acontecendo no sistema e suas limitações.

# Foi utilizado Máscara de campo para facilitar a visualização ou inserção dos dados nos campos utilizado.

#########################################

# Demais complementos do projeto
  * https://github.com/emersoneton/CRUD-de-Clientes-JAVA
    - Commits estão no Branches "Master" e o arquivo README principal no "Main"
  * Senha da exportação do sql padrão "a2m8x7h5"
  * Senhas do SLQ - usuário=ROOT e senha="".



