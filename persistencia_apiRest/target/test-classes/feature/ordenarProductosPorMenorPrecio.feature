Feature: Usuario comprador de argMercado

  Scenario: Se desea ordenar los producto
    Given productos
    When se ordenan los productos por menor precio
    Then se verifica que los producto este ordenado de menor a mayor