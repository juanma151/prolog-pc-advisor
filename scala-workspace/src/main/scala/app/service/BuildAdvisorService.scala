// vim: filetype=scala: tabstop=3: shiftwidth=3: noexpandtab
package app.service

import app.model.ValidationResult
import app.prolog.PrologGateway
import app.ui.UiField

final class BuildAdvisorService(
											prologGateway: PrologGateway
										) {

	def loadUiFields(): Seq[UiField] =
		prologGateway.loadUiFields()

	def validateSelection(
									selectedCpuId: Option[String],
									selectedMotherboardId: Option[String],
									selectedRamId: Option[String]
								): ValidationResult =
		(selectedCpuId, selectedMotherboardId, selectedRamId) match {
			case (Some(cpuId), Some(motherboardId), Some(ramId)) =>
				prologGateway.validateBuild(cpuId, motherboardId, ramId)

			case _ =>
				ValidationResult(
					isValid = false,
					message = "Please select CPU, motherboard and RAM"
				)
		}
}
