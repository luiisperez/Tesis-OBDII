/**************************************************REGISTRO DE USUARIO**************************************************/

CREATE OR REPLACE FUNCTION CREATE_USER(varchar(20), varchar(50), varchar(50), varchar(50), varchar(50)) RETURNS integer AS $$
DECLARE
 result integer;

BEGIN
	INSERT INTO person(PERSONUSERNAME, PERSONFIRSTNAME, PERSONLASTNAME, PERSONEMAIL, PERSONPASSWORD) VALUES ($1, $2, $3, $4, $5);
	result := 200;
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