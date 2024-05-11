# language: pt
Funcionalidade: Produto

  @smoke @average
  Cenário: Registrar produto
    Quando eu estou registrando um novo produto
    Então o produto é criado com sucesso
    E deve ser apresentado

  @smoke @high
  Cenário: Buscar produto
    Dado que um produto já foi criado
    Quando efetuar a busca do produto
    Então o produto é exibido com sucesso

  @average
  Cenário: Alterar produto
    Dado que um produto já foi criado
    Quando efetuar requisição para alterar produto
    Então o produto é atualizado com sucesso
    E deve ser apresentado

  @average
  Cenário: Remover produto
    Dado que um produto já foi criado
    Quando requisitar a remoção do produto
    Então o produto é removido com sucesso