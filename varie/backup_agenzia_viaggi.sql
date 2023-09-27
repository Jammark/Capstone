--
-- PostgreSQL database dump
--

-- Dumped from database version 15.3
-- Dumped by pg_dump version 15.3

-- Started on 2023-09-27 10:21:43 CEST

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

SET default_table_access_method = heap;

--
-- TOC entry 215 (class 1259 OID 24851)
-- Name: acquisti; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.acquisti (
    stazione_id bigint NOT NULL,
    data date,
    prezzo double precision NOT NULL,
    prenotazione_id bigint,
    utente_id bigint
);


ALTER TABLE public.acquisti OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 24850)
-- Name: acquisti_stazione_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.acquisti_stazione_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.acquisti_stazione_id_seq OWNER TO postgres;

--
-- TOC entry 3755 (class 0 OID 0)
-- Dependencies: 214
-- Name: acquisti_stazione_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.acquisti_stazione_id_seq OWNED BY public.acquisti.stazione_id;


--
-- TOC entry 216 (class 1259 OID 24857)
-- Name: aereoporti; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.aereoporti (
    km_distanza integer,
    stazione_id bigint NOT NULL
);


ALTER TABLE public.aereoporti OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 24863)
-- Name: alloggi; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.alloggi (
    alloggio_id bigint NOT NULL,
    descrizione character varying(255),
    nome character varying(255),
    prezzo double precision NOT NULL,
    url_immagine character varying(255),
    meta_id bigint
);


ALTER TABLE public.alloggi OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 24862)
-- Name: alloggi_alloggio_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.alloggi_alloggio_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.alloggi_alloggio_id_seq OWNER TO postgres;

--
-- TOC entry 3756 (class 0 OID 0)
-- Dependencies: 217
-- Name: alloggi_alloggio_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.alloggi_alloggio_id_seq OWNED BY public.alloggi.alloggio_id;


--
-- TOC entry 219 (class 1259 OID 24871)
-- Name: appartamenti; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.appartamenti (
    capienza integer NOT NULL,
    alloggio_id bigint NOT NULL
);


ALTER TABLE public.appartamenti OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 24876)
-- Name: città; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."città" (
    meta_id bigint NOT NULL,
    destinazione_id bigint
);


ALTER TABLE public."città" OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 24881)
-- Name: destinazioni; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.destinazioni (
    contenuto_principale character varying(255),
    contenuto_secondario character varying(255),
    meta_id bigint NOT NULL
);


ALTER TABLE public.destinazioni OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 24888)
-- Name: hotels; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.hotels (
    numero_doppie integer,
    num_doppie_occupate integer,
    numero_singole integer,
    num_singole_occupate integer,
    stelle integer NOT NULL,
    alloggio_id bigint NOT NULL
);


ALTER TABLE public.hotels OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 24894)
-- Name: meta; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.meta (
    meta_id bigint NOT NULL,
    descrizione character varying(255),
    nome character varying(255),
    url_immagine character varying(255),
    num_hotels integer
);


ALTER TABLE public.meta OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 24893)
-- Name: meta_meta_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.meta_meta_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.meta_meta_id_seq OWNER TO postgres;

--
-- TOC entry 3757 (class 0 OID 0)
-- Dependencies: 223
-- Name: meta_meta_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.meta_meta_id_seq OWNED BY public.meta.meta_id;


--
-- TOC entry 226 (class 1259 OID 24903)
-- Name: prenotazioni; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.prenotazioni (
    prenotazione_id bigint NOT NULL,
    data date,
    numero_giorni integer,
    num_posti integer,
    prezzo real,
    alloggio_id bigint,
    meta_id bigint,
    trasporto_id bigint,
    utente_id bigint,
    ritorno_id bigint
);


ALTER TABLE public.prenotazioni OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 24902)
-- Name: prenotazioni_prenotazione_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.prenotazioni_prenotazione_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.prenotazioni_prenotazione_id_seq OWNER TO postgres;

--
-- TOC entry 3758 (class 0 OID 0)
-- Dependencies: 225
-- Name: prenotazioni_prenotazione_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.prenotazioni_prenotazione_id_seq OWNED BY public.prenotazioni.prenotazione_id;


--
-- TOC entry 228 (class 1259 OID 24910)
-- Name: ratings; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ratings (
    id bigint NOT NULL,
    rate integer NOT NULL,
    alloggio_id bigint,
    utente_id bigint
);


ALTER TABLE public.ratings OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 24909)
-- Name: ratings_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.ratings_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ratings_id_seq OWNER TO postgres;

--
-- TOC entry 3759 (class 0 OID 0)
-- Dependencies: 227
-- Name: ratings_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.ratings_id_seq OWNED BY public.ratings.id;


--
-- TOC entry 230 (class 1259 OID 24917)
-- Name: stazioni; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.stazioni (
    stazione_id bigint NOT NULL,
    "località" character varying(255),
    nome character varying(255),
    sigla character varying(255),
    "città_id" bigint
);


ALTER TABLE public.stazioni OWNER TO postgres;

--
-- TOC entry 231 (class 1259 OID 24925)
-- Name: stazioni_pulman; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.stazioni_pulman (
    num_stalli integer,
    stazione_id bigint NOT NULL
);


ALTER TABLE public.stazioni_pulman OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 24916)
-- Name: stazioni_stazione_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.stazioni_stazione_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.stazioni_stazione_id_seq OWNER TO postgres;

--
-- TOC entry 3760 (class 0 OID 0)
-- Dependencies: 229
-- Name: stazioni_stazione_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.stazioni_stazione_id_seq OWNED BY public.stazioni.stazione_id;


--
-- TOC entry 233 (class 1259 OID 24931)
-- Name: trasporti; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.trasporti (
    trasporto_id bigint NOT NULL,
    data_arrivo timestamp(6) without time zone,
    data_partenza timestamp(6) without time zone,
    descrizione character varying(255),
    durata time without time zone NOT NULL,
    nome character varying(255),
    posti_disponibili integer,
    posti_occupati integer,
    prezzo real
);


ALTER TABLE public.trasporti OWNER TO postgres;

--
-- TOC entry 232 (class 1259 OID 24930)
-- Name: trasporti_trasporto_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.trasporti_trasporto_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.trasporti_trasporto_id_seq OWNER TO postgres;

