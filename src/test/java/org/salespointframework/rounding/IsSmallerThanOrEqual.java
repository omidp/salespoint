package org.salespointframework.rounding;

import java.math.BigDecimal;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

@SuppressWarnings("javadoc")
public class IsSmallerThanOrEqual extends TypeSafeMatcher<BigDecimal> {
	private BigDecimal right;

	public IsSmallerThanOrEqual(BigDecimal right) {
		this.right = right;
	}

	@Override
	public void describeTo(Description description) {
		description.appendText("not smaller than");
	}

	@Override
	public boolean matchesSafely(BigDecimal number) {
		return number.compareTo(right) <= 0;
	}

	@Factory
	public static <T> Matcher<BigDecimal> smallerThanOrEqual(BigDecimal right) {
		return new IsSmallerThanOrEqual(right);
	}

}
