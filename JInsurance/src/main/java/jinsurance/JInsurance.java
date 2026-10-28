package jinsurance;

import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * JInsurance - lets the user choose insurance options using JCheckBoxes.
 *
 * - HMO and PPO are placed in a ButtonGroup, so only ONE of them can be selected.
 * - Dental and Vision are independent check boxes (none, one or both).
 * - The text field shows the chosen insurance type, followed by every ticked
 *   extra appended to it, plus the total monthly cost.
 */
public class JInsurance extends JFrame implements ItemListener {

    // Monthly prices stored as constants so they are only defined in one place
    private static final int HMO_PRICE = 750;
    private static final int PPO_PRICE = 1500;
    private static final int DENTAL_PRICE = 300;
    private static final int VISION_PRICE = 100;

    // Insurance type check boxes (mutually exclusive via the ButtonGroup)
    private final JCheckBox hmoBox = new JCheckBox("HMO");
    private final JCheckBox ppoBox = new JCheckBox("PPO");

    // Optional extras (independent check boxes)
    private final JCheckBox dentalBox = new JCheckBox("Dental");
    private final JCheckBox visionBox = new JCheckBox("Vision");

    // Groups HMO and PPO so selecting one automatically deselects the other
    private final ButtonGroup insuranceTypeGroup = new ButtonGroup();

    // Displays the selected insurance type, the ticked extras and the total
    private final JTextField outputField = new JTextField(32);

    public JInsurance() {
        super("Insurance Options");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setSize(420, 170);

        // Only HMO and PPO go in the group - dental and vision stay independent
        insuranceTypeGroup.add(hmoBox);
        insuranceTypeGroup.add(ppoBox);

        // The output field is for display only
        outputField.setEditable(false);

        // Add components to the frame
        add(new JLabel("Choose an insurance type:"));
        add(hmoBox);
        add(ppoBox);
        add(new JLabel("Optional extras:"));
        add(dentalBox);
        add(visionBox);
        add(outputField);

        // The frame itself handles item events for every check box
        hmoBox.addItemListener(this);
        ppoBox.addItemListener(this);
        dentalBox.addItemListener(this);
        visionBox.addItemListener(this);
    }

    /**
     * Called whenever any check box is selected or deselected.
     * Instead of reacting to just the one box that changed, the summary is
     * rebuilt from the current state of ALL the boxes, so:
     *  - the insurance type (HMO OR PPO) is shown first
     *  - every ticked extra (Dental AND/OR Vision) is appended after it
     *  - unticking an extra simply removes it from the summary
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        StringBuilder summary = new StringBuilder();
        int total = 0;

        // Insurance type - the ButtonGroup guarantees at most one of these is ticked
        if (hmoBox.isSelected()) {
            summary.append("HMO (R").append(HMO_PRICE).append(")");
            total += HMO_PRICE;
        } else if (ppoBox.isSelected()) {
            summary.append("PPO (R").append(PPO_PRICE).append(")");
            total += PPO_PRICE;
        }

        // Optional extras - independent, so each is checked separately (AND, not OR)
        if (dentalBox.isSelected()) {
            appendOption(summary, "Dental", DENTAL_PRICE);
            total += DENTAL_PRICE;
        }
        if (visionBox.isSelected()) {
            appendOption(summary, "Vision", VISION_PRICE);
            total += VISION_PRICE;
        }

        // Nothing ticked -> blank field, otherwise show the options and the monthly total
        if (summary.length() == 0) {
            outputField.setText("");
        } else {
            outputField.setText(summary + " = R" + total + " per month");
        }
    }

    // Adds " + " between options so the extras are appended to whatever is already there
    private void appendOption(StringBuilder summary, String name, int price) {
        if (summary.length() > 0) {
            summary.append(" + ");
        }
        summary.append(name).append(" (R").append(price).append(")");
    }

    public static void main(String[] args) {
        // Build and show the GUI on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> new JInsurance().setVisible(true));
    }
}
