package model;

/**
 * Created by creep on 2017/7/9.
 */
public class Feature {
    private boolean isStatic;
    private boolean isFinal;
    private JavaVisibility visibility = JavaVisibility.DEFAULT;
    private Comment comment;

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public boolean isStatic() {
        return isStatic;
    }

    public void setStatic(boolean aStatic) {
        isStatic = aStatic;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public void setFinal(boolean aFinal) {
        isFinal = aFinal;
    }

    public JavaVisibility getVisibility() {
        return visibility;
    }

    public void setVisibility(JavaVisibility visibility) {
        this.visibility = visibility;
    }
}
