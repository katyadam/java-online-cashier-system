create sequence product_id_seq
    as integer;

alter sequence product_id_seq owner to postgres;

create sequence token_id_seq
    as integer;

alter sequence token_id_seq owner to postgres;

create table "user"
(
    user_id       serial
        primary key,
    given_name    varchar(255),
    family_name   varchar(255),
    email         varchar(255)
        unique,
    password_hash varchar(255),
    role          varchar
);

alter table "user"
    owner to postgres;

create table product_plane
(
    product_plane_id serial
        primary key,
    name             varchar(255),
    fk_user_id       integer
        constraint product_plane___fk
            references "user",
    creation_date    date default CURRENT_TIMESTAMP
);

alter table product_plane
    owner to postgres;

create table product
(
    product_id          integer default nextval('product_id_seq'::regclass) not null
        primary key,
    name                varchar(255),
    fk_product_plane_id integer
        constraint product_product_plane_product_plane_id_fk
            references product_plane,
    price               double precision,
    currency            varchar
);

alter table product
    owner to postgres;

alter sequence product_id_seq owned by product.product_id;

create table transaction
(
    transaction_id serial
        primary key,
    record         varchar,
    fk_user_id     integer not null
        constraint transaction_user_user_id_fk
            references "user",
    creation_time  timestamp default CURRENT_TIMESTAMP
);

alter table transaction
    owner to postgres;

create table token
(
    token_id   integer default nextval('token_id_seq'::regclass) not null
        constraint token_pk
            primary key,
    token      varchar                                           not null,
    token_type varchar                                           not null,
    fk_user_id integer                                           not null
        constraint token_user_fk
            references "user",
    expired    boolean                                           not null,
    revoked    boolean
);

alter table token
    owner to postgres;

