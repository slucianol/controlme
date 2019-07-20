<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>Create an income</name>
   <tag></tag>
   <elementGuidId>bf583c10-1559-4709-a38d-5b75d18a7a7e</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n  \t\&quot;amount\&quot;:${IncomeAmount},\n\t\&quot;executionDate\&quot;:\&quot;${IncomeDate}\&quot;,\n\t\&quot;fixed\&quot;:true,\n\t\&quot;type\&quot;:${IncomeType}\n}&quot;,
  &quot;contentType&quot;: &quot;application/json&quot;,
  &quot;charset&quot;: &quot;UTF-8&quot;
}</httpBodyContent>
   <httpBodyType>text</httpBodyType>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Content-Type</name>
      <type>Main</type>
      <value>application/json</value>
   </httpHeaderProperties>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>POST</restRequestMethod>
   <restUrl>${TestUrl}/api/Incomes</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>GlobalVariable.g_TestUrl</defaultValue>
      <description></description>
      <id>7dad12fc-aef5-4c99-9984-f67dad12fc10</id>
      <masked>false</masked>
      <name>TestUrl</name>
   </variables>
   <variables>
      <defaultValue>'2019-04-15T04:00Z'</defaultValue>
      <description></description>
      <id>b7f5a7c5-3388-43a9-8f9e-802a040537ef</id>
      <masked>false</masked>
      <name>IncomeDate</name>
   </variables>
   <variables>
      <defaultValue>'1'</defaultValue>
      <description></description>
      <id>48f5578c-950a-4e98-99d1-3d00b64dfbc1</id>
      <masked>false</masked>
      <name>IncomeType</name>
   </variables>
   <variables>
      <defaultValue>80000</defaultValue>
      <description></description>
      <id>01a0ac93-0924-4c51-afec-d5cf7505de0f</id>
      <masked>false</masked>
      <name>IncomeAmount</name>
   </variables>
   <verificationScript>import static org.assertj.core.api.Assertions.*

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webservice.verification.WSResponseManager

import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable

RequestObject request = WSResponseManager.getInstance().getCurrentRequest()

ResponseObject response = WSResponseManager.getInstance().getCurrentResponse()
</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