--
-- TOC entry 3761 (class 0 OID 0)
-- Dependencies: 232
-- Name: trasporti_trasporto_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.trasporti_trasporto_id_seq OWNED BY public.trasporti.trasporto_id;


--
-- TOC entry 234 (class 1259 OID 24939)
-- Name: tratte; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tratte (
    nome_azienda character varying(255),
    trasporto_id bigint NOT NULL,
    arrivo_id bigint,
    partenza_id bigint
);


ALTER TABLE public.tratte OWNER TO postgres;

--
-- TOC entry 236 (class 1259 OID 24945)
-- Name: utenti; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.utenti (
    utente_id bigint NOT NULL,
    cognome character varying(255),
    data_nascita date,
    nome character varying(255),
    password character varying(255),
    ruolo character varying(255),
    username character varying(255),
    CONSTRAINT utenti_ruolo_check CHECK (((ruolo)::text = ANY ((ARRAY['USER'::character varying, 'ADMIN'::character varying])::text[])))
);


ALTER TABLE public.utenti OWNER TO postgres;

--
-- TOC entry 235 (class 1259 OID 24944)
-- Name: utenti_utente_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.utenti_utente_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.utenti_utente_id_seq OWNER TO postgres;

--
-- TOC entry 3762 (class 0 OID 0)
-- Dependencies: 235
-- Name: utenti_utente_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.utenti_utente_id_seq OWNED BY public.utenti.utente_id;


--
-- TOC entry 237 (class 1259 OID 24954)
-- Name: voli; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.voli (
    compagnia character varying(255),
    trasporto_id bigint NOT NULL,
    arrivo_id bigint NOT NULL,
    partenza_id bigint NOT NULL,
    stop_id bigint
);


ALTER TABLE public.voli OWNER TO postgres;

--
-- TOC entry 3506 (class 2604 OID 24854)
-- Name: acquisti stazione_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.acquisti ALTER COLUMN stazione_id SET DEFAULT nextval('public.acquisti_stazione_id_seq'::regclass);


--
-- TOC entry 3507 (class 2604 OID 24866)
-- Name: alloggi alloggio_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.alloggi ALTER COLUMN alloggio_id SET DEFAULT nextval('public.alloggi_alloggio_id_seq'::regclass);


--
-- TOC entry 3508 (class 2604 OID 24897)
-- Name: meta meta_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.meta ALTER COLUMN meta_id SET DEFAULT nextval('public.meta_meta_id_seq'::regclass);


--
-- TOC entry 3509 (class 2604 OID 24906)
-- Name: prenotazioni prenotazione_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prenotazioni ALTER COLUMN prenotazione_id SET DEFAULT nextval('public.prenotazioni_prenotazione_id_seq'::regclass);


--
-- TOC entry 3510 (class 2604 OID 24913)
-- Name: ratings id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ratings ALTER COLUMN id SET DEFAULT nextval('public.ratings_id_seq'::regclass);


--
-- TOC entry 3511 (class 2604 OID 24920)
-- Name: stazioni stazione_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.stazioni ALTER COLUMN stazione_id SET DEFAULT nextval('public.stazioni_stazione_id_seq'::regclass);


--
-- TOC entry 3512 (class 2604 OID 24934)
-- Name: trasporti trasporto_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.trasporti ALTER COLUMN trasporto_id SET DEFAULT nextval('public.trasporti_trasporto_id_seq'::regclass);


--
-- TOC entry 3513 (class 2604 OID 24948)
-- Name: utenti utente_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utenti ALTER COLUMN utente_id SET DEFAULT nextval('public.utenti_utente_id_seq'::regclass);


--
-- TOC entry 3727 (class 0 OID 24851)
-- Dependencies: 215
-- Data for Name: acquisti; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.acquisti (stazione_id, data, prezzo, prenotazione_id, utente_id) FROM stdin;
1	2023-09-11	500	1	2
2	2023-09-18	750	3	2
4	2023-09-18	750	6	2
5	2023-09-20	750	4	2
6	2023-09-21	600	2	2
7	2023-09-25	750	7	2
\.


--
-- TOC entry 3728 (class 0 OID 24857)
-- Dependencies: 216
-- Data for Name: aereoporti; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.aereoporti (km_distanza, stazione_id) FROM stdin;
140	5
30	8
50	9
20	10
30	11
30	12
50	13
30	14
20	15
40	16
50	17
60	18
30	1
\.


