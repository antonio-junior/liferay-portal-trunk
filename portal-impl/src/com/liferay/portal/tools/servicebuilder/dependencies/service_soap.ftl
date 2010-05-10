package ${packagePath}.service.http;

import ${packagePath}.service.${entity.name}ServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ListUtil;

import java.rmi.RemoteException;

/**
 * <a href="${entity.name}ServiceSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides a SOAP utility for the
 * {@link ${packagePath}.service.${entity.name}ServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 * </p>
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link ${packagePath}.model.${entity.name}Soap}.
 * If the method in the service utility returns a
 * {@link ${packagePath}.model.${entity.name}}, that is translated to a
 * {@link ${packagePath}.model.${entity.name}Soap}. Methods that SOAP cannot
 * safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at
 * http://localhost:8080/tunnel-web/secure/axis. Set the property
 * <b>tunnel.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author    ${author}
 * @see       ${entity.name}ServiceHttp
 * @see       ${packagePath}.model.${entity.name}Soap
 * @see       ${packagePath}.service.${entity.name}ServiceUtil
 * @generated
 */
public class ${entity.name}ServiceSoap {

	<#assign hasMethods = false>

	<#list methods as method>
		<#if !method.isConstructor() && method.isPublic() && serviceBuilder.isCustomMethod(method) && serviceBuilder.isSoapMethod(method)>
			<#assign hasMethods = true>

			<#assign returnValueName = method.returns.value>
			<#assign returnValueGenericsName = method.returnsGenericsName>
			<#assign returnValueDimension = serviceBuilder.getDimensions(method.returns.dimensions)>
			<#assign extendedModelName = packagePath + ".model." + entity.name>
			<#assign soapModelName = packagePath + ".model." + entity.name + "Soap">

			public static

			<#if returnValueName == extendedModelName>
				${soapModelName}${returnValueDimension}
			<#elseif returnValueName == "java.util.List">
				<#if entity.hasColumns()>
					<#if returnValueGenericsName == "<java.lang.Boolean>">
						java.lang.Boolean[]
					<#elseif returnValueGenericsName == "<java.lang.Double>">
						java.lang.Double[]
					<#elseif returnValueGenericsName == "<java.lang.Float>">
						java.lang.Float[]
					<#elseif returnValueGenericsName == "<java.lang.Integer>">
						java.lang.Integer[]
					<#elseif returnValueGenericsName == "<java.lang.Long>">
						java.lang.Long[]
					<#elseif returnValueGenericsName == "<java.lang.Short>">
						java.lang.Short[]
					<#elseif returnValueGenericsName == "<java.lang.String>">
						java.lang.String[]
					<#else>
						${soapModelName}[]
					</#if>
				<#else>
					java.util.List
				</#if>
			<#else>
				${returnValueName}${returnValueDimension}
			</#if>

			${method.name}(

			<#list method.parameters as parameter>
				<#assign parameterTypeName = parameter.type.value + parameter.genericsName + serviceBuilder.getDimensions(parameter.type.dimensions)>

				<#if parameterTypeName == "java.util.Locale">
					<#assign parameterTypeName = "String">
				<#elseif parameterTypeName == "java.util.List<Long>">
					<#assign parameterTypeName = "Long[]">
				<#elseif (parameter.type.value == "java.util.List") && serviceBuilder.hasEntityByGenericsName(parameter.genericsName)>
					<#assign parameterEntity = serviceBuilder.getEntityByGenericsName(parameter.genericsName)>

					<#assign parameterTypeName = parameterEntity.packagePath + ".model." + parameterEntity.name + "Soap[]">
				<#elseif serviceBuilder.hasEntityByParameterTypeValue(parameter.type.value)>
					<#assign parameterEntity = serviceBuilder.getEntityByParameterTypeValue(parameter.type.value)>

					<#assign parameterTypeName = parameterEntity.packagePath + ".model." + parameterEntity.name + "Soap">
				</#if>

				${parameterTypeName} ${parameter.name}

				<#if parameter_has_next>
					,
				</#if>
			</#list>

			) throws RemoteException {
				try {
					<#if returnValueName != "void">
						${returnValueName}${returnValueGenericsName}${returnValueDimension} returnValue =
					</#if>

					${entity.name}ServiceUtil.${method.name}(

					<#list method.parameters as parameter>
						<#assign parameterTypeName = parameter.type.value + parameter.genericsName + serviceBuilder.getDimensions(parameter.type.dimensions)>

						<#if parameterTypeName == "java.util.Locale">
							new java.util.Locale(
						<#elseif parameterTypeName == "java.util.List<Long>">
							ListUtil.toList(
						<#elseif (parameter.type.value == "java.util.List") && serviceBuilder.hasEntityByGenericsName(parameter.genericsName)>
							<#assign parameterEntity = serviceBuilder.getEntityByGenericsName(parameter.genericsName)>

							${parameterEntity.packagePath}.model.impl.${parameterEntity.name}ModelImpl.toModels(
						<#elseif serviceBuilder.hasEntityByParameterTypeValue(parameter.type.value)>
							<#assign parameterEntity = serviceBuilder.getEntityByGenericsName(parameter.genericsName)>

							${parameterEntity.packagePath}.model.impl.${parameterEntity.name}ModelImpl.toModel(
						</#if>

						${parameter.name}

						<#if parameterTypeName == "java.util.Locale">
							)
						<#elseif parameterTypeName == "java.util.List<Long>">
							)
						<#elseif (parameter.type.value == "java.util.List") && serviceBuilder.hasEntityByGenericsName(parameter.genericsName)>
							)
						<#elseif serviceBuilder.hasEntityByParameterTypeValue(parameter.type.value)>
							)
						</#if>

						<#if parameter_has_next>
							,
						</#if>
					</#list>

					);

					<#if returnValueName != "void">
						<#if (returnValueName == extendedModelName) && (returnValueDimension == "")>
							return ${soapModelName}.toSoapModel(returnValue);
						<#elseif (returnValueName == extendedModelName) && (returnValueDimension != "")>
							return ${soapModelName}.toSoapModels(returnValue);
						<#elseif entity.hasColumns() && returnValueName == "java.util.List">
							<#if returnValueGenericsName == "<java.lang.Boolean>">
								return returnValue.toArray(new java.lang.Boolean[returnValue.size()]);
							<#elseif returnValueGenericsName == "<java.lang.Double>">
								return returnValue.toArray(new java.lang.Double[returnValue.size()]);
							<#elseif returnValueGenericsName == "<java.lang.Integer>">
								return returnValue.toArray(new java.lang.Integer[returnValue.size()]);
							<#elseif returnValueGenericsName == "<java.lang.Float>">
								return returnValue.toArray(new java.lang.Float[returnValue.size()]);
							<#elseif returnValueGenericsName == "<java.lang.Long>">
								return returnValue.toArray(new java.lang.Long[returnValue.size()]);
							<#elseif returnValueGenericsName == "<java.lang.Short>">
								return returnValue.toArray(new java.lang.Short[returnValue.size()]);
							<#elseif returnValueGenericsName == "<java.lang.String>">
								return returnValue.toArray(new java.lang.String[returnValue.size()]);
							<#else>
								return ${soapModelName}.toSoapModels(returnValue);
							</#if>
						<#else>
							return returnValue;
						</#if>
					</#if>
				}
				catch (Exception e) {
					_log.error(e, e);

					throw new RemoteException(e.getMessage());
				}
			}
		</#if>
	</#list>

	<#if hasMethods>
		private static Log _log = LogFactoryUtil.getLog(${entity.name}ServiceSoap.class);
	</#if>

}