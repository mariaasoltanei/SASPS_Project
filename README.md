## Google Docs documentation: https://docs.google.com/document/d/1CWucB3eWkTF0hVO5bljsiGx-juQIn97p7Ti6rnpytFI/edit?usp=sharing
# Document Management Application

### Table of Contents
- [Introducere](#introducere)
- [Metodologie](#metodologie)
- [Analiza modelelor de proiectare](#analiza-modelelor-de-proiectare)
    - [Creational](#creational)
        - [Factory](#factory)
    - [Structural](#structural)
        - [Composite](#composite)
        - [Proxy](#proxy)
        - [Adapter](#adapter)
    - [Arhitectural](#arhitectural)
        - [Layers](#layers)
- [Eficienta si Optimizarea](#eficienta-si-optimizarea)
- [Studiu comparativ](#studiu-comparativ)
- [Impactul Anti-pattern-urilor](#impactul-anti-pattern-urilor)
- [Concluzii](#concluzii)
- [Bibliografie](#bibliografie)
- [Technology Stack](#technology-stack)
# Introducere
_________________________________________________________
Proiectul curent isi propune sa ofere un studiu comparativ privind eficienta, optimizarea si impactul crearii unei aplicatii de management al documentelor folosind diferite design patternuri in tehnologia Java, folosind framework-ul Spring Boot.

Aplicatia curenta este o aplicatie de management al documentelor, care permite utilizatorilor sa creeze, sa editeze si sa stearga documente. De asemenea, aplicatia permite utilizatorilor creeze un cont, sa creeze diferite documente in diferite formate (fara continut) si sa le salveze in folderul lor personal. De asemenea, aplicatia permite utilizatorilor sa isi schimbe parola, sa isi stearga contul, sa isi schimbe numele si sa isi schimbe email-ul.

Proiectul in sine isi propune sa capteze diferenta dintre o aplicatie facuta folosind aceste design pattern-uri si aceeasi aplicatie (sau aceeasi functionalitate) fara a folosi aceste design pattern-uri, poate chiar cu anti-pattern-uri specifice design patter-urilor alese. 
Astfel, se va putea observa diferenta dintre cele doua abordari si se va putea masura eficienta si optimizarea adusa de aceste design pattern-uri.

# Metodologie
_________________________________________________________
Pentru a putea realiza acest proiect, am ales sa folosesc framework-ul Spring Boot, deoarece acesta ofera o arhitectura stratificata, care este impartita in 3 layere: Controller, Service si Repository. Acestea sunt folosite pentru a separa logica aplicatiei in functie de responsabilitatea fiecarei clase.

De asemenea, am ales sa folosesc baza de date PostgreSQL, deoarece aceasta este o baza de date relațională, care este foarte usor de folosit si care ofera o performanta foarte buna.

Pentru a putea masura eficienta si optimizarea adusa de aceste design pattern-uri, am ales sa folosesc aplicatia Postman, care ofera o serie de metrici care pot fi folosite pentru a masura performanta aplicatiei. Aceste metrici sunt:
* Timpul de executie al unei cereri
* Spatiul de memorie folosit de o cerere
# Analiza modelelor de proiectare
_________________________________________________________
## Creational
### Factory
Factory dispune de o clasa numita `DocumentFactory`, care are o metoda numita createDocument, care primeste ca parametru un String si returneaza un obiect de tip Document sau DocumentFolder, in functie de String-ul primit ca parametru. Astfel, se poate crea un document simplu sau un folder de documente.
    
    - Creat clasa abstracta "DocumentFactory" care defineste metoda "createDocument"
    - Creat clasa "DocumentFactoryImpl" care implementeaza metoda "createDocument" pentru a crea un document de tip "Document"
    - Creat clasa "DocumentFactoryImpl2" care implementeaza metoda "createDocument" pentru a crea un document de tip "DocumentFolder"
    - Creat clasa "DocumentFactoryProducer" care returneaza o instanta a unei clase care implementeaza interfata "DocumentFactory" in functie de tipul documentului dorit.

## Structural
### Composite
Composite dispune de o interfata care defineste actiunile posibile tuturor documentelor definite. Aceasta interfata este mai apoi implementata de clasa `Document`, care este o componenta de tip frunza. De asemenea, interfata este implementata si de clasa `DocumentFolder`, care este o componenta de tip compozit. Astfel, `DocumentFolder` poate contine mai multe documente, iar `Document` este un document simplu, care nu contine alte documente.

	- Creat interfata "DocumentComponent" care defineste functionalitatea unei componente de tip Composite;
    - Creat clasa "Document" care este o componenta de tip "frunza"
    - Creat clasa "DocumentFolder" care are in componenta mai multe documente, adica o componenta compozita.


### Proxy
Proxy dispune de o clasa numita `DocumentProxy` care implementeaza interfata `DocumentService` care defineste functionalitatea aplicatiei in aria management-ului de documente.

    - Implementat JWT pentru autentificare
    - Implementat JwtUserDetailsService pentru a verifica daca un utilizator este autentificat si pentru a-i returna datele
    - Implementat metodele din interfata "DocumentService" pentru a gestiona documentele in urma autentificarii unui utilizator si a verificarii rolului acestuia
    
### Adapter
Adapter dispune de clase de tip Adapter numite `DocumentAdapter` si `PersonAdapter`. Acestea au rolul de a crea documente de tip `DocumentDTO` din obiecte de tip `Document` precum si invers. Acelasi comportament se aplica si pentru clasa `PersonAdapter`.

	- Implementat clasele PersonAdapter si DocumentAdapter pentru trecerea dintre entitate si DTO
    - Am implementat metodele "toDocumentDTO()" si "toDocument() pentru a crea un obiect de tip "Document" dintr-un obiect de tip "DocumentDTO" si invers.
    - Am implementat metodele "toPersonDTO()" si "toPerson()" pentru a crea un obiect de tip "Person" dintr-un obiect de tip "PersonDTO" si invers.
## Arhitectural
### Layers
Layers dispune de o arhitectura stratificata, care este impartita in 3 layere: Controller, Service si Repository. Acestea sunt folosite pentru a separa logica aplicatiei in functie de responsabilitatea fiecarei clase.

    - Creat clasa "DocumentController" care este responsabila de comunicarea cu clientul
    - Creat clasa "DocumentService" care este responsabila de logica aplicatiei
    - Creat clasa "DocumentRepository" care este responsabila de comunicarea cu baza de date
    - Creat clasa "PersonController" care este responsabila de comunicarea cu clientul
    - Creat clasa "PersonService" care este responsabila de logica aplicatiei
    - Creat clasa "PersonRepository" care este responsabila de comunicarea cu baza de date
    - Creat clasa "Document" care este o entitate din baza de date
    - Creat clasa "DocumentDTO" care este un obiect de transfer de date
    - Creat clasa "Person" care este o entitate din baza de date
    - Creat clasa "PersonDTO" care este un obiect de transfer de date

# Eficienta si Optimizarea
# Studiu comparativ
# Impactul Anti-pattern-urilor
# Concluzii
# Bibliografie
* [1] https://www.baeldung.com/java-composite-pattern
* [2] https://www.baeldung.com/java-factory-pattern
* [3] https://www.baeldung.com/java-proxy-pattern
* [4] https://www.baeldung.com/java-adapter-pattern
* [5] https://www.baeldung.com/java-architecture
* [6] https://www.baeldung.com/java-anti-patterns
* [7] https://www.baeldung.com/java-optimization-techniques
* [8] https://www.baeldung.com/java-performance-metrics

## Technology Stack
* Java 17
* Spring Boot 3.0.2
* Maven
* PostgreSQL
* GitHub