--
-- TOC entry 3730 (class 0 OID 24863)
-- Dependencies: 218
-- Data for Name: alloggi; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.alloggi (alloggio_id, descrizione, nome, prezzo, url_immagine, meta_id) FROM stdin;
7	turismo montano	Appartamento1	40	Appartamento1.jpg	2
9	turismo montano	Appartamento2	40	Appartamento2.jpg	2
12	turismo montano x	Appartamento3	60	Appartamento3.jpg	2
14	turismo montano y	Appartamento4	60	Appartamento4.jpg	2
15	turismo montano z	Appartamento5	60	Appartamento5.jpg	2
17	turismo balneare	Hotel Aurora	150	Hotel Aurora.jpg	9
18	turismo balneare	Hotel Ventosa	250	Hotel Ventosa.jpg	14
20	turismo metropolitano	Hotel Bulgari	250	Hotel Bulgari.jpg	28
21	turismo metropolitano	Hotel Paradiso	150	Hotel Paradiso.jpg	28
22	turismo metropolitano	France resort	60	France resort.jpg	28
23	turismo metropolitano	Dautch resort	80	Dautch resort.jpg	29
24	turismo metropolitano	Hotel Leonardo	180	Hotel Leonardo.jpg	29
25	turismo metropolitano	Hotel Riu Piaza	180	Hotel Riu Piaza.jpg	29
26	turismo culturale	Toscana resort	90	Toscana resort.jpg	27
27	turismo culturale	Hotel Parking	180	Hotel Parking.jpg	27
28	turismo culturale	Hotel Bologna	180	Hotel Bologna.jpg	27
29	turismo culturale	Firenze resort	70	Firenze resort.jpg	26
30	turismo culturale	Hotel Number Nine	130	Hotel Number Nine.jpg	26
31	turismo culturale	Hotel City Center	110	Hotel City Center.jpg	26
32	turismo metropolitano	Milano resort	80	Milano resort.jpg	15
33	turismo metropolitano	Hotel Kleos	110	Hotel Kleos.jpg	15
34	turismo metropolitano	Hotel Art	90	Hotel Art.jpg	15
35	turismo metropolitano	Bologna resort	70	Bologna resort.jpg	14
36	turismo metropolitano	Hotel Aemilia	100	Hotel Aemilia.jpg	14
37	turismo metropolitano	Hotel Vlorè	70	Hotel Vlorè.jpg	14
38	turismo metropolitano	Palermo resort	50	Palermo resort.jpg	17
39	turismo balneare	Hotel Ai Cavalieri	90	Hotel Ai Cavalieri.jpg	17
40	turismo balneare	Hotel Quattro Canti	120	Hotel Quattro Canti.jpg	17
41	turismo culturale	Agrigento resort	60	Agrigento resort.jpg	18
42	turismo culturale	Hotel Tre Torri	80	Hotel Tre Torri.jpg	18
43	turismo culturale	Hotel Villa Altena	90	Hotel Villa Altena.jpg	18
44	turismo balneare	Cagliari resort	90	Cagliari resort.jpg	20
45	turismo balneare	Hotel Birkin	140	Hotel Birkin.jpg	20
46	turismo balneare	Hotel Villa Fanny	170	Hotel Villa Fanny.jpg	20
47	turismo balneare	Olbia resort	100	Olbia resort.jpg	21
48	turismo balneare	Hotel Jazz	180	Hotel Jazz.jpg	21
49	turismo balneare	Hotel Panorama	160	Hotel Panorama.jpg	21
50	turismo balneare	Lecce resort	100	Lecce resort.jpg	23
51	turismo balneare	Hotel President	140	Hotel President.jpg	23
52	turismo balneare	Hotel Aloisi	140	Hotel Aloisi.jpg	23
53	turismo metropolitano	Bari Case vacanza	110	Bari Case vacanza.jpg	24
54	turismo metropolitano	Hotel Hi	160	Hotel Hi.jpg	24
55	turismo metropolitano	Hotel BVenturo	190	Hotel BVenturo.jpg	24
2	turismo montano	La Place	50	La Place.jpg	9
19	turismo montano z	Roma case vacanza	60	Roma case vacanza.jpg	9
\.


--
-- TOC entry 3731 (class 0 OID 24871)
-- Dependencies: 219
-- Data for Name: appartamenti; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.appartamenti (capienza, alloggio_id) FROM stdin;
4	7
4	9
4	12
4	14
4	15
0	19
0	32
0	22
0	23
0	26
0	29
0	35
0	38
0	41
0	44
0	47
0	50
0	53
\.


--
-- TOC entry 3732 (class 0 OID 24876)
-- Dependencies: 220
-- Data for Name: città; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."città" (meta_id, destinazione_id) FROM stdin;
9	10
18	16
17	16
20	19
21	19
23	22
24	22
14	10
15	10
26	25
27	25
29	12
28	2
\.


--
-- TOC entry 3733 (class 0 OID 24881)
-- Dependencies: 221
-- Data for Name: destinazioni; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.destinazioni (contenuto_principale, contenuto_secondario, meta_id) FROM stdin;
prova	prova	2
prova	prova	10
prova	prova	12
Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.	Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.	16
Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.	Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.	19
Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.	Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.	22
Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.	Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.	25
\.


--
-- TOC entry 3734 (class 0 OID 24888)
-- Dependencies: 222
-- Data for Name: hotels; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.hotels (numero_doppie, num_doppie_occupate, numero_singole, num_singole_occupate, stelle, alloggio_id) FROM stdin;
50	0	25	0	5	17
50	0	25	0	5	18
30	3	20	0	4	2
30	0	25	0	5	20
30	0	25	0	4	21
30	0	25	0	3	24
30	0	25	0	3	25
30	0	25	0	2	27
30	0	25	0	4	28
30	0	25	0	4	30
30	0	25	0	4	31
30	0	25	0	3	33
30	0	25	0	2	34
30	0	25	0	2	36
30	0	25	0	2	37
30	0	25	0	3	39
30	0	25	0	4	40
30	0	25	0	3	42
30	0	25	0	4	43
30	0	25	0	4	45
30	0	25	0	5	46
30	0	25	0	5	48
30	0	25	0	4	49
30	0	25	0	3	51
30	0	25	0	2	52
30	0	25	0	3	54
30	0	25	0	5	55
\.


--
-- TOC entry 3736 (class 0 OID 24894)
-- Dependencies: 224
-- Data for Name: meta; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.meta (meta_id, descrizione, nome, url_immagine, num_hotels) FROM stdin;
9	Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.	Roma	Roma.jpg	\N
14	Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.	Bologna	Bologna.jpg	\N
15	Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.	Milano	Milano.jpg	\N
12	Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.	Germania	Germania.jpg	\N
10	Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.	Italia	Italia.jpg	\N
2	Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.	Francia	Francia.jpg	\N
16	Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.	Sicilia	Sicilia.jpg	\N
17	capoluogo siciliano	Palermo	Palermo.jpg	\N
18	città siciliana	Agrigento	Agrigento.jpg	\N
19	Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.	Sardegna	Sardegna.jpg	\N
20	capoluogo sardo	Cagliari	Cagliari.jpg	\N
21	città sarda	Olbia	Olbia.jpg	\N
22	Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.	Puglia	Puglia.jpg	\N
23	città pugliese	Lecce	Lecce.jpg	\N
24	città pugliese	Bari	Bari.jpg	\N
25	Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.	Toscana	Toscana.jpg	\N
26	città toscana	Firenze	Firenze.jpg	\N
27	città toscana	Pisa	Pisa.jpg	\N
28	capitale francese	Parigi	Parigi.jpg	\N
29	capitale tedesca	Berlino	Berlino.jpg	\N
\.


