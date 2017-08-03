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