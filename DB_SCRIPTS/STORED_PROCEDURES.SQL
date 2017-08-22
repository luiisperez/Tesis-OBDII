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