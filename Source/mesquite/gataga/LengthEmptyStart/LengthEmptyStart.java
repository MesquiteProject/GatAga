package mesquite.gataga.LengthEmptyStart;


import mesquite.lib.*;
import mesquite.lib.characters.*;
import mesquite.lib.duties.*;

public class LengthEmptyStart extends NumberForMatrix {

	public boolean startJob(String arguments, Object condition, boolean hiredByName) {
		return true;
	} 

	/** Called to provoke any necessary initialization.  This helps prevent the module's initialization queries to the user from happening at inopportune times (e.p., while a long chart calculation is in mid-progress*/
	public void initialize(MCharactersDistribution data) {
	} 
	
	double emptyThreshold =0.5;

	public void calculateNumber(MCharactersDistribution data, MesquiteNumber result, MesquiteString resultString) {
		if (result == null || data == null)
			return;
		clearResultAndLastResult(result);

		CharacterData parentData = data.getParentData();
		int numTaxa = parentData.getNumTaxa();
		int numChars = parentData.getNumChars();
		
		int totalNumSequences = 0;
		for (int it=0; it<numTaxa; it++) {
			if (!parentData.entirelyInapplicableTaxon(it))
				totalNumSequences++;
		}
		int threshold = (int)(emptyThreshold*totalNumSequences);  // this is the number of taxa with data that needs to be exceeded at a site for that site to be considered not empty
		
		int numEmptyStart =0;
		for (int ic=0; ic<numChars; ic++){
			int count =0;
			for (int it=0; it<numTaxa; it++) {
				if (!parentData.isInapplicable(ic,it) && !parentData.isUnassigned(ic,it))
					count++;
			}
			if (count<threshold)
				numEmptyStart++;
			else  // we are above the threshold
				break;
		}

		result.setValue(numEmptyStart); 

		if (resultString!=null) {
			resultString.setValue("Length Empty Start: " + result.toString());
		}
		saveLastResult(result);
		saveLastResultString(resultString);
	} 

	public boolean isPrerelease (){
		return false;
	}

	public String getName() {
		return "Length Empty Start";
	} 

	public String getExplanation(){
		return "Calculates the length of \"empty\" start of the matrix.";
	} 

} 
