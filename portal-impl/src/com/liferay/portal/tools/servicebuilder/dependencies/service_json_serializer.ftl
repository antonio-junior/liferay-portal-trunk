package ${packagePath}.service.http;

import ${packagePath}.model.${entity.name};

import com.liferay.util.JSONUtil;

import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * <a href="${entity.name}JSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>${packagePath}.service.http.${entity.name}ServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see ${packagePath}.service.http.${entity.name}ServiceJSON
 *
 */
public class ${entity.name}JSONSerializer {

	public static JSONObject toJSONObject(${entity.name} model) {
		JSONObject jsonObj = new JSONObject();

		<#list entity.regularColList as column>
			JSONUtil.put(jsonObj, "${column.name}", model.get${column.methodName}());
		</#list>

		return jsonObj;
	}

	public static JSONArray toJSONArray(List<${packagePath}.model.${entity.name}> models) {
		JSONArray jsonArray = new JSONArray();

		for (${entity.name} model : models) {
			jsonArray.put(toJSONObject(model));
		}

		return jsonArray;
	}

}