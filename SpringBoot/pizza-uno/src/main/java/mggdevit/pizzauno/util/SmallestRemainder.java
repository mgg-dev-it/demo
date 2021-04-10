package mggdevit.pizzauno.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class SmallestRemainder {

	private int[] values = {};
	private int fullamount = 0;
	private int minvalue = 0;
	private Set<ArrayList<Integer>> hsPossibleCombinations = new HashSet<>();
	private ArrayList<ArrayList<Integer>> alPossibleCombinationsOrdered = new ArrayList<>();
	private ArrayList<ArrayList<Integer>> alResultList = new ArrayList<>();
	private int longestResultLineLength = 0;

	public SmallestRemainder() {
		this.fullamount = 0;
		this.values = new int[] {};
	}

	public SmallestRemainder(int fullamount, int[] values) {
		this.fullamount = fullamount;
		this.values = values;
	}

	public void reset(int fullamount, int[] values) {
		this.fullamount = fullamount;
		this.values = values;
		hsPossibleCombinations = new HashSet<>();
		alPossibleCombinationsOrdered = new ArrayList<>();
		alResultList = new ArrayList<>();
		longestResultLineLength = 0;
	}

	public void findTheSmallestRemainder() {
		if (fullamount < 1 || values.length < 1 || hsPossibleCombinations.size() > 0) {
			return;
		}
		Set<Integer> set = new HashSet<>();
		for (int value : values) {
			set.add(value);
		}
		values = new ArrayList<>(set).stream().mapToInt(i -> i).toArray();
		Arrays.sort(values);
		minvalue = values[0];
		if (fullamount < minvalue) {
			return;
		}
		r2(fullamount, 0, null);
		sortAndTransformResult();
	}

	private void r2(int currentamount, int level, ArrayList<Integer> al) {
		ArrayList<Integer> alinner;
		for (int i = 0; i < values.length; i++) {
			if (currentamount > values[i]) {
				if (al == null) {
					alinner = new ArrayList<>();
					alinner.add(values[i]);
				} else {
					alinner = new ArrayList<>(al);
					alinner.add(values[i]);
				}
				r2(currentamount - values[i], level + 1, alinner); // go further
			} else {
				if (al == null) {
					alinner = new ArrayList<>();
				} else {
					alinner = new ArrayList<>(al);
				}

				int remaining = currentamount;
				if (currentamount == values[i]) { // no remainder
					remaining = 0;
					alinner.add(values[i]);
				}
				ArrayList<Integer> alcount = new ArrayList<>();
				for (int j = 0; j < values.length; j++) {
					int k = values[j];
					long cnt = alinner.stream().filter(a -> a == k).count();
					alcount.add((int) cnt);
				}
				alcount.add(remaining);
				if (remaining < minvalue) {
					hsPossibleCombinations.add(alcount);
				}
			}
		}
	}

	private void sortAndTransformResult() {
		longestResultLineLength = 0;
		alPossibleCombinationsOrdered = new ArrayList<>(hsPossibleCombinations);
		alPossibleCombinationsOrdered.sort(
				(ArrayList<Integer> al1, ArrayList<Integer> al2) -> al1.get(al1.size() - 1) - al2.get(al2.size() - 1));

		alPossibleCombinationsOrdered.forEach((ArrayList<Integer> al) -> {
			addOneLineToResultlist(al);
		});
	}

	private void addOneLineToResultlist(ArrayList<Integer> al) {
		ArrayList<Integer> alLine = new ArrayList<>();
		for (int i = 0; i < al.size() - 1; i++) {
			for (int j = 0; j < al.get(i); j++) {
				alLine.add(values[i]);
			}
		}
		alLine.add(al.get(al.size() - 1));
		longestResultLineLength = Math.max(longestResultLineLength, alLine.size());
		alResultList.add(alLine);
	}

	public ArrayList<ArrayList<Integer>> getResult() {
		return (alResultList);
	}

	public int getLongestResultLineLength() {
		return longestResultLineLength;
	}

}
