package FindCasualNames.relation;

import util.Config;

import java.util.ArrayList;

public class Method extends IdentifierWithoutType {
	public static String NAME = "MethodName";
	private ArrayList<Parameter> parameters;
	private ArrayList<RegularVariable> variables;

	public Method(int line, String content, ArrayList<Parameter> parameters, ArrayList<RegularVariable> variables) {
		super(line, content);
		this.parameters = parameters;
		this.variables = variables;
	}


	public String getName() {
		return getContent();
	}

	public ArrayList<Parameter> getParameters() {
		return parameters;
	}

	public ArrayList<RegularVariable> getVariables() {
		return variables;
	}
}
