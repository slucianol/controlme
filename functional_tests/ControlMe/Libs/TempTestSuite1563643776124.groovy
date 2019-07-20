import com.kms.katalon.core.logging.KeywordLogger
import com.kms.katalon.core.exception.StepFailedException
import com.kms.katalon.core.reporting.ReportUtil
import com.kms.katalon.core.main.TestCaseMain
import com.kms.katalon.core.testdata.TestDataColumn
import com.kms.katalon.core.testcase.TestCaseBinding
import com.kms.katalon.core.driver.internal.DriverCleanerCollector
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.configuration.RunConfiguration
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import internal.GlobalVariable as GlobalVariable

Map<String, String> suiteProperties = new HashMap<String, String>();


suiteProperties.put('id', 'Test Suites/Verify Incomes Endpoint')

suiteProperties.put('name', 'Verify Incomes Endpoint')

suiteProperties.put('description', '')
 

DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.webui.contribution.WebUiDriverCleaner())
DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.mobile.contribution.MobileDriverCleaner())
DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.cucumber.keyword.internal.CucumberDriverCleaner())



RunConfiguration.setExecutionSettingFile("C:\\repos\\vstudio\\ControlMe\\functional_tests\\ControlMe\\Reports\\Verify Incomes Endpoint\\20190720_132936\\execution.properties")

TestCaseMain.beforeStart()

TestCaseMain.startTestSuite('Test Suites/Verify Incomes Endpoint', suiteProperties, [new TestCaseBinding('Test Cases/Get All Incomes', 'Test Cases/Get All Incomes',  null), new TestCaseBinding('Test Cases/Create Income', 'Test Cases/Create Income',  null), new TestCaseBinding('Test Cases/Delete an income', 'Test Cases/Delete an income',  null), new TestCaseBinding('Test Cases/Get All Incomes', 'Test Cases/Get All Incomes',  null)])
