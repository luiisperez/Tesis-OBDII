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

CREATE OR REPLACE FUNCTION ADD_VEHICLE(varchar(20), varchar(20), varchar(1000), varchar(1000), numeric) RETURNS integer AS $$
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

CREATE OR REPLACE FUNCTION CARS_USER_INFORMATION(varchar(20)) RETURNS TABLE (carserial varchar(20), carbrand varchar(1000), carmodel varchar(1000), caryear numeric) AS $$

BEGIN
 	RETURN QUERY
	 	SELECT C.CARSERIAL, C.CARBRAND, C.CARMODEL, C.CARYEAR
		FROM PERSON P, CAR C, PERSON_CAR PC
		WHERE PC.PERSONCAR_FK_CAR = C.CARSERIAL AND PC.PERSONCAR_FK_PERSON = P.PERSONUSERNAME AND P.PERSONUSERNAME = $1
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

/**************************************************AGREGAR MARCAS DE VEHICULOS**************************************************/

CREATE OR REPLACE FUNCTION SYNC_NHTSA_INFORMATION_BRANDS(varchar(1000)) RETURNS integer AS $$
DECLARE
 result integer;

BEGIN
	IF ((SELECT COUNT(*) FROM brand WHERE brandname = $1) = 0) THEN

		INSERT INTO brand(BRANDNAME) VALUES ($1);
		result := 200;
	
	END IF;
 	RETURN result;
END;
$$ LANGUAGE plpgsql;

/**************************************************AGREGAR MODELOS DE VEHICULOS**************************************************/

CREATE OR REPLACE FUNCTION SYNC_NHTSA_INFORMATION_MODELS(varchar(1000), varchar(1000)) RETURNS integer AS $$
DECLARE
 result integer;

BEGIN
	IF ((SELECT COUNT(*) FROM model WHERE modelname = $1 AND model_fk_brand = $2) = 0) THEN

		INSERT INTO model(MODELNAME, MODEL_FK_BRAND) VALUES ($1, $2);
		result := 200;
	
	END IF;
 	RETURN result;
END;
$$ LANGUAGE plpgsql;

/**************************************************CONSULTAR MARCAS DE VEHICULOS**************************************************/

CREATE OR REPLACE FUNCTION CAR_BRANDS_INFORMATION() RETURNS TABLE (brand varchar(1000)) AS $$

BEGIN
 	RETURN QUERY
	 	SELECT BRANDNAME
		FROM BRAND
	;
END;
$$ LANGUAGE plpgsql;

/**************************************************CONSULTAR MODELOS DE VEHICULOS POR MARCA**************************************************/

CREATE OR REPLACE FUNCTION CAR_MODELS_BY_BRAND_INFORMATION(varchar(1000)) RETURNS TABLE (model varchar(1000), brand varchar(1000)) AS $$

BEGIN
 	RETURN QUERY
	 	SELECT M.MODELNAME, B.BRANDNAME
		FROM MODEL M, BRAND B
		WHERE M.MODEL_FK_BRAND = B.BRANDNAME AND B.BRANDNAME = $1
	;
END;
$$ LANGUAGE plpgsql;

/**************************************************AGREGAR_HISTORICO_PORCARRO**************************************************/

CREATE OR REPLACE FUNCTION ADD_OBDDATA(float(30), float(30), float(30), float(30), float(30), float(30), float(30), varchar(30), float(30), float(30), float(30), 
	float(30), varchar(30), float(30), varchar(30), float(30), float(30),varchar(30), float(30), float(30), float(30), varchar(30), float(30),float(30),float(30),
	float(30),float(30),float(30),float(30),float(30),timestamp,varchar(30),varchar(30),varchar(30)) RETURNS integer AS $$
DECLARE
 result integer;

