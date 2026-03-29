// vim: filetype=scala: tabstop=3: shiftwidth=3: noexpandtab
package app

import app.prolog.JplPrologGateway
import app.ui.{FormRenderer, UiOption}
import scalafx.application.JFXApp3
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.control.{Button, ComboBox, Label}
import scalafx.scene.layout.VBox

object Main extends JFXApp3 {
	override def start(): Unit = {
		val gateway = new JplPrologGateway()
		val fields = gateway.loadUiFields()
		val renderedForm = FormRenderer.render(fields)

		val resultLabel = new Label("Select components and validate")

		val validateButton = new Button("Validate configuration") {
			onAction = _ => {
				val selectedCpu = selectedOption(renderedForm.comboBoxes("cpu"))
				val selectedBoard = selectedOption(renderedForm.comboBoxes("motherboard"))
				val selectedRam = selectedOption(renderedForm.comboBoxes("ram"))

				(selectedCpu, selectedBoard, selectedRam) match {
					case (Some(cpu), Some(board), Some(ram)) =>
						val result = gateway.validateBuild(
							cpu.id,
							board.id,
							ram.id
						)

						resultLabel.text = result.message

					case _ =>
						resultLabel.text = "Please select CPU, motherboard and RAM"
				}
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
