// vim: filetype=scala: tabstop=3: shiftwidth=3: noexpandtab
package app.prolog

import app.ui.{UiField, UiOption}
import org.jpl7.{Atom, Query, Term}

import java.nio.file.Paths

final class JplPrologGateway extends PrologGateway {

	override def loadUiFields(): Seq[UiField] = {
		consultFile("ui_schema.pl")
		consultFile("catalog.pl")

		val fieldQuery = new Query("field(Id, ControlType, Label, Order)")
		val solutions = fieldQuery.allSolutions().toSeq

		solutions.map { solution =>
			val fieldId = solution.get("Id").name

			UiField(
				id = fieldId,
				controlType = solution.get("ControlType").name,
				label = solution.get("Label").name,
				order = solution.get("Order").intValue,
				options = loadOptionsForField(fieldId)
			)
		}
	}

	private def consultFile(fileName: String): Unit = {
		val prologFile = Paths
			.get("src", "main", "resources", "prolog", fileName)
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
	}

	private def loadOptionsForField(fieldId: String): Seq[UiOption] = {
		val optionsQuery = new Query(
			"field_options",
			Array[Term](new Atom(fieldId), new org.jpl7.Variable("Options"))
		)

		if (!optionsQuery.hasSolution) {
			Seq.empty
		} else {
			val solution = optionsQuery.oneSolution()
			val optionsList = solution.get("Options")

			optionsList.listToTermArray().toSeq.map { optionTerm =>
				UiOption(
					id = optionTerm.arg(1).name,
					label = optionTerm.arg(2).name
				)
			}
		}
	}
}
