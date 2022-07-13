create table resume
(
    uuid char(36) PRIMARY KEY,
    full_name TEXT NOT NULL
);

create table contact
(
     id serial primary key,
     resume_uuid char(36) not null references resume (uuid) on delete cascade,
     type text not null,
     value text not null
);

create unique index contact_uuid_type_index on contact (resume_uuid, type);

insert into resume (uuid, full_name) VALUES
('7defi549-9934-id7f-yr67-t56507gi7530', 'name4'),
('7defi549-9934-idrt-int3-t56507gi7530', 'name5'),
('7defi549-9934-7799-hhy3-t56507gi7530', 'name6');

select * from resume;