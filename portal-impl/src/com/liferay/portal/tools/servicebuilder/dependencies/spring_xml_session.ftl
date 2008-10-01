<bean id="${packagePath}.service.${entity.name}${sessionType}Service.impl" class="${packagePath}.service.impl.${entity.name}${sessionType}ServiceImpl" />

<#if entity.TXManager != "none">
	<#assign txRequiredList = entity.getTxRequiredList()>

	<#if txRequiredList?size gt 0>
		<aop:config>
			<aop:pointcut id="${packagePath}.service.${entity.name}${sessionType}Service.operation" expression="bean(${packagePath}.service.${entity.name}${sessionType}Service.impl)" />
			<aop:advisor advice-ref="${packagePath}.service.${entity.name}${sessionType}Service.advice" pointcut-ref="${packagePath}.service.${entity.name}${sessionType}Service.operation" />
		</aop:config>
		<tx:advice id="${packagePath}.service.${entity.name}${sessionType}Service.advice" transaction-manager="liferayTransactionManager">
			<tx:attributes>
				<#list txRequiredList as txRequired>
					<tx:method name="${txRequired}" propagation="REQUIRED" rollback-for="com.liferay.portal.PortalException,com.liferay.portal.SystemException" />
				</#list>
			</tx:attributes>
		</tx:advice>
	</#if>
</#if>

<bean id="${packagePath}.service.${entity.name}${sessionType}Service.velocity" class="org.springframework.aop.framework.ProxyFactoryBean" parent="baseVelocityService">
	<property name="target" ref="${packagePath}.service.${entity.name}${sessionType}Service.impl" />
</bean>

<#if pluginName == "">
	<bean id="${packagePath}.service.${entity.name}${sessionType}ServiceFactory" class="${packagePath}.service.${entity.name}${sessionType}ServiceFactory">
		<property name="service" ref="${packagePath}.service.${entity.name}${sessionType}Service.impl" />
	</bean>
</#if>

<bean id="${packagePath}.service.${entity.name}${sessionType}ServiceUtil" class="${packagePath}.service.${entity.name}${sessionType}ServiceUtil">
	<property name="service" ref="${packagePath}.service.${entity.name}${sessionType}Service.impl" />
</bean>