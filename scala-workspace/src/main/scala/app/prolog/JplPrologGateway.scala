// vim: filetype=scala: tabstop=3: shiftwidth=3: noexpandtab
package app.prolog

import app.model.{BuildSelection, ValidationResult, ValidationSuggestion}
import app.ui.{UiField, UiOption}
import org.jpl7.{Atom, Query, Term, Variable}

import java.nio.file.Paths
import scala.jdk.CollectionConverters.*

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

	override def validateBuild(selection: BuildSelection): ValidationResult = {
		consultFile("catalog.pl")
		consultFile("rules.pl")

		val selectionTerms = buildSelectionTerms(selection)

		val validQuery = new Query("valid_build", selectionTerms)

		val message =
			runSingleSolutionQuery(
				functor = "validation_message",
				variableName = "Message",
				args = selectionTerms
			).map(_("Message").name)
				.getOrElse("Unknown validation result")

		val isValid = validQuery.hasSolution

		val suggestion =
			if (isValid) {
				None
			} else {
				loadSuggestion(selection)
			}

		ValidationResult(
			isValid = isValid,
			message = message,
			suggestion = suggestion
		)
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
		runSingleSolutionQuery(
			functor = "field_options",
			variableName = "Options",
			args = Array[Term](new Atom(fieldId))
		).map { solution =>
			solution("Options").listToTermArray().toSeq.map { optionTerm =>
				UiOption(
					id = optionTerm.arg(1).name,
					label = optionTerm.arg(2).name
				)
			}
		}.getOrElse(Seq.empty)
	}

	private def loadSuggestion(
										selection: BuildSelection
									): Option[ValidationSuggestion] = {
		runSingleSolutionQuery(
			functor = "suggest_build_fix",
			variableName = "Suggestion",
			args = buildSelectionTerms(selection)
		).map { solution =>
			val term = solution("Suggestion")

			ValidationSuggestion(
				fieldId = term.arg(1).name,
				optionId = term.arg(2).name,
				optionLabel = term.arg(3).name
			)
		}
	}

	private def runSingleSolutionQuery(
													functor: String,
													variableName: String,
													args: Array[Term]
												): Option[Map[String, Term]] = {
		val variable = new Variable(variableName)
		val query = new Query(functor, args :+ variable)

		if (!query.hasSolution) {
			None
		} else {
			Some(query.oneSolution().asScala.toMap)
		}
	}

	private def buildSelectionTerms(
												selection: BuildSelection
											): Array[Term] =
		Array[Term](
			new Atom(selection.cpuId),
			new Atom(selection.motherboardId),
			new Atom(selection.ramId)
		)
}
