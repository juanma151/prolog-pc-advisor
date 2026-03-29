% vim: filetype=prolog: tabstop=3: shiftwidth=3: noexpandtab
:- module(ui_schema, [
	field/4,
	list_fields/1
]).

field(cpu, combo, "CPU", 1).
field(motherboard, combo, "Motherboard", 2).
field(ram, combo, "RAM", 3).

list_fields(Fields) :-
	findall(field(Id, ControlType, Label, Order),
		field(Id, ControlType, Label, Order),
		Fields).
