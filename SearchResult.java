/**
 * SearchResult class containing attributes, compareTo, and update method
 *
 * @author raywang
 *
 */

public class SearchResult implements Comparable<SearchResult> {

	private int frequency;
	private int position;
	private final String path;


	/**
	 * constructor
	 * @param frequency
	 * 			frequency of words
	 * @param position
	 * 			where the word appears in the file
	 * @param path
	 * 			path to file containing word
	 */
	public SearchResult(String path, int frequency, int position) {
		this.path = path;
		this.frequency = frequency;
		this.position = position;
	}


	public int getFrequency() {
		return frequency;
	}


	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	/*
	public void addFrequency(int frequency) adds together frequency
	public void updatePosition(int position) only updates if new position is less than old position
	public void update(int frequency, int position)
	 */

	public void update(int frequency, int position) {
		this.frequency += frequency;

		if (this.position > position) {
			this.position = position;
		}
	}

	public int getPosition() {
		return position;
	}


	public void setPosition(int position) {
		this.position = position;
	}


	public String getPath() {
		return path;
	}


	/**
	 * overrides the compareTo
	 */
	@Override
	public int compareTo(SearchResult result) {

		if (this.frequency != result.frequency) {
			return Integer.compare(result.frequency, this.frequency);
		} else {
			if (this.position != result.position) {
				return Integer.compare(this.position, result.position);
			} else {
				return this.path.compareToIgnoreCase(result.path);
			}
		}

	}

	@Override
	public String toString() {
		return "Path: " + path + ",\n Position: " + position + ",\n Frequency: " + frequency;
	}

}
