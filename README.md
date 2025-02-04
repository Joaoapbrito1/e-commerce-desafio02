# E-Commerce API

## Visão Geral
Este projeto é uma API REST para um sistema de e-commerce, que permite a gestão de clientes, produtos e compras. A API é construída utilizando Spring Boot e segue boas práticas de desenvolvimento.

## Funcionalidades Implementadas

### Clientes
- Cadastro de clientes com validação de CPF e e-mail.
- Consulta de clientes pelo CPF.
- Listagem de todos os clientes.
- Atualização de informações do cliente.

### Produtos
- Cadastro de produtos com nome único e preço positivo.
- Listagem de todos os produtos disponíveis.
- Exclusão de produtos cadastrados.

### Compras
- Realização de compras a partir de um ID de produto válido.
- Validação de estoque antes da compra.

## Tecnologias Utilizadas
- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA** (para persistência de dados)
- **Hibernate Validator** (para validação de dados de entrada)
- **Banco de Dados H2** (pode ser substituído por outro, como PostgreSQL ou MySQL)

## Tratamento de Exceções
- **ClientNotFoundException**: Lançada quando um cliente não é encontrado pelo CPF.
- **DuplicateClientException**: Lançada ao tentar cadastrar um cliente com CPF já existente.
- **ProductNotFoundException**: Lançada quando um produto não é encontrado pelo ID.
- **DuplicateProductException**: Lançada ao tentar cadastrar um produto com nome já existente.
- **InsufficientStockException**: Lançada quando não há estoque suficiente para realizar uma compra.
- **InvalidPurchaseException**: Lançada quando há problemas com a requisição de compra.
- **PurchaseNotFoundException**: Lançada quando a compra não encontra um produto correspondente.
- **GlobalExceptionHandler**: Gerencia exceções globais, retornando mensagens apropriadas para o cliente.

## Estrutura do Projeto
O projeto segue a seguinte organização:
- `models/` - Contém as entidades do sistema (`Client` e `Product`).
- `repositories/` - Interfaces que gerenciam a persistência de dados.
- `services/` - Contém a lógica de negócios e regras de validação.
- `controllers/` - Responsável por expor endpoints da API.
- `dtos/` - Objetos de transferência de dados (Request e Response DTOs).
- `exceptions/` - Tratamento de exceções personalizadas.

## Como Executar o Projeto
1. Clone o repositório.
2. Certifique-se de ter o **JDK 17** instalado.
3. Configure o banco de dados no `application.properties` (ou use H2 como está configurado por padrão).
4. Execute o projeto com:
   ```sh
   mvn spring-boot:run
   ```
5. Acesse a API através do endereço padrão: `http://localhost:8080`

## Endpoints Principais

### Clientes
- `POST /client` - Cadastra um novo cliente.
- `GET /client` - Retorna todos os clientes cadastrados.
- `GET /client/{cpf}` - Busca um cliente pelo CPF.
- `PUT /client/{cpf}` - Atualiza as informações de um cliente.

### Produtos
- `POST /product` - Cadastra um novo produto.
- `GET /product` - Retorna todos os produtos cadastrados.
- `DELETE /product/{id}` - Remove um produto pelo ID.

### Compras
- `POST /purchases/{productId}` - Realiza uma compra de um produto pelo ID.

## Contribuição
Contribuições são bem-vindas! Para contribuir, siga os passos:
1. Faça um fork do repositório.
2. Crie uma nova branch (`feature/nova-funcionalidade`).
3. Faça suas alterações e envie um pull request.

## Licença
Este projeto é distribuído sob a licença MIT.
