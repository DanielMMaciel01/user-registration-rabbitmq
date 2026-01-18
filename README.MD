User Registration with RabbitMQ

Projeto de estudo para demonstrar comunicação assíncrona entre microserviços utilizando Spring Boot e RabbitMQ.

O fluxo simula o cadastro de usuários e o envio de um evento para uma fila, onde um consumer processa a mensagem (simulando o envio de um e-mail de boas-vindas).

Tecnologias Utilizadas:

- Java 21
- Spring Boot 
- Spring AMQP (RabbitMQ)
- RabbitMQ 
- Jackson (JSON)
- Lombok 
- Maven

Arquitetura do Projeto

O projeto segue um modelo Producer / Consumer com comunicação assíncrona:

1 - O usuário é cadastrado via API REST

2 - Um evento User é publicado em uma Exchange

3 - A mensagem é roteada para uma Queue

4 - Um Consumer escuta a fila e processa a mensagem

5 - Em caso de falha, a mensagem é redirecionada para uma Dead Letter Queue (DLQ)

[Controller] → [Service] → [Exchange] → [Queue] → [Consumer]
↳ DLQ (em caso de erro)

RabbitMQ – Configuração

Exchange : user.exchange

Queue: user.created.queue

Routing Key

user.created

Dead Letter

Dead Letter Exchange: user.dlq.exchange

Dead Letter Queue: user.created.dlq

A aplicação também conta com:

- Retry automático no consumo

- Redirecionamento para DLQ após falhas

🔌 Endpoints Disponíveis
Criar usuário

- POST /users

{
"name": "Daniel",
"email": "daniel@email.com"
}

Listar usuários:

- GET /users

⚙️ application.yml (Resumo)

Configuração de conexão com RabbitMQ

Retry automático no consumer

Evita reprocessamento infinito de mensagens com erro


Comportamento do Consumer:

- O consumer escuta a fila user.created.queue e simula o envio de um e-mail de boas-vindas ao usuário cadastrado.

Em caso de erro durante o processamento:

- A mensagem é tentada novamente

- Após exceder o número de tentativas, é enviada para a DLQ

Objetivo do Projeto

- Praticar comunicação assíncrona

- Entender o funcionamento do RabbitMQ

- Trabalhar com filas, exchanges, routing keys e DLQ

- Simular um cenário real de microserviços