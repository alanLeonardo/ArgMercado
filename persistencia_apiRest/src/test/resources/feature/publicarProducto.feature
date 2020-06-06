Feature: Vendedor de argMercado

   Scenario: Se desea publicar un producto a vender
     Given un producto
     When se publica
     Then se verifica que este publicado
