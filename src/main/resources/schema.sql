create table nations (
    id bigint PRIMARY KEY AUTO_INCREMENT,
    name character varying(254) NOT NULL,
    iso character varying(254) NOT NULL,
    iso3 character varying(254) NOT NULL,
    numeric_code character varying(254),
    phone_code character varying(254),
    capital character varying(254),
    currency character varying(254),
    currency_name character varying(254),
    currency_symbol character varying(254),
    native_name character varying(254),
    region character varying(254),
    subregion character varying(254),
    lat decimal,
    lng decimal,
    emoji character varying(254) NOT NULL
);

create table states (
    id bigint PRIMARY KEY AUTO_INCREMENT,
    code character varying(254),
    name character varying(254) NOT NULL,
    lat decimal,
    lng decimal,
    nation_id bigint NOT NULL REFERENCES nations(id)
);

create table towns (
    id bigint PRIMARY KEY AUTO_INCREMENT,
    code character varying(254) NOT NULL,
    name character varying(254) NOT NULL,
    lat decimal,
    lng decimal,
    state_id bigint NOT NULL REFERENCES states(id),
    nation_id bigint NOT NULL REFERENCES nations(id)
);

create table users (
    id bigint PRIMARY KEY AUTO_INCREMENT,
    uid character varying(254),
    guid character varying(254),
    ref character varying(254),
    refs text,
    name character varying(155) NOT NULL,
    phone character varying(40),
    email character varying(254) NOT NULL,
    passwd character varying(155) NOT NULL,
    image clob,
    bio text,
    quote text,
    needs text,
    age bigint,
    years_need bigint,
    address character varying(120),
    address2 character varying(120),
    zip_code character varying(19),
    nation_id bigint,
    state_id bigint,
    town_id bigint,
    user_type character varying(254) NOT NULL,
    lat decimal default 0.0,
    lng decimal default 0.0,
    activated boolean default false,
    onboarded boolean default false,
    stripe_account_id text,
    stripe_customer_id text,
    has_bank boolean default false,
    has_cell boolean default false,
    time_created bigint default 0,
    sponsor boolean default false
);

create table roles (
    id bigint PRIMARY KEY AUTO_INCREMENT,
    name character varying(155) NOT NULL UNIQUE
);

create table user_permissions(
    user_id bigint REFERENCES users(id),
    permission character varying(55)
);

create table user_roles(
    role_id bigint NOT NULL REFERENCES roles(id),
    user_id bigint NOT NULL REFERENCES users(id)
);

create table places (
    id bigint PRIMARY KEY AUTO_INCREMENT,
    name character varying(255) NOT NULL,
    description text,
    uri character varying (254),
    latitude character varying (254),
    longitude character varying (254),
    stripe_account_id text,
    constraint unique_uri unique(uri),
    town_id bigint NOT NULL REFERENCES towns(id)
);

create table ownership_requests (
    id bigint PRIMARY KEY AUTO_INCREMENT,
    name character varying(254) NOT NULL,
    email character varying (254),
    phone character varying (254),
    date_requested bigint,
    approved boolean,
    place_id bigint REFERENCES places(id)
);

create table products(
    id bigint PRIMARY KEY AUTO_INCREMENT,
    nickname character varying (255),
    stripe_id text
);

create table prices(
    id bigint PRIMARY KEY AUTO_INCREMENT,
    stripe_id text,
    amount decimal default 0.0,
    nickname character varying (255),
    product_id bigint NOT NULL REFERENCES products(id)
);

create table if not exists gifts (
    id bigint PRIMARY KEY AUTO_INCREMENT,
    guid character varying(250) default '',
    amount decimal default 0.0,
    amount_cents bigint default 0,
    charge_id text,
    subscription_id text,
    recipient_id bigint REFERENCES users(id),
    kind_person_id bigint REFERENCES users(id),
    kind_person_name character varying(201),
    processed boolean default false,
    gift_time bigint default 0,
    email character varying (250),
    monthly boolean default false,
    cancelled boolean default false
);