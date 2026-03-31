% vim: filetype=prolog: tabstop=3: shiftwidth=3: noexpandtab
:- module(rules, [
	compatible_cpu_motherboard/2,
	compatible_motherboard_ram/2,
	valid_build/3,
	validation_message/4,
	suggest_build_fix/4
]).

:- use_module(catalog).

compatible_cpu_motherboard(CpuId, MotherboardId) :-
	cpu(CpuId, _CpuLabel, Socket),
	motherboard(MotherboardId, _BoardLabel, Socket, _RamType).

compatible_motherboard_ram(MotherboardId, RamId) :-
	motherboard(MotherboardId, _BoardLabel, _Socket, RamType),
	ram(RamId, _RamLabel, RamType).

valid_build(CpuId, MotherboardId, RamId) :-
	compatible_cpu_motherboard(CpuId, MotherboardId),
	compatible_motherboard_ram(MotherboardId, RamId).

validation_message(CpuId, MotherboardId, RamId, Message) :-
	(	valid_build(CpuId, MotherboardId, RamId) ->
		Message = "Configuration is valid"
	;	\+ compatible_cpu_motherboard(CpuId, MotherboardId) ->
		Message = "CPU and motherboard sockets are not compatible"
	;	\+ compatible_motherboard_ram(MotherboardId, RamId) ->
		Message = "Motherboard and RAM type are not compatible"
	).

suggest_build_fix(CpuId, MotherboardId, _RamId, Suggestion) :-
	\+ compatible_cpu_motherboard(CpuId, MotherboardId),
	cpu(CpuId, _CpuLabel, Socket),
	motherboard(SuggestedBoardId, SuggestedBoardLabel, Socket, _RamType),
	SuggestedBoardId \= MotherboardId,
	Suggestion = suggestion(
		motherboard,
		SuggestedBoardId,
		SuggestedBoardLabel
	),
	!.

suggest_build_fix(_CpuId, MotherboardId, RamId, Suggestion) :-
	\+ compatible_motherboard_ram(MotherboardId, RamId),
	motherboard(MotherboardId, _BoardLabel, _Socket, RamType),
	ram(SuggestedRamId, SuggestedRamLabel, RamType),
	SuggestedRamId \= RamId,
	Suggestion = suggestion(
		ram,
		SuggestedRamId,
		SuggestedRamLabel
	),
	!.
