<#setting number_format = "0">

<#list dataFactory.classNames as className>
	insert into ClassName_ (classNameId, value) values (${className.classNameId}, '${className.value}');
</#list>