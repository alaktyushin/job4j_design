CREATE TYPE sex AS ENUM ('male', 'female', 'other');
CREATE table person (
    id serial primary key, 
    name text,
    date_of_birth date,
    height smallint,
    smoker boolean
);
INSERT into person (name, date_of_birth, height, smoker) values ('Petr', '01.01.1991', 180, false);
INSERT into person (name, date_of_birth, height, smoker) values ('Ivan', '02.02.1992', 185, false);
SELECT * from person;
UPDATE person SET height = 177 WHERE height = 180;
UPDATE person SET smoker = true WHERE height = 177;
INSERT into person (name, date_of_birth, height, smoker) values ('Petr', '01.01.1991', 180, false);
SELECT * from person;
DELETE FROM person WHERE name = 'Ivan';
DELETE FROM person;