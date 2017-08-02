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
import top.nvhang.util.BaseUtil;
import top.nvhang.util.DocumentWarpper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yeyh on 2017/7/26.
 */
@Component
public class SqlMapGenerator extends AbstractFileGenerator {
	public static final String PAGE_BEGIN_SQL = "select * from ( select t1.* , rownum linenum from (";
	public static final String PAGE_END_SQL = ") t1 where rownum &lt;= #endIndex#) t2 where t2.linenum\n" +
			"\t\t&gt;= #beginIndex#";
	public static final String PARAMETER_CLASS_ATR = "parameterClass";
	public static final String RESULT_CLASS_ATR = "resultClass";

	@Autowired
	private Context context;



	public void generate() {

		List<DocumentWarpper> documents=genIbatisDocuments();
		for(DocumentWarpper warpper:documents){

			try {
				File file =new File(warpper.getTargetPath());
				if(!file.exists()){
					file.mkdirs();
				}
				FileWriter out = new FileWriter(new File(file,warpper.getFileName()));
				OutputFormat format = new OutputFormat();
				format.setNewlines(true);
				format.setIndent(true);
				XMLWriter xw = new XMLWriter(out,format);
				xw.setEscapeText(false);
				xw.write(warpper.getDocument());
				out.flush();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private List<DocumentWarpper> genIbatisDocuments() {
		List<DocumentWarpper> documentList=new ArrayList<DocumentWarpper>();
		for(Table table:context.getTables()){
			Document document=getBaseDocument();
			documentList.add(new DocumentWarpper(document,
					BaseUtil.caculateAbsolutelyPath(context.getConfig().getProjectPath(),
							table.getTableConfiguration().getSqlMapPath()),
					table.getTableConfiguration().getMapperName()));


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
		Element element=root.addElement("select");
		element.addAttribute("id",table.getTableConfiguration().getSelectCountSqlId());
		element.addAttribute(PARAMETER_CLASS_ATR,table.getTableConfiguration().getDomainObjectQueryName());
		element.addAttribute(RESULT_CLASS_ATR,table.getTableConfiguration().getDomainObjectName());
		element.setText("\nSELECT COUNT(1) \n"+
				"FROM \n"+
				table.getTableConfiguration().getTableName()+"\n"+
				"<include refid=\"pageCondition\" />");
		root.addText("\n");


	}

	private void addRootElement(Document document,Table table) {
		Element root =document.addElement("sqlMap");
		root.addAttribute("namespace",table.getTableName());
		document.setRootElement(root);

	}

	private void addDeleteElement(Element root,Table table) {
		Element element=root.addElement("delete");
		element.addAttribute("id",table.getTableConfiguration().getDeleteObjectSqlId());
		element.addAttribute(PARAMETER_CLASS_ATR,"long");
		element.setText("\n\t\tDELETE FROM "+table.getTableConfiguration().getTableName()+" WHERE ID="+"#id#");
		root.addText("\n");
	}

	private void addUpdateElement(Element root,Table table) {
		Element element=root.addElement("update");
		element.addAttribute("id",table.getTableConfiguration().getUpdateObjectSqlId());
		element.addAttribute(PARAMETER_CLASS_ATR,table.getTableConfiguration().getDomainObjectName());
		StringBuilder sb =new StringBuilder();
		for(Column column:table.getColumnList()){
			sb.append("\n");
			sb.append("\t\t");
			if(column.getFieldName().equals("id")){
				continue;
			}
			sb.append("<isNotNull prepend=\",\" property=\""+column.getFieldName()+"\">"+
					column.getColumnName()+"=#"+column.getFieldName()+"#"+
					"</isNotNull>");
		}
		element.addText("\n");
		element.addText("UPDATE  "+table.getTableConfiguration().getTableName()+
			"<dynamic prepend=\"SET\">"+
			sb.toString()+
			"</dynamic>");
		element.addText("\n");
		element.addText("WHERE ID=#id#");
		root.addText("\n");
	}

	private void addInsertElement(Element root,Table table) {
		Element element =root.addElement("insert");
		element.addAttribute("id",table.getTableConfiguration().getInsertObjectSqlId());
		element.addAttribute(PARAMETER_CLASS_ATR,table.getTableConfiguration().getDomainObjectName());
		StringBuilder sb =new StringBuilder();
		StringBuilder sb2=new StringBuilder();
		if(table.getColumnList()!=null&&table.getColumnList().size()!=0) {
			for (Column column : table.getColumnList()) {
				sb.append("\n");
				sb.append("\t\t");
				sb2.append("\n");
				sb2.append("\t\t");
				sb.append(column.getColumnName());
				sb2.append("#" + column.getFieldName() + "#");
				sb.append(",");
				sb2.append(",");

			}

			String temp = sb.toString().trim();
			String temp2 = sb2.toString().trim();
			if (temp.lastIndexOf(",") == temp.length() - 1) {
				sb.deleteCharAt(sb.lastIndexOf(","));
			}
			if (temp2.lastIndexOf(",") == temp2.length() - 1) {
				sb2.deleteCharAt(sb2.lastIndexOf(","));
			}
		}
		element.addText("\n\t\t");
		element.addText("INSERT INTO "+
		table.getTableConfiguration().getClassName()+
		"(" +
			sb.toString()+
		")"+"\n\t\t"+
				"VALUES(" +
				sb2.toString()+
				")");
		root.addText("\n");
	}

	private void addQueryListPaingElement(Element root,Table table) {
		Element element=root.addElement("select");
		element.addAttribute("id",table.getTableConfiguration().getQueryPageableListSqlId());
		element.addAttribute(PARAMETER_CLASS_ATR,table.getTableConfiguration().getDomainObjectQueryName());
		element.addAttribute(RESULT_CLASS_ATR,table.getTableConfiguration().getDomainObjectName());
		element.addText("\n<include refid=\"pageBeginSql\"/>");
		element.addText("\n");
		element.addText("\t\t");
		element.addText("SELECT <include refid=\"all_columns\"/>"+"\n\t\t"+
		"FROM "+"\n\t\t"+
		table.getTableConfiguration().getTableName()+"\n\t\t"+
		"<dynamic prepend=\"WHERE\">" +"\n\t\t"+

				"<isNotEmpty property=\"\" prepend=\"AND\"> " +
				"</isNotEmpty>" +
				"</dynamic>");
		element.addText("<include refid=\"pageEndSql\"/>");
		root.addText("\n");
	}

	private void addQueryListElement(Element root,Table table) {
		Element element=root.addElement("select");
		element.addAttribute("id",table.getTableConfiguration().getQueryListSqlId());
		element.addAttribute(PARAMETER_CLASS_ATR,table.getTableConfiguration().getDomainObjectQueryName());
		element.addAttribute(RESULT_CLASS_ATR,table.getTableConfiguration().getDomainObjectName());

		element.addText("\nSELECT <include refid=\"all_columns\"/>"+"\n"+
				"FROM "+"\n"+
				table.getTableConfiguration().getTableName()+"\n"+
				"<dynamic prepend=\"WHERE\">" +"\n"+

				"<isNotEmpty property=\"\" prepend=\"AND\"> \n" +
				"</isNotEmpty>" +
				"</dynamic>");
		root.addText("\n");
	}

	private void addSelectUsingIdElement(Element root,Table table) {
		Element element=root.addElement("select");
		element.addAttribute("id",table.getTableConfiguration().getSelectUsingIdSqlId());
		element.addAttribute(PARAMETER_CLASS_ATR,"long");
		element.addAttribute(RESULT_CLASS_ATR,table.getTableConfiguration().getDomainObjectName());
		element.addText("\n");
		element.setText("SELECT \n"+
				"<include refid=\"all_columns\"/>\n" +
				"FROM \n"+
				table.getTableConfiguration().getTableName()+"\n"+
				"WHERE \n"+
				"ID=#id#");
		root.addText("\n");
	}

	private void addResultMapElement(Element root,Table table) {
	}

	private void addTypeAliasElement(Element root,Table table) {

		Element element =root.addElement("typeAlias");
		element.addAttribute("alias",table.getTableConfiguration().getDomainObjectName());
		element.addAttribute("type",table.getTableConfiguration().getDomainPackageName()+
				"."+
				table.getTableConfiguration().getClassName());

		Element element2 =root.addElement("typeAlias");
		element2.addAttribute("alias",table.getTableConfiguration().getDomainObjectQueryName());
		element2.addAttribute("type",table.getTableConfiguration().getDomainQueryObjectPackageName()+
				"."+
				table.getTableConfiguration().getDomainObjectQueryClassName());

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

		root.addText("\n");
	}

	private void addColumnsElement(Element root,Table table) {
		Element element =root.addElement("sql");
		element.addAttribute("id","all_columns");
		StringBuilder sb =new StringBuilder();
		if(table.getColumnList()!=null&&table.getColumnList().size()!=0) {
			for (Column column : table.getColumnList()) {

				sb.append("\n");
				sb.append("\t\t");
				sb.append(column.getPrefix());
				sb.append(column.getColumnName());
				sb.append(" ");
				sb.append(column.getFieldName());
				sb.append(",");

			}


			String temp = sb.toString().trim();

			if (temp.lastIndexOf(",") == temp.length() - 1) {
				sb.deleteCharAt(sb.lastIndexOf(","));
			}
			element.setText(sb.toString());
		}
		root.addText("\n");


	}

	public Document getBaseDocument(){
		Document document = DocumentHelper.createDocument();
		document.addDocType("sqlMap","-//ibatis.apache.org//DTD SQL Map 2.0//EN\" \"http://ibatis.apache.org/dtd/sql-map-2.dtd","");
		return document;
	}

}
