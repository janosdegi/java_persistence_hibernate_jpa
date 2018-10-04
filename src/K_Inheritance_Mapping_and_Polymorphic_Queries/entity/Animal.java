
package K_Inheritance_Mapping_and_Polymorphic_Queries.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE) 
//@Inheritance(strategy=InheritanceType.JOINED) // to be used when you want to test JOINED strategy for inheritance mapping
//@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS) // to be used when you want to test TABLE_PER_CLASS (Table per concrete class) strategy for inheritance mapping
public abstract class Animal {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	//@GeneratedValue(strategy=GenerationType.TABLE) // to be used when using TABLE_PER_CLASS strategy
//	create table 'hibernate_sequences' ( // to be used when using TABLE_PER_CLASS strategy
//			'sequence_name' varchar(255) null default null
//			'sequence_next_hi_value' int(11) null default null
//			);
	private Long id;
	
	//@Column(nullable=false) // cannot be used when using SINGLE_TABLE strategy
	private String name;	
	
	public void setName(String name) { 	
                   	this.name = name; 
	}
	
	public abstract String makeNoise();
	
	public String toString() {
		return name + " making " + makeNoise() + " noises";
	}	

}
