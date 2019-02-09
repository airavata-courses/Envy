CREATE ROLE me WITH LOGIN PASSWORD 'password';
ALTER ROLE me CREATEDB;

CREATE DATABASE api;
\c api

create table user_travel(email text primary key, password text, dob date);
create table airport_index(airport text, latitude double precision, longitude double precision, iata text, primary key(latitude, longitude));
create table search_details(id serial primary key, car_carrier text, flight_carrier text, total_price double precision,car_source text, car_destination text, flight_source text, flight_destination text, date text, search_id text, car_source_price double precision, car_destination_price double precision, flight_price double precision);

Grant all on airport_index to me;
Grant all on search_details to me;
Grant all on user_travel to me;                                                                                                                       
GRANT USAGE, SELECT ON SEQUENCE search_details_id_seq TO me;
