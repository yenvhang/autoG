package top.nvhang.model;

import top.nvhang.core.CodeMaker;

/**
 * Created by creep on 2017/7/9.
 */
public class Comment implements CodeMaker {
    private String comment;

    public Comment(String comment) {
        this.comment = comment;
    }

    public String getFormattedContent() {
        StringBuffer sb =new StringBuffer();
        sb.append("/**");
        sb.append("\n");
        sb.append(" *");
        sb.append(getComment());
        sb.append("\n");
        sb.append("*/");
        sb.append("\n");
        return sb.toString();
    }
    public String getComment() {
        if(comment==null){
            return "";
        }
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


}
