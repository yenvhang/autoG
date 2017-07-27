package top.nvhang.generator;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.nvhang.core.Context;
import top.nvhang.model.db.Column;
import top.nvhang.model.db.Table;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yeyh on 2017/7/26.
 */
@Component
public class SqlMapGenerator implements Generator {
	@Autowired
	private Context context;



	public void generate() {

		List<Document> documents=genIbatisDocuments();
		for(Document document:documents){

			try {
				FileWriter out = new FileWriter("sqlmap-test.xml");
				document.write(out);
				out.flush();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private List<Document> genIbatisDocuments() {
		List<Document> documentList=new ArrayList<Document>();
		for(Table table:context.getTables()){
			Document document=getBaseDocument();
			documentList.add(document);


			addRootElement(document,table);

			addCommonSeqmentElement(document.getRootElement(),table);
			addTypeAliasElement(document.getRootElement(),table);
			addResultMapElement(document.getRootElement(),table);

			addSelectUsingIdElement(document.getRootElement(),table);
			addQueryListElement(document.getRootElement(),table);
			addQueryListPaingElement(document.getRootElement(),table);

			addInsertElement(document.getRootElement(),table);
			addUpdateElement(document.getRootElement(),table);
			addDeleteElement(document.getRootElement(),table);

		}

		return documentList;
	}

	private void addRootElement(Document document,Table table) {
		Element root =document.addElement("sqlMap");
		root.addAttribute("namespace",table.getTableName());
		document.setRootElement(root);
	}

	private void addDeleteElement(Element root,Table table) {
	}

	private void addUpdateElement(Element root,Table table) {
	}

	private void addInsertElement(Element root,Table table) {
	}

	private void addQueryListPaingElement(Element root,Table table) {
	}

	private void addQueryListElement(Element root,Table table) {
	}

	private void addSelectUsingIdElement(Element root,Table table) {
	}

	private void addResultMapElement(Element root,Table table) {
	}

	private void addTypeAliasElement(Element root,Table table) {
	}

	private void addCommonSeqmentElement(Element root,Table table) {
		addColumnsElement(root,table);
		addPagingElement(root,table);
	}

	private void addPagingElement(Element root,Table table) {
	}

	private void addColumnsElement(Element root,Table table) {
		Element element =root.addElement("sql");
		element.addAttribute("id","all_cloumns");
		StringBuilder sb =new StringBuilder();
		for(Column column:table.getColumnList()){
			sb.append(column.getPrefix());
			sb.append(column.getColumnName());
			sb.append(" ");
			sb.append(column.getFieldName());
			sb.append(",");
			sb.append("\n");
		}

		String temp=sb.toString().trim();

		if(temp.lastIndexOf(",")==temp.length()-1){
			sb.deleteCharAt(sb.lastIndexOf(","));
		}
		element.setText(sb.toString());

	}

	public Document getBaseDocument(){
		Document document = DocumentHelper.createDocument();
		document.addDocType("sqlmap","-//ibatis.apache.org//DTD SQL Map 2.0//EN\" \"http://ibatis.apache.org/dtd/sql-map-2.dtd","");
		return document;
	}
}
