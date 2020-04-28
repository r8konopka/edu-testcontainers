### Jak to działa
Wraz z uruchamianiem kontenerów testowych jest uruchamiany kontener pomocniczy tzw: `Ryuk resource reaper` 
Uruchamiany jest z obrazu `quay.io/testcontainers/ryuk`, służy do czyszczenia i usuwania kontenerów po zakończonych testach.

##### Docker compose
Pomocniczo jest uruchamiany kontener, który pełni funkcję proxy pomiędzy testowymi kontenerami a portami dostępnymi z poziomu testów. 
Kontener jest budowany z obrazu `alpine/socat`.

Zalety:
- umożliwia testowanie interakcji z bazą danych (specyficzne cechy dla danej dystrybucji bazy danych).
- zapewnia jednakowy stan bazy za każdorazowym uruchomieniem


Wady
- długi czas uruchamiania kontenerów (możliwość optymalizacji - reużycie istniejących)


Reużywanie kontenerów
- pomaga zredukować czas potrzebny na wykonanie testów (tworzenie kontenerów)
- ma zastosowanie jeżeli testy nie ingerują w stan kontenera



