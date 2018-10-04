
package A_firstlesson.entity1;

import javax.persistence.*;

@Entity
public class Person3 {

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
	private Address homeAddress;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name="street",column=@Column(name="billing_street")),
			@AttributeOverride(name="city",column=@Column(name="billing_city")),
			@AttributeOverride(name="zipcode",column=@Column(name="billing_zipcode"))
	})
	private Address billingAddress;

	public Person3() {}
	public Person3(String name, Address homeAddress, Address billingAddress) {
		this.name = name;
		this.homeAddress = homeAddress;
		this.billingAddress = billingAddress;
	}
	
}
















