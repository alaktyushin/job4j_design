CREATE TABLE post
(
    id integer PRIMARY KEY,
    name varchar (250),
	text varchar (30000),
	link varchar (250) UNIQUE,
	created timestamp
);
