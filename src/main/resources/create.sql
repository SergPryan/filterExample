CREATE TABLE public.part
(
    id SERIAL,
    qty integer,
    shipped date,
    received date,
    "partName" character varying(255) COLLATE pg_catalog."default",
    "partNumber" character varying(255) COLLATE pg_catalog."default",
    vendor character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT part_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.part
    OWNER to postgres;

INSERT INTO public.part(
    qty, shipped, received, "partName", "partNumber", vendor)
VALUES (4, '1990-01-01', '1991-02-02', 'name', 'np6', 'vendor');