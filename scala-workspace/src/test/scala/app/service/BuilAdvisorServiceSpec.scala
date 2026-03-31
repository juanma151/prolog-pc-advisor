// vim: filetype=scala: tabstop=3: shiftwidth=3: noexpandtab
package app.service

import app.model.BuildSelection
import app.model.ValidationResult
import app.prolog.PrologGateway
import app.ui.UiField
import org.scalatest.funsuite.AnyFunSuite

class BuildAdvisorServiceSpec extends AnyFunSuite {

	test("loadUiFields should delegate to PrologGateway") {
		val gateway = new FakePrologGateway(
			fields = Seq(
				UiField("cpu", "combo", "CPU", 1),
				UiField("motherboard", "combo", "Motherboard", 2),
				UiField("ram", "combo", "RAM", 3)
			),
			validationResult = ValidationResult(
				isValid = true,
				message = "Configuration is valid"
			)
		)

		val service = new BuildAdvisorService(gateway)

		val result = service.loadUiFields()

		assert(result.length == 3)
		assert(result.head.id == "cpu")
	}

	test("validateSelection should return missing selection message when some value is missing") {
		val gateway = new FakePrologGateway(
			fields = Seq.empty,
			validationResult = ValidationResult(
				isValid = true,
				message = "Configuration is valid"
			)
		)

		val service = new BuildAdvisorService(gateway)

		val result = service.validateSelection(
			selectedCpuId = Some("ryzen7_7700"),
			selectedMotherboardId = None,
			selectedRamId = Some("ddr5_32_6000")
		)

		assert(!result.isValid)
		assert(result.message == "Please select CPU, motherboard and RAM")
	}

	test("validateSelection should delegate to PrologGateway when all values are present") {
		val gateway = new FakePrologGateway(
			fields = Seq.empty,
			validationResult = ValidationResult(
				isValid = false,
				message = "CPU and motherboard sockets are not compatible"
			)
		)

		val service = new BuildAdvisorService(gateway)

		val result = service.validateSelection(
			selectedCpuId = Some("ryzen7_7700"),
			selectedMotherboardId = Some("z790_aorus_elite"),
			selectedRamId = Some("ddr5_32_6000")
		)

		assert(!result.isValid)
		assert(result.message == "CPU and motherboard sockets are not compatible")
	}
}

final class FakePrologGateway(
										fields: Seq[UiField],
										validationResult: ValidationResult
									) extends PrologGateway {

	override def loadUiFields(): Seq[UiField] =
		fields

	override def validateBuild(
										selection: BuildSelection
									): ValidationResult =
		validationResult
}
