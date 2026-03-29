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

test(cpu_field_options_return_known_values) :-
	field_options(cpu, Options),
	memberchk(option(ryzen7_7700, "AMD Ryzen 7 7700"), Options),
	memberchk(option(i5_14600k, "Intel Core i5-14600K"), Options).

test(motherboard_field_options_return_known_values) :-
	field_options(motherboard, Options),
	memberchk(option(b650_tomahawk, "MSI MAG B650 Tomahawk"), Options),
	memberchk(option(z790_aorus_elite, "Gigabyte Z790 Aorus Elite"), Options).

test(ram_field_options_return_known_values) :-
	field_options(ram, Options),
	memberchk(option(ddr5_32_6000, "32 GB DDR5 6000"), Options),
	memberchk(option(ddr4_16_3200, "16 GB DDR4 3200"), Options).

:- end_tests(catalog).
