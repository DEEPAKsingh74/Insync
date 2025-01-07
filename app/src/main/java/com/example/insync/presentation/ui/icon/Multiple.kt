/*
* Converted using https://composables.com/svgtocompose
*/

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val Multiple: ImageVector
	get() {
		if (_Multiple != null) {
			return _Multiple!!
		}
		_Multiple = ImageVector.Builder(
            name = "Multiple",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
			path(
    			fill = SolidColor(Color.Black),
    			fillAlpha = 1.0f,
    			stroke = null,
    			strokeAlpha = 1.0f,
    			strokeLineWidth = 1.0f,
    			strokeLineCap = StrokeCap.Butt,
    			strokeLineJoin = StrokeJoin.Miter,
    			strokeLineMiter = 1.0f,
    			pathFillType = PathFillType.NonZero
			) {
				moveTo(15f, 0f)
				horizontalLineTo(5f)
				curveTo(2.2430f, 00f, 00f, 2.2430f, 00f, 50f)
				verticalLineToRelative(10f)
				curveToRelative(00f, 2.7570f, 2.2430f, 50f, 50f, 50f)
				horizontalLineToRelative(10f)
				curveToRelative(2.7570f, 00f, 50f, -2.2430f, 50f, -50f)
				verticalLineTo(5f)
				curveToRelative(00f, -2.7570f, -2.2430f, -50f, -50f, -50f)
				close()
				moveToRelative(-1f, 11f)
				horizontalLineToRelative(-3f)
				verticalLineToRelative(3f)
				curveToRelative(00f, 0.5520f, -0.4480f, 10f, -10f, 10f)
				reflectiveCurveToRelative(-1f, -0.448f, -1f, -1f)
				verticalLineToRelative(-3f)
				horizontalLineToRelative(-3f)
				curveToRelative(-0.5520f, 00f, -10f, -0.4480f, -10f, -10f)
				reflectiveCurveToRelative(0.448f, -1f, 1f, -1f)
				horizontalLineToRelative(3f)
				verticalLineToRelative(-3f)
				curveToRelative(00f, -0.5520f, 0.4480f, -10f, 10f, -10f)
				reflectiveCurveToRelative(1f, 0.448f, 1f, 1f)
				verticalLineToRelative(3f)
				horizontalLineToRelative(3f)
				curveToRelative(0.5520f, 00f, 10f, 0.4480f, 10f, 10f)
				reflectiveCurveToRelative(-0.448f, 1f, -1f, 1f)
				close()
				moveToRelative(5f, 13f)
				horizontalLineTo(7f)
				curveToRelative(-0.5520f, 00f, -10f, -0.4480f, -10f, -10f)
				reflectiveCurveToRelative(0.448f, -1f, 1f, -1f)
				horizontalLineToRelative(12f)
				curveToRelative(1.6540f, 00f, 30f, -1.3460f, 30f, -30f)
				verticalLineTo(7f)
				curveToRelative(00f, -0.5520f, 0.4480f, -10f, 10f, -10f)
				reflectiveCurveToRelative(1f, 0.448f, 1f, 1f)
				verticalLineToRelative(12f)
				curveToRelative(00f, 2.7570f, -2.2430f, 50f, -50f, 50f)
				close()
			}
		}.build()
		return _Multiple!!
	}

private var _Multiple: ImageVector? = null
