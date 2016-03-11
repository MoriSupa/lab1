// No Tamper unless Informing 'RavenSanstete'
package lab1.handler;

import org.json.JSONObject;

// Objective: Read From a JSON file and return JSON Object.

// Note:This Modular should be extensive and can be applied to other projects.

// Note[For Users]:to initialize a retriever A FILE PATH is needed.
public interface I_Retriever {	
	// Input: nothing
	// Output: JSONObject, the same structure as the file content
	public JSONObject retrieve();
}
