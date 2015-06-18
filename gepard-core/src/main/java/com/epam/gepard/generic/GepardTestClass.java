package com.epam.gepard.generic;

import com.epam.gepard.annotations.TestClass;
import com.epam.gepard.common.TestClassExecutionData;
import com.epam.gepard.exception.SimpleGepardException;

/**
 * @author Tamas_Kohegyi
 */
public interface GepardTestClass {

    /**
     * Detects the Test Class Execution data object for the given Object, that should be test class annotated with @TestClass.
     * @param clazz is the test class object
     * @return with Test Class Execution data object or null, if it was not properly set.
     * May throw @SimpleGepardException in case @TestClass annotation does not present.
     */
    default TestClassExecutionData getTestClassExecutionData(final Object clazz) {
        String myId = Thread.currentThread().getName();
        if (clazz.getClass().isAnnotationPresent(TestClass.class)) {
            return GenericListTestSuite.getTestClassExecutionData(myId);
        }
        throw new SimpleGepardException("Given Object: " + clazz.toString()
                + " is not annotated as Gepard Test Class, cannot determine Test Class Execution Data.");
    }

    default void logComment(final Object clazz, final String comment) {
        getTestClassExecutionData(clazz).getHtmlRunReporter().logComment(comment);

    }

    default void logStep(final Object clazz, final String comment) {
        getTestClassExecutionData(clazz).getHtmlRunReporter().logStep(comment);
    }

    default void logEvent(final Object clazz, final String comment) {
        getTestClassExecutionData(clazz).getHtmlRunReporter().logEvent(comment);
    }

    /**
     * Sets the testcase. N/A test cases are tests with Not Applicable results.
     *
     * @param reason The reason why this TC is N/A
     */
    default void naTestCase(final Object clazz, final String reason) {
        getTestClassExecutionData(clazz).getHtmlRunReporter().naTestCase(reason);
    }

    default void dummyTestCase(final Object clazz) {
        getTestClassExecutionData(clazz).getHtmlRunReporter().dummyTestCase();
    }

}
