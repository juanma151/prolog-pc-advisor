// vim: filetype=scala: tabstop=3: shiftwidth=3: noexpandtab
package app.ui

final case class UiField(
									id: String,
									controlType: String,
									label: String,
									order: Int
								) {
	def isCombo: Boolean =
		controlType == "combo"
}
