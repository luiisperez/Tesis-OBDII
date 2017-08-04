CREATE TABLE PERSON(
	PERSONUSERNAME				varchar(20) not null,
	PERSONFIRSTNAME				varchar(50) not null,
	PERSONLASTNAME				varchar(50) not null,
	PERSONEMAIL					varchar(50) unique not null,
	PERSONPASSWORD				varchar(50) not null,
 	CONSTRAINT PK_PERSON PRIMARY KEY (PERSONUSERNAME)
);