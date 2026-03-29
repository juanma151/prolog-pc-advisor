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
	}

	test("ValidationResult should preserve invalid result") {
		val result = ValidationResult(
			isValid = false,
			message = "Configuration is not valid"
		)

		assert(!result.isValid)
		assert(result.message == "Configuration is not valid")
	}
}
