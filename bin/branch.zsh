#! /usr/bin/env zsh
# vim: filetype=zsh: tabstop=3: shiftwidth=3: noexpandtab

function __gawk_code () {
	cat - << 'gawk-eos'
BEGIN { untracked = 0; changes=0; branch=""; rbranch=""; ahead=""; behind="" }

$1=="?" { untracked++; next }

$1!="#" { changes++; next }

$2=="branch.head"     { branch=$3 }
$2=="branch.upstream" { rbranch=$3 }
$2=="branch.ab"       { ahead=strtonum($3); behind=strtonum($4) }

END {
	OFS="\n"
	print "branch",branch
	print "rbranch",rbranch
	print "untracked",untracked
	print "changes",changes
	print "ahead",ahead
	print "behind",behind
}
gawk-eos
}


function __get_gawk_data () {
	{
		git status \
			--porcelain=v2 \
			--branch \
			--untracked-files=all \
			--ignore-submodules=all \
			--ignored=no \
			--ahead-behind \
			--renames
	} |
		gawk -f =( __gawk_code )
}


function __do_get_branch_data () {
	typeset -gA DATA

	local -a tmparr

	tmparr=( ${(Aps.\n.)${:-"$( __get_gawk_data )"}} )
	DATA=( ${tmparr} )
}

funcion get_branch_data () {
	__do_get_branch_data

	typeset -gA DATA
	typeset -ga PDATA

	local -a  keys
	local     k    v
	local -i  bad  col isOk

	local -a buff

	keys=( branch rbranch ahead behind untracked changes )

	isOk=1

	PDATA=()

	for k in "${(@)keys}"; do
		v=${DATA[${k}]}
		
		buff=( $'\e[32m'${k}$'\e[00m' )

		bad=0

		case "${k}"; in
			(r|)branch)
				case "${v}"; in
					'')             v="[main]" ;&
					(origin/|)main) bad=1      ;;
				esac
				;;
			
			ahead)     ;&
			behind)    ;&
			untracked) ;&
			changes)
				if (( v != 0 )); then
					bad=1
				fi
				;;
		esac

		if (( bad == 1 )); then
			col=91
			if (( isOk == 1 )); then
				isOk=0
			fi
		else
			col=37
		fi

		buff+=( $'\e['${col}m${v}$'\e[00m' )

		PDATA+=( "${(pj. .)buff}" )
		
	done

	DATA[isOk]=${isOk}
}


function print_data () {
	typeset -ga PDATA

	{
		print -C1 - "${(@)PDATA}"

	} | column -xt
}


function _run () {
	get_branch_data
	print_data

	typeset -gA DATA

	local -i isOk        numChanges
	local    branch
	local    rbranch
	local -a fileChanges

	isOk=${DATA[isOk]}
	branch=${DATA[branch]}
	rbranch=${DATA[rbranch]}

	print

	if (( isOk==0 )); then
		print - "\e[91;1mCan't delete the branch because it's not ok.\e[0m"
		return 100
	else
		{
			git checkout main
			git pull
			git checkout "${branch}"

		} 1>/dev/null 2>&1
		
		fileChanges=( ${(Aps.\n.)${:-"$( git diff --name-status main "${branch}" )"}} )
		numChanges=${#fileChanges}

		if (( numChanges > 0 )); then
			print - \
				"\e[91;1mCan't delete the branch because there are changes not in main.\e[0m"
			print -C1 - "${(@)fileChanges}"
			return 100
		else
			print - "\e[32mYou can delete the branch with the following commands:\e[0m"
			
			git checkout main 1>/dev/null 2>&1

			print - "git push origin --delete ${branch}"
			print - "git branch -D ${branch}"
			print
		fi
	fi
}


_run "${@}"