--
-- TOC entry 3738 (class 0 OID 24903)
-- Dependencies: 226
-- Data for Name: prenotazioni; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.prenotazioni (prenotazione_id, data, numero_giorni, num_posti, prezzo, alloggio_id, meta_id, trasporto_id, utente_id, ritorno_id) FROM stdin;
1	2020-04-30	10	4	500	2	9	8	2	\N
2	2020-04-30	12	2	600	2	9	8	2	\N
3	2023-09-21	5	0	750	17	9	16	2	15
4	2023-09-21	5	0	750	17	9	16	2	15
6	2023-09-21	5	0	750	17	9	16	2	15
7	2023-09-21	5	0	750	17	9	16	2	15
10	2023-09-21	5	0	750	17	9	16	2	15
11	2023-09-21	5	0	750	17	9	16	2	15
12	2023-09-21	5	0	750	17	9	16	2	15
13	2023-09-21	5	0	750	17	9	16	2	15
14	2023-09-21	5	0	750	17	9	16	2	15
15	2023-09-21	5	0	750	17	9	16	2	15
16	2023-09-21	5	0	750	17	9	16	2	15
17	2023-09-21	5	0	750	17	9	16	2	15
18	2023-09-21	5	0	750	17	9	16	2	15
20	2023-09-21	5	2	650	2	9	16	2	15
21	2023-09-21	5	40	8250	2	2	16	2	15
22	2023-09-21	5	35	7250	2	2	16	2	15
23	2023-09-21	5	2	650	2	2	16	2	15
25	2023-09-24	6	1	500	2	2	17	2	18
26	2023-09-21	5	1	750	17	9	16	2	15
27	2023-09-24	6	3	1500	17	9	17	7	18
30	2023-09-24	6	2	760	19	9	17	2	18
\.


--
-- TOC entry 3740 (class 0 OID 24910)
-- Dependencies: 228
-- Data for Name: ratings; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.ratings (id, rate, alloggio_id, utente_id) FROM stdin;
1	4	2	2
4	4	19	2
2	3	17	2
7	4	36	2
8	5	35	2
3	2	18	2
9	4	48	2
10	2	49	2
11	4	32	2
\.


--
-- TOC entry 3742 (class 0 OID 24917)
-- Dependencies: 230
-- Data for Name: stazioni; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.stazioni (stazione_id, "località", nome, sigla, "città_id") FROM stdin;
6	Roma	Tiburtinaz	TBRMz	\N
5	Roma	Fiumicino	FMCRM	9
2	Roma	Tiburtina-y	TBRMy	9
7	Bologna	Bologna centrale	CTRBL	14
8	Bologna	G. Marconi	BLQ	14
9	Firenze	Firenze-Peretola	FLR	26
10	Pisa	Pisa-San Giusto	PSA	27
11	Palermo	Falcone e Borsellino	PMO	17
12	Catania	Catania Fontanarossa	CTA	18
13	Cagliari	Cagliari Elmas	CAG	20
14	Olbia	Olbia Costa Smeralda	OLB	21
15	Lecce	Lecce-San Cataldo	LCC	23
16	Bari	Carol Wojtila	BRI	24
17	Parigi	Charles De Gaulle	CDG	28
18	Berlino	Brandemburg	BER	29
1	Milano	Linate	LIN	15
\.


--
-- TOC entry 3743 (class 0 OID 24925)
-- Dependencies: 231
-- Data for Name: stazioni_pulman; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.stazioni_pulman (num_stalli, stazione_id) FROM stdin;
18	2
8	6
8	7
\.


