% vim: filetype=prolog: tabstop=3: shiftwidth=3: noexpandtab
:- begin_tests(ui_schema).

:- use_module('../ui_schema').

test(cpu_field_exists) :-
	field(cpu, combo, "CPU", 1).

test(motherboard_field_exists) :-
	field(motherboard, combo, "Motherboard", 2).

test(ram_field_exists) :-
	field(ram, combo, "RAM", 3).

test(list_fields_returns_all_known_fields) :-
	list_fields(Fields),
	memberchk(field(cpu, combo, "CPU", 1), Fields),
	memberchk(field(motherboard, combo, "Motherboard", 2), Fields),
	memberchk(field(ram, combo, "RAM", 3), Fields).

test(list_fields_has_three_items) :-
	list_fields(Fields),
	length(Fields, 3).

:- end_tests(ui_schema).