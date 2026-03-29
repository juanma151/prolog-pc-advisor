% vim: filetype=prolog: tabstop=3: shiftwidth=3: noexpandtab
:- module(catalog, [
	cpu/3,
	motherboard/4,
	ram/3,
	list_cpus/1,
	field_options/2
]).

cpu(ryzen7_7700, "AMD Ryzen 7 7700", am5).
cpu(i5_14600k, "Intel Core i5-14600K", lga1700).

motherboard(b650_tomahawk, "MSI MAG B650 Tomahawk", am5, ddr5).
motherboard(z790_aorus_elite, "Gigabyte Z790 Aorus Elite", lga1700, ddr5).
motherboard(b760_legacy, "ASUS B760 Legacy", lga1700, ddr4).

ram(ddr5_32_6000, "32 GB DDR5 6000", ddr5).
ram(ddr4_16_3200, "16 GB DDR4 3200", ddr4).

list_cpus(Cpus) :-
	findall(cpu(Id, Label), cpu(Id, Label, _Socket), Cpus).

field_options(cpu, Options) :-
	findall(option(Id, Label), cpu(Id, Label, _Socket), Options).

field_options(motherboard, Options) :-
	findall(option(Id, Label), motherboard(Id, Label, _Socket, _RamType), Options).

field_options(ram, Options) :-
	findall(option(Id, Label), ram(Id, Label, _RamType), Options).
