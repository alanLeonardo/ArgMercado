Feature: Vendedor de argMercado

  Scenario: Se desea publicar 3 un producto a vender de forma consecutiva
    Given 3 producto
    When se publican
    Then se verifica que este publicados
