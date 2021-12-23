package utils;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
/**
 * A class to simplify the use of gridBagLayout and gridBagConstraints
 * @author Carlo Bobba
 * @version 2.1 21/03/2015: Added the simplified add method
 */
public class GridBagBuilder extends GridBagConstraints {

	private static final long serialVersionUID = 0;

	private Container container;
	private GridBagLayout layout = new GridBagLayout();;
	/**
	 * The container of all the components
	 * @param container
	 */
	public GridBagBuilder(Container container) {
		super();
		this.container = container;
		this.container.setLayout(layout);
	}
	
	/**
	 * A method to get the limits of the layout to add more options to a component
	 * @return The gridBagConstraints
	 */
	public GridBagConstraints limits() {
		return this;
	}
	
	/**
	 * A method to add a component to the relative container, with the most common options of positioning
	 * For more information @see GridBagConstraints or @see http://docs.oracle.com/javase/7/docs/api/java/awt/GridBagConstraints.html
	 * @param c The component to add and position
	 * @param gridx The x position of the component
	 * @param gridy The y position of the component
	 * @param gridwidth The number of cells in a row used by the component
	 * @param gridheight The number of cells in a column used by the component
	 * @param weightx The "weight", priority to distribute extra horizontal space
	 * @param weighty The "weight", priority to distribute extra vertical space
	 */
	public void add(Component c, int gridx, int gridy, int gridwidth, int gridheight, double weightx, double weighty) {
		this.gridx = gridx;
		this.gridy = gridy;
		this.gridwidth = gridwidth;
		this.gridheight = gridheight;
		this.weightx = weightx;
		this.weighty = weighty;
		this.fill = GridBagConstraints.BOTH;
		this.anchor = GridBagConstraints.CENTER;
		container.add(c, this);
	}

	/**
	 * A method to add a component to the relative container, with all the option of positioning
	 * For more information @see GridBagConstraints or @see http://docs.oracle.com/javase/7/docs/api/java/awt/GridBagConstraints.html
	 * @param c The component to add and position
	 * @param gridx The x position of the component
	 * @param gridy The y position of the component
	 * @param gridwidth The number of cells in a row used by the component
	 * @param gridheight The number of cells in a column used by the component
	 * @param weightx The "weight", priority to distribute extra horizontal space
	 * @param weighty The "weight", priority to distribute extra vertical space
	 * @param fill The resize of the component if the component's display area is larger than the component's requested size
	 * @param anchor Where to place the component in the component's display area
	 */
	public void add(Component c, int gridx, int gridy, int gridwidth, int gridheight, double weightx, double weighty, int fill, int anchor) {
			this.gridx = gridx;
			this.gridy = gridy;
			this.gridwidth = gridwidth;
			this.gridheight = gridheight;
			this.weightx = weightx;
			this.weighty = weighty;
			this.fill = fill;
			this.anchor = anchor;
			container.add(c, this);
	}
}
