package top.nvhang.model;

import top.nvhang.core.CodeMaker;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by creep on 2017/7/9.
 */
public class Feature implements CodeMaker{
    private boolean isStatic;
    private boolean isFinal;
    private JavaVisibility visibility = JavaVisibility.DEFAULT;
    private Comment comment;
    private List<String> annotations;
    protected Set<Imported> importedSet=new HashSet<Imported>();
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

    public List<String> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<String> annotations) {
        this.annotations = annotations;
    }

    public String getFormattedContent() {
        StringBuilder sb =new StringBuilder();
        if(getComment()!=null){
            sb.append(getComment().getFormattedContent());
        }
        if(annotations!=null&&annotations.size()!=0){

            for(String annotation:annotations){
                sb.append(annotation);
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public void addAnnotation(String annotation,Set<Imported> importedSet){
        if(annotations==null){
            annotations=new ArrayList<String>();
        }
        annotations.add(annotation);
        if("@Autowired".equals(annotation)){
            importedSet.add(new Imported("org.springframework.beans.factory.annotation.Autowired;"));
        }
    }


}