--
-- TOC entry 3745 (class 0 OID 24931)
-- Dependencies: 233
-- Data for Name: trasporti; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.trasporti (trasporto_id, data_arrivo, data_partenza, descrizione, durata, nome, posti_disponibili, posti_occupati, prezzo) FROM stdin;
1	2020-04-16 00:00:00	2020-04-16 00:00:00	volo Milano Roma	12:15:00	Linate-Ciampino	80	0	0
5	2020-04-16 00:00:00	2020-04-16 00:00:00	volo Milano Roma	12:15:00	Linate-Ciampino	80	0	0
6	2020-10-16 00:00:00	2020-10-16 00:00:00	volo Milano Roma	12:15:00	Linate-Fiumicino	80	0	100
7	2020-05-17 00:00:00	2020-05-16 00:00:00	pulman Bologna Roma	06:15:00	Turismo Bologna	60	0	0
8	2020-08-17 00:00:00	2020-08-16 00:00:00	pulman Bologna Roma	06:15:00	Turismo Bologna2	60	0	0
9	2020-08-17 00:00:00	2020-08-16 00:00:00	pulman Bologna Roma	06:15:00	Turismo Bologna3	60	0	60
10	2023-09-16 02:30:00	2023-09-16 00:00:00	volo Milano Roma	02:30:00	Linate-Fiumicino	80	0	100
11	2023-09-24 02:30:00	2023-09-24 00:00:00	volo Roma Milano	02:30:00	Linate-Fiumicino	80	0	100
12	2023-09-20 02:30:00	2023-09-20 00:00:00	volo Roma Milano	02:30:00	Linate-Fiumicino	80	0	100
13	2023-09-25 02:30:00	2023-09-25 00:00:00	volo Roma Milano	02:30:00	Fiumicino-Linate	80	0	100
14	2023-09-26 02:30:00	2023-09-26 00:00:00	volo Roma Milano	02:30:00	Fiumicino-Linate	80	0	100
17	2023-09-24 02:30:00	2023-09-24 00:00:00	volo Roma Milano	02:30:00	Linate-Fiumicino	80	0	100
18	2023-09-30 02:30:00	2023-09-30 00:00:00	volo Roma Milano	02:30:00	Fiumicino-Linate	80	0	100
15	2023-09-26 02:30:00	2023-09-26 00:00:00	volo Roma Milano	02:30:00	Fiumicino-Linate	90	0	0
16	2023-09-21 02:30:00	2023-09-21 00:00:00	volo Roma Milano	02:30:00	Linate-Fiumicino	90	0	0
19	2023-10-10 02:30:00	2023-10-10 00:00:00	volo Roma Milano	02:30:00	Fiumicino-Linate	80	0	100
20	2023-10-02 02:00:00	2023-10-02 00:00:00	volo Roma Milano	02:00:00	Linate-Fiumicino	90	0	0
21	2023-10-20 02:30:00	2023-10-20 00:00:00	volo Roma Bari	02:30:00	Fiumicino-Wojtila	80	0	100
22	2023-10-30 02:30:00	2023-10-30 00:00:00	volo Roma Bari	02:30:00	Fiumicino-Wojtila	80	0	100
23	2023-10-26 02:30:00	2023-10-26 00:00:00	volo Roma Bari	02:30:00	Wojtila-Fiumicino	80	0	100
24	2023-11-02 02:30:00	2023-11-02 00:00:00	volo Roma Bari	02:30:00	Wojtila-Fiumicino	80	0	100
25	2023-10-20 02:30:00	2023-10-20 00:00:00	volo Roma Lecce	02:30:00	Fiumicino-San Cataldo	80	0	100
26	2023-10-30 02:30:00	2023-10-30 00:00:00	volo Roma Lecce	02:30:00	Fiumicino-San Cataldo	80	0	100
27	2023-10-26 02:30:00	2023-10-26 00:00:00	volo Roma Lecce	02:30:00	San Cataldo-Fiumicino	80	0	100
28	2023-11-03 02:30:00	2023-11-03 00:00:00	volo Roma Lecce	02:30:00	San Cataldo-Fiumicino	80	0	100
29	2023-10-20 02:30:00	2023-10-20 00:00:00	volo Roma Berlino	02:30:00	Fiumicino-Brandeburg	80	0	100
30	2023-10-30 02:30:00	2023-10-30 00:00:00	volo Roma Berlino	02:30:00	Fiumicino-Brandeburg	80	0	100
31	2023-10-26 02:30:00	2023-10-26 00:00:00	volo Roma Berlino	02:30:00	Brandeburg-Fiumicino	80	0	100
32	2023-11-03 02:30:00	2023-11-03 00:00:00	volo Roma Berlino	02:30:00	Brandeburg-Fiumicino	80	0	100
33	2023-10-20 02:30:00	2023-10-20 00:00:00	volo Roma Parigi	02:30:00	Fiumicino-Charles De Gaulle	80	0	100
34	2023-10-30 02:30:00	2023-10-30 00:00:00	volo Roma Parigi	02:30:00	Fiumicino-Charles De Gaulle	80	0	100
35	2023-10-26 02:30:00	2023-10-26 00:00:00	volo Roma Parigi	02:30:00	Charles De Gaulle-Fiumicino	80	0	100
36	2023-11-03 02:30:00	2023-11-03 00:00:00	volo Roma Parigi	02:30:00	Charles De Gaulle-Fiumicino	80	0	100
37	2023-10-20 02:30:00	2023-10-20 00:00:00	volo Roma Olbia	02:30:00	Fiumicino-Olbia C.S.	80	0	100
38	2023-10-30 02:30:00	2023-10-30 00:00:00	volo Roma Olbia	02:30:00	Fiumicino-Olbia C.S.	80	0	100
39	2023-10-26 02:30:00	2023-10-26 00:00:00	volo Roma Olbia	02:30:00	Olbia C.S.-Fiumicino	80	0	100
40	2023-11-03 02:30:00	2023-11-03 00:00:00	volo Roma Olbia	02:30:00	Olbia C.S.-Fiumicino	80	0	100
41	2023-10-20 02:30:00	2023-10-20 00:00:00	volo Roma Cagliari	02:30:00	Fiumicino-Elmas	80	0	100
42	2023-10-30 02:30:00	2023-10-30 00:00:00	volo Roma Cagliari	02:30:00	Fiumicino-Elmas	80	0	100
43	2023-10-26 02:30:00	2023-10-26 00:00:00	volo Roma Cagliari	02:30:00	Elmas-Fiumicino	80	0	100
44	2023-11-03 02:30:00	2023-11-03 00:00:00	volo Roma Cagliari	02:30:00	Elmas-Fiumicino	80	0	100
45	2023-10-20 02:30:00	2023-10-20 00:00:00	volo Roma Catania	02:30:00	Fiumicino-Fontanarossa	80	0	100
46	2023-10-30 02:30:00	2023-10-30 00:00:00	volo Roma Catania	02:30:00	Fiumicino-Fontanarossa	80	0	100
47	2023-10-26 02:30:00	2023-10-26 00:00:00	volo Roma Catania	02:30:00	Fontanarossa-Fiumicino	80	0	100
48	2023-11-03 02:30:00	2023-11-03 00:00:00	volo Roma Catania	02:30:00	Fontanarossa-Fiumicino	80	0	100
49	2023-10-20 02:30:00	2023-10-20 00:00:00	volo Roma Palermo	02:30:00	Fiumicino-Falcone e Borsellino	80	0	100
50	2023-10-30 02:30:00	2023-10-30 00:00:00	volo Roma Palermo	02:30:00	Fiumicino-Falcone e Borsellino	80	0	100
51	2023-10-26 02:30:00	2023-10-26 00:00:00	volo Roma Palermo	02:30:00	Falcone e Borsellino-Fiumicino	80	0	100
52	2023-11-03 02:30:00	2023-11-03 00:00:00	volo Roma Palermo	02:30:00	Falcone e Borsellino-Fiumicino	80	0	100
53	2023-10-20 02:30:00	2023-10-20 00:00:00	volo Roma Pisa	02:30:00	Fiumicino-San Giusto	80	0	100
54	2023-10-30 02:30:00	2023-10-30 00:00:00	volo Roma Pisa	02:30:00	Fiumicino-San Giusto	80	0	100
55	2023-10-26 02:30:00	2023-10-26 00:00:00	volo Roma Pisa	02:30:00	San Giusto-Fiumicino	80	0	100
56	2023-11-03 02:30:00	2023-11-03 00:00:00	volo Roma Pisa	02:30:00	San Giusto-Fiumicino	80	0	100
57	2023-10-20 02:30:00	2023-10-20 00:00:00	volo Roma Firenze	02:30:00	Fiumicino-Peretola	80	0	100
58	2023-10-30 02:30:00	2023-10-30 00:00:00	volo Roma Firenze	02:30:00	Fiumicino-Peretola	80	0	100
59	2023-10-26 02:30:00	2023-10-26 00:00:00	volo Roma Firenze	02:30:00	Peretola-Fiumicino	80	0	100
60	2023-11-03 02:30:00	2023-11-03 00:00:00	volo Roma Firenze	02:30:00	Peretola-Fiumicino	80	0	100
61	2023-10-20 02:30:00	2023-10-20 00:00:00	volo Roma Bologna	02:30:00	Fiumicino-G. Marconi	80	0	100
62	2023-10-30 02:30:00	2023-10-30 00:00:00	volo Roma Bologna	02:30:00	Fiumicino-G. Marconi	80	0	100
63	2023-10-26 02:30:00	2023-10-26 00:00:00	volo Roma Bologna	02:30:00	G. Marconi-Fiumicino	80	0	100
64	2023-11-03 02:30:00	2023-11-03 00:00:00	volo Roma Bologna	02:30:00	G. Marconi-Fiumicino	80	0	100
\.


