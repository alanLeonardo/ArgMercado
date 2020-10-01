Feature: Usuario comprador de argMercado
  Scenario: Se desea que un usuario se pueda iniciar seccion en argMercado
    Given un usuario y el sistema
    When  inicia seccion
    Then  se verifica que se pueda iniciar seccion correctamente