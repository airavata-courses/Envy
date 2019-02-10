CREATE ROLE me WITH LOGIN PASSWORD 'password';
DO
$do$
BEGIN
	IF NOT EXISTS(
			SELECT FROM pg_catalog.pg_roles where rolname = 'me')
			THEN
			CREATE ROLE me WITH LOGIN PASSWORD 'password';
		END IF;
	END
$do$;

ALTER ROLE me CREATEDB;


DROP DATABASE IF EXISTS api;
CREATE DATABASE api;
\c api
