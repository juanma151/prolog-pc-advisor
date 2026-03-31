// vim: filetype=scala: tabstop=3: shiftwidth=3: noexpandtab
package app.model

final case class BuildSelection(
											cpuId: String,
											motherboardId: String,
											ramId: String
										)