--
-- TOC entry 3746 (class 0 OID 24939)
-- Dependencies: 234
-- Data for Name: tratte; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tratte (nome_azienda, trasporto_id, arrivo_id, partenza_id) FROM stdin;
gp turismo	7	7	2
gp turismo	8	7	2
gp turismo	9	7	2
\.


--
-- TOC entry 3748 (class 0 OID 24945)
-- Dependencies: 236
-- Data for Name: utenti; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.utenti (utente_id, cognome, data_nascita, nome, password, ruolo, username) FROM stdin;
1	Admin	1993-09-07	Super	$2a$11$mdRIUKYkVVXHAQNNsLiD0egY7A0x5Q/mKCfkmF3iYbIEVBGcFNdz2	ADMIN	superadmin
2	Admin	1993-09-07	Super	$2a$11$Tl9WNCL0Hb2X9SccfPQtm.dhEXtAhfhdDAk96HpnEpkkRFpDRg6UG	ADMIN	super.admin@test.com
6	Di Nicola	1988-04-25	Marco	$2a$11$34QoInsXMmpMp2JDERjnKuQtB7JlBNF1tNWMULyfl9F.6MUk3jvBq	USER	marco.dinicola@yahoo.it
7	De Luca	\N	Paolo	$2a$11$103yg06eVMTYavbj0h3E8e8iSjB0NpVkqKYvReGY9BWBV.HQ9gdui	USER	paolo.deluca@yahoo.it
\.


--
-- TOC entry 3749 (class 0 OID 24954)
-- Dependencies: 237
-- Data for Name: voli; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.voli (compagnia, trasporto_id, arrivo_id, partenza_id, stop_id) FROM stdin;
air france	1	5	1	\N
air france	5	5	1	\N
air france	6	5	1	\N
air france	10	5	1	\N
air france	11	1	5	\N
air france	12	1	5	\N
air france	13	5	1	\N
air france	14	5	1	\N
air france	15	1	5	\N
air france	16	5	1	\N
air france	17	5	1	\N
air france	18	1	5	\N
air france	19	1	5	\N
air france	20	5	1	\N
alitalia	21	16	5	\N
alitalia	22	16	5	\N
alitalia	23	5	16	\N
alitalia	24	5	16	\N
alitalia	25	15	5	\N
alitalia	26	15	5	\N
alitalia	27	5	15	\N
alitalia	28	5	15	\N
alitalia	29	18	5	\N
alitalia	30	18	5	\N
alitalia	31	5	18	\N
alitalia	32	5	18	\N
alitalia	33	17	5	\N
alitalia	34	17	5	\N
alitalia	35	5	17	\N
alitalia	36	5	17	\N
alitalia	37	14	5	\N
alitalia	38	14	5	\N
alitalia	39	5	14	\N
alitalia	40	5	14	\N
alitalia	41	13	5	\N
alitalia	42	13	5	\N
alitalia	43	5	13	\N
alitalia	44	5	13	\N
alitalia	45	12	5	\N
alitalia	46	12	5	\N
alitalia	47	5	12	\N
alitalia	48	5	12	\N
alitalia	49	11	5	\N
alitalia	50	11	5	\N
alitalia	51	5	11	\N
alitalia	52	5	11	\N
alitalia	53	10	5	\N
alitalia	54	10	5	\N
alitalia	55	5	10	\N
alitalia	56	5	10	\N
alitalia	57	9	5	\N
alitalia	58	9	5	\N
alitalia	59	5	9	\N
alitalia	60	5	9	\N
alitalia	61	8	5	\N
alitalia	62	8	5	\N
alitalia	63	5	8	\N
alitalia	64	5	8	\N
\.


--
-- TOC entry 3763 (class 0 OID 0)
-- Dependencies: 214
-- Name: acquisti_stazione_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.acquisti_stazione_id_seq', 7, true);


--
-- TOC entry 3764 (class 0 OID 0)
-- Dependencies: 217
-- Name: alloggi_alloggio_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.alloggi_alloggio_id_seq', 55, true);


--
-- TOC entry 3765 (class 0 OID 0)
-- Dependencies: 223
-- Name: meta_meta_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.meta_meta_id_seq', 29, true);


--
-- TOC entry 3766 (class 0 OID 0)
-- Dependencies: 225
-- Name: prenotazioni_prenotazione_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.prenotazioni_prenotazione_id_seq', 30, true);


--
-- TOC entry 3767 (class 0 OID 0)
-- Dependencies: 227
-- Name: ratings_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ratings_id_seq', 11, true);


--
-- TOC entry 3768 (class 0 OID 0)
-- Dependencies: 229
-- Name: stazioni_stazione_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.stazioni_stazione_id_seq', 18, true);


--
-- TOC entry 3769 (class 0 OID 0)
-- Dependencies: 232
-- Name: trasporti_trasporto_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.trasporti_trasporto_id_seq', 64, true);


--
-- TOC entry 3770 (class 0 OID 0)
-- Dependencies: 235
-- Name: utenti_utente_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.utenti_utente_id_seq', 7, true);


--
-- TOC entry 3516 (class 2606 OID 24856)
-- Name: acquisti acquisti_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.acquisti
    ADD CONSTRAINT acquisti_pkey PRIMARY KEY (stazione_id);


--
-- TOC entry 3522 (class 2606 OID 24861)
-- Name: aereoporti aereoporti_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.aereoporti
    ADD CONSTRAINT aereoporti_pkey PRIMARY KEY (stazione_id);


--
-- TOC entry 3524 (class 2606 OID 24870)
-- Name: alloggi alloggi_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.alloggi
    ADD CONSTRAINT alloggi_pkey PRIMARY KEY (alloggio_id);


--
-- TOC entry 3526 (class 2606 OID 24875)
-- Name: appartamenti appartamenti_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.appartamenti
    ADD CONSTRAINT appartamenti_pkey PRIMARY KEY (alloggio_id);


--
-- TOC entry 3528 (class 2606 OID 24880)
-- Name: città città_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."città"
    ADD CONSTRAINT "città_pkey" PRIMARY KEY (meta_id);


