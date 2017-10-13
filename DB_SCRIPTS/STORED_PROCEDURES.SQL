/**************************************************REGISTRO DE USUARIO**************************************************/

CREATE OR REPLACE FUNCTION CREATE_USER(varchar(20), varchar(50), varchar(50), varchar(50), varchar(50)) RETURNS integer AS $$
DECLARE
 duplicatedusername integer;
 duplicatedemail integer;
 result integer;

BEGIN
	IF ((SELECT COUNT(*) FROM person WHERE personusername = $1) = 1) THEN

		duplicatedusername := 400;
  	
  	ELSE
	
		duplicatedusername := 0;
	
	END IF;

	IF ((SELECT COUNT(*) FROM person WHERE personemail = $4) = 1) THEN

		INSERT INTO person(PERSONUSERNAME, PERSONFIRSTNAME, PERSONLASTNAME, PERSONEMAIL, PERSONPASSWORD) VALUES ($1, $2, $3, $4, $5);

		duplicatedemail := 600;
  	
  	ELSE

		duplicatedemail := 0;
	
	END IF;

	IF ((duplicatedusername = 0) AND (duplicatedemail = 0)) THEN

		INSERT INTO person(PERSONUSERNAME, PERSONFIRSTNAME, PERSONLASTNAME, PERSONEMAIL, PERSONPASSWORD) VALUES ($1, $2, $3, $4, $5);

		result := 200;
  	
  	ELSE

		result := duplicatedusername + duplicatedemail;
	
	END IF;

 	RETURN result;
END;
$$ LANGUAGE plpgsql;

/**************************************************INICIO DE SESION**************************************************/

CREATE OR REPLACE FUNCTION USER_LOGIN(varchar(20), varchar(50)) RETURNS integer AS $$
DECLARE
 result integer;

BEGIN
	IF ((SELECT COUNT(*) FROM person WHERE personemail = $1 AND personpassword = $2) = 0) THEN

		result := 500;
  	
  	ELSE
	
		result := 200;
	
	END IF;
 	RETURN result;
END;
$$ LANGUAGE plpgsql;

/**************************************************CONSULTAR USUARIO**************************************************/

CREATE OR REPLACE FUNCTION USER_INFORMATION(varchar(50)) RETURNS TABLE (personusername varchar(20), personfirstname varchar(50), personlastname varchar(50), personmail varchar(50), personpassword varchar(50)) AS $$

BEGIN
 	RETURN QUERY
	 	SELECT P.PERSONUSERNAME, P.PERSONFIRSTNAME, P.PERSONLASTNAME, P.PERSONEMAIL, P.PERSONPASSWORD
		FROM PERSON P
		WHERE PERSONEMAIL = $1
	;
END;
$$ LANGUAGE plpgsql;

/**************************************************AGREGAR VEHICULO**************************************************/

CREATE OR REPLACE FUNCTION ADD_VEHICLE(varchar(20), varchar(20), varchar(20), varchar(20), numeric) RETURNS integer AS $$
DECLARE
 result integer;

BEGIN
	IF ((SELECT COUNT(*) FROM car WHERE carserial = $2) = 1) THEN

		IF ((SELECT COUNT(*) FROM car WHERE carserial = $2 AND carbrand = $3 AND carmodel = $4 AND caryear = $5) = 0) THEN

			result := 700;

		ELSE

			INSERT INTO person_car(PERSONCAR_FK_PERSON, PERSONCAR_FK_CAR) VALUES ($1, $2);
			result := 200;

		END IF;
  	
  	ELSE

		INSERT INTO car(CARSERIAL, CARBRAND, CARMODEL, CARYEAR) VALUES ($2, $3, $4, $5);
		INSERT INTO person_car(PERSONCAR_FK_PERSON, PERSONCAR_FK_CAR) VALUES ($1, $2);
		result := 200;
	
	END IF;
 	RETURN result;
END;
$$ LANGUAGE plpgsql;

/**************************************************CONSULTAR VEHICULOS DE UN USUARIO**************************************************/

CREATE OR REPLACE FUNCTION CARS_USER_INFORMATION(varchar(20)) RETURNS TABLE (carserial varchar(20), carbrand varchar(20), carmodel varchar(20), caryear numeric) AS $$

BEGIN
 	RETURN QUERY
	 	SELECT C.CARSERIAL, C.CARBRAND, C.CARMODEL, C.CARYEAR
		FROM PERSON P, CAR C, PERSON_CAR PC
		WHERE P.PERSONCAR_FK_CAR = C.CARSERIAL AND C.PERSONCAR_FK_PERSON = P.PERSONUSERNAME AND P.PERSONUSERNAME = $1
	;
