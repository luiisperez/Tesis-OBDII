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
	PERSONCARID					serial not null,
	PERSONCAR_FK_PERSON			varchar(20) not null,
	PERSONCAR_FK_CAR			varchar(20) not null,
 	CONSTRAINT PK_PERSON_CAR PRIMARY KEY (PERSONCARID)
);

CREATE TABLE HISTORY (
	AIR_INTAKE_TEMP 			float(30) not null, 
	AMBIENT_AIR_TEMP 			float(30) not null, 
	ENGINE_COOLANT_TEMP 		float(30) not null, 
	BAROMETRIC_PRESSURE 		float(30) not null, 
	FUEL_PRESSURE 				float(30) not null, 
	INTAKE_MANIFOLD_PRESSURE 	float(30) not null, 
	ENGINE_LOAD 				float(30) not null, 
	ENGINE_RUNTIME 				varchar(30) not null, 
	ENGINE_RPM 					float(30) not null, 
	SPEED 						float(30) not null, 
	MAF 						float(30) not null, 
	THROTTLE_POS 				float(30) not null, 
	TROUBLE_CODES 				varchar(30) not null, 
	FUEL_LEVEL 					float(30) not null, 
	FUEL_TYPE 					varchar(30) not null, 
	FUEL_CONSUMPTION_RATE 		float(30) not null, 
	TIMING_ADVANCE 				float(30) not null, 
	DTC_NUMBER 					varchar(30) not null, 
	EQUIV_RATIO 				float(30) not null, 
	CONTROL_MODULE_VOLTAGE 		float(30) not null, 
	FUEL_RAIL_PRESSURE 			float(30) not null, 
	VIN 						varchar(30) not null,
	DISTANCE_TRAVELED_MIL_ON 	float(30) not null, 
	STFT2 						float(30) not null, 
	STFT1 						float(30) not null, 
	LTFT2 						float(30) not null, 
	LTFT1 						float(30) not null, 
	ENGINE_OIL_TEMP 			float(30) not null, 
	AIR_FUEL_RATIO 				float(30) not null, 
	WIDEBAND_AIR_FUEL_RATIO 	float(30) not null, 
	TIME_MARK 					timestamp not null,
	LAT 						varchar(30) not null, 
	LON 						varchar(30) not null, 
	ALT 						varchar(30) not null
);
