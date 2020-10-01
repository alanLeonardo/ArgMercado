Feature: Usuario comprador de argMercado

  Scenario: Se desea buscar un producto
    Given un producto y una lista de productos
    When busca un producto
    Then se verifica que la busqueda devuelva el producto o productos del tipo buscado