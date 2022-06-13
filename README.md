# CFMS
Correctional facility management system

## Team HS
- [Mehmedović Faris](https://github.com/fmehmedovi1 "Github") (fmehmedovi1)
- [Halilović Kemal](https://github.com/KHalilovic3 "Github") (KHalilovic3)
- [Memić Miralem](https://github.com/MiralemMemic "Github") (MiralemMemic)
- [Omić Kenan](https://github.com/komic1) (komic1)

## Opis teme
<div style="text-align: justify ">Kazneno-popravni zavod se može podijeliti na sljedeće odjele: osoblje i zatvorenici. Da bi objekat mogao da funkcioniše potrebno je osoblje. Organizacija osoblja uključuje komunikaciju između članova osoblja, pristup informacijama u skladu sa nivoom provjere, dokumentaciju svakog člana osoblja i upravljanje zavodom za osoblje višeg nivoa. Kao što je navedeno, članovi osoblja su podijeljeni prema ulozi i nivou pristupa, pri čemu osoblje nižeg nivoa, kao što su čuvari, nije u mogućnosti da stupi u interakciju sa aspektima višeg nivoa u zatvoru, kao što su upravljanje ili komunikacija sa drugim ustanovama. Zbog toga su, međutim, viši nivoi osoblja, kao što je upravnik, takođe odgovorni za veći dio efektivnog rada objekta. Takve odgovornosti uključuju upravljanje uslugama objekta i upravljanje krizama. Što se tiče zatvorenika, uzima se u obzir organizacija i dokumentacija. Sve dostupne informacije o licu koje je postalo zatvorenik moraju biti dostupne svim relevantnim članovima osoblja. Nakon što se zatvorenik dokumentuje, on se može smjestiti u ćeliju za zadržavanje, a podaci koji se odnose na njihovu kaznu moraju biti evidentirani.</div>

## Uputstva za pokretanje pojedinačnih mikroservisa preko Dockera
Prilikom pokretanja projekta preko Dockera neophodno je izvršiti sljedeće korake: 

- mvn clean install govori Mavenu da izvrši fazu čišćenja u svakom modulu prije nego što pokrene fazu instalacije za svaki modul. Ovo čini brisanje svih    kompajliranih datoteka koje imate, pazeći da zaista kompajlirate svaki modul od nule.
  ```
  mvn clean install
  ```

- Docker build komanda gradi Docker images iz Dockerfile-a i “context”. Context iz build-a je skup datoteka koje se nalaze u navedenom PATH ili URL-u. Proces build-a može se odnositi na bilo koju datoteku u context-u. Opća forma komande bi izgledala na sljedeći način: 
  ```
  docker build -f Dockerfile -t docker-<naziv mikroservisa> .
  ```
  Dok konkretan primjer vezan za projekat bi izgledao ovako: 
  ```
  docker build -f Dockerfile -t docker-discovery-service .
  ```
- Docker run naredba kreira kontejner od date Docker imager i pokreće kontejner pomoću date naredbe. Opća forma komande bi izgledala na sljedeći način: 
  ```
  docker run -p <port definisan u Dockerfile>:<port na kojem želimo da bude u browseru> docker-<naziv servisa>
  ```
  Dok konkretan primjer vezan za projekat bi izgledao ovako: 
  ```
  docker run -p 8761:8761 docker-discovery-service
  ```

## Uputstva za pokretanje projekta preko Dockera (docker compose) 
Docker compose je alat koji je razvijen da pomogne u definiranju i dijeljenju aplikacija s više kontejnera. Sa docker compose, možemo kreirati YAML datoteku za definiranje usluga i jednom komandom možemo sve pokrenuti ili zaustaviti (što ujedno predstavlja ključnu razliku u odnosu na Dockerfile).

Prilikom pokretanja projekta preko Docker compose neophodno je izvršiti sljedeće korake: 

- Prva komanda koja se koristi je docker-compose build, koja kreira slike u docker-compose.yml file-u. Zadatak naredbe build je da pripremi slike za kreiranje kontejnera, tako da ako usluga koristi unaprijed izgrađenu sliku, preskočit će ovu uslugu.

  ```
  docker-compose build
  ```
- Naredna komanda je docker-compose up, koja agregira izlaz svakog kontejnera. Kada naredba završi, svi kontejneri se zaustavljaju. Pokretanje docker-compose up --detach pokreće kontejnere u pozadini i ostavlja ih da rade.
  ```
  docker-compose up-d
  ```


## Video demo aplikacije
[Link za pristup](https://drive.google.com/drive/folders/1cmKvrfQzdEPEGF3bB8zE4pD9ahwc1c1e?usp=sharing)
