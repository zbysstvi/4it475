package sample;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.DefaultCaret;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


/**
Main Window of the application.
 */
public class MainWindow {

    public JPanel panel1;
    private JTextField xmlInput;
    private JTextField xsdInput;
    private JButton xmlButton;
    private JButton xsdButton;
    private JPanel XMLPanel;
    private JPanel XSDPanel;
    private JTextField xslInput;
    private JButton XSLTButton;
    private JPanel XSLTPanel;
    private JTextArea output;
    private JLabel outputLabel;
    private JPanel outputPanel;
    private JButton validateButton;
    private JButton transformButton;
    private JPanel buttonsPanel;


    //paths to store a directory from which a file of specified type was opened or saved to
    private String currentDirectory = "";
    private String currentDirectoryXML = "";
    private String currentDirectoryXSD = "";
    private String currentDirectoryXSL = "";


    public MainWindow() {

        //XML button listener
        xmlButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "XML files", new String[] {"xml", "XML"});
                chooser.setFileFilter(filter);
                chooser.setDialogTitle("Choose an XML file");
                if (currentDirectoryXML.isEmpty()) {
                    chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                }
                else {
                    chooser.setCurrentDirectory(new File(currentDirectoryXML));
                }
                int returnVal = chooser.showOpenDialog(null);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    currentDirectoryXML = chooser.getSelectedFile().getAbsolutePath();
                    xmlInput.setText(chooser.getSelectedFile().getAbsolutePath());
                }
            }
        });

        //XSD button listener
        xsdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "XSD schema files", new String[] {"XSD", "xsd"});
                chooser.setFileFilter(filter);
                chooser.setDialogTitle("Choose an XSD schema file");
                if (currentDirectoryXSD.isEmpty()) {
                    chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                }
                else {
                    chooser.setCurrentDirectory(new File(currentDirectoryXSD));
                }
                int returnVal = chooser.showOpenDialog(null);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    currentDirectoryXSD = chooser.getSelectedFile().getAbsolutePath();
                    xsdInput.setText(chooser.getSelectedFile().getAbsolutePath());
                }
            }
        });

        //XSL button listener
        XSLTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "XSLT style sheet files", new String[] {"xsl", "XSL"});
                chooser.setFileFilter(filter);
                chooser.setDialogTitle("Choose an XSL file");
                if (currentDirectoryXSL.isEmpty()) {
                    chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                }
                else {
                    chooser.setCurrentDirectory(new File(currentDirectoryXSL));
                }
                int returnVal = chooser.showOpenDialog(null);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    currentDirectoryXSL = chooser.getSelectedFile().getAbsolutePath();
                    xslInput.setText(chooser.getSelectedFile().getAbsolutePath());
                }
            }
        });

        //validate button listener
        validateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (xmlInput.getText() == null || xmlInput.getText().isEmpty() ||
                    !xmlInput.getText().endsWith(".xml"))
                {
                    output.append("\nPlease select a valid XML file.");
                    output.setCaretPosition(output.getDocument().getLength());
                }
                else if(xsdInput.getText() == null || xsdInput.getText().isEmpty() ||
                        !xsdInput.getText().endsWith(".xsd"))
                {
                    output.append("\nPlease select a valid XSD file.");
                    output.setCaretPosition(output.getDocument().getLength());
                }
                else
                {
                String result = XMLValidator.Validate(xmlInput.getText(),xsdInput.getText());
                output.append("\n" + result);
                output.setCaretPosition(output.getDocument().getLength());
                }
            }
        });

        //transform button listener
        transformButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //check for valid inputs
                if (xmlInput.getText() == null || xmlInput.getText().isEmpty() ||
                    !xmlInput.getText().endsWith(".xml"))
                {
                    output.append("\nPlease select a valid XML file.");
                    output.setCaretPosition(output.getDocument().getLength());
                }
                else if(xslInput.getText() == null || xslInput.getText().isEmpty() ||
                        !xslInput.getText().endsWith(".xsl"))
                {
                    output.append("\nPlease select a valid XSLT file.");
                    output.setCaretPosition(output.getDocument().getLength());
                }
                //inputs are valid, proceed with transformation
                //first create a html file to save to, then enter the results
                else {
                    JFileChooser chooser = new JFileChooser();
                    FileNameExtensionFilter filter = new FileNameExtensionFilter(
                            "HTML files", new String[]{"htm", "html", "HTML", "HTM"});
                    chooser.setFileFilter(filter);
                    chooser.setDialogTitle("Save as");
                    if (currentDirectory.isEmpty()) {
                        chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                    }
                    else {
                        chooser.setCurrentDirectory(new File(currentDirectory));
                    }
                    int returnVal = chooser.showSaveDialog(null);
                    if(returnVal == JFileChooser.APPROVE_OPTION) {
                        currentDirectory = chooser.getSelectedFile().getAbsolutePath();
                        File resultFile = chooser.getSelectedFile();
                        String result = XMLTransformator.Transform(xmlInput.getText(), xslInput.getText(), resultFile);
                        output.append("\n" + result);
                        output.setCaretPosition(output.getDocument().getLength());
                    }
                }
            }
        });
    }
}
