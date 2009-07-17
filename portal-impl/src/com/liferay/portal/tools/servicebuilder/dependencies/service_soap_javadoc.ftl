<?xml version="1.0" encoding="UTF-8"?>

<javadoc>
	<name>${entity.name}ServiceSoap</name>
	<type>${packagePath}.service.http.${entity.name}ServiceSoap</type>
	<comment>
		<![CDATA[
			<p>
				ServiceBuilder generated this class. Modifications in this class will be
				overwritten the next time is generated.
			</p>

			<p>
				This class provides a SOAP utility for the
				{@link ${packagePath}.service.${entity.name}ServiceUtil} service utility.
				The static methods of this class calls the same methods of the service
				utility. However, the signatures are different because it is difficult for
				SOAP to support certain types.
			</p>

			<p>
				ServiceBuilder follows certain rules in translating the methods. For example, if
				the method in the service utility returns a {@link java.util.List}, that is
				translated to an array of {@link ${packagePath}.model.${entity.name}Soap}. If
				the method in the service utility returns a
				{@link ${packagePath}.model.${entity.name}}, that is translated to a
				{@link ${packagePath}.model.${entity.name}Soap}. Methods that SOAP cannot safely
				wire are skipped.
			</p>

			<p>
				The benefits of using the SOAP utility is that it is cross platform
				compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
				even Perl, to call the generated services. One drawback of SOAP is that it
				is slow because it needs to serialize all calls into a text format (XML).
			</p>

			<p>
				You can see a list of services at
				http://localhost:8080/tunnel-web/secure/axis. Set the property
				<b>tunnel.servlet.hosts.allowed</b> in portal.properties to configure
				security.
			</p>

			<p>
				The SOAP utility is only generated for remote services.
			</p>
		]]>
	</comment>
	<author>${author}</author>
	<see>${entity.name}ServiceHttp</see>
	<see>${packagePath}.model.${entity.name}Soap</see>
	<see>${packagePath}.service.${entity.name}ServiceUtil</see>
</javadoc>