Feature: Usuario comprador de argMercado
  Scenario: Se desea comprar un producto
    Given un producto y un usuario
    When  se comprar
    Then  se verifica que el producto fue comprado