END;
$$ LANGUAGE plpgsql;

/**************************************************BORRAR VEHICULO DE UN USUARIO**************************************************/

CREATE OR REPLACE FUNCTION REMOVE_VEHICLE(varchar(20), varchar(20)) RETURNS integer AS $$
DECLARE
 result integer;

BEGIN
	DELETE FROM PERSON_CAR WHERE PERSONCARID = (SELECT PERSONCARID 
												FROM PERSON_CAR
												WHERE PERSONCAR_FK_CAR = $1 AND PERSONCAR_FK_PERSON = $2);
	result := 200;
 	RETURN result;
END;
$$ LANGUAGE plpgsql;


/**************************************************AGREGAR_HISTORICO_PORCARRO**************************************************/

CREATE OR REPLACE FUNCTION ADD_OBDDATA(float(30), float(30), float(30), float(30), float(30), float(30), float(30), varchar(30), float(30), float(30), float(30), 
	float(30), varchar(30), float(30), varchar(30), float(30), float(30),varchar(30), float(30), float(30), float(30), varchar(30), float(30),float(30),float(30),
	float(30),float(30),float(30),float(30),float(30),varchar(30),varchar(30),varchar(30),varchar(30)) RETURNS integer AS $$
DECLARE
 result integer;

BEGIN
		INSERT INTO HISTORY(CARSERIAL, CARBRAND, CARMODEL, CARYEAR) VALUES ($2, $3, $4, $5);
		result := 200;

 	RETURN result;
END;
$$ LANGUAGE plpgsql;

/**************************************************CONSULTAR_HISTORICO_PORCARRO**************************************************/

CREATE OR REPLACE FUNCTION HISTORY_INFORMATION_CAR(varchar(30)) RETURNS TABLE (AIR_INTAKE_TEMP float(30), AMBIENT_AIR_TEMP float(30), 
							ENGINE_COOLANT_TEMP float(30), BAROMETRIC_PRESSURE float(30), FUEL_PRESSURE float(30), INTAKE_MANIFOLD_PRESSURE float(30), 
							ENGINE_LOAD float(30), ENGINE_RUNTIME float(30), ENGINE_RPM float(30), SPEED float(30), MAF float(30), THROTTLE_POS float(30), 
							TROUBLE_CODES varchar(30), FUEL_LEVEL float(30), FUEL_TYPE varchar(30), FUEL_CONSUMPTION_RATE float(30), TIMING_ADVANCE float(30), 
							DTC_NUMBER varchar(30), EQUIV_RATIO float(30), CONTROL_MODULE_VOLTAGE float(30), FUEL_RAIL_PRESSURE float(30), VIN varchar(30),
							DISTANCE_TRAVELED_MIL_ON float(30), STFT2 float(30), STFT1 float(30), LTFT2 float(30), LTFT1 float(30), 
							ENGINE_OIL_TEMP float(30), AIR_FUEL_RATIO float(30), WIDEBAND_AIR_FUEL_RATIO float(30), TIME_MARK varchar(30), LAT varchar(30), LON varchar(30), ALT varchar(30)) AS $$

BEGIN
 	RETURN QUERY
	 	SELECT H.AIR_INTAKE_TEMP, H.AMBIENT_AIR_TEMP, H.ENGINE_COOLANT_TEMP, H.BAROMETRIC_PRESSURE, H.FUEL_PRESSURE, H.INTAKE_MANIFOLD_PRESSURE, 
	 	H.ENGINE_LOAD, H.ENGINE_RUNTIME, H.ENGINE_RPM, H.SPEED, H.MAF, H.THROTTLE_POS, H.TROUBLE_CODES, H.FUEL_LEVEL, H.FUEL_TYPE, 
	 	H.FUEL_CONSUMPTION_RATE, H.TIMING_ADVANCE, H.DTC_NUMBER, H.EQUIV_RATIO, H.CONTROL_MODULE_VOLTAGE, H.FUEL_RAIL_PRESSURE, H.VIN,
	 	H.DISTANCE_TRAVELED_MIL_ON, H.STFT2, H.STFT1, H.LTFT2, H.LTFT1, H.ENGINE_OIL_TEMP, H.AIR_FUEL_RATIO, H.WIDEBAND_AIR_FUEL_RATIO, H.TIME_MARK, H.LAT, H.LON, H.ALT
		FROM HISTORY H
		WHERE H.VIN = $1
	;
END;
$$ LANGUAGE plpgsql;