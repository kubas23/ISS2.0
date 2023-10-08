# ISS2.0

Project Description:

Projekt by měl vytvořit aplikaci, která bude zobrazovat informace o pohybu Mezinárodní vesmírné stanice (ISS).

Hlavní funkce systému
1 Stažení provozních dat ISS z příslušné webové služby +
2 Zpracování stažených dat na straně aplikace
3 Stažená data by měla být uložena do databáze
4 Technologie
5 Konzolová aplikace
6 DBC nebo Hibernate
7 Doménová logika rozdělená do vrstev, např. DAO, Service
8 Nástroje pro testování jednotek

Funkce:
Uživatelské rozhraní

Jako součást konzolového/grafického zobrazení by měl mít uživatel možnost vybrat jednu z následujících možností:
1 Výpočet rychlosti ISS
Uživatel by měl být informován o rychlosti ISS na základě dvou odečtů pomocí 
API http://open-notify.org/Open-Notify-API/ISS-Location-Now/ . Vypočtená rychlost by měla být uložena do databáze.
2 vrácení seznamu nadcházejících běhů ISS pro konkrétní místo
Získejte seznam nadcházejících běhů ISS pro konkrétní místo
Výběrem této možnosti by uživatel měl mít možnost vidět aktuální seznam nadcházejících běhů ISS pro konkrétní místo
pomocí API http://open-notify.org/Open-Notify-API/ISS-Pass-Times/ . Data by měla být zapsána do databáze. Zadané místo 
by mělo být ověřeno v souladu s pokyny popsanými v dokumentaci.
3 vrací počet lidí ve vesmíru v rámci ISS
Uživatel, který zvolí tuto možnost, by měl mít možnost vidět aktuální seznam lidí ve vesmíru v rámci vesmírné mise pomocí 
API http://open-notify.org/Open-Notify-API/People-In-Space/ . Data by měla být zapsána do databáze.
4 Integrace API
5 Funkčnost aplikace by měla být založena na API: http://open-notify.org/Open-Notify-API

Pokud nechcete používat protokol HTTP, vygenerujte data a uložte je do externích souborů (použijte formát CSV nebo JSON).
Příklad dat (ve formátu JSON) naleznete zde: http://api.open-notify.org/iss-now.json.

Vizualizace
V rámci grafického rozhraní implementujte mechanismus, který zobrazuje aktuální polohu ISS na mapě světa.

Zápis/čtení dat
Uživatel by měl mít možnost uložit aktuálně shromážděná data do souboru v libovolném formátu a poté je obnovit uložením přímo 
do databáze.

Jednotkové testy
Implementované funkcionality by měly být pokryty jednotkovými testy v souladu s běžně používanými metodikami a postupy.

POZNÁMKA: Vezměte prosím na vědomí, že s největší pravděpodobností nebudete moci psát testy pro všechny třídy, protože témata 
související s posměchem ještě nebyla probrána.

Volitelně:
1 Http klient, např. HttpClient, OkHttp
2 Prezentační vrstva aplikace založená na vzoru, např. MVC/MVP
3 Nástroj pro serializaci/deserializaci dat JSON, např. Gson, Jackson
4 rámec pro vkládání závislostí, jako je Guice
5 Frontend založený na JavaFX

Volitelné funkce
Statistická data
Uživatel by měl být schopen vypočítat např.
průměrná rychlost za určité časové období, např. měsíc, rok. Tyto výpočty by měly vycházet z aktuálních záznamů z databáze 
kolikrát byla ISS nad daným místem v časovém intervalu
počet lidí na vesmírné misi ISS

Další požadavky
Program by měl splňovat následující kritéria:
1 funkčnost
2 kvalita kódu (transparentnost, udržovatelnost, struktura)
3 používání nejnovějších technologií (alespoň nejnovější stabilní verze JDK, nejnovější stabilní verze knihoven)
4 uživatelská zkušenost
5 spolehlivost
6 Úkol je záměrně formulován velmi obecně. Pokud něco není specifikováno, lze to realizovat způsobem, který je pro studenta 
pohodlný. Neexistují žádná preferovaná řešení nebo technologie kromě těch, které jsou uvedeny.