BEGIN
		INSERT INTO HISTORY(AIR_INTAKE_TEMP, AMBIENT_AIR_TEMP, ENGINE_COOLANT_TEMP, BAROMETRIC_PRESSURE, FUEL_PRESSURE, INTAKE_MANIFOLD_PRESSURE, 
			ENGINE_LOAD, ENGINE_RUNTIME, ENGINE_RPM, SPEED, MAF, THROTTLE_POS, TROUBLE_CODES, FUEL_LEVEL, FUEL_TYPE, FUEL_CONSUMPTION_RATE, 
			TIMING_ADVANCE, DTC_NUMBER, EQUIV_RATIO, CONTROL_MODULE_VOLTAGE, FUEL_RAIL_PRESSURE, VIN, DISTANCE_TRAVELED_MIL_ON, 
			STFT2, STFT1, LTFT2, LTFT1, ENGINE_OIL_TEMP, AIR_FUEL_RATIO, WIDEBAND_AIR_FUEL_RATIO, TIME_MARK, LAT, LON, ALT) 
		VALUES ($1, $2, $3, $4, $5, $6, $7, $8, $9, $10, $11, $12, $13, $14, $15, $16, $17, $18, $19, $20, $21, $22, $23, $24, $25, $26, $27, $28, $29, $30, $31, $32, $33, $34);
		result := 200;

 	RETURN result;
END;
$$ LANGUAGE plpgsql;

/**************************************************CONSULTAR_HISTORICO_PORCARRO**************************************************/

CREATE OR REPLACE FUNCTION HISTORY_INFORMATION_CAR(varchar(30)) RETURNS TABLE (AIR_INTAKE_TEMP float(30), AMBIENT_AIR_TEMP float(30), 
							ENGINE_COOLANT_TEMP float(30), BAROMETRIC_PRESSURE float(30), FUEL_PRESSURE float(30), INTAKE_MANIFOLD_PRESSURE float(30), 
							ENGINE_LOAD float(30), ENGINE_RUNTIME varchar(30), ENGINE_RPM float(30), SPEED float(30), MAF float(30), THROTTLE_POS float(30), 
							TROUBLE_CODES varchar(30), FUEL_LEVEL float(30), FUEL_TYPE varchar(30), FUEL_CONSUMPTION_RATE float(30), TIMING_ADVANCE float(30), 
							DTC_NUMBER varchar(30), EQUIV_RATIO float(30), CONTROL_MODULE_VOLTAGE float(30), FUEL_RAIL_PRESSURE float(30), VIN varchar(30),
							DISTANCE_TRAVELED_MIL_ON float(30), STFT2 float(30), STFT1 float(30), LTFT2 float(30), LTFT1 float(30), 
							ENGINE_OIL_TEMP float(30), AIR_FUEL_RATIO float(30), WIDEBAND_AIR_FUEL_RATIO float(30), TIME_MARK timestamp, LAT varchar(30), LON varchar(30), ALT varchar(30)) AS $$

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

/**************************************************AGREGAR FALLAS DE VEHICULOS**************************************************/

CREATE OR REPLACE FUNCTION ADD_FAILURE(varchar(1000), varchar(1000), varchar(1000)) RETURNS integer AS $$
DECLARE
 result integer;

BEGIN

	INSERT INTO failure(FAILUREBRANDNAME, FAILUREBRANDMODEL, FAILUREDESCRIPTION) VALUES ($1, $2, $3);
	result := 200;
 	RETURN result;
END;
$$ LANGUAGE plpgsql;

/**************************************************CANTIDAD DE FALLAS AGRUPADOS POR GRUPOS**************************************************/

CREATE OR REPLACE FUNCTION ALL_FAILURES_STATISTICS() RETURNS TABLE (FAILURE_DESCRIPTION varchar(1000), TOTAL_FAILURES bigint) AS $$

BEGIN
 	RETURN QUERY
	 	SELECT FAILUREDESCRIPTION AS FAILURE_DESCRIPTION, COUNT(FAILUREDESCRIPTION) as TOTAL_FAILURES
		FROM FAILURE
		GROUP BY FAILURE_DESCRIPTION
		ORDER BY TOTAL_FAILURES DESC
	;
END;
$$ LANGUAGE plpgsql;

/**************************************************CANTIDAD DE FALLAS EN TOTAL**************************************************/

CREATE OR REPLACE FUNCTION TOTAL_FAILURES() RETURNS TABLE (TOTAL_FAILURES bigint) AS $$

BEGIN
 	RETURN QUERY
	 	SELECT COUNT(*)
		FROM FAILURE
	;
END;
$$ LANGUAGE plpgsql;

/**************************************************CANTIDAD DE FALLAS AGRUPADOS POR GRUPOS DADA UNA MARCA**************************************************/

CREATE OR REPLACE FUNCTION ALL_FAILURES_STATISTICS_BY_BRAND(varchar(1000)) RETURNS TABLE (FAILURE_DESCRIPTION varchar(1000), TOTAL_FAILURES bigint) AS $$

