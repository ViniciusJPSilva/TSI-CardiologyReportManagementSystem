PGDMP     .    0            	    {            cardiology-system    14.8    14.8                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    115301    cardiology-system    DATABASE     s   CREATE DATABASE "cardiology-system" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Portuguese_Brazil.1252';
 #   DROP DATABASE "cardiology-system";
                aluno    false            �            1259    115317    seq-exam    SEQUENCE     s   CREATE SEQUENCE public."seq-exam"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 !   DROP SEQUENCE public."seq-exam";
       public          postgres    false            �            1259    115318    exam    TABLE     �  CREATE TABLE public.exam (
    id bigint DEFAULT nextval('public."seq-exam"'::regclass) NOT NULL,
    patient_cpf character varying(11) NOT NULL,
    type character varying(63) NOT NULL,
    status character varying(63) NOT NULL,
    hypothesis character varying(7) NOT NULL,
    recomendation character varying(255),
    physician_crm bigint NOT NULL,
    expected_date date,
    exam_date timestamp with time zone,
    result_path character varying(512)
);
    DROP TABLE public.exam;
       public         heap    postgres    false    212            �            1259    115307    patient    TABLE     �   CREATE TABLE public.patient (
    cpf character varying(11) NOT NULL,
    name character varying(255) NOT NULL,
    email character varying(127) NOT NULL,
    gender character varying(15) NOT NULL,
    birth_date date NOT NULL
);
    DROP TABLE public.patient;
       public         heap    postgres    false            �            1259    115302 	   physician    TABLE     �   CREATE TABLE public.physician (
    crm bigint NOT NULL,
    name character varying(255) NOT NULL,
    type character varying(63),
    residency_start_year smallint,
    title character varying(63)
);
    DROP TABLE public.physician;
       public         heap    postgres    false            �            1259    115336 
   seq-report    SEQUENCE     u   CREATE SEQUENCE public."seq-report"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public."seq-report";
       public          postgres    false            �            1259    115337    report    TABLE     }  CREATE TABLE public.report (
    id bigint DEFAULT nextval('public."seq-report"'::regclass) NOT NULL,
    exam_id bigint NOT NULL,
    is_definitive boolean DEFAULT false NOT NULL,
    description character varying(255) NOT NULL,
    conclusion character varying(7) NOT NULL,
    resident_crm bigint NOT NULL,
    teaching_crm bigint,
    emission_date timestamp with time zone
);
    DROP TABLE public.report;
       public         heap    postgres    false    214            �            1259    115312    user    TABLE     g   CREATE TABLE public."user" (
    login bigint NOT NULL,
    password character varying(31) NOT NULL
);
    DROP TABLE public."user";
       public         heap    postgres    false                      0    115318    exam 
   TABLE DATA           �   COPY public.exam (id, patient_cpf, type, status, hypothesis, recomendation, physician_crm, expected_date, exam_date, result_path) FROM stdin;
    public          postgres    false    213   �       
          0    115307    patient 
   TABLE DATA           G   COPY public.patient (cpf, name, email, gender, birth_date) FROM stdin;
    public          postgres    false    210   �       	          0    115302 	   physician 
   TABLE DATA           Q   COPY public.physician (crm, name, type, residency_start_year, title) FROM stdin;
    public          postgres    false    209   !                 0    115337    report 
   TABLE DATA           �   COPY public.report (id, exam_id, is_definitive, description, conclusion, resident_crm, teaching_crm, emission_date) FROM stdin;
    public          postgres    false    215   "                 0    115312    user 
   TABLE DATA           1   COPY public."user" (login, password) FROM stdin;
    public          postgres    false    211   2"                  0    0    seq-exam    SEQUENCE SET     9   SELECT pg_catalog.setval('public."seq-exam"', 1, false);
          public          postgres    false    212                       0    0 
   seq-report    SEQUENCE SET     ;   SELECT pg_catalog.setval('public."seq-report"', 1, false);
          public          postgres    false    214            w           2606    115325    exam pk_exam 
   CONSTRAINT     J   ALTER TABLE ONLY public.exam
    ADD CONSTRAINT pk_exam PRIMARY KEY (id);
 6   ALTER TABLE ONLY public.exam DROP CONSTRAINT pk_exam;
       public            postgres    false    213            s           2606    115311    patient pk_patient 
   CONSTRAINT     Q   ALTER TABLE ONLY public.patient
    ADD CONSTRAINT pk_patient PRIMARY KEY (cpf);
 <   ALTER TABLE ONLY public.patient DROP CONSTRAINT pk_patient;
       public            postgres    false    210            q           2606    115306    physician pk_physician 
   CONSTRAINT     U   ALTER TABLE ONLY public.physician
    ADD CONSTRAINT pk_physician PRIMARY KEY (crm);
 @   ALTER TABLE ONLY public.physician DROP CONSTRAINT pk_physician;
       public            postgres    false    209            y           2606    115343    report pk_report 
   CONSTRAINT     N   ALTER TABLE ONLY public.report
    ADD CONSTRAINT pk_report PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.report DROP CONSTRAINT pk_report;
       public            postgres    false    215            u           2606    115316    user pk_user 
   CONSTRAINT     O   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT pk_user PRIMARY KEY (login);
 8   ALTER TABLE ONLY public."user" DROP CONSTRAINT pk_user;
       public            postgres    false    211            |           2606    115344    report fk_exam    FK CONSTRAINT     ~   ALTER TABLE ONLY public.report
    ADD CONSTRAINT fk_exam FOREIGN KEY (exam_id) REFERENCES public.exam(id) ON DELETE CASCADE;
 8   ALTER TABLE ONLY public.report DROP CONSTRAINT fk_exam;
       public          postgres    false    3191    215    213            z           2606    115326    exam fk_patient    FK CONSTRAINT     �   ALTER TABLE ONLY public.exam
    ADD CONSTRAINT fk_patient FOREIGN KEY (patient_cpf) REFERENCES public.patient(cpf) ON DELETE CASCADE;
 9   ALTER TABLE ONLY public.exam DROP CONSTRAINT fk_patient;
       public          postgres    false    213    3187    210            {           2606    115331    exam fk_physician    FK CONSTRAINT     �   ALTER TABLE ONLY public.exam
    ADD CONSTRAINT fk_physician FOREIGN KEY (physician_crm) REFERENCES public.physician(crm) ON DELETE CASCADE;
 ;   ALTER TABLE ONLY public.exam DROP CONSTRAINT fk_physician;
       public          postgres    false    209    3185    213            }           2606    115349    report fk_resident    FK CONSTRAINT     �   ALTER TABLE ONLY public.report
    ADD CONSTRAINT fk_resident FOREIGN KEY (resident_crm) REFERENCES public.physician(crm) ON DELETE CASCADE;
 <   ALTER TABLE ONLY public.report DROP CONSTRAINT fk_resident;
       public          postgres    false    215    209    3185                  x������ � �      
   H  x�u�An� E��)� ���]�HYT�%RW݌\ZQaA�E�ӣ�b�8]Ļ����TUݬֺ6ғu�cV�G�x�{ؙ�v� �V0�0ِ2����|{z���#|z�<^�?��nt3C'�bJ���҇`�>;;�l��م,-��L�<��xA8g���^��֩�jH!4��1�:�����1��!�fb̈́"���t�yz0�o���y�a�ԋd� ���-��!=b������l��Q"m#j&5)G�쇧;�<a~�ﷇ�vj&V�V��Z8���l�d"����$� M
@�~�0�{���]��vR��|�WN��C      	     x�]�Kj�0@��)�-q�_�BC��t��Ԟ�@����"�骋����@k�1�a���,�JA�B��E�cGT;�L�j�B��6<}1(�=4�z��қD��L�Ѕ�o֌d��mBU��v(m��a��Ӛ�C��GUrK��h��>,)�.�"�x�����i��<�,�>1V��BE�6�[3
]u�~H����b6�5�U�1���W�U�P8�11r�������K���*zO��W�ф�r;�Ŝ,�ﯵֿ���            x������ � �         7   x�%Ź� ���.�����@�N`��H�5�܂�=�<��3輂�;�|��wQ?��     