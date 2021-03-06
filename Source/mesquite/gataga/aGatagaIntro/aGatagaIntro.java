package mesquite.gataga.aGatagaIntro;

import mesquite.lib.Debugg;
import mesquite.lib.duties.PackageIntro;

public class aGatagaIntro extends PackageIntro {

	/*.................................................................................................................*/
	public boolean startJob(String arguments, Object condition, boolean hiredByName) {
		return true;
	}
	public Class getDutyClass(){
		return aGatagaIntro.class;
	}
	/*.................................................................................................................*/
	public String getExplanation() {
		return "GatAga is a package of Mesquite modules providing tools for genomic analyses.";
	}

	/*.................................................................................................................*/
	public boolean isPrerelease(){
		return true;  
	}

	/*.................................................................................................................*/
	public String getName() {
		return "GatAga Package";
	}
	/*.................................................................................................................*/
	/** Returns the name of the package of modules (e.g., "Basic Mesquite Package", "Rhetenor")*/
	public String getPackageName(){
		return "GatAga Package";
	}
	/*.................................................................................................................*/
	/** Returns whether there is a splash banner*/
	public boolean hasSplash(){
		return true; 
	}
	/*.................................................................................................................*/
	/** Returns citation for a package of modules*/
 	public String getPackageCitation(){
 		return "Maddison, DR & Maddison WP.  2018.  GatAga: a Mesquite package for processing high-throughput sequencing data. Version "+getPackageVersion();
 	}
	/*.................................................................................................................*/
	/** Returns version for a package of modules*/
	public String getPackageVersion(){
				return "0.52";
	}
	/*.................................................................................................................*/
	/** Returns version for a package of modules as an integer*/
	public int getPackageVersionInt(){
		return 500;
	}
	/*.................................................................................................................*/
	public String getPackageDateReleased(){
		return "20 June 2018";
	}
	/*.................................................................................................................*/
	/** Returns build number for a package of modules as an integer*/
	public int getPackageBuildNumber(){
		return 33;
	}
	/*  Release dates:
	 * */

}
