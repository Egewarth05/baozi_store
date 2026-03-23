# 🥟 Baozi Store — API REST

API REST para controle de clientes, produtos e pedidos da Baozi Store.

**Tecnologias:** Java 17 · Spring Boot 3.2 · Spring Data JPA · H2 (dev) / MySQL (prod)

---

## Como rodar

```bash
# Na pasta raiz do projeto
./mvnw spring-boot:run
```

A API sobe em: `http://localhost:8080`

Console H2: `http://localhost:8080/h2-console`  
(JDBC URL: `jdbc:h2:mem:baozidb` · usuário: `sa` · senha: em branco)

---

## Trocar para MySQL

1. No `pom.xml`: comente a dependência do H2 e descomente a do MySQL
2. No `application.properties`: comente as linhas do H2 e descomente as do MySQL, ajustando usuário/senha

---

## Endpoints

### CLIENTE — `/clientes`

| Método | Endpoint         | Descrição          |
|--------|------------------|--------------------|
| POST   | /clientes        | Criar cliente      |
| GET    | /clientes        | Listar todos       |
| GET    | /clientes/{id}   | Buscar por ID      |
| PUT    | /clientes/{id}   | Atualizar          |
| DELETE | /clientes/{id}   | Apagar             |

**Body POST/PUT:**
```json
{
  "nome": "Jean123456",
  "clienteDesde": "2024-01-15"
}
```

---

### PRODUTO — `/produtos`

| Método | Endpoint         | Descrição          |
|--------|------------------|--------------------|
| POST   | /produtos        | Criar produto      |
| GET    | /produtos        | Listar todos       |
| GET    | /produtos/{id}   | Buscar por ID      |
| PUT    | /produtos/{id}   | Atualizar          |
| DELETE | /produtos/{id}   | Apagar             |

**Body POST/PUT:**
```json
{
  "nome": "Baozi Tradicional",
  "preco": 8.50,
  "estoque": true
}
```

---

### PEDIDO — `/pedidos`

| Método | Endpoint         | Descrição          |
|--------|------------------|--------------------|
| POST   | /pedidos         | Criar pedido       |
| GET    | /pedidos         | Listar todos       |
| GET    | /pedidos/{id}    | Buscar por ID      |
| PUT    | /pedidos/{id}    | Atualizar          |
| DELETE | /pedidos/{id}    | Apagar             |

**Body POST/PUT:**
```json
{
  "clienteId": 1,
  "produtoId": 1,
  "quantidade": 5
}
```

---

## Arquitetura do Projeto

```
src/main/java/com/baozi/store/
├── BaoziStoreApplication.java   ← Classe principal
├── model/
│   ├── Cliente.java             ← Entidade JPA
│   ├── Produto.java             ← Entidade JPA
│   └── Pedido.java              ← Entidade JPA
├── repository/
│   ├── ClienteRepository.java   ← Spring Data JPA
│   ├── ProdutoRepository.java
│   └── PedidoRepository.java
└── controller/
    ├── ClienteController.java   ← Endpoints REST
    ├── ProdutoController.java
    └── PedidoController.java
```