--
-- TOC entry 3530 (class 2606 OID 24887)
-- Name: destinazioni destinazioni_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.destinazioni
    ADD CONSTRAINT destinazioni_pkey PRIMARY KEY (meta_id);


--
-- TOC entry 3532 (class 2606 OID 24892)
-- Name: hotels hotels_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.hotels
    ADD CONSTRAINT hotels_pkey PRIMARY KEY (alloggio_id);


--
-- TOC entry 3534 (class 2606 OID 24901)
-- Name: meta meta_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.meta
    ADD CONSTRAINT meta_pkey PRIMARY KEY (meta_id);


--
-- TOC entry 3540 (class 2606 OID 24908)
-- Name: prenotazioni prenotazioni_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prenotazioni
    ADD CONSTRAINT prenotazioni_pkey PRIMARY KEY (prenotazione_id);


--
-- TOC entry 3542 (class 2606 OID 24915)
-- Name: ratings ratings_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ratings
    ADD CONSTRAINT ratings_pkey PRIMARY KEY (id);


--
-- TOC entry 3546 (class 2606 OID 24924)
-- Name: stazioni stazioni_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.stazioni
    ADD CONSTRAINT stazioni_pkey PRIMARY KEY (stazione_id);


--
-- TOC entry 3548 (class 2606 OID 24929)
-- Name: stazioni_pulman stazioni_pulman_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.stazioni_pulman
    ADD CONSTRAINT stazioni_pulman_pkey PRIMARY KEY (stazione_id);


--
-- TOC entry 3550 (class 2606 OID 24938)
-- Name: trasporti trasporti_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.trasporti
    ADD CONSTRAINT trasporti_pkey PRIMARY KEY (trasporto_id);


--
-- TOC entry 3552 (class 2606 OID 24943)
-- Name: tratte tratte_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tratte
    ADD CONSTRAINT tratte_pkey PRIMARY KEY (trasporto_id);


--
-- TOC entry 3518 (class 2606 OID 24962)
-- Name: acquisti uk_h1ducc9ta42ci3nb7hqh8fb2j; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.acquisti
    ADD CONSTRAINT uk_h1ducc9ta42ci3nb7hqh8fb2j UNIQUE (prenotazione_id);


--
-- TOC entry 3536 (class 2606 OID 24964)
-- Name: meta uk_nbvcapk9uu3j8b59tor0pvsh2; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.meta
    ADD CONSTRAINT uk_nbvcapk9uu3j8b59tor0pvsh2 UNIQUE (nome);


--
-- TOC entry 3554 (class 2606 OID 24968)
-- Name: utenti uk_tn8mwk6h2wn28yyj7fco65gls; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utenti
    ADD CONSTRAINT uk_tn8mwk6h2wn28yyj7fco65gls UNIQUE (username);


--
-- TOC entry 3520 (class 2606 OID 24960)
-- Name: acquisti uke0e9b8fpoaaqnpob2r8t6bny6; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.acquisti
    ADD CONSTRAINT uke0e9b8fpoaaqnpob2r8t6bny6 UNIQUE (prenotazione_id, utente_id);


--
-- TOC entry 3538 (class 2606 OID 25094)
-- Name: meta ukfdqcsfv60qn29cmu2cac8bgqr; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.meta
    ADD CONSTRAINT ukfdqcsfv60qn29cmu2cac8bgqr UNIQUE (nome, url_immagine);


--
-- TOC entry 3544 (class 2606 OID 25122)
-- Name: ratings ukj1npmsiwskfxxcdqspcvdeq4c; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ratings
    ADD CONSTRAINT ukj1npmsiwskfxxcdqspcvdeq4c UNIQUE (utente_id, alloggio_id);


--
-- TOC entry 3556 (class 2606 OID 24953)
-- Name: utenti utenti_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utenti
    ADD CONSTRAINT utenti_pkey PRIMARY KEY (utente_id);


--
-- TOC entry 3558 (class 2606 OID 24958)
-- Name: voli voli_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.voli
    ADD CONSTRAINT voli_pkey PRIMARY KEY (trasporto_id);


--
-- TOC entry 3559 (class 2606 OID 24971)
-- Name: acquisti fk42m4qcnsdb86vf7w6qjaeyvtd; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.acquisti
    ADD CONSTRAINT fk42m4qcnsdb86vf7w6qjaeyvtd FOREIGN KEY (prenotazione_id) REFERENCES public.prenotazioni(prenotazione_id);


--
-- TOC entry 3576 (class 2606 OID 25051)
-- Name: stazioni_pulman fk451dq8776yf3wr5itdr09ckf2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.stazioni_pulman
    ADD CONSTRAINT fk451dq8776yf3wr5itdr09ckf2 FOREIGN KEY (stazione_id) REFERENCES public.stazioni(stazione_id);


--
-- TOC entry 3580 (class 2606 OID 25071)
-- Name: voli fk5nt88rwe9qbi7n4rk6ndnu8px; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.voli
    ADD CONSTRAINT fk5nt88rwe9qbi7n4rk6ndnu8px FOREIGN KEY (arrivo_id) REFERENCES public.aereoporti(stazione_id);


--
-- TOC entry 3573 (class 2606 OID 25036)
-- Name: ratings fk6l4xf8y5perb98i79uubusp0a; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ratings
    ADD CONSTRAINT fk6l4xf8y5perb98i79uubusp0a FOREIGN KEY (alloggio_id) REFERENCES public.alloggi(alloggio_id);


--
-- TOC entry 3581 (class 2606 OID 25081)
-- Name: voli fk7ey0983dwbgutr2ptoiccnvnm; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.voli
    ADD CONSTRAINT fk7ey0983dwbgutr2ptoiccnvnm FOREIGN KEY (stop_id) REFERENCES public.aereoporti(stazione_id);


--
-- TOC entry 3568 (class 2606 OID 25031)
-- Name: prenotazioni fk8kg9d6tr3jt4dmyxxwamn41r9; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prenotazioni
    ADD CONSTRAINT fk8kg9d6tr3jt4dmyxxwamn41r9 FOREIGN KEY (utente_id) REFERENCES public.utenti(utente_id);


--
-- TOC entry 3569 (class 2606 OID 25021)
-- Name: prenotazioni fk98o3xbq8m8qj9glnaj5ubqa9a; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prenotazioni
    ADD CONSTRAINT fk98o3xbq8m8qj9glnaj5ubqa9a FOREIGN KEY (meta_id) REFERENCES public.meta(meta_id);


