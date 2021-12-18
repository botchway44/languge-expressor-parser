
public class Parser {
	/**
	 *  f b
		i a
		a = 5
		b = a + 3.2
		p b
	 */
	private SymbolTable sym;
	/**
	 * 1. Read each line
	 * 2. Check for each key word && Check for 
	 * 3. 
	 */
	
	Parser(){
		sym = new SymbolTable();
	}
	
	
	public void parseLine(String line, int lineNumber){
		String[] parts = line.split(" ");
		
		//verify if it has parts
		if(parts.length > 0) {
			
			if(parts[0].compareTo("f") == 0 || parts[0].compareTo("i") == 0) {
				//verify if it has a variable attached
				if(parts.length>=2) {					
					this.sym.addVariable(parts[1]);
				}else {
					System.out.println("ERROR at Line "+ lineNumber +": Invalid Statement.No Variable name added.");
					return;
				}
				
			}else if(parts[0].compareTo("p") == 0){
				// check if the variable exit
				System.out.println("Printing "+parts[1]+">> "+this.sym.getValue(parts[1].trim()));
			}else {
				//we know it is definitely an expression or an assigment
				//search for an equal to in the expression
				String[] lines = line.trim().split("=");
				//this should have 2 parts, one should be the right side of the expression
				// example a = 2 + 2 => ["a", "2+2"]
 				if(lines.length >=2) {
					//split the second part of the expression
					// ["a", "2+2"]
					if(lines[1].contains("+") || lines[1].contains("-")) {
						String[] expression = lines[1].trim().split("\\s");
						
						//Parse this  "2+2" => [2 , + , 2]
					 float res =this.verifyExpression(expression[0], expression[2], expression[1], lineNumber);
						this.sym.updateVariable(parts[0], res);

					}else {
						//This is an assignment, check if the variable is already in the syymbol table	
						if(this.sym.hasVariable(parts[0])) {						
							this.sym.updateVariable(parts[0], (Float.parseFloat(lines[1])));
						}else {
							System.out.println("ERROR at Line "+ lineNumber +": Variable not found");

						}
					}
					
				}else if(lines.length <=1)  {
						System.out.println("ERROR at Line "+ lineNumber +": Expression is not valid");
					}
				}
		}
	}
	
	private float verifyExpression(String left,String right,String operator, int lineNumber){
		//Store the left and right values globally
		float tempLeft, tempRight = 0.0f;
		
		 try {
				tempLeft = Float.parseFloat(left);

		    } catch (NumberFormatException nfe) {
				tempLeft = this.checkVariable(left, lineNumber);
		    }
		 
		 try {
				tempRight = Float.parseFloat(right);

		    } catch (NumberFormatException nfe) {
				tempRight = this.checkVariable(right, lineNumber);
		    }
		 
		 //Check operator and find results
		 return tempLeft + tempRight;
	}
	
	private float checkVariable(String name, int lineNumber) {
		if(this.sym.hasVariable(name)) {
			return this.sym.getValue(name);
		}else {
			System.out.println("ERROR at Line "+ lineNumber +": Variable not found");
		}

		return 0;
	}
}

