% vim: filetype=prolog: tabstop=3: shiftwidth=3: noexpandtab
:- begin_tests(catalog).

:- use_module('../catalog').

test(cpu_exists_ryzen_7700) :-
	cpu(ryzen7_7700, "AMD Ryzen 7 7700", am5).

test(cpu_exists_i5_14600k) :-
	cpu(i5_14600k, "Intel Core i5-14600K", lga1700).

test(list_cpus_returns_known_ids) :-
	list_cpus(Cpus),
	memberchk(cpu(ryzen7_7700, "AMD Ryzen 7 7700"), Cpus),
	memberchk(cpu(i5_14600k, "Intel Core i5-14600K"), Cpus).

test(list_cpus_has_two_items) :-
	list_cpus(Cpus),
	length(Cpus, 2).

:- end_tests(catalog).