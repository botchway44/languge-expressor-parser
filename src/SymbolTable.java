import java.util.HashMap;

public class SymbolTable {
	/**
	 *  f b
		i a
		a = 5
		b = a + 3.2
		p b
	 */
	private HashMap<String,Float> variables;
	
	SymbolTable(){
		variables = new HashMap<String, Float>();
	}
	
	public void addVariable(String variable) {
		this.variables.put(variable, 0.0f);
		
		
	}
	
	
	public void updateVariable(String variable, float value) throws IllegalStateException{
		this.variables.put(variable, value);
		
		for(String key : this.variables.keySet()) {
			System.out.println(key+" "+this.variables.get(key));
		}
	}
	
	public float getValue(String name){
		return this.variables.get(name);
	}
	
	
	public boolean hasVariable(String name) {
		return this.variables.containsKey(name);
	}
}
