package org.salespointframework.payment;

/**
 * The cash {@link PaymentMethod} is used to designate all payments made in cash.
 * 
 * @author Hannes Weisbach
 */
@SuppressWarnings("serial")
public final class Cash extends PaymentMethod {

	/**
	 * A convenience instance {@link Cash#CASH} is defined in this class, which can be reused, instead of instantiating a
	 * new instance every time one is needed.
	 */
	public static final Cash CASH = new Cash();

	/**
	 * Creates a new cash instance. You can use {@link Cash#CASH} instead of instantiating a new one.
	 */
	public Cash() {
		super("Cash");
	}
}