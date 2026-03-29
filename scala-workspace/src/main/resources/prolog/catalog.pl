% vim: filetype=prolog: tabstop=3: shiftwidth=3: noexpandtab
:- module(catalog, [
	cpu/3,
	list_cpus/1
]).

cpu(ryzen7_7700, "AMD Ryzen 7 7700", am5).
cpu(i5_14600k, "Intel Core i5-14600K", lga1700).

list_cpus(Cpus) :-
	findall(cpu(Id, Label), cpu(Id, Label, _Socket), Cpus).
