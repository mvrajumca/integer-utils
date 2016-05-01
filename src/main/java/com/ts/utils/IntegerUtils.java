package com.ts.utils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;

import com.ts.exception.InvalidIntegerListException;

/**
 * This class provides util methods that will identify the second largest and
 * the required positional largest number from the given collection of integers.
 * 
 * @author Raju
 *
 */
public class IntegerUtils {

	private final static Logger logger = Logger
			.getLogger(IntegerUtils.class);
	/**
	 * This method uses the Set collection implementation to find the second
	 * largest from the given integer values.
	 * 
	 * @param values
	 * @return
	 */
	public int findSecondLargest(int[] values) throws Exception {
		validate(values);
		Set<Integer> integerValues = new TreeSet<Integer>();
		for (Integer value : values) {
			integerValues.add(value);
		}
		return iterateAndFind(integerValues);
	}

	/**
	 * This method sorts the given random integers and finds the largest
	 * value from the required position
	 * 
	 * @param values
	 * @param position
	 * @return
	 * @throws Exception
	 */
	public int findLargest(int[] values, int position) throws Exception {
		validate(values);
		sortElements(0, values.length - 1, values);
		logger.debug("Values after the sort : "+Arrays.toString(values));
		return filterDups(values)[position - 1];
	}

	private int iterateAndFind(Set<Integer> integerValues) {
		Iterator<Integer> iterator = integerValues.iterator();
		int result = 0, counter = 0;
		while (iterator.hasNext()) {
			counter++;
			result = iterator.next();
			if (counter == integerValues.size() - 1) {
				break;
			}
		}
		return result;
	}

	private void sortElements(int start, int end, int[] randomIntegers) {
		int middleElement = randomIntegers[(start + end)  / 2];
		int i = start;
		int j = end;
		while (i < j) {
			while (randomIntegers[i] > middleElement) {
				i++;
			}
			while (randomIntegers[j] < middleElement) {
				j--;
			}
			if (j >= i) {
				swap(randomIntegers, i, j);
				i++;
				j--;
			}
		}
		if (start < j) {
			sortElements(start, j, randomIntegers);
		}
		if (i < end) {
			sortElements(i, end, randomIntegers);
		}
	}

	private static void swap(int[] values, int startIndex, int nextIndex) {
		int temp = values[startIndex];
		values[startIndex] = values[nextIndex];
		values[nextIndex] = temp;
	}
	
	private int[] filterDups(int[] randomIntegers) {
		int count = 1;
		int[] filterElements = new int[randomIntegers.length];
		int lastElement = randomIntegers[0];
		filterElements[0] = lastElement;
		for (int i = 1; i < randomIntegers.length; i++) {
			if (randomIntegers[i] != lastElement) {
				filterElements[count] = randomIntegers[i];
				count = count + 1;
			}
			lastElement = randomIntegers[i];
		}
		int[] filterValues = new int[count];
		System.arraycopy(filterElements, 0, filterValues, 0, count);
		logger.debug("Numbers using sort and filter "
				+ Arrays.toString(filterValues));
		return filterValues;
	}

	private void validate(int[] values) throws InvalidIntegerListException {
		if (values == null || values.length <= 0) {
			throw new InvalidIntegerListException("Invalid integer values.");
		}
	}
}
