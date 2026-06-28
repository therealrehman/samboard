package com.google.android.material.shape;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import com.google.android.material.shadow.ShadowRenderer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ShapePath {
    protected static final float ANGLE_LEFT = 180.0f;
    private static final float ANGLE_UP = 270.0f;
    private boolean containsIncompatibleShadowOp;

    @Deprecated
    public float currentShadowAngle;

    @Deprecated
    public float endShadowAngle;

    @Deprecated
    public float endX;

    @Deprecated
    public float endY;
    private final List<PathOperation> operations = new ArrayList();
    private final List<ShadowCompatOperation> shadowCompatOperations = new ArrayList();

    @Deprecated
    public float startX;

    @Deprecated
    public float startY;

    public static class ArcShadowOperation extends ShadowCompatOperation {
        private final PathArcOperation operation;

        public ArcShadowOperation(PathArcOperation pathArcOperation) {
            this.operation = pathArcOperation;
        }

        @Override // com.google.android.material.shape.ShapePath.ShadowCompatOperation
        public void draw(Matrix matrix, ShadowRenderer shadowRenderer, int i5, Canvas canvas) {
            shadowRenderer.drawCornerShadow(canvas, matrix, new RectF(this.operation.getLeft(), this.operation.getTop(), this.operation.getRight(), this.operation.getBottom()), i5, this.operation.getStartAngle(), this.operation.getSweepAngle());
        }
    }

    public static class InnerCornerShadowOperation extends ShadowCompatOperation {
        private final PathLineOperation operation1;
        private final PathLineOperation operation2;
        private final float startX;
        private final float startY;

        public InnerCornerShadowOperation(PathLineOperation pathLineOperation, PathLineOperation pathLineOperation2, float f2, float f7) {
            this.operation1 = pathLineOperation;
            this.operation2 = pathLineOperation2;
            this.startX = f2;
            this.startY = f7;
        }

        @Override // com.google.android.material.shape.ShapePath.ShadowCompatOperation
        public void draw(Matrix matrix, ShadowRenderer shadowRenderer, int i5, Canvas canvas) {
            float sweepAngle = getSweepAngle();
            if (sweepAngle > 0.0f) {
                return;
            }
            double dHypot = Math.hypot(this.operation1.f10113x - this.startX, this.operation1.f10114y - this.startY);
            double dHypot2 = Math.hypot(this.operation2.f10113x - this.operation1.f10113x, this.operation2.f10114y - this.operation1.f10114y);
            float fMin = (float) Math.min(i5, Math.min(dHypot, dHypot2));
            double d8 = fMin;
            double dTan = Math.tan(Math.toRadians((-sweepAngle) / 2.0f)) * d8;
            if (dHypot > dTan) {
                RectF rectF = new RectF(0.0f, 0.0f, (float) (dHypot - dTan), 0.0f);
                this.renderMatrix.set(matrix);
                this.renderMatrix.preTranslate(this.startX, this.startY);
                this.renderMatrix.preRotate(getStartAngle());
                shadowRenderer.drawEdgeShadow(canvas, this.renderMatrix, rectF, i5);
            }
            float f2 = 2.0f * fMin;
            RectF rectF2 = new RectF(0.0f, 0.0f, f2, f2);
            this.renderMatrix.set(matrix);
            this.renderMatrix.preTranslate(this.operation1.f10113x, this.operation1.f10114y);
            this.renderMatrix.preRotate(getStartAngle());
            this.renderMatrix.preTranslate((float) ((-dTan) - d8), (-2.0f) * fMin);
            shadowRenderer.drawInnerCornerShadow(canvas, this.renderMatrix, rectF2, (int) fMin, 450.0f, sweepAngle, new float[]{(float) (d8 + dTan), f2});
            if (dHypot2 > dTan) {
                RectF rectF3 = new RectF(0.0f, 0.0f, (float) (dHypot2 - dTan), 0.0f);
                this.renderMatrix.set(matrix);
                this.renderMatrix.preTranslate(this.operation1.f10113x, this.operation1.f10114y);
                this.renderMatrix.preRotate(getEndAngle());
                this.renderMatrix.preTranslate((float) dTan, 0.0f);
                shadowRenderer.drawEdgeShadow(canvas, this.renderMatrix, rectF3, i5);
            }
        }

        public float getEndAngle() {
            return (float) Math.toDegrees(Math.atan((this.operation2.f10114y - this.operation1.f10114y) / (this.operation2.f10113x - this.operation1.f10113x)));
        }

        public float getStartAngle() {
            return (float) Math.toDegrees(Math.atan((this.operation1.f10114y - this.startY) / (this.operation1.f10113x - this.startX)));
        }

        public float getSweepAngle() {
            float endAngle = ((getEndAngle() - getStartAngle()) + 360.0f) % 360.0f;
            return endAngle <= ShapePath.ANGLE_LEFT ? endAngle : endAngle - 360.0f;
        }
    }

    public static class LineShadowOperation extends ShadowCompatOperation {
        private final PathLineOperation operation;
        private final float startX;
        private final float startY;

        public LineShadowOperation(PathLineOperation pathLineOperation, float f2, float f7) {
            this.operation = pathLineOperation;
            this.startX = f2;
            this.startY = f7;
        }

        @Override // com.google.android.material.shape.ShapePath.ShadowCompatOperation
        public void draw(Matrix matrix, ShadowRenderer shadowRenderer, int i5, Canvas canvas) {
            RectF rectF = new RectF(0.0f, 0.0f, (float) Math.hypot(this.operation.f10114y - this.startY, this.operation.f10113x - this.startX), 0.0f);
            this.renderMatrix.set(matrix);
            this.renderMatrix.preTranslate(this.startX, this.startY);
            this.renderMatrix.preRotate(getAngle());
            shadowRenderer.drawEdgeShadow(canvas, this.renderMatrix, rectF, i5);
        }

        public float getAngle() {
            return (float) Math.toDegrees(Math.atan((this.operation.f10114y - this.startY) / (this.operation.f10113x - this.startX)));
        }
    }

    public static class PathArcOperation extends PathOperation {
        private static final RectF rectF = new RectF();

        @Deprecated
        public float bottom;

        @Deprecated
        public float left;

        @Deprecated
        public float right;

        @Deprecated
        public float startAngle;

        @Deprecated
        public float sweepAngle;

        @Deprecated
        public float top;

        public PathArcOperation(float f2, float f7, float f9, float f10) {
            setLeft(f2);
            setTop(f7);
            setRight(f9);
            setBottom(f10);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public float getBottom() {
            return this.bottom;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public float getLeft() {
            return this.left;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public float getRight() {
            return this.right;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public float getStartAngle() {
            return this.startAngle;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public float getSweepAngle() {
            return this.sweepAngle;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public float getTop() {
            return this.top;
        }

        private void setBottom(float f2) {
            this.bottom = f2;
        }

        private void setLeft(float f2) {
            this.left = f2;
        }

        private void setRight(float f2) {
            this.right = f2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setStartAngle(float f2) {
            this.startAngle = f2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSweepAngle(float f2) {
            this.sweepAngle = f2;
        }

        private void setTop(float f2) {
            this.top = f2;
        }

        @Override // com.google.android.material.shape.ShapePath.PathOperation
        public void applyToPath(Matrix matrix, Path path) {
            Matrix matrix2 = this.matrix;
            matrix.invert(matrix2);
            path.transform(matrix2);
            RectF rectF2 = rectF;
            rectF2.set(getLeft(), getTop(), getRight(), getBottom());
            path.arcTo(rectF2, getStartAngle(), getSweepAngle(), false);
            path.transform(matrix);
        }
    }

    public static class PathCubicOperation extends PathOperation {
        private float controlX1;
        private float controlX2;
        private float controlY1;
        private float controlY2;
        private float endX;
        private float endY;

        public PathCubicOperation(float f2, float f7, float f9, float f10, float f11, float f12) {
            setControlX1(f2);
            setControlY1(f7);
            setControlX2(f9);
            setControlY2(f10);
            setEndX(f11);
            setEndY(f12);
        }

        private float getControlX1() {
            return this.controlX1;
        }

        private float getControlX2() {
            return this.controlX2;
        }

        private float getControlY1() {
            return this.controlY1;
        }

        private float getControlY2() {
            return this.controlY1;
        }

        private float getEndX() {
            return this.endX;
        }

        private float getEndY() {
            return this.endY;
        }

        private void setControlX1(float f2) {
            this.controlX1 = f2;
        }

        private void setControlX2(float f2) {
            this.controlX2 = f2;
        }

        private void setControlY1(float f2) {
            this.controlY1 = f2;
        }

        private void setControlY2(float f2) {
            this.controlY2 = f2;
        }

        private void setEndX(float f2) {
            this.endX = f2;
        }

        private void setEndY(float f2) {
            this.endY = f2;
        }

        @Override // com.google.android.material.shape.ShapePath.PathOperation
        public void applyToPath(Matrix matrix, Path path) {
            Matrix matrix2 = this.matrix;
            matrix.invert(matrix2);
            path.transform(matrix2);
            path.cubicTo(this.controlX1, this.controlY1, this.controlX2, this.controlY2, this.endX, this.endY);
            path.transform(matrix);
        }
    }

    public static class PathLineOperation extends PathOperation {

        /* JADX INFO: renamed from: x, reason: collision with root package name */
        private float f10113x;

        /* JADX INFO: renamed from: y, reason: collision with root package name */
        private float f10114y;

        @Override // com.google.android.material.shape.ShapePath.PathOperation
        public void applyToPath(Matrix matrix, Path path) {
            Matrix matrix2 = this.matrix;
            matrix.invert(matrix2);
            path.transform(matrix2);
            path.lineTo(this.f10113x, this.f10114y);
            path.transform(matrix);
        }
    }

    public static abstract class PathOperation {
        protected final Matrix matrix = new Matrix();

        public abstract void applyToPath(Matrix matrix, Path path);
    }

    public static class PathQuadOperation extends PathOperation {

        @Deprecated
        public float controlX;

        @Deprecated
        public float controlY;

        @Deprecated
        public float endX;

        @Deprecated
        public float endY;

        private float getControlX() {
            return this.controlX;
        }

        private float getControlY() {
            return this.controlY;
        }

        private float getEndX() {
            return this.endX;
        }

        private float getEndY() {
            return this.endY;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setControlX(float f2) {
            this.controlX = f2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setControlY(float f2) {
            this.controlY = f2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setEndX(float f2) {
            this.endX = f2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setEndY(float f2) {
            this.endY = f2;
        }

        @Override // com.google.android.material.shape.ShapePath.PathOperation
        public void applyToPath(Matrix matrix, Path path) {
            Matrix matrix2 = this.matrix;
            matrix.invert(matrix2);
            path.transform(matrix2);
            path.quadTo(getControlX(), getControlY(), getEndX(), getEndY());
            path.transform(matrix);
        }
    }

    public static abstract class ShadowCompatOperation {
        static final Matrix IDENTITY_MATRIX = new Matrix();
        final Matrix renderMatrix = new Matrix();

        public abstract void draw(Matrix matrix, ShadowRenderer shadowRenderer, int i5, Canvas canvas);

        public final void draw(ShadowRenderer shadowRenderer, int i5, Canvas canvas) {
            draw(IDENTITY_MATRIX, shadowRenderer, i5, canvas);
        }
    }

    public ShapePath() {
        reset(0.0f, 0.0f);
    }

    private void addConnectingShadowIfNecessary(float f2) {
        if (getCurrentShadowAngle() == f2) {
            return;
        }
        float currentShadowAngle = ((f2 - getCurrentShadowAngle()) + 360.0f) % 360.0f;
        if (currentShadowAngle > ANGLE_LEFT) {
            return;
        }
        PathArcOperation pathArcOperation = new PathArcOperation(getEndX(), getEndY(), getEndX(), getEndY());
        pathArcOperation.setStartAngle(getCurrentShadowAngle());
        pathArcOperation.setSweepAngle(currentShadowAngle);
        this.shadowCompatOperations.add(new ArcShadowOperation(pathArcOperation));
        setCurrentShadowAngle(f2);
    }

    private void addShadowCompatOperation(ShadowCompatOperation shadowCompatOperation, float f2, float f7) {
        addConnectingShadowIfNecessary(f2);
        this.shadowCompatOperations.add(shadowCompatOperation);
        setCurrentShadowAngle(f7);
    }

    private float getCurrentShadowAngle() {
        return this.currentShadowAngle;
    }

    private float getEndShadowAngle() {
        return this.endShadowAngle;
    }

    private void setCurrentShadowAngle(float f2) {
        this.currentShadowAngle = f2;
    }

    private void setEndShadowAngle(float f2) {
        this.endShadowAngle = f2;
    }

    private void setEndX(float f2) {
        this.endX = f2;
    }

    private void setEndY(float f2) {
        this.endY = f2;
    }

    private void setStartX(float f2) {
        this.startX = f2;
    }

    private void setStartY(float f2) {
        this.startY = f2;
    }

    public void addArc(float f2, float f7, float f9, float f10, float f11, float f12) {
        PathArcOperation pathArcOperation = new PathArcOperation(f2, f7, f9, f10);
        pathArcOperation.setStartAngle(f11);
        pathArcOperation.setSweepAngle(f12);
        this.operations.add(pathArcOperation);
        ArcShadowOperation arcShadowOperation = new ArcShadowOperation(pathArcOperation);
        float f13 = f11 + f12;
        boolean z9 = f12 < 0.0f;
        if (z9) {
            f11 = (f11 + ANGLE_LEFT) % 360.0f;
        }
        addShadowCompatOperation(arcShadowOperation, f11, z9 ? (ANGLE_LEFT + f13) % 360.0f : f13);
        double d8 = f13;
        setEndX((((f9 - f2) / 2.0f) * ((float) Math.cos(Math.toRadians(d8)))) + ((f2 + f9) * 0.5f));
        setEndY((((f10 - f7) / 2.0f) * ((float) Math.sin(Math.toRadians(d8)))) + ((f7 + f10) * 0.5f));
    }

    public void applyToPath(Matrix matrix, Path path) {
        int size = this.operations.size();
        for (int i5 = 0; i5 < size; i5++) {
            this.operations.get(i5).applyToPath(matrix, path);
        }
    }

    public boolean containsIncompatibleShadowOp() {
        return this.containsIncompatibleShadowOp;
    }

    public ShadowCompatOperation createShadowCompatOperation(Matrix matrix) {
        addConnectingShadowIfNecessary(getEndShadowAngle());
        final Matrix matrix2 = new Matrix(matrix);
        final ArrayList arrayList = new ArrayList(this.shadowCompatOperations);
        return new ShadowCompatOperation() { // from class: com.google.android.material.shape.ShapePath.1
            @Override // com.google.android.material.shape.ShapePath.ShadowCompatOperation
            public void draw(Matrix matrix3, ShadowRenderer shadowRenderer, int i5, Canvas canvas) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    ((ShadowCompatOperation) it.next()).draw(matrix2, shadowRenderer, i5, canvas);
                }
            }
        };
    }

    public void cubicToPoint(float f2, float f7, float f9, float f10, float f11, float f12) {
        this.operations.add(new PathCubicOperation(f2, f7, f9, f10, f11, f12));
        this.containsIncompatibleShadowOp = true;
        setEndX(f11);
        setEndY(f12);
    }

    public float getEndX() {
        return this.endX;
    }

    public float getEndY() {
        return this.endY;
    }

    public float getStartX() {
        return this.startX;
    }

    public float getStartY() {
        return this.startY;
    }

    public void lineTo(float f2, float f7) {
        PathLineOperation pathLineOperation = new PathLineOperation();
        pathLineOperation.f10113x = f2;
        pathLineOperation.f10114y = f7;
        this.operations.add(pathLineOperation);
        LineShadowOperation lineShadowOperation = new LineShadowOperation(pathLineOperation, getEndX(), getEndY());
        addShadowCompatOperation(lineShadowOperation, lineShadowOperation.getAngle() + ANGLE_UP, lineShadowOperation.getAngle() + ANGLE_UP);
        setEndX(f2);
        setEndY(f7);
    }

    public void quadToPoint(float f2, float f7, float f9, float f10) {
        PathQuadOperation pathQuadOperation = new PathQuadOperation();
        pathQuadOperation.setControlX(f2);
        pathQuadOperation.setControlY(f7);
        pathQuadOperation.setEndX(f9);
        pathQuadOperation.setEndY(f10);
        this.operations.add(pathQuadOperation);
        this.containsIncompatibleShadowOp = true;
        setEndX(f9);
        setEndY(f10);
    }

    public void reset(float f2, float f7) {
        reset(f2, f7, ANGLE_UP, 0.0f);
    }

    public void reset(float f2, float f7, float f9, float f10) {
        setStartX(f2);
        setStartY(f7);
        setEndX(f2);
        setEndY(f7);
        setCurrentShadowAngle(f9);
        setEndShadowAngle((f9 + f10) % 360.0f);
        this.operations.clear();
        this.shadowCompatOperations.clear();
        this.containsIncompatibleShadowOp = false;
    }

    public ShapePath(float f2, float f7) {
        reset(f2, f7);
    }

    public void lineTo(float f2, float f7, float f9, float f10) {
        if ((Math.abs(f2 - getEndX()) < 0.001f && Math.abs(f7 - getEndY()) < 0.001f) || (Math.abs(f2 - f9) < 0.001f && Math.abs(f7 - f10) < 0.001f)) {
            lineTo(f9, f10);
            return;
        }
        PathLineOperation pathLineOperation = new PathLineOperation();
        pathLineOperation.f10113x = f2;
        pathLineOperation.f10114y = f7;
        this.operations.add(pathLineOperation);
        PathLineOperation pathLineOperation2 = new PathLineOperation();
        pathLineOperation2.f10113x = f9;
        pathLineOperation2.f10114y = f10;
        this.operations.add(pathLineOperation2);
        InnerCornerShadowOperation innerCornerShadowOperation = new InnerCornerShadowOperation(pathLineOperation, pathLineOperation2, getEndX(), getEndY());
        if (innerCornerShadowOperation.getSweepAngle() > 0.0f) {
            lineTo(f2, f7);
            lineTo(f9, f10);
        } else {
            addShadowCompatOperation(innerCornerShadowOperation, innerCornerShadowOperation.getStartAngle() + ANGLE_UP, innerCornerShadowOperation.getEndAngle() + ANGLE_UP);
            setEndX(f9);
            setEndY(f10);
        }
    }
}
