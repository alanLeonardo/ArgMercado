Feature: Usuario comprador de argMercado
  Scenario: Se desea que un usuario se pueda registrar en el sistema
    Given un usuario
    When  se registra
    Then  se verifica que se alla registrado correctamente