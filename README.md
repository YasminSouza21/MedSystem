# MedSystem
**Um sistema de gestão de consulta médica** feito com **Java** e **Spring**

## Descrição
Essa aplicação visa em um sistema para auxiliar uma clínica médica onde tem como objetivo gerenciar consultas, cadastro de pacientes e médicos,
então é uma aplicação que contém uma api rest que vai receber requisições https para os endpoints específicados no sistema para o gerenciamento dos dados da clínica. 


## Funcionalidades do Sistema
- Cadastrar dados do paciente e do médico
- Atualizar dados do paciente e do médico
- Deletar dados do paciente e do médico
- Listar pacientes e médicos cadastrados
- Listar médicos ativos, com a especialidade específicada e no horário que o paciente deseja marcar as consultas 
- Agendar consultas
- Atualizar data da consultas
- Cancelar consultas
- Enviar email ao paciente para confirmação da consulta marcada ou deletada
- Enviar email ao médico para avisar que uma consulta foi marcada ou deletada

## Tecnologias usadas 
- Java 17
- Spring Boot
  - Spring MVC
  - Spring Data JPA
  - JavaMailSender (envio de e-mails)
  - Rest Template (integrações com APIs externas)
- MySQL
- Maven (gerenciador de depêndencia)
- Lombok (anotações para evitar boilerplate)
- Flyway Migration (controle de versionamento de banco de dados)
