create database recherche ; 

\c recherche2;

create table categorie(
    categorie_id serial primary key,
    nom varchar(50)

);


insert into categorie(nom) VALUES ('telephone');
insert into categorie(nom) VALUES ('ordinateur');


create table produit(
    produit_id serial primary key,
    nom varchar(50),
    categorie int,
    qualite int ,
    prix double precision,
    foreign key (categorie) references categorie(categorie_id)

) ; 

insert into produit (nom,categorie,qualite,prix) VALUES ('samsung s10',1,7,460000);
insert into produit (nom,categorie,qualite,prix) VALUES ('samsung a20',1,5,300000);
insert into produit (nom,categorie,qualite,prix) VALUES ('redmi note 8',1,9,560000);
insert into produit (nom,categorie,qualite,prix) VALUES ('iphone 8',1,5,300000);

insert into produit (nom,categorie,qualite,prix) VALUES ('asus tuf gaming',2,8,2060000);
insert into produit (nom,categorie,qualite,prix) VALUES ('lenovo m10',2,6,1300000);
insert into produit (nom,categorie,qualite,prix) VALUES ('acer nitro',2,9,2100000);
insert into produit (nom,categorie,qualite,prix) VALUES ('Mac book',2,7,4100000);


drop view vproduit;
create or replace view vproduit as select 
produit_id ,p.nom,categorie,qualite,prix,c.nom as cat_nom, qualite/prix as rapport
 from produit p join categorie c on c.categorie_id = p.categorie ;

create table mot_cle(
    nom varchar(100),
    view varchar(50)

);
insert into mot_cle(nom,view) values ('produit','vproduit');

insert into mot_cle(nom,view) values ('meilleur prix','meilleur_prix');
insert into mot_cle(nom,view) values ('pire prix','pire_prix');
insert into mot_cle(nom,view) values ('meilleur rapport prix','meilleur_rqp');
insert into mot_cle(nom,view) values ('pire rapport  prix','pire_rqp');
insert into mot_cle(nom,view) values ('plus chere','pire_prix');
insert into mot_cle(nom,view) values ('moins chere','meilleur_prix');
insert into mot_cle(nom,view) values ('meilleur qualite','meilleur_qualite');
insert into mot_cle(nom,view) values ('pire qualite','pire_qualite');




drop view meilleur_prix;
drop view pire_prix;
drop view meilleur_rqp;
drop view pire_rqp;
drop view meilleur_qualite;
drop view pire_qualite;

create or replace view meilleur_prix as select * from vproduit order by prix asc;
create or replace view pire_prix as select * from vproduit order by prix desc;
create or replace view meilleur_rqp as select * from vproduit order by rapport desc;
create or replace view pire_rqp as select * from vproduit order by rapport asc;
create or replace view meilleur_qualite as select * from vproduit order by qualite desc;
create or replace view pire_qualite as select * from vproduit order by qualite asc;



select * from vproduit where cat_nom LIKE'%telephone%' or cat_nom LIKE'%ordi%' order by qualite/prix desc;

