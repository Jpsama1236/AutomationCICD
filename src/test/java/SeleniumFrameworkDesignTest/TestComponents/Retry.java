package SeleniumFrameworkDesignTest.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{
	int count=0;
	int maxretry=1;

	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		if(count<maxretry) {
			count+=1;
			return true;
			
		}
		return false;
	}
	

}
