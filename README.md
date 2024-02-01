# Portifólio Orange Juice Hackathon Edição 5

Este é um projeto API REST para gerenciar portifólios. Ele permite cadastrar, consultar, atualizar e excluir projetos.
O projeto foi construído utilizando a linguagem de programação Java e o framework Spring Boot, com o spring JPA para
interação com o banco de dados PostgreSQL além de utilizar o springDoc(Swagger) para documentação da API

## Características

- CRUD de user, projetos e tags
- Validação de dados de entrada do usuário (Login com geração de token JWT)
- Banco de dados PostgreSQL
- Documentação das apis (swagger)

## Tecnologias utilizadas

- JDK versão 17
- spring boot versão 3.2.2
  - spring data jpa versão 3.2.2
  - spring web versão 3.2.2
  - spring doc openapi ui versão 1.5.12
  - spring validation versão 3.2.2
  - spring devtools versão 3.2.2
  - spring postgresql versão 3.2.2
- maven versão 3.6.0

## Utilização

A API pode ser utilizada para realização de funcionalidades de CRUD (CREATE READ UPDATE DELETE) para os projetos
cadastrados pelos usuários com a possibilidade de realização de buscas de projetos por tags
(que são criadas a partir do cadastro do próprio projeto) e permite as seguintes operações:

- Registrar Usuário: `POST https://portifolio-deploy.onrender.com/user/register`
- Login de Usuário: `POST https://portifolio-deploy.onrender.com/user/login`
- Cadastrar Projeto: `POST https://portifolio-deploy.onrender.com/project`
- Atualizar Projeto: `PUT https://portifolio-deploy.onrender.com/project/{id}`
- Consultar Projetos de todos usuários: `GET https://portifolio-deploy.onrender.com/project/projects`
- Consultar Projeto por Id: `GET https://portifolio-deploy.onrender.com/project/{id}`
- Consultar Projeto por tag: `GET https://portifolio-deploy.onrender.com/project/tag?tags={listname}&allUsers={boolean}`
- Excluir Projeto: `DELETE https://portifolio-deploy.onrender.com/project/{id}`

A documentação da API está disponível em https://portifolio-deploy.onrender.com/swagger-ui/index.html