Feature: Usuario comprador de argMercado

  Scenario: Se desea ordenar los producto
    Given productos
    When se ordenan los productos por mayor precio
    Then se verifica que los producto este ordenado de mayor a menor