Feature: Tenda

  That is what happens in a shop.

  Scenario: venda d'un producte amb canvi just
    Given la caixa registradora 1 amb 0 euros al calaix
    And el producte amb nom gerro amb preu 10 euros
    When s'obre un tiquet caixa registradora 1
    And es registre el producte amb nom gerro a la caixa registradora 1
    And es tanca el tiquet
    Then el tiquet te un valor de 10 euros
    # Then hi ha 10 euros al calaix de la caixa registradora 1
