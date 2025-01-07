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

public val Bell: ImageVector
	get() {
		if (_Bell != null) {
			return _Bell!!
		}
		_Bell = ImageVector.Builder(
            name = "Bell",
            defaultWidth = 512.dp,
            defaultHeight = 512.dp,
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
				moveTo(4.068f, 18f)
				horizontalLineTo(19.724f)
				arcToRelative(3f, 3f, 0f, isMoreThanHalf = false, isPositiveArc = false, 2.821f, -4.021f)
				lineTo(19.693f, 6.094f)
				arcTo(8.323f, 8.323f, 0f, isMoreThanHalf = false, isPositiveArc = false, 11.675f, 0f)
				horizontalLineToRelative(0f)
				arcTo(8.321f, 8.321f, 0f, isMoreThanHalf = false, isPositiveArc = false, 3.552f, 6.516f)
				lineToRelative(-2.35f, 7.6f)
				arcTo(3f, 3f, 0f, isMoreThanHalf = false, isPositiveArc = false, 4.068f, 18f)
				close()
			}
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
				moveTo(7.1f, 20f)
				arcToRelative(5f, 5f, 0f, isMoreThanHalf = false, isPositiveArc = false, 9.8f, 0f)
				close()
			}
		}.build()
		return _Bell!!
	}

private var _Bell: ImageVector? = null
