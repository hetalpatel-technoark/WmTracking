PGDMP     '    :            
    w            wm_trucking    9.4.6    11.2     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false            �           1262    676151    wm_trucking    DATABASE     �   CREATE DATABASE wm_trucking WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'English_United States.1252' LC_CTYPE = 'English_United States.1252';
    DROP DATABASE wm_trucking;
             postgres    false            �           0    0    SCHEMA public    ACL     �   REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;
                  postgres    false    6            �            1259    676155    ma_authobject    TABLE     �   CREATE TABLE public.ma_authobject (
    authid bigint NOT NULL,
    name character varying,
    email character varying,
    password character varying
);
 !   DROP TABLE public.ma_authobject;
       public         postgres    false            �            1259    676199    ma_authobject_seq    SEQUENCE     z   CREATE SEQUENCE public.ma_authobject_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.ma_authobject_seq;
       public       postgres    false            �            1259    676163    ma_customer    TABLE     �  CREATE TABLE public.ma_customer (
    id bigint NOT NULL,
    companyname character varying,
    firstname character varying,
    middlename character varying,
    lastname character varying,
    address1 character varying,
    address2 character varying,
    address3 character varying,
    city character varying,
    state character varying,
    country character varying,
    pincode character varying,
    phone character varying,
    email character varying,
    status character varying
);
    DROP TABLE public.ma_customer;
       public         postgres    false            �            1259    676201    ma_customer_seq    SEQUENCE     x   CREATE SEQUENCE public.ma_customer_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.ma_customer_seq;
       public       postgres    false            �            1259    676171 	   ma_driver    TABLE     �  CREATE TABLE public.ma_driver (
    licensenumber character varying,
    firstname character varying,
    middlename character varying,
    lastname character varying,
    address1 character varying,
    address2 character varying,
    address3 character varying,
    city character varying,
    state character varying,
    country character varying,
    pincode character varying,
    mobile character varying,
    email character varying,
    id bigint NOT NULL,
    status character varying
);
    DROP TABLE public.ma_driver;
       public         postgres    false            �            1259    676203    ma_driver_seq    SEQUENCE     v   CREATE SEQUENCE public.ma_driver_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.ma_driver_seq;
       public       postgres    false            �            1259    676179    ma_jobs    TABLE     t  CREATE TABLE public.ma_jobs (
    id bigint NOT NULL,
    cust_id bigint,
    driver_id bigint,
    jobnumber character varying,
    jobdate timestamp without time zone,
    notes character varying(4000),
    status character varying,
    hauloff character varying,
    haulback character varying,
    selectfill character varying(255),
    createddate timestamp without time zone,
    other character varying,
    address1 character varying,
    address2 character varying,
    address3 character varying,
    city character varying,
    state character varying,
    country character varying,
    pincode character varying
);
    DROP TABLE public.ma_jobs;
       public         postgres    false            �            1259    676205    ma_jobs_seq    SEQUENCE     t   CREATE SEQUENCE public.ma_jobs_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.ma_jobs_seq;
       public       postgres    false            �          0    676155    ma_authobject 
   TABLE DATA               F   COPY public.ma_authobject (authid, name, email, password) FROM stdin;
    public       postgres    false    173   �#       �          0    676163    ma_customer 
   TABLE DATA               �   COPY public.ma_customer (id, companyname, firstname, middlename, lastname, address1, address2, address3, city, state, country, pincode, phone, email, status) FROM stdin;
    public       postgres    false    174   �#       �          0    676171 	   ma_driver 
   TABLE DATA               �   COPY public.ma_driver (licensenumber, firstname, middlename, lastname, address1, address2, address3, city, state, country, pincode, mobile, email, id, status) FROM stdin;
    public       postgres    false    175   %       �          0    676179    ma_jobs 
   TABLE DATA               �   COPY public.ma_jobs (id, cust_id, driver_id, jobnumber, jobdate, notes, status, hauloff, haulback, selectfill, createddate, other, address1, address2, address3, city, state, country, pincode) FROM stdin;
    public       postgres    false    176   J&       �           0    0    ma_authobject_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.ma_authobject_seq', 1, false);
            public       postgres    false    177            �           0    0    ma_customer_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.ma_customer_seq', 6, true);
            public       postgres    false    178            �           0    0    ma_driver_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.ma_driver_seq', 8, true);
            public       postgres    false    179            �           0    0    ma_jobs_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.ma_jobs_seq', 15, true);
            public       postgres    false    180            p           2606    676162     ma_authobject ma_authobject_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.ma_authobject
    ADD CONSTRAINT ma_authobject_pkey PRIMARY KEY (authid);
 J   ALTER TABLE ONLY public.ma_authobject DROP CONSTRAINT ma_authobject_pkey;
       public         postgres    false    173            r           2606    676170    ma_customer ma_customer_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.ma_customer
    ADD CONSTRAINT ma_customer_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.ma_customer DROP CONSTRAINT ma_customer_pkey;
       public         postgres    false    174            t           2606    676178    ma_driver ma_driver_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.ma_driver
    ADD CONSTRAINT ma_driver_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.ma_driver DROP CONSTRAINT ma_driver_pkey;
       public         postgres    false    175            x           2606    676186    ma_jobs ma_jobs_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.ma_jobs
    ADD CONSTRAINT ma_jobs_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.ma_jobs DROP CONSTRAINT ma_jobs_pkey;
       public         postgres    false    176            u           1259    676192    fki_customerid_fk    INDEX     H   CREATE INDEX fki_customerid_fk ON public.ma_jobs USING btree (cust_id);
 %   DROP INDEX public.fki_customerid_fk;
       public         postgres    false    176            v           1259    676198    fki_driverid_fk    INDEX     H   CREATE INDEX fki_driverid_fk ON public.ma_jobs USING btree (driver_id);
 #   DROP INDEX public.fki_driverid_fk;
       public         postgres    false    176            y           2606    676187    ma_jobs customerid_fk    FK CONSTRAINT     z   ALTER TABLE ONLY public.ma_jobs
    ADD CONSTRAINT customerid_fk FOREIGN KEY (cust_id) REFERENCES public.ma_customer(id);
 ?   ALTER TABLE ONLY public.ma_jobs DROP CONSTRAINT customerid_fk;
       public       postgres    false    1906    176    174            z           2606    676193    ma_jobs driverid_fk    FK CONSTRAINT     x   ALTER TABLE ONLY public.ma_jobs
    ADD CONSTRAINT driverid_fk FOREIGN KEY (driver_id) REFERENCES public.ma_driver(id);
 =   ALTER TABLE ONLY public.ma_jobs DROP CONSTRAINT driverid_fk;
       public       postgres    false    175    176    1908            �       x�3�tI���L B/9?������ �A	       �   #  x�eQ�N�0<O����I�����P!7.K��vbGJ�Z�`�]�Z���u�^KXo�mme�O�ӡ��y��U�Ի��N��d�%H_�Ii�>1;4�"/�pbv�6�h��Rϊ�b���י/��\�M�?]n� ��Q	4��F#�Fq���*M�1��V-��T� �S̎�&�L�T���G�z�H�ّ:�<a%qz�7�ۗ�ꩪ�l��&����aFI�?3�������w���[c~�e~yв;i�I�*+�躷��.��oZ���|!���}�DQ�'��      �   #  x�MP�n�0<o��@�#���8���7.K�8�Ӫ_;�+�X{gw�a��p�CF��������GuQQ����}�PBgM9�6Z�#���6�<hژj��K`S�1+��-WrpQ�������S}TaA�
��ֺ���L���d�C��X�O�ɕ���ڼ7�4~�Z�6�nj�6U~���g��3vn]W[��]�	����<��6�ے|
�JYf��-���
\�*A�R�2�O����vf�k�%��ɫ�lo�K=�'����!��K�EQ��M�      �   n  x��S�n�0<���H�1MzK���D*9��0I�"�R��k7������X`���쬄�]*���[�ʮ?�������`tg@pG�?��k�}�0�2�$(�FD�C�'�\V��	 �D!�D�b��p����^�9$�b����]R�R��Y�A�Y$�$�24M���"I��^�P�@Ҭ(�FX�ƿ��!:k((;��B��tD�=���+k�m�w�-�`���q�3�/m�o�T�1�s�G�{����₱�)K�k4_,��ɦ�Vj�L���}�<��f�I��:��rHs�N�!��q�0��̊�얼z��7���(�N�ַ�hn�P��'t��T������=;i     