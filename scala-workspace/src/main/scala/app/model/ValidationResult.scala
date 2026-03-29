// vim: filetype=scala: tabstop=3: shiftwidth=3: noexpandtab
package app.model

final case class ValidationResult(
												isValid: Boolean,
												message: String
											)
