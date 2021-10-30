CREATE TABLE post
(
    id integer NOT NULL CONSTRAINT post_pkey PRIMARY KEY,
    name varchar (250),
	text varchar (30000),
	link varchar (250) CONSTRAINT post_link UNIQUE,
	created timestamp
);
