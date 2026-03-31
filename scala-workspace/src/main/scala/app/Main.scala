// vim: filetype=scala: tabstop=3: shiftwidth=3: noexpandtab
package app

import app.prolog.JplPrologGateway
import app.service.BuildAdvisorService
import app.ui.{FormRenderer, UiOption}
import scalafx.application.JFXApp3
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.control.{Button, ComboBox, Label}
import scalafx.scene.layout.VBox

object Main extends JFXApp3 {
	override def start(): Unit = {
		val service = new BuildAdvisorService(new JplPrologGateway())
		val fields = service.loadUiFields()
		val renderedForm = FormRenderer.render(fields)

		val resultLabel = new Label("Select components and validate")

		val validateButton = new Button("Validate configuration") {
			onAction = _ => {
				val result = service.validateSelection(
					selectedCpuId = selectedOption(renderedForm.comboBoxes("cpu")).map(_.id),
					selectedMotherboardId = selectedOption(renderedForm.comboBoxes("motherboard")).map(_.id),
					selectedRamId = selectedOption(renderedForm.comboBoxes("ram")).map(_.id)
				)

				resultLabel.text = result.message
			}
		}

		stage = new JFXApp3.PrimaryStage {
			title = "Prolog PC Advisor"

			scene = new Scene(520, 420) {
				root = new VBox {
					padding = Insets(20)
					spacing = 15
					children = Seq(
						renderedForm.container,
						validateButton,
						resultLabel
					)
				}
			}
		}
	}

	private def selectedOption(combo: ComboBox[UiOption]): Option[UiOption] =
		Option(combo.delegate.getSelectionModel.getSelectedItem)
}
