//ListenerClass,MethodInterceptor are taken from PIM Automation for Reporting
package com.framework.listeners;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

public class MethodInterceptor implements IMethodInterceptor {
    @Override
    public List<IMethodInstance> intercept(List<IMethodInstance> list, ITestContext iTestContext) {
        return list;
    }
}

  