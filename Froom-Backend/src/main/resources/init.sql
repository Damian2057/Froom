create table public."user"
(
    birth_date timestamp(6),
    id         bigint not null
        primary key,
    uuid       uuid
        unique,
    email      varchar(255)
        unique,
    gender     varchar(255)
        constraint user_gender_check
            check ((gender)::text = ANY
        ((ARRAY ['MAN'::character varying, 'WOMAN'::character varying, 'OTHER'::character varying, 'UNKNOWN'::character varying])::text[])),
    password   varchar(255),
    user_name  varchar(255)
);

alter table public."user"
    owner to postgres;

create table public.item
(
    id            integer not null
        primary key,
    user_id       bigint
        constraint fka4tfu0wpn0kw0ufojj8pu99gi
            references public."user",
    uuid          uuid,
    category_type varchar(255)
        constraint item_category_type_check
            check ((category_type)::text = ANY
        ((ARRAY ['T_SHIRT_TOP'::character varying, 'TROUSERS'::character varying, 'PULLOVER'::character varying, 'DRESS'::character varying, 'COAT'::character varying, 'SANDALS'::character varying, 'SHIRT'::character varying, 'SNEAKERS'::character varying, 'BAG'::character varying, 'ANKLE_BOOTS'::character varying, 'UNKNOWN'::character varying])::text[])),
    image_format  varchar(255),
    image         oid
);

alter table public.item
    owner to postgres;

create table public.item_color
(
    color   integer,
    item_id integer not null
        constraint fkjo0wxh07jrevjyo39oydtv8w5
            references public.item
);

alter table public.item_color
    owner to postgres;

create table public.outfit
(
    id      integer not null
        primary key,
    user_id bigint
        constraint fk99egs45v62yqxajmuovm6hyle
            references public."user",
    uuid    uuid,
    name    varchar(255)
);

alter table public.outfit
    owner to postgres;

create table public.outfit_item
(
    item_id   integer not null
        constraint fkdvkbrp5nj4ss0uavibvo2s6hu
            references public.item,
    outfit_id integer not null
        constraint fk1pssnc57nx2ayd52t0lb2blcp
            references public.outfit
);

alter table public.outfit_item
    owner to postgres;

create table public.item_outfit
(
    item_id   integer not null
        constraint fk58iwt7ikyac5wekc8ad447k2s
            references public.item,
    outfit_id integer not null
        constraint fkr7qk2xer7flmtx2vo505vlcoq
            references public.outfit
);

alter table public.item_outfit
    owner to postgres;

