# UMGLogger Anypoint Connector

[Connector description including destination service or application with]

# Mule supported versions
Mule 3.4.x, 3.5.x

#Usage
<?xml version="1.0" encoding="UTF-8"?>

<mule xmlns="http://www.mulesoft.org/schema/mule/core"
      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xmlns:spring="http://www.springframework.org/schema/beans"
      xmlns:umglogger="http://www.mulesoft.org/schema/mule/umglogger"
      xsi:schemaLocation="
        http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
        http://www.mulesoft.org/schema/mule/core http://www.mulesoft.org/schema/mule/core/current/mule.xsd
        http://www.mulesoft.org/schema/mule/umglogger http://www.mulesoft.org/schema/mule/umglogger/1.0/mule-umglogger.xsd">

	
    <umglogger:config>
		<umglogger:context-params>
			<umglogger:context-param key="serviceName">UMGLoggerTestService</umglogger:context-param>
		</umglogger:context-params>
    </umglogger:config>

    <flow name="testFlow">
        <umglogger:log message="#[message.payload]" level="INFO"/>
        <umglogger:log message="#[message.payload]" level="ERROR"/>
        <umglogger:log message="#[message.payload]" level="DEBUG"/>
    </flow>

</mule>