// vim: filetype=scala: tabstop=3: shiftwidth=3: noexpandtab
package app.model

final case class ValidationSuggestion(
													fieldId: String,
													optionId: String,
													optionLabel: String
												)

final case class ValidationResult(
												isValid: Boolean,
												message: String,
												suggestion: Option[ValidationSuggestion] = None
											)
