CREATE TABLE PERSON(
	PERSONUSERNAME				varchar(20) not null,
	PERSONFIRSTNAME				varchar(50) not null,
	PERSONLASTNAME				varchar(50) not null,
	PERSONEMAIL					varchar(50) unique not null,
	PERSONPASSWORD				varchar(50) not null,
 	CONSTRAINT PK_PERSON PRIMARY KEY (PERSONUSERNAME)
);

CREATE TABLE CAR(
	CARSERIAL					varchar(20) not null,
	CARBRAND					varchar(20) not null,
	CARMODEL					varchar(20) not null,
	CARYEAR						numeric(20) not null,
 	CONSTRAINT PK_CAR PRIMARY KEY (CARSERIAL)
);

CREATE TABLE PERSON_CAR(
	PERSONCARID					varchar(20) not null,
	PERSONCAR_FK_PERSON			varchar(20) not null,
	PERSONCAR_FK_CAR			varchar(20) not null,
 	CONSTRAINT PK_PERSON_CAR PRIMARY KEY (PERSONCARID)
);