# Estoque de roupas — CRUD com Spring Boot, JPA e MySQL

Atividade prática UMC. Tema escolhido: **Produto** (peças de uma loja de roupas), com os atributos
`nome`, `tamanho`, `preco` e `estoque`.

## Como rodar
1. Abra o XAMPP e inicie **Apache** e **MySQL**.
2. (Opcional) Se o seu MySQL tiver senha, defina antes de rodar:
   - Windows (PowerShell): `$env:DB_PASSWORD="sua_senha"`
   - Linux/macOS: `export DB_PASSWORD=sua_senha`
   O padrão do XAMPP (usuário `root`, senha vazia) funciona sem configurar nada.
3. Rode o projeto: `mvn spring-boot:run` (ou o botão Run da sua IDE na classe `EstoqueRoupasApplication`).
4. Acesse http://localhost:8080
5. Confira a tabela `produto` no banco `estoque_roupas` em http://localhost/phpmyadmin

## Estrutura
- `Produto` — entidade com Bean Validation
- `ProdutoRepository` — `JpaRepository` + query method `findByNomeContainingIgnoreCase`
- `ProdutoService` — regras de negócio (injeção pelo construtor)
- `ProdutoController` — listar, novo, salvar (redirect), editar e excluir
- `templates/lista.html` e `templates/form.html` — Thymeleaf
- `static/css/estilo.css` — visual
