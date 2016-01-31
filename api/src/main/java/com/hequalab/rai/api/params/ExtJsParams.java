package com.hequalab.rai.api.params;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ExtJsParams {

	private String select = " ";
	private String selectObject = " ";
	private String where = " ";
	private String order = " ";

	public ExtJsParams(String select, String selectObject) throws Exception {
		super();
		this.select = select;
		this.selectObject = selectObject;
	}

	public String addFilters(String input) throws Exception {

		if (input.length() == 0 || input == null)
			return null;

		ObjectMapper mapper = new ObjectMapper();
		JsonNode nodes = mapper.readTree(input);

		for (JsonNode jsonNode : nodes) {
			if (where.length() > 1)
				where += " AND ";
			String operator = "like";
			String value = jsonNode.get("value").toString()
					.replaceAll("\"", "");
			
			String property = jsonNode.get("property").toString()
					.replaceAll("\"", "");

			if (jsonNode.get("operator") != null) {
				operator = jsonNode.get("operator").toString().replaceAll("\"", "");
			}
			
			
			switch (operator.toLowerCase()) {
			case "like":
				operator = " LIKE ";
				value = "'%" + value + "%'";
				break;
			case "in":
				operator = " = ";
				where += "( " ;
				String[] values = value.replace("[", "").replace("]", "").split(",");
				int index = 0;
				for (String v : values) {
					value ="'" + v + "'";
					if (index++ > 0) where += " OR ";
					where += selectObject + "." + property + operator + value;
					//FUXME: Non funziona
				}
				where += " )";
				continue;
			
			case "lt":
				operator = " <= ";
				value = "" + value + "";
				break;

			case "gt":
				operator = " >= ";
				value = "" + value + "";
				break;

			case "eq":
				operator = " = ";
				value = "" + value + "";
				break;
			case "streq":
				operator = " = ";
				value = "'" + value + "'";
				break;
			case "strnoteq":
				operator = " <> ";
				value = "'" + value + "'";
				break;
			    
			}
			
			
			where += selectObject + "." + property + operator + value;
		}
		System.out.println(" QUERY SELEZIONATA ::::::  " + where);
		return where;

	}

	public String addOrders(String input) throws Exception {

		if (input.length() == 0 || input == null)
			return null;

		ObjectMapper mapper = new ObjectMapper();
		JsonNode nodes = mapper.readTree(input);

		for (JsonNode jsonNode : nodes) {
			if (order.length() > 1)
				order += ", ";
			order += selectObject + "."
					+ jsonNode.get("property").toString().replaceAll("\"", "")
					+ " "
					+ jsonNode.get("direction").toString().replaceAll("\"", "");
		}
		return order;

	}

	public String getWhere() {
		return where;
	}

	public void setWhere(String where) {
		this.where = where;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getSqlStatement() {
		String sqlStatement = select;
		if (where.length() > 1)
			sqlStatement += " where " + where;
		if (order.length() > 1)
			sqlStatement += " order by " + order;
		return sqlStatement;
	}

}