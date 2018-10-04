
package K_Inheritance_Mapping_and_Polymorphic_Queries.entity;

import javax.persistence.Entity;

@Entity
public class Cat extends Animal {
	
	@Override
	public String makeNoise() {
		return "meow meow..";
	}
	
}




