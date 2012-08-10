package controller;

import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Class Name: SliderListener. Class responsibility: Contains the slider
 * listener Class Collaborators: The listener interface for receiving slider
 * events. The class that is interested in processing a slider event implements
 * this interface, and the object created with that class is registered with a
 * component using the component's <code>addSliderListener<code> method. When
 * the slider event occurs, that object's appropriate
 * method is invoked.
 */
public class SliderListener implements ChangeListener
{

	// The slider value.
	private JTextField sliderValue;

	// Constructor SliderListener: Instantiates a new slider listener.
	public SliderListener(JTextField textField)
	{
		sliderValue = textField;
	}

	/* Method stateChanged : Contains the state change event */
	public void stateChanged(ChangeEvent e)
	{
		JSlider source = (JSlider) e.getSource();
		int fps = (int) source.getValue();
		sliderValue.setText(fps + "");
	}

}
