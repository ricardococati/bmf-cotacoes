drop table campanha if exists;

create table campanha (
	id_campanha integer primary key,
	time_do_coracao varchar(50),
	nome_campanha varchar(50),
	data_vigencia_init date,
	data_vigencia_term date
);
