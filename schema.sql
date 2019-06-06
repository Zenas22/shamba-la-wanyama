--
-- PostgreSQL database dump
--

-- Dumped from database version 11.3 (Ubuntu 11.3-1.pgdg18.04+1)
-- Dumped by pg_dump version 11.3 (Ubuntu 11.3-1.pgdg18.04+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: animals; Type: TABLE; Schema: public; Owner: zeus
--

CREATE TABLE public.animals (
    id integer NOT NULL,
    name character varying,
    age character varying,
    health character varying,
    speciesid integer
);


ALTER TABLE public.animals OWNER TO zeus;

--
-- Name: animals_id_seq; Type: SEQUENCE; Schema: public; Owner: zeus
--

CREATE SEQUENCE public.animals_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.animals_id_seq OWNER TO zeus;

--
-- Name: animals_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: zeus
--

ALTER SEQUENCE public.animals_id_seq OWNED BY public.animals.id;


--
-- Name: locations; Type: TABLE; Schema: public; Owner: zeus
--

CREATE TABLE public.locations (
    id integer NOT NULL,
    name character varying
);


ALTER TABLE public.locations OWNER TO zeus;

--
-- Name: locations_id_seq; Type: SEQUENCE; Schema: public; Owner: zeus
--

CREATE SEQUENCE public.locations_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.locations_id_seq OWNER TO zeus;

--
-- Name: locations_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: zeus
--

ALTER SEQUENCE public.locations_id_seq OWNED BY public.locations.id;


--
-- Name: rangers; Type: TABLE; Schema: public; Owner: zeus
--

CREATE TABLE public.rangers (
    id integer NOT NULL,
    name character varying,
    badge integer
);


ALTER TABLE public.rangers OWNER TO zeus;

--
-- Name: rangers_id_seq; Type: SEQUENCE; Schema: public; Owner: zeus
--

CREATE SEQUENCE public.rangers_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.rangers_id_seq OWNER TO zeus;

--
-- Name: rangers_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: zeus
--

ALTER SEQUENCE public.rangers_id_seq OWNED BY public.rangers.id;


--
-- Name: sightings; Type: TABLE; Schema: public; Owner: zeus
--

CREATE TABLE public.sightings (
    id integer NOT NULL,
    animalid integer,
    locationid integer,
    rangerid integer,
    speciesid integer,
    "timestamp" integer
);


ALTER TABLE public.sightings OWNER TO zeus;

--
-- Name: sightings_id_seq; Type: SEQUENCE; Schema: public; Owner: zeus
--

CREATE SEQUENCE public.sightings_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.sightings_id_seq OWNER TO zeus;

--
-- Name: sightings_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: zeus
--

ALTER SEQUENCE public.sightings_id_seq OWNED BY public.sightings.id;


--
-- Name: species; Type: TABLE; Schema: public; Owner: zeus
--

CREATE TABLE public.species (
    id integer NOT NULL,
    name character varying,
    population integer,
    endangered boolean
);


ALTER TABLE public.species OWNER TO zeus;

--
-- Name: species_id_seq; Type: SEQUENCE; Schema: public; Owner: zeus
--

CREATE SEQUENCE public.species_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.species_id_seq OWNER TO zeus;

--
-- Name: species_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: zeus
--

ALTER SEQUENCE public.species_id_seq OWNED BY public.species.id;


--
-- Name: animals id; Type: DEFAULT; Schema: public; Owner: zeus
--

ALTER TABLE ONLY public.animals ALTER COLUMN id SET DEFAULT nextval('public.animals_id_seq'::regclass);


--
-- Name: locations id; Type: DEFAULT; Schema: public; Owner: zeus
--

ALTER TABLE ONLY public.locations ALTER COLUMN id SET DEFAULT nextval('public.locations_id_seq'::regclass);


--
-- Name: rangers id; Type: DEFAULT; Schema: public; Owner: zeus
--

ALTER TABLE ONLY public.rangers ALTER COLUMN id SET DEFAULT nextval('public.rangers_id_seq'::regclass);


--
-- Name: sightings id; Type: DEFAULT; Schema: public; Owner: zeus
--

ALTER TABLE ONLY public.sightings ALTER COLUMN id SET DEFAULT nextval('public.sightings_id_seq'::regclass);


--
-- Name: species id; Type: DEFAULT; Schema: public; Owner: zeus
--

ALTER TABLE ONLY public.species ALTER COLUMN id SET DEFAULT nextval('public.species_id_seq'::regclass);


--
-- Data for Name: animals; Type: TABLE DATA; Schema: public; Owner: zeus
--

COPY public.animals (id, name, age, health, speciesid) FROM stdin;
\.


--
-- Data for Name: locations; Type: TABLE DATA; Schema: public; Owner: zeus
--

COPY public.locations (id, name) FROM stdin;
\.


--
-- Data for Name: rangers; Type: TABLE DATA; Schema: public; Owner: zeus
--

COPY public.rangers (id, name, badge) FROM stdin;
\.


--
-- Data for Name: sightings; Type: TABLE DATA; Schema: public; Owner: zeus
--

COPY public.sightings (id, animalid, locationid, rangerid, speciesid, "timestamp") FROM stdin;
\.


--
-- Data for Name: species; Type: TABLE DATA; Schema: public; Owner: zeus
--

COPY public.species (id, name, population, endangered) FROM stdin;
\.


--
-- Name: animals_id_seq; Type: SEQUENCE SET; Schema: public; Owner: zeus
--

SELECT pg_catalog.setval('public.animals_id_seq', 13, true);


--
-- Name: locations_id_seq; Type: SEQUENCE SET; Schema: public; Owner: zeus
--

SELECT pg_catalog.setval('public.locations_id_seq', 1, false);


--
-- Name: rangers_id_seq; Type: SEQUENCE SET; Schema: public; Owner: zeus
--

SELECT pg_catalog.setval('public.rangers_id_seq', 1, false);


--
-- Name: sightings_id_seq; Type: SEQUENCE SET; Schema: public; Owner: zeus
--

SELECT pg_catalog.setval('public.sightings_id_seq', 1, false);


--
-- Name: species_id_seq; Type: SEQUENCE SET; Schema: public; Owner: zeus
--

SELECT pg_catalog.setval('public.species_id_seq', 1, false);


--
-- Name: animals animals_pkey; Type: CONSTRAINT; Schema: public; Owner: zeus
--

ALTER TABLE ONLY public.animals
    ADD CONSTRAINT animals_pkey PRIMARY KEY (id);


--
-- Name: locations locations_pkey; Type: CONSTRAINT; Schema: public; Owner: zeus
--

ALTER TABLE ONLY public.locations
    ADD CONSTRAINT locations_pkey PRIMARY KEY (id);


--
-- Name: rangers rangers_pkey; Type: CONSTRAINT; Schema: public; Owner: zeus
--

ALTER TABLE ONLY public.rangers
    ADD CONSTRAINT rangers_pkey PRIMARY KEY (id);


--
-- Name: sightings sightings_pkey; Type: CONSTRAINT; Schema: public; Owner: zeus
--

ALTER TABLE ONLY public.sightings
    ADD CONSTRAINT sightings_pkey PRIMARY KEY (id);


--
-- Name: species species_pkey; Type: CONSTRAINT; Schema: public; Owner: zeus
--

ALTER TABLE ONLY public.species
    ADD CONSTRAINT species_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

