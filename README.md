# SimarroPop - Projecte Integrador 2024/2025

**Versió 1:** 03/02/2025

---

## Índex

1. [Introducció](#introducció)
2. [Funcionament de l'Aplicació](#funcionament-de-laplicació)
3. [Aplicacions Implementades](#aplicacions-implementades)
   - [Aplicació ERP (SGE)](#aplicació-erp-sge)
   - [BackEnd (Accés a Dades)](#backend-accés-a-dades)
   - [Aplicació d'Android (PMM)](#aplicació-dandroid-pmm)
   - [Aplicació d'Escriptori (DI)](#aplicació-descriptori-di)
   - [Servici (PSP)](#servici-psp)
   - [Empresa i Iniciativa Emprenedora (EIE)](#empresa-i-iniciativa-emprenedora-eie)
   - [Anglès](#anglès)
4. [Disseny de la Base de Dades](#disseny-de-la-base-de-dades)
5. [Equips](#equips)
6. [Exposició](#exposició)
7. [Avaluació](#avaluació)
   - [Exemple d’Avaluació](#exemple-davaluació-del-projecte)

---

## Introducció

**SimarroPop** és una aplicació destinada a posar en contacte particulars per a comprar i vendre productes de segona mà. L'objectiu és fomentar la reutilització d'articles ja no utilitzats, facilitant la interacció entre venedors i compradors.

---

## Funcionament de l'Aplicació

L'aplicació permet als usuaris:
- **Visualitzar i filtrar articles:** Cerca per categoria, etiqueta, nom, preu o distància.
- **Registrar-se:** Per publicar articles o enviar comentaris.
- **Publicar productes:** Amb informació com l'usuari que publica, categoria (definida prèviament), nom, descripció, antiguitat, etiquetes, imatges, preu i ubicació (mostrada en un mapa).
- **Gestionar favorits:** Els usuaris poden guardar localment els seus productes favorits.
- **Notificacions:** En enviar comentaris o valorar venedors/compradors, s'envien notificacions (email, SMS, etc.).
- **Accions d'administració:** Els administradors poden eliminar articles o usuaris considerats inadequats.

---

## Aplicacions Implementades

El sistema centralitza la informació en una base de dades accessible per les diferents aplicacions a través d’API REST (propia) o XML-RPC d’Odoo, amb accés directe en alguns casos.

### Aplicació ERP (SGE)
- **Plataforma:** Odoo amb un mòdul personalitzat.
- **Funcionalitats:** 
  - Gestió d’empleats amb permisos (lectura, escriptura, creació i eliminació).
  - Administració de taules mestres, creació de menús, vistes i formularis.
  - Control del servei premium (publicitat i anuncis promocionals).

### BackEnd (Accés a Dades)
- **Tecnologia:** API REST desenvolupada amb Spring Boot.
- **Característiques:**
  - Gestió de les taules de la BD amb MySQL o PostgreSQL i Hibernate (JPA).
  - Implementació de la lògica de negoci, endpoints generals i específics.
  - Gestió de codis de resposta, excepcions personalitzades i validacions.
  - Documentació amb Swagger (OpenAPI) i desplegament a Microsoft Azure (PaaS).

### Aplicació d'Android (PMM)
- **Objectiu:** Aplicació mòbil per a usuaris finals.
- **Detalls tècnics:**
  - Disseny amb Material Design i creació de mokups (Figma, Balsamiq, etc.).
  - Separació de lògica, interfície i accés a dades.
  - Persistència offline amb Room, càrrega d’imatges amb Glide i accés a l’API amb Retrofit.
  - Funcionalitats addicionals: cercador avançat, menú lateral (Navigation Drawer) i geolocalització amb Google Maps.

### Aplicació d'Escriptori (DI)
- **Usuaris:** Empleats i opcionalment usuaris finals.
- **Funcions:**
  - Gestió de categories, etiquetes, usuaris i moderació de comentaris.
  - Visualització de dades en taules (QTableView), diàlegs i estructuració amb QMainWindow i QTreeView.
  - Accés a la informació a través de la BD o de l'API REST.

### Servici (PSP)
- **Configuració:** Proxy invers amb Nginx per a gestionar el tràfic i millorar la seguretat.
- **Aplicació Java:**
  - Realitza còpies de seguretat de la BD mitjançant SSH.
  - Processament en segon pla amb notificació per correu electrònic.
  - Gestió de redireccions, HTTPS amb certificats SSL i registre d’accions (logs).

### Empresa i Iniciativa Emprenedora (EIE)
- **Anàlisi de negoci:** Elaboració del Business Model Canvas.
- **Aspectes clau:**
  - Identificació d’activitats, proposta de valor, socis, estratègies de màrqueting, relació amb els clients i finançament.
  - Reflexió sobre la viabilitat de l’aplicació.

### Anglès
- **Vídeo promocional:**
  - Creació d’un vídeo explicatiu per atraure inversors o espònsors.
  - Contingut: presentació de l’empresa, problema a resoldre, solució tècnica, beneficis i implicació de l’inversor.

---

## Disseny de la Base de Dades

- **Estructura:** Disseny centralitzat amb relacions entre articles i categories.
- **Implementació:** Possibilitat d’implementar la categoria com a atribut o com a taula independent, segons la complexitat requerida.

---

## Equips

- **Composició:** Equips de 3-4 membres, assignats per l’equip docent segons el recorregut del curs.
- **Organització:**
  - Cada equip designa un cap de projecte i assigna rols específics.
  - Contractació interna per comprometre cada membre amb els requisits.
  - Ús d’eines professionals (GitHub per al control de versions i Trello per a la planificació).

---

## Exposició

- **Presentació final:**
  - Exposició del treball realitzat amb demostracions (captures, vídeos, anotacions).
  - Enviament de la memòria del projecte i presentació abans del 20 de febrer a les 21:00h.
  - Exposicions finals el 21 de febrer amb sessions de 25 minuts per equip.
  - Defensa col·lectiva i individual davant el tribunal docent.

---

## Avaluació

- **Components de la nota final:**
  - **20% Nota Global:** Avaluació del resultat final, funcionament, exposició, presentació i documentació.
  - **80% Nota Específica per Mòdul:** Valoració de cada professor segons el seu mòdul, amb mitjana ponderada segons les hores dedicades.
- **Exemple d’avaluació:** Es pot consultar la documentació addicional per a un exemple concret d’avaluació del projecte.

---

Aquest README.md resumeix els punts clau del projecte integrador de SimarroPop, dissenyat per a la compravenda d’articles de segona mà i desenvolupat com a part del curs de Desenvolupament d’Aplicacions Multiplataforma del curs 2024/2025.
