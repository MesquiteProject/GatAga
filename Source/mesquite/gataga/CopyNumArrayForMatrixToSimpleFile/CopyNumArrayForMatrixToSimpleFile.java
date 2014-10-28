
package mesquite.gataga.CopyNumArrayForMatrixToSimpleFile; 

import java.awt.FileDialog;

import mesquite.lib.*;
import mesquite.lib.characters.CharacterData;
import mesquite.lib.duties.*;
import mesquite.molec.lib.Blaster;

/* ======================================================================== */
public class CopyNumArrayForMatrixToSimpleFile extends FileProcessor {
	NumberArrayForMatrix numTask;
	String saveFile = null;
	public void getEmployeeNeeds(){  //This gets called on startup to harvest information; override this and inside, call registerEmployeeNeed
		EmployeeNeed e = registerEmployeeNeed(NumberArrayForMatrix.class, getName() + "  needs a NumbersForMatrix module.","");
	}

	/*.................................................................................................................*/
	public boolean startJob(String arguments, Object condition, boolean hiredByName){
		numTask = (NumberArrayForMatrix)hireEmployee(NumberArrayForMatrix.class, "NumbersForMatrix (for " + getName() + ")"); 
		if (numTask==null)
			return sorry(getName() + " couldn't start because no NumberForMatrix module could be obtained.");
		return true;
	}
	/*.................................................................................................................*/
	/** returns whether this module is requesting to appear as a primary choice */
	public boolean requestPrimaryChoice(){
		return false;  
	}
	/*.................................................................................................................*/
	public boolean isPrerelease(){
		return true;
	}
	/*.................................................................................................................*/
	public boolean isSubstantive(){
		return true; //not really, but to force checking of prerelease
	}

	/** if returns true, then requests to remain on even after alterFile is called.  Default is false*/
	public boolean pleaseLeaveMeOn(){
		return false;
	}
	/*.................................................................................................................*/
	/** Called to alter file. */
	public boolean processFile(MesquiteFile file){
		if (numTask==null)
			return false;
		if (saveFile == null || okToInteractWithUser(CAN_PROCEED_ANYWAY, "Asking for file to save")){ //need to check if can proceed
			
			MesquiteFileDialog fdlg= new MesquiteFileDialog(containerOfModule(), "Output File for Numbers for Matrices", FileDialog.SAVE);
			fdlg.setBackground(ColorTheme.getInterfaceBackground());
			fdlg.setVisible(true);
			String fileName=fdlg.getFile();
			String directory=fdlg.getDirectory();
			// fdlg.dispose();
			if (StringUtil.blank(fileName) || StringUtil.blank(directory))
				return false;
			saveFile = MesquiteFile.composePath(directory, fileName);
			String[] names = numTask.getNumbersNames();
			String s = "";
			
			if (proj.getNumberCharMatricesVisible(file)>1){
				s+="\t"; // go to the first matrix start
				for (int im = 0; im < proj.getNumberCharMatricesVisible(file); im++){
					CharacterData data = proj.getCharacterMatrix(file, null, null, im, true);  //DAVIDCHECK: this should be getCharacterMatrixVisible;   
					s+= data.getName();
					for (int i=0; i<numTask.getNumberOfNumbers(); i++)
						s+= "\t";
				}
				s += StringUtil.lineEnding();
			}
			s += "file name";
			if (names!=null)
				for (int i=0; i<names.length; i++)
					s+= "\t" + names[i];
			s += StringUtil.lineEnding();
			MesquiteFile.appendFileContents(saveFile, s, true);
		}
		if (saveFile == null)
			return false;

		NumberArray result = new NumberArray();
		MesquiteString resultString = new MesquiteString("");

		StringBuffer sb = new StringBuffer();
		sb.append(file.getName()+"\t");
		for (int im = 0; im < proj.getNumberCharMatricesVisible(file); im++){  //
			if (im>0)
				sb.append("\t");
			CharacterData data = proj.getCharacterMatrix(file, null, null, im, true); //DAVIDCHECK: this should be getCharacterMatrixVisible
			numTask.calculateNumbers(data.getMCharactersDistribution(), result, resultString);
			for (int i=0; i<result.getNumParts(); i++) {
				sb.append(result.toString(i)+"\t");
			}
   		}
		MesquiteFile.appendFileContents(saveFile, sb.toString() + StringUtil.lineEnding(), true);
		return true;

	}
	/*.................................................................................................................*/
	public String getName() {
		return "Put Number Array for Matrix into Simple File";
	}
	/*.................................................................................................................*/
	/** returns an explanation of what the module does.*/
	public String getExplanation() {
		return "Puts calculated number arrays about a matrix into a simple text file." ;
	}

}


