
package A_firstlesson.entity1;

import javax.persistence.*;

@Entity
public class Person2 {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(nullable=false)
	private String name;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name="street",column=@Column(name="home_street")),
			@AttributeOverride(name="city",column=@Column(name="home_city")),
			@AttributeOverride(name="zipcode",column=@Column(name="home_zipcode"))
	})
	private Address address;

	public Person2() {}
	public Person2(String name, Address address) {
		this.name = name;
		this.address = address;
	}
	
}
















