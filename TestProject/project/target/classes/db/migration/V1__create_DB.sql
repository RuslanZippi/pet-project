create table captcha (
     id integer not null auto_increment,
     code varchar(255),
     secret_code varchar(255),
     time dateTime,
 primary key (id)) engine=MyISAM;


create table global_settings (
    id integer not null auto_increment,
    code varchar(255),
    name varchar(255),
    value varchar(255),
    primary key (id)) engine=MyISAM;

create table hibernate_sequence (next_val bigint) engine=MyISAM;

create table post_comments (
    id integer not null auto_increment,
    text varchar(2048),
    time date,
    parent_id integer,
    post_id integer,
    user_id integer,
    primary key (id)) engine=MyISAM;

create table posts (
    id integer not null auto_increment,
    is_active bit not null,
    moderator_status varchar(255),
    text varchar(4096),
    time date,
    title varchar(1024),
    view_count integer not null,
    moderator_id integer,
    user_id integer,
    primary key (id)) engine=MyISAM;

create table post_votes (
    id integer not null auto_increment,
    time date,
    value integer not null,
    post_id integer,
    user_id integer,
    primary key (id)) engine=MyISAM;

create table tag2post (
    id integer not null auto_increment,
    post_id integer,
    tag_id integer,
    primary key (id)) engine=MyISAM;

create table tags (
    id integer not null auto_increment,
    name varchar(255),
    primary key (id)) engine=MyISAM;

create table tags_posts (post_id integer not null,
    tag_id integer not null) engine=MyISAM;

create table users (
    id integer not null auto_increment,
    code varchar(255),
    email varchar(255),
    is_moderator bit not null,
    name varchar(1024),
    password varchar(255),
    photo varchar(255),
    reg_time dateTime,
    primary key (id)) engine=MyISAM;