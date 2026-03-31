// vim: filetype=scala: tabstop=3: shiftwidth=3: noexpandtab
package app.model

import org.scalatest.funsuite.AnyFunSuite

class ValidationResultSpec extends AnyFunSuite {

	test("ValidationResult should preserve valid result") {
		val result = ValidationResult(
			isValid = true,
			message = "Configuration is valid"
		)

		assert(result.isValid)
		assert(result.message == "Configuration is valid")
		assert(result.suggestion.isEmpty)
	}

	test("ValidationResult should preserve invalid result") {
		val result = ValidationResult(
			isValid = false,
			message = "Configuration is not valid"
		)

		assert(!result.isValid)
		assert(result.message == "Configuration is not valid")
		assert(result.suggestion.isEmpty)
	}

	test("ValidationResult should preserve suggestion") {
		val result = ValidationResult(
			isValid = false,
			message = "CPU and motherboard sockets are not compatible",
			suggestion = Some(
				ValidationSuggestion(
					fieldId = "motherboard",
					optionId = "b650_tomahawk",
					optionLabel = "MSI MAG B650 Tomahawk"
				)
			)
		)

		assert(!result.isValid)
		assert(result.suggestion.nonEmpty)
		assert(result.suggestion.exists(_.fieldId == "motherboard"))
	}
}
