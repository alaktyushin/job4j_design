CREATE TABLE post
(
    id integer NOT NULL,
    name varchar (250),
	text varchar (30000),
	link varchar (250),
	created timestamp,
    CONSTRAINT post_pkey PRIMARY KEY (id)
);