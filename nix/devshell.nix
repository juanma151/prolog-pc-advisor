# vim: filetype=nix: tabstop=3: shiftwidth=3: noexpandtab
{ ... }:
{
	perSystem = { pkgs, ... }: {
		devShells.default = pkgs.mkShell {
			packages = with pkgs; [
				jdk21
				gradle
				scala
				swi-prolog
				git
			];

			shellHook = ''
				echo "Entorno de desarrollo listo"
				echo "Workspace Scala: ./scala-workspace"

				export JPL_JAR="$(find ${pkgs.swi-prolog} -type f -name 'jpl.jar' | head -n1)"
				export JPL_LIB_DIR="$(dirname "$(find ${pkgs.swi-prolog} -type f \( -name 'libjpl.dylib' -o -name 'libjpl.so' -o -name 'jpl.dll' \) | head -n1)")"

				echo "JPL_JAR=$JPL_JAR"
				echo "JPL_LIB_DIR=$JPL_LIB_DIR"
			'';
		};
	};
}
