create table WSABatchEntity (batch_id bigint generated by default as identity, batch_no varchar(128), batch_type varchar(128), batch_quantity int, primary key (batch_id));