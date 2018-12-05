--
-- PostgreSQL database dump
--

-- Dumped from database version 9.4.19
-- Dumped by pg_dump version 10.5

-- Started on 2018-12-05 18:12:38

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 11861)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2051 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 178 (class 1259 OID 83970)
-- Name: category; Type: TABLE; Schema: public; Owner: nc2018
--

CREATE TABLE public.category (
    category_id integer NOT NULL,
    category_name character varying(30) NOT NULL,
    category_description character varying(50) NOT NULL
);


ALTER TABLE public.category OWNER TO nc2018;

--
-- TOC entry 177 (class 1259 OID 83968)
-- Name: category_category_id_seq; Type: SEQUENCE; Schema: public; Owner: nc2018
--

CREATE SEQUENCE public.category_category_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.category_category_id_seq OWNER TO nc2018;

--
-- TOC entry 2052 (class 0 OID 0)
-- Dependencies: 177
-- Name: category_category_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: nc2018
--

ALTER SEQUENCE public.category_category_id_seq OWNED BY public.category.category_id;


--
-- TOC entry 174 (class 1259 OID 83949)
-- Name: city; Type: TABLE; Schema: public; Owner: nc2018
--

CREATE TABLE public.city (
    city_id integer NOT NULL,
    city_name character varying(30) NOT NULL,
    city_phone_extension character varying(20) NOT NULL,
    city_description character varying(50) NOT NULL
);


ALTER TABLE public.city OWNER TO nc2018;

--
-- TOC entry 173 (class 1259 OID 83947)
-- Name: city_city_id_seq; Type: SEQUENCE; Schema: public; Owner: nc2018
--

CREATE SEQUENCE public.city_city_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.city_city_id_seq OWNER TO nc2018;

--
-- TOC entry 2053 (class 0 OID 0)
-- Dependencies: 173
-- Name: city_city_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: nc2018
--

ALTER SEQUENCE public.city_city_id_seq OWNED BY public.city.city_id;


--
-- TOC entry 181 (class 1259 OID 83989)
-- Name: offering; Type: TABLE; Schema: public; Owner: nc2018
--

CREATE TABLE public.offering (
    product_id integer NOT NULL,
    office_id integer NOT NULL,
    offering_price double precision NOT NULL
);


ALTER TABLE public.offering OWNER TO nc2018;

--
-- TOC entry 176 (class 1259 OID 83957)
-- Name: office; Type: TABLE; Schema: public; Owner: nc2018
--

CREATE TABLE public.office (
    office_id integer NOT NULL,
    office_name character varying(30) NOT NULL,
    office_phone_number character varying(20) NOT NULL,
    city_id integer NOT NULL
);


ALTER TABLE public.office OWNER TO nc2018;

--
-- TOC entry 175 (class 1259 OID 83955)
-- Name: office_office_id_seq; Type: SEQUENCE; Schema: public; Owner: nc2018
--

CREATE SEQUENCE public.office_office_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.office_office_id_seq OWNER TO nc2018;

--
-- TOC entry 2054 (class 0 OID 0)
-- Dependencies: 175
-- Name: office_office_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: nc2018
--

ALTER SEQUENCE public.office_office_id_seq OWNED BY public.office.office_id;


--
-- TOC entry 180 (class 1259 OID 83978)
-- Name: product; Type: TABLE; Schema: public; Owner: nc2018
--

CREATE TABLE public.product (
    product_id integer NOT NULL,
    product_name character varying(30) NOT NULL,
    category_id integer NOT NULL,
    product_description character varying(50) NOT NULL
);


ALTER TABLE public.product OWNER TO nc2018;

--
-- TOC entry 179 (class 1259 OID 83976)
-- Name: product_product_id_seq; Type: SEQUENCE; Schema: public; Owner: nc2018
--

CREATE SEQUENCE public.product_product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.product_product_id_seq OWNER TO nc2018;

--
-- TOC entry 2055 (class 0 OID 0)
-- Dependencies: 179
-- Name: product_product_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: nc2018
--

ALTER SEQUENCE public.product_product_id_seq OWNED BY public.product.product_id;


--
-- TOC entry 1909 (class 2604 OID 83973)
-- Name: category category_id; Type: DEFAULT; Schema: public; Owner: nc2018
--

ALTER TABLE ONLY public.category ALTER COLUMN category_id SET DEFAULT nextval('public.category_category_id_seq'::regclass);


--
-- TOC entry 1907 (class 2604 OID 83952)
-- Name: city city_id; Type: DEFAULT; Schema: public; Owner: nc2018
--

ALTER TABLE ONLY public.city ALTER COLUMN city_id SET DEFAULT nextval('public.city_city_id_seq'::regclass);


--
-- TOC entry 1908 (class 2604 OID 83960)
-- Name: office office_id; Type: DEFAULT; Schema: public; Owner: nc2018
--

ALTER TABLE ONLY public.office ALTER COLUMN office_id SET DEFAULT nextval('public.office_office_id_seq'::regclass);


--
-- TOC entry 1910 (class 2604 OID 83981)
-- Name: product product_id; Type: DEFAULT; Schema: public; Owner: nc2018
--

ALTER TABLE ONLY public.product ALTER COLUMN product_id SET DEFAULT nextval('public.product_product_id_seq'::regclass);


--
-- TOC entry 2039 (class 0 OID 83970)
-- Dependencies: 178
-- Data for Name: category; Type: TABLE DATA; Schema: public; Owner: nc2018
--

COPY public.category (category_id, category_name, category_description) FROM stdin;
\.


--
-- TOC entry 2035 (class 0 OID 83949)
-- Dependencies: 174
-- Data for Name: city; Type: TABLE DATA; Schema: public; Owner: nc2018
--

COPY public.city (city_id, city_name, city_phone_extension, city_description) FROM stdin;
\.


--
-- TOC entry 2042 (class 0 OID 83989)
-- Dependencies: 181
-- Data for Name: offering; Type: TABLE DATA; Schema: public; Owner: nc2018
--

COPY public.offering (product_id, office_id, offering_price) FROM stdin;
\.


--
-- TOC entry 2037 (class 0 OID 83957)
-- Dependencies: 176
-- Data for Name: office; Type: TABLE DATA; Schema: public; Owner: nc2018
--

COPY public.office (office_id, office_name, office_phone_number, city_id) FROM stdin;
\.


--
-- TOC entry 2041 (class 0 OID 83978)
-- Dependencies: 180
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: nc2018
--

COPY public.product (product_id, product_name, category_id, product_description) FROM stdin;
\.


--
-- TOC entry 2056 (class 0 OID 0)
-- Dependencies: 177
-- Name: category_category_id_seq; Type: SEQUENCE SET; Schema: public; Owner: nc2018
--

SELECT pg_catalog.setval('public.category_category_id_seq', 1, false);


--
-- TOC entry 2057 (class 0 OID 0)
-- Dependencies: 173
-- Name: city_city_id_seq; Type: SEQUENCE SET; Schema: public; Owner: nc2018
--

SELECT pg_catalog.setval('public.city_city_id_seq', 1, false);


--
-- TOC entry 2058 (class 0 OID 0)
-- Dependencies: 175
-- Name: office_office_id_seq; Type: SEQUENCE SET; Schema: public; Owner: nc2018
--

SELECT pg_catalog.setval('public.office_office_id_seq', 1, false);


--
-- TOC entry 2059 (class 0 OID 0)
-- Dependencies: 179
-- Name: product_product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: nc2018
--

SELECT pg_catalog.setval('public.product_product_id_seq', 1, false);


--
-- TOC entry 1916 (class 2606 OID 83975)
-- Name: category category_pkey; Type: CONSTRAINT; Schema: public; Owner: nc2018
--

ALTER TABLE ONLY public.category
    ADD CONSTRAINT category_pkey PRIMARY KEY (category_id);


--
-- TOC entry 1912 (class 2606 OID 83954)
-- Name: city city_pkey; Type: CONSTRAINT; Schema: public; Owner: nc2018
--

ALTER TABLE ONLY public.city
    ADD CONSTRAINT city_pkey PRIMARY KEY (city_id);


--
-- TOC entry 1920 (class 2606 OID 83993)
-- Name: offering offering_pkey; Type: CONSTRAINT; Schema: public; Owner: nc2018
--

ALTER TABLE ONLY public.offering
    ADD CONSTRAINT offering_pkey PRIMARY KEY (product_id, office_id);


--
-- TOC entry 1914 (class 2606 OID 83962)
-- Name: office office_pkey; Type: CONSTRAINT; Schema: public; Owner: nc2018
--

ALTER TABLE ONLY public.office
    ADD CONSTRAINT office_pkey PRIMARY KEY (office_id);


--
-- TOC entry 1918 (class 2606 OID 83983)
-- Name: product product_pkey; Type: CONSTRAINT; Schema: public; Owner: nc2018
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (product_id);


--
-- TOC entry 1923 (class 2606 OID 84015)
-- Name: offering offering_office_fk; Type: FK CONSTRAINT; Schema: public; Owner: nc2018
--

ALTER TABLE ONLY public.offering
    ADD CONSTRAINT offering_office_fk FOREIGN KEY (office_id) REFERENCES public.office(office_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1924 (class 2606 OID 84020)
-- Name: offering offering_product_fk; Type: FK CONSTRAINT; Schema: public; Owner: nc2018
--

ALTER TABLE ONLY public.offering
    ADD CONSTRAINT offering_product_fk FOREIGN KEY (product_id) REFERENCES public.product(product_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1922 (class 2606 OID 84005)
-- Name: product product_category_fk; Type: FK CONSTRAINT; Schema: public; Owner: nc2018
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_category_fk FOREIGN KEY (category_id) REFERENCES public.category(category_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1921 (class 2606 OID 84010)
-- Name: office product_category_fk; Type: FK CONSTRAINT; Schema: public; Owner: nc2018
--

ALTER TABLE ONLY public.office
    ADD CONSTRAINT product_category_fk FOREIGN KEY (city_id) REFERENCES public.city(city_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2050 (class 0 OID 0)
-- Dependencies: 6
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2018-12-05 18:12:46

--
-- PostgreSQL database dump complete
--

