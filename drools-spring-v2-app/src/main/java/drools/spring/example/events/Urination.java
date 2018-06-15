package drools.spring.example.events;

import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
public class Urination {
	
	public int amount;
	
	public Urination(int amount) {
		this.amount = amount;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}