--
-- TOC entry 3570 (class 2606 OID 25097)
-- Name: prenotazioni fk9s04q9bkwogrn0xb7j60reuo1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prenotazioni
    ADD CONSTRAINT fk9s04q9bkwogrn0xb7j60reuo1 FOREIGN KEY (ritorno_id) REFERENCES public.trasporti(trasporto_id);


--
-- TOC entry 3574 (class 2606 OID 25041)
-- Name: ratings fkdh5es0low7gmwc0scurlpy1vh; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ratings
    ADD CONSTRAINT fkdh5es0low7gmwc0scurlpy1vh FOREIGN KEY (utente_id) REFERENCES public.utenti(utente_id);


--
-- TOC entry 3566 (class 2606 OID 25006)
-- Name: destinazioni fkdm3y7p6mm3ct5w0eo0ebavwp5; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.destinazioni
    ADD CONSTRAINT fkdm3y7p6mm3ct5w0eo0ebavwp5 FOREIGN KEY (meta_id) REFERENCES public.meta(meta_id);


--
-- TOC entry 3577 (class 2606 OID 25061)
-- Name: tratte fkffcm5nwcn1bruvqi796c2a9fl; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tratte
    ADD CONSTRAINT fkffcm5nwcn1bruvqi796c2a9fl FOREIGN KEY (partenza_id) REFERENCES public.stazioni_pulman(stazione_id);


--
-- TOC entry 3564 (class 2606 OID 25001)
-- Name: città fkfg953vd3s41nrj4h1reo52djn; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."città"
    ADD CONSTRAINT fkfg953vd3s41nrj4h1reo52djn FOREIGN KEY (meta_id) REFERENCES public.meta(meta_id);


--
-- TOC entry 3578 (class 2606 OID 25056)
-- Name: tratte fkgso0rt1pbr5g0qmx19s9eqte; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tratte
    ADD CONSTRAINT fkgso0rt1pbr5g0qmx19s9eqte FOREIGN KEY (arrivo_id) REFERENCES public.stazioni_pulman(stazione_id);


--
-- TOC entry 3582 (class 2606 OID 25076)
-- Name: voli fkh46esdpbb1rthdw3of18b09qv; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.voli
    ADD CONSTRAINT fkh46esdpbb1rthdw3of18b09qv FOREIGN KEY (partenza_id) REFERENCES public.aereoporti(stazione_id);


--
-- TOC entry 3567 (class 2606 OID 25011)
-- Name: hotels fkigc54v3oh4afrtovfnd2i1hny; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.hotels
    ADD CONSTRAINT fkigc54v3oh4afrtovfnd2i1hny FOREIGN KEY (alloggio_id) REFERENCES public.alloggi(alloggio_id);


--
-- TOC entry 3583 (class 2606 OID 25086)
-- Name: voli fkkjambe9tieyjmcng3sirkk8kh; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.voli
    ADD CONSTRAINT fkkjambe9tieyjmcng3sirkk8kh FOREIGN KEY (trasporto_id) REFERENCES public.trasporti(trasporto_id);


--
-- TOC entry 3575 (class 2606 OID 25046)
-- Name: stazioni fkknyu925a1q1brulu9w44ts1fd; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.stazioni
    ADD CONSTRAINT fkknyu925a1q1brulu9w44ts1fd FOREIGN KEY ("città_id") REFERENCES public."città"(meta_id);


--
-- TOC entry 3561 (class 2606 OID 24981)
-- Name: aereoporti fkl8ngoognfpghp3qvqgml4buf9; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.aereoporti
    ADD CONSTRAINT fkl8ngoognfpghp3qvqgml4buf9 FOREIGN KEY (stazione_id) REFERENCES public.stazioni(stazione_id);


--
-- TOC entry 3563 (class 2606 OID 24991)
-- Name: appartamenti fkllmtdcduepx3h5om2ydw6w7bf; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.appartamenti
    ADD CONSTRAINT fkllmtdcduepx3h5om2ydw6w7bf FOREIGN KEY (alloggio_id) REFERENCES public.alloggi(alloggio_id);


--
-- TOC entry 3579 (class 2606 OID 25066)
-- Name: tratte fkofe76ioi1uirjr6a7hu43xvgi; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tratte
    ADD CONSTRAINT fkofe76ioi1uirjr6a7hu43xvgi FOREIGN KEY (trasporto_id) REFERENCES public.trasporti(trasporto_id);


--
-- TOC entry 3562 (class 2606 OID 24986)
-- Name: alloggi fkpmanib9apfo58v660jcpv6xbj; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.alloggi
    ADD CONSTRAINT fkpmanib9apfo58v660jcpv6xbj FOREIGN KEY (meta_id) REFERENCES public.meta(meta_id);


--
-- TOC entry 3571 (class 2606 OID 25016)
-- Name: prenotazioni fkqd0sc2it71wh56lqt4dr0ssqf; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prenotazioni
    ADD CONSTRAINT fkqd0sc2it71wh56lqt4dr0ssqf FOREIGN KEY (alloggio_id) REFERENCES public.alloggi(alloggio_id);


--
-- TOC entry 3572 (class 2606 OID 25026)
-- Name: prenotazioni fkqk0f2gkdyy3h64p6fcifhinjy; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prenotazioni
    ADD CONSTRAINT fkqk0f2gkdyy3h64p6fcifhinjy FOREIGN KEY (trasporto_id) REFERENCES public.trasporti(trasporto_id);


--
-- TOC entry 3565 (class 2606 OID 24996)
-- Name: città fkro5n9uyv7x08agxqmmwby25t4; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."città"
    ADD CONSTRAINT fkro5n9uyv7x08agxqmmwby25t4 FOREIGN KEY (destinazione_id) REFERENCES public.destinazioni(meta_id);


--
-- TOC entry 3560 (class 2606 OID 24976)
-- Name: acquisti fksqumrbmcky50sa5r8ru85amrp; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.acquisti
    ADD CONSTRAINT fksqumrbmcky50sa5r8ru85amrp FOREIGN KEY (utente_id) REFERENCES public.utenti(utente_id);


-- Completed on 2023-09-27 10:21:44 CEST

--
-- PostgreSQL database dump complete
--