BEGIN
 	RETURN QUERY
	 	SELECT FAILUREDESCRIPTION AS FAILURE_DESCRIPTION, COUNT(FAILUREDESCRIPTION) as TOTAL_FAILURES
		FROM FAILURE
		WHERE FAILUREBRANDNAME = $1
		GROUP BY FAILURE_DESCRIPTION
		ORDER BY TOTAL_FAILURES DESC
	;
END;
$$ LANGUAGE plpgsql;

/**************************************************CANTIDAD DE FALLAS EN TOTAL DADA UNA MARCA**************************************************/

CREATE OR REPLACE FUNCTION TOTAL_FAILURES_BY_BRAND(varchar(1000)) RETURNS TABLE (TOTAL_FAILURES bigint) AS $$

BEGIN
 	RETURN QUERY
	 	SELECT COUNT(*)
		FROM FAILURE
		WHERE FAILUREBRANDNAME = $1
	;
END;
$$ LANGUAGE plpgsql;

/**************************************************CANTIDAD DE FALLAS AGRUPADOS POR GRUPOS DADA UNA MARCA Y MODELO**************************************************/

CREATE OR REPLACE FUNCTION ALL_FAILURES_STATISTICS_BY_MODEL(varchar(1000), varchar(1000)) RETURNS TABLE (FAILURE_DESCRIPTION varchar(1000), TOTAL_FAILURES bigint) AS $$

BEGIN
 	RETURN QUERY
	 	SELECT FAILUREDESCRIPTION AS FAILURE_DESCRIPTION, COUNT(FAILUREDESCRIPTION) as TOTAL_FAILURES
		FROM FAILURE
		WHERE FAILUREBRANDNAME = $1 AND FAILUREBRANDMODEL = $2
		GROUP BY FAILURE_DESCRIPTION
		ORDER BY TOTAL_FAILURES DESC
	;
END;
$$ LANGUAGE plpgsql;

/**************************************************CANTIDAD DE FALLAS EN TOTAL DADA UNA MARCA Y MODELO**************************************************/

CREATE OR REPLACE FUNCTION TOTAL_FAILURES_BY_MODEL(varchar(1000), varchar(1000)) RETURNS TABLE (TOTAL_FAILURES bigint) AS $$

BEGIN
 	RETURN QUERY
	 	SELECT COUNT(*)
		FROM FAILURE
		WHERE FAILUREBRANDNAME = $1 AND FAILUREBRANDMODEL = $2
	;
END;
$$ LANGUAGE plpgsql;

/**************************************************CANTIDAD DE FALLAS AGRUPADOS POR GRUPOS DADA UNA MARCA**************************************************/

CREATE OR REPLACE FUNCTION ALL_BRANDS_STATISTICS_BY_FAILURE(varchar(1000)) RETURNS TABLE (FAILURE_BRAND varchar(1000), TOTAL_FAILURES bigint) AS $$

BEGIN
 	RETURN QUERY
 		SELECT FAILUREBRANDNAME AS FAILURE_BRAND, COUNT(FAILUREBRANDNAME) as TOTAL_FAILURES
		FROM FAILURE
		WHERE FAILUREDESCRIPTION = $1
		GROUP BY FAILURE_BRAND
		ORDER BY TOTAL_FAILURES DESC
	;
END;
$$ LANGUAGE plpgsql;

/**************************************************CANTIDAD DE FALLAS AGRUPADOS POR GRUPOS DADA UNA MARCA Y MODELO**************************************************/

CREATE OR REPLACE FUNCTION ALL_MODELS_STATISTICS_BY_FAILURE(varchar(1000), varchar(1000)) RETURNS TABLE (FAILURE_MODEL varchar(1000),TOTAL_FAILURES bigint) AS $$

BEGIN
 	RETURN QUERY
 		SELECT FAILUREBRANDMODEL AS FAILURE_MODEL, COUNT(FAILUREBRANDNAME) as TOTAL_FAILURES
		FROM FAILURE
		WHERE FAILUREDESCRIPTION = $1 AND FAILUREBRANDNAME = $2
		GROUP BY FAILURE_MODEL
		ORDER BY TOTAL_FAILURES DESC
	;
END;
$$ LANGUAGE plpgsql;