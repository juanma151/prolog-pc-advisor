% vim: filetype=prolog: tabstop=3: shiftwidth=3: noexpandtab
:- module(rules, [
	compatible_cpu_motherboard/2,
	compatible_motherboard_ram/2,
	valid_build/3
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
