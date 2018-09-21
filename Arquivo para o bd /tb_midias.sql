


create table tb_midias( id int(3) NOT NULL AUTO_INCREMENT, tipo varchar(255) NOT NULL, descricao varchar(255) NOT NULL, valor decimal(10,2) NOT NULL, PRIMARY KEY (id)) ENGINE=InnoDB;


INSERT INTO tb_midias (TIPO, DESCRICAO, VALOR) VALUES ('dvd','DVD da Anitta' ,10.0 );

INSERT INTO tb_midias (TIPO, DESCRICAO, VALOR) VALUES ('cd','Cd do Jesus Culture' ,14.0 );

INSERT INTO tb_midias (TIPO, DESCRICAO, VALOR) VALUES ('dvd','DVD da Rihanna' ,7.0 );