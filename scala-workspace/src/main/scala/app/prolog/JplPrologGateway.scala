// vim: filetype=scala: tabstop=3: shiftwidth=3: noexpandtab
package app.prolog

import app.ui.UiField
import org.jpl7.{Atom, Query, Term}

import java.nio.file.Paths

final class JplPrologGateway extends PrologGateway {

	override def loadUiFields(): Seq[UiField] = {
		val prologFile = Paths
			.get("src", "main", "resources", "prolog", "ui_schema.pl")
			.toAbsolutePath
			.normalize
			.toString

		val consultQuery = new Query(
			"consult",
			Array[Term](new Atom(prologFile))
		)

		if (!consultQuery.hasSolution) {
			throw new IllegalStateException(s"Could not consult Prolog file: $prologFile")
		}

		val fieldQuery = new Query("field(Id, ControlType, Label, Order)")
		val solutions = fieldQuery.allSolutions().toSeq

		solutions.map { solution =>
			UiField(
				id = solution.get("Id").name,
				controlType = solution.get("ControlType").name,
				label = solution.get("Label").name,
				order = solution.get("Order").intValue
			)
		}
	}
}
