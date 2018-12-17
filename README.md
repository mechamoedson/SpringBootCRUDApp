# SpringBootCRUDApp
CRUD app usando Spring Boot, AngularJS, Spring Data, JPA/Hibernate e MySQL


## Rodando a aplicação
Rodar com ‘local’ [H2]. 
Rodar com ‘prod’ [MySQL].

## Eclipse:: 
Executando diretamente o perfil padrão será usado. Caso você queira que um perfil diferente seja usado, crie uma configuração especificando o perfil. Para fazer isso na barra de ferramentas, selecione Run-> Run Configurations-> Arguments-> VM Arguments. Adicione -Dspring.profiles.active = local ou -Dspring.profiles.active = prod

## Command line::
Na raiz do projeto
$> java -jar target/SpringBootCRUDApp-1.0.0.jar –spring.profiles.active=local



# Sobre o projeto


### PREMISSA

Desenvolver camada visual com AngularJS e um serviço para tratar das
regras de negócio

- Linguagem: Java
- Front-end: AngularJS
- Injeção de dependência: Spring
- Informações devem ser persistidas em um banco de dados. 


#### FLUXO DE TELA

Criar um formulário com os seguintes campos:

- Nome Cliente
Tipo: texto
- Limite de credito
Tipo: numérico (R$)
- Risco
Tipo: lista
Valores possíveis: A, B e C 

### REGRAS DE NEGÓCIO

- Todas informações devem ser persistidas mais um campo indicando a
taxa de juros.
- Todos campos são obrigatórios
- Se o risco for do tipo A manter os dados informados
- Se o risco for do tipo B, a taxa de juros deve ser de 10%
- Se o risco for do tipo C, a taxa de juros deve ser de 20% 
