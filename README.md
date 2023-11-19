# Document Management Application

## Design patterns implemented:

## Composite
	- Creat interfata "DocumentComponent" care defineste functionalitatea unei componente de tip Composite;
    - Creat clasa "Document" care este o componenta de tip "frunza"
    - Creat clasa "DocumentFolder" care are in componenta mai multe documente, adica o componenta compozita.
### TODO:
    - Este dependenta de implementarea AbstractFactory, mai multe update-uri dupa AbstractFactory
    - Creat mai multe componente compozite;
    - Adaugat functionalitate interfetei DocumentComponent.
		
## Abstract Factory
### TODO:
  	- Structura care creaza obiecte abstracte
## Decorator
### TODO:
    - Stategia pentru stratificarea nivelurilor de securitatea a documentului, comportamentul fiecarui layer de securitate
      + de implementat
## Proxy 
	- Am implementat metodele grantAccess si revokeAccess astfel incat, user-ul admin are dreptul de a da sau revoca accesul unui simplu user la un document anume
	- Cele doua metode mentionate mai sus se bazareaza pe un enum numit ROLE. Astfel, un user poate fi admin sau un simplu user.
	- Am implementat o parte din metoda signDocument care se foloseste de KeyPair pentru a semna un document. Aceasta semantura se bazeaza pe o cheie privata si una publica.
## Builder
	- Implementat clasele UserBuilder si DocumentBuilder pentru trecerea dintre entitate si DTO
### TODO:
    - Creat alte clase Builder pentru alte entitati create ulterior.
## Unit of Work
### TODO: 
  	- Fiecare entitate va avea un repository propriu. Unit of Work va avea rolul de a accesa aceste repository-uri

## Technology Stack
* Java 17
* Spring Boot 3.0.2
* Maven
* PostgreSQL
* GitHub