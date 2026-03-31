% vim: filetype=prolog: tabstop=3: shiftwidth=3: noexpandtab
:- begin_tests(rules).

:- use_module('../rules').

test(cpu_and_board_are_compatible) :-
	compatible_cpu_motherboard(ryzen7_7700, b650_tomahawk).

test(cpu_and_board_are_not_compatible, [fail]) :-
	compatible_cpu_motherboard(ryzen7_7700, z790_aorus_elite).

test(board_and_ram_are_compatible) :-
	compatible_motherboard_ram(b760_legacy, ddr4_16_3200).

test(board_and_ram_are_not_compatible, [fail]) :-
	compatible_motherboard_ram(b650_tomahawk, ddr4_16_3200).

test(valid_build_should_succeed_for_matching_parts) :-
	valid_build(i5_14600k, b760_legacy, ddr4_16_3200).

test(valid_build_should_fail_for_mixed_platforms, [fail]) :-
	valid_build(ryzen7_7700, z790_aorus_elite, ddr5_32_6000).

test(validation_message_should_report_valid_build) :-
	validation_message(
		i5_14600k,
		b760_legacy,
		ddr4_16_3200,
		"Configuration is valid"
	).

test(validation_message_should_report_socket_problem) :-
	validation_message(
		ryzen7_7700,
		z790_aorus_elite,
		ddr5_32_6000,
		"CPU and motherboard sockets are not compatible"
	).

test(validation_message_should_report_ram_problem) :-
	validation_message(
		i5_14600k,
		b760_legacy,
		ddr5_32_6000,
		"Motherboard and RAM type are not compatible"
	).

test(suggest_build_fix_should_suggest_motherboard_for_cpu) :-
	suggest_build_fix(
		ryzen7_7700,
		z790_aorus_elite,
		ddr5_32_6000,
		suggestion(motherboard, b650_tomahawk, "MSI MAG B650 Tomahawk")
	).

test(suggest_build_fix_should_suggest_ram_for_board) :-
	suggest_build_fix(
		i5_14600k,
		b760_legacy,
		ddr5_32_6000,
		suggestion(ram, ddr4_16_3200, "16 GB DDR4 3200")
	).

test(suggest_build_fix_should_not_suggest_motherboard_when_cpu_and_board_are_already_compatible, [fail]) :-
	suggest_build_fix(
		i5_14600k,
		b760_legacy,
		ddr5_32_6000,
		suggestion(motherboard, _AnyId, _AnyLabel)
	).

test(suggest_build_fix_should_not_suggest_same_ram, [fail]) :-
	suggest_build_fix(
		i5_14600k,
		b760_legacy,
		ddr4_16_3200,
		suggestion(ram, ddr4_16_3200, "16 GB DDR4 3200")
	).

:- end_tests(rules).
