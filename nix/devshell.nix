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
      '';
    };
  };
}

