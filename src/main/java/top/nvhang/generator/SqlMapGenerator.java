package top.nvhang.generator;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
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
	public static final String PAGE_BEGIN_SQL = "select * from ( select t1.* , rownum linenum from (";
	public static final String PAGE_END_SQL = ") t1 where rownum &lt;= #endIndex#) t2 where t2.linenum\n" +
			"\t\t&gt;= #beginIndex#";
	public static final String PARAMETER_CLASS_ATR = "parameterClass";
	public static final String RESULT_CLASS_ATR = "resultClass";

	@Autowired
	private Context context;



	public void generate() {

		List<Document> documents=genIbatisDocuments();
		for(Document document:documents){

			try {
				FileWriter out = new FileWriter("sqlmap-test.xml");
				OutputFormat format = OutputFormat.createPrettyPrint();
				XMLWriter xw = new XMLWriter(out,format);
				xw.setEscapeText(false);
				xw.write(document);
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
			addTypeAliasElement(document.getRootElement(),table);
			addResultMapElement(document.getRootElement(),table);
			addCommonSeqmentElement(document.getRootElement(),table);

			addSelectCountElement(document.getRootElement(),table);

			addSelectUsingIdElement(document.getRootElement(),table);
			addQueryListElement(document.getRootElement(),table);
			addQueryListPaingElement(document.getRootElement(),table);

			addInsertElement(document.getRootElement(),table);
			addUpdateElement(document.getRootElement(),table);
			addDeleteElement(document.getRootElement(),table);

		}

		return documentList;
	}

	private void addSelectCountElement(Element root, Table table) {
		Element element=root.addElement("SELECT");
		element.addAttribute("id","selectCount");
		element.addAttribute(PARAMETER_CLASS_ATR,table.getTableConfiguration().getDomainObjectQueryName());
		element.addAttribute(RESULT_CLASS_ATR,table.getTableConfiguration().getDomainObjectName());
		element.setText("SELECT COUNT(1) \n"+
				"FROM \n"+
				table.getTableConfiguration().getTableName()+"\n"+
				"<include refid=\"pageCondition\" />");



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
		Element element=root.addElement("SELECT");
		element.addAttribute("id","select"+table.getTableConfiguration().getClassName()+"UsingId");
		element.addAttribute(PARAMETER_CLASS_ATR,"long");
		element.addAttribute(RESULT_CLASS_ATR,table.getTableConfiguration().getDomainObjectName());
		element.setText("SELECT \n"+
				"<include refid=\"all_cloumns\"/>" +
				"FROM \n"+
				table.getTableConfiguration().getTableName()+"\n"+
				"WHERE \n"+
				"ID=#id#");

	}

	private void addResultMapElement(Element root,Table table) {
	}

	private void addTypeAliasElement(Element root,Table table) {

		Element element =root.addElement("typeAlias");
		element.addAttribute("alias",table.getTableConfiguration().getDomainObjectName());
		element.addAttribute("type",table.getTableConfiguration().getPackageName()+"."+table.getTableConfiguration().getClassName());

		Element element2 =root.addElement("typeAlias");
		element2.addAttribute("alias",table.getTableConfiguration().getDomainObjectQueryName());
		element2.addAttribute("type",table.getTableConfiguration().getPackageName()+
				".query."+
				table.getTableConfiguration().getClassName()+
				"Query");
	}

	private void addCommonSeqmentElement(Element root,Table table) {
		addColumnsElement(root,table);
		addPagingElement(root,table);
	}

	private void addPagingElement(Element root,Table table) {
		Element element =root.addElement("sql");
		element.addAttribute("id","pageBeginSql");
		element.setText(PAGE_BEGIN_SQL);
		Element element1 =root.addElement("sql");
		element1.setText(PAGE_END_SQL);
		element1.addAttribute("id","pageEndSql");
		Element element2=root.addElement("sql");
		element2.addAttribute("id","pageCondition");
		element2.setText("<dynamic prepend=\"where\">\n" +
				"<isNotEmpty property=\"\" prepend=\"AND\">\n" +

				"</isNotEmpty>\n" +
				"</dynamic>");

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
	/**
	 * element.addAttribute(PARAMETER_CLASS_ATR,table.getTableConfiguration().getDomainObjectQueryName());
	 element.addAttribute(RESULT_CLASS_ATR,table.getTableConfiguration().getDomainObjectName());
	 element.setText("SELECT \n"+
	 "<include refid=\"all_cloumns\"" +
	 "FROM \n"+
	 table.getTableConfiguration().getTableName()+"\n"+
	 "<include refid=\"pageCondition\"");
	 */
}
