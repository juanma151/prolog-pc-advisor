// vim: filetype=scala: tabstop=3: shiftwidth=3: noexpandtab
package app.ui

final case class UiOption(
									id: String, 
									label: String
								)

final case class UiField(
									id: String, 
									controlType: String, 
									label: String, 
									order: Int, 
									options: Seq[UiOption] = Seq.empty
								) {
	def isCombo: Boolean =
		controlType == "combo"
}
