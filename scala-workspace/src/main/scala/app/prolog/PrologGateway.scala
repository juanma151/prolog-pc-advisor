// vim: filetype=scala: tabstop=3: shiftwidth=3: noexpandtab
package app.prolog

import app.model.{BuildSelection, ValidationResult}
import app.ui.UiField

trait PrologGateway {
	def loadUiFields(): Seq[UiField]
	def validateBuild(selection: BuildSelection): ValidationResult
}
