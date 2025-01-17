package eu.isas.peptideshaker.cmd;

import com.compomics.util.experiment.identification.parameters_cli.IdentificationParametersCLIParams;
import static eu.isas.peptideshaker.cmd.PathSettingsCLIParams.LOG;
import org.apache.commons.cli.Options;

/**
 * Enum class specifying the Command Line Parameters for PeptideShaker.
 *
 * @author Marc Vaudel
 * @author Harald Barsnes
 */
public enum PeptideShakerCLIParams {

    ///////////////////////////////////////////////////////////////////////////
    // IMPORTANT: Any change here must be reported in the wiki: 
    // http://compomics.github.io/peptide-shaker/wiki/peptideshakercli.html
    ///////////////////////////////////////////////////////////////////////////
    EXPERIMENT("experiment", "Specifies the experiment name.", true),
    SAMPLE("sample", "Specifies the sample name.", true),
    REPLICATE("replicate", "The replicate number.", true),
    SPECTRUM_FILES("spectrum_files", "Spectrum files (mgf format), comma separated list or an entire folder.", false),
    IDENTIFICATION_FILES("identification_files", "Identification files (.t.xml, .mzid, .cvs, .omx, .dat, .txt, .pep.xml, .zip), comma separated list or an entire folder.", true),
    PEPTIDESHAKER_OUTPUT("out", "PeptideShaker output file. Note: if file exists it will be overwritten.", true),
    GUI("gui", "Use a dialog to display the progress (1: true, 0: false, default is '0').", false),
    ZIP("zip", "Exports the entire project as a zip file in the file specified.", false),
    THREADS("threads", "The number of threads to use. Defaults to the number of available CPUs.", false);

    /**
     * Short Id for the CLI parameter.
     */
    public String id;
    /**
     * Explanation for the CLI parameter.
     */
    public String description;
    /**
     * Boolean indicating whether the parameter is mandatory.
     */
    public boolean mandatory;

    /**
     * Private constructor managing the various variables for the enum
     * instances.
     *
     * @param id the id
     * @param description the description
     * @param mandatory is the parameter mandatory
     */
    private PeptideShakerCLIParams(String id, String description, boolean mandatory) {
        this.id = id;
        this.description = description;
        this.mandatory = mandatory;
    }

    /**
     * Creates the options for the command line interface based on the possible
     * values.
     *
     * @param aOptions the options object where the options will be added
     */
    public static void createOptionsCLI(Options aOptions) {

        // standard options
        for (PeptideShakerCLIParams value : values()) {
            aOptions.addOption(value.id, true, value.description);
        }
        
        // Identification parameters
        PeptideShakerIdentificationParametersCLIParams.createOptionsCLI(aOptions);

        // follow-up options
        FollowUpCLIParams.createOptionsCLI(aOptions);

        // report options
        ReportCLIParams.createOptionsCLI(aOptions);
        
        // path setup
        aOptions.addOption(PathSettingsCLIParams.ALL.id, true, PathSettingsCLIParams.ALL.description);

        // note: remember to add new parameters to the getOptionsAsString below as well
    }

    /**
     * Returns the options as a string.
     *
     * @return the options as a string
     */
    public static String getOptionsAsString() {

        String output = "";
        String formatter = "%-35s";

        output += "Mandatory Parameters:\n\n";
        output += "-" + String.format(formatter, EXPERIMENT.id) + " " + EXPERIMENT.description + "\n";
        output += "-" + String.format(formatter, SAMPLE.id) + " " + SAMPLE.description + "\n";
        output += "-" + String.format(formatter, REPLICATE.id) + " " + REPLICATE.description + "\n";
        output += "-" + String.format(formatter, IDENTIFICATION_FILES.id) + " " + IDENTIFICATION_FILES.description + "\n";
        output += "-" + String.format(formatter, PEPTIDESHAKER_OUTPUT.id) + " " + PEPTIDESHAKER_OUTPUT.description + "\n";

        output += "\n\nOptional Input Parameter:\n\n";
        output += "-" + String.format(formatter, SPECTRUM_FILES.id) + " " + SPECTRUM_FILES.description + "\n";
        output += "-" + String.format(formatter, IdentificationParametersCLIParams.IDENTIFICATION_PARAMETERS.id) + " " + IdentificationParametersCLIParams.IDENTIFICATION_PARAMETERS.description + "\n";
        
        output += "\n\nOptional Processing Parameters:\n\n";
        output += "-" + String.format(formatter, GUI.id) + " " + GUI.description + "\n";
        output += "-" + String.format(formatter, THREADS.id) + " " + THREADS.description + "\n";

        output += "\n\nOptional Export Parameters:\n\n";
        output += "-" + String.format(formatter, ZIP.id) + " " + ZIP.description + "\n";

        output += "\n\nOptional Log Folder:\n\n";
        output += "-" + String.format(formatter, LOG.id) + " " + LOG.description + "\n";

        output += "\n\nOptional Temporary Folder:\n\n";
        output += "-" + String.format(formatter, PathSettingsCLIParams.ALL.id) + " " + PathSettingsCLIParams.ALL.description + "\n";

        output += "\n\n\nFor identification parameters options:\nReplace eu.isas.peptideshaker.cmd.PeptideShakerCLI with eu.isas.peptideshaker.cmd.IdentificationParametersCLI\n\n";
        output += "\n\n\nFor follow up export options:\nReplace eu.isas.peptideshaker.cmd.PeptideShakerCLI with eu.isas.peptideshaker.cmd.FollowUpCLI\n\n";
        output += "\nFor report export options:\nReplace eu.isas.peptideshaker.cmd.PeptideShakerCLI with eu.isas.peptideshaker.cmd.ReportCLI\n";
        output += "\nFor path setting options:\nReplace eu.isas.peptideshaker.cmd.PeptideShakerCLI with eu.isas.peptideshaker.cmd.PathSettingsCLI\n";

        return output;
    }
}
