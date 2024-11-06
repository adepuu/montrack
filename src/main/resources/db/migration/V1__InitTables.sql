create table users (
    user_id bigserial constraint users_pk primary key,
    email varchar(50) not null constraint users_email_unique unique,
    password text not null,
    pin varchar(50) not null,
    profile_picture_url varchar(100),
    is_onboarding_finished boolean default false not null,
    created_at timestamp with time zone default CURRENT_TIMESTAMP not null,
    updated_at timestamp with time zone default CURRENT_TIMESTAMP not null,
    deleted_at timestamp with time zone
);

create table wallet (
    wallet_id bigserial constraint wallet_pk primary key,
    is_active boolean not null default false,
    name varchar(100) not null,
    current_expense numeric(25, 2) not null default 0,
    current_income numeric(25, 2) not null default 0,
    allocated_budget numeric(25, 2) not null default 0,
    created_at timestamp with time zone default CURRENT_TIMESTAMP not null,
    updated_at timestamp with time zone default CURRENT_TIMESTAMP not null,
    deleted_at timestamp with time zone
);

create table pocket (
    pocket_id bigserial constraint pocket_pk primary key,
    name varchar(100) not null,
    emoji_code varchar(50) not null,
    allocated_budget numeric(25, 2) not null default 0,
    description text,
    used_amount numeric(25, 2) not null default 0,
    wallet_id bigint references wallet(wallet_id),
    created_at timestamp with time zone default CURRENT_TIMESTAMP not null,
    updated_at timestamp with time zone default CURRENT_TIMESTAMP not null,
    deleted_at timestamp with time zone
);

create table goal (
    goal_id bigserial constraint goal_pk primary key,
    name varchar(100) not null,
    target_amount numeric(25, 2) not null default 0,
    current_amount numeric(25, 2) not null default 0,
    wallet_id bigint references wallet(wallet_id),
    attachment_url varchar(200),
    created_at timestamp with time zone default CURRENT_TIMESTAMP not null,
    updated_at timestamp with time zone default CURRENT_TIMESTAMP not null,
    deleted_at timestamp with time zone
);

create table trx (
    trx_id bigserial constraint trx_pk primary key,
    value numeric(25, 2) not null,
    description text not null,
    attachment_url varchar(200),
    pocket_id bigint references pocket(pocket_id),
    wallet_id bigint references wallet(wallet_id),
    goal_id bigint references goal(goal_id),
    created_at timestamp with time zone default CURRENT_TIMESTAMP not null,
    updated_at timestamp with time zone default CURRENT_TIMESTAMP not null,
    deleted_at timestamp with time zone
);

create unique index trx_id_uindex on trx (trx_id desc);

create unique index pocket_id_uindex on pocket (pocket_id desc);

create unique index wallet_id_uindex on wallet (wallet_id desc);

create unique index goal_id_uindex on goal (goal_id desc);

create unique index users_email_uindex on users (email);

create unique index users_user_id_uindex on users (user_id desc);