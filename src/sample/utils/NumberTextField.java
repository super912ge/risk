package sample.utils;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import java.text.NumberFormat;
import java.text.ParseException;

public class NumberTextField extends TextField {

	private final NumberFormat nf;

	private int min = 0;

	private int max = Integer.MAX_VALUE;

	private ObjectProperty<Integer> number = new SimpleObjectProperty<> ();

	public final Integer getNumber () {

		return number.get ();
	}

	public final void setNumber (Integer value) {


		number.set (value);
	}

	private ObjectProperty<Integer> numberProperty () {

		return number;
	}

	public NumberTextField ()  {

		this (0);
	}

	private NumberTextField (Integer value)  {

		this (value, NumberFormat.getInstance ());

		initHandlers ();
	}

	private NumberTextField (Integer value, NumberFormat nf)  {

		super ();

		this.nf = nf;

		initHandlers ();

		setNumber (value);
	}

	private void initHandlers () {

		// try to parse when focus is lost or RETURN is hit
		setOnAction ((ActionEvent e) -> parseAndFormatInput());

		focusedProperty ().addListener ((observable, oldValue, newValue) -> {

            if (! newValue) parseAndFormatInput ();
        });

		// Set text in field if Integer property is changed from outside.
		numberProperty ().addListener ((observable, oldValue, newValue) -> setText (nf.format (newValue)));
	}

	/**
	 * Tries to parse the user input to a number according to the provided
	 * NumberFormat
	 */
	private void parseAndFormatInput () {

		try {

			String input = getText ();

			if (input == null || input.length () == 0) { return; }

			Number parsedNumber = nf.parse (input);

			Integer newValue = new Integer (parsedNumber.toString ());

			setNumber (newValue);

			selectAll ();

		}
		catch (ParseException ex) {

			setText (null);

		}
	}

	public void setRange(int min, int max){

		this.min = min;

		this.max = max;
	}

	public boolean isOutRange(){

		return this.number.getValue()> max || this.number.getValue()< min;
	}

	public String getRange() {

		return "[" + min+ ", "+max+"]";
	}
}