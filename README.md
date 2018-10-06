# Opensource
Opensource free code available.

# Dynamic Property file Handler
This library enables code to load property file at run time. 
Post application loading, if any changes are made to property file, this library will automatically refresh the cache.

# Key configuration 

1. Include below lines as part of your application context

<bean id="dynamicPropertyLoader" class="com.common.resource.DynamicPropertyLoader"
		init-method="init">
		<constructor-arg type="String" value="/media/<filepath>"></constructor-arg>
		<constructor-arg type="String" value="<filename>.properties"></constructor-arg>
</bean>

2. Autowire EventMapper class where property is supposed to be read

@Autowired
	EventMapper eventMapper;
  
3. Access property values
eventMapper.getProperties().get("<fileName>").getProperty("<Property Keyname>")

Feel free to contribute 
himanshu.tiwari@incededmindedmachines.com

