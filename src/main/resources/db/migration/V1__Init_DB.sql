create table message (
  id bigint not null auto_increment,
  filename varchar(255),
  tag varchar(255),
  text varchar(255),
  user_id bigint,
  primary key (id)
) engine=MyISAM;

create table user_role (
  user_id bigint not null,
  roles varchar(255)
) engine=MyISAM;

create table usr (
  id bigint not null auto_increment,
  active bit not null,
  password varchar(255),
  username varchar(255),
  primary key (id)
) engine=MyISAM;

alter table message
  add constraint message_user_fk
  foreign key (user_id) references usr (id);

alter table user_role
  add constraint message_user_fk
  foreign key (user_id) references usr (id);