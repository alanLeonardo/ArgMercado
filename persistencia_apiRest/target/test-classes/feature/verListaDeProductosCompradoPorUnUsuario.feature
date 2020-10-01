Feature: Usuario comprador de argMercado
  Scenario: Se desea que un usuario puda ver la lista de productos que el compros
    Given un usuario
    When  intenta ver la lista de produtos comprados
    Then  se verifica que pueda ver la lista de productos comprados