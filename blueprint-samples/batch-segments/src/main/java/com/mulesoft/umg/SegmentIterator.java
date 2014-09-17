package com.mulesoft.umg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class SegmentIterator implements Iterator<List<String>> {

	private BufferedReader reader;
	private boolean done = false;
	private String lastLine;

	private enum State {
		EXPECTING_OPENING, EXPECTING_CLOSURE
	};

	public SegmentIterator(InputStream in) {
		reader = new BufferedReader(new InputStreamReader(in));
	}

	@Override
	public boolean hasNext() {
		return !done;
	}

	@Override
	public List<String> next() {
		try {
			List<String> buffer = new LinkedList<String>();
			State currentState = State.EXPECTING_OPENING;

			if (lastLine == null) {
				lastLine = reader.readLine();
			}

			while (true) {

				if (currentState == State.EXPECTING_OPENING
						&& lastLine.startsWith("HEAD")) {

					buffer.add(lastLine);

					currentState = State.EXPECTING_CLOSURE;

				} else if (currentState == State.EXPECTING_CLOSURE
						&& !lastLine.startsWith("HEAD")) {

					buffer.add(lastLine);

				} else if (currentState == State.EXPECTING_CLOSURE
						&& lastLine.startsWith("HEAD")) {

					break;

				}

				lastLine = reader.readLine();
				if (lastLine == null) {
					done = true;
					break;
				}
			}

			return buffer;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public void remove() {
		// no-op

	}

}
