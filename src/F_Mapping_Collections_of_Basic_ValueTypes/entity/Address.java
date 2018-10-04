
package F_Mapping_Collections_of_Basic_ValueTypes.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

//	@Column(name="address_street")
	private String street;
	private String city;
	private String zipcode;
	
	public Address() {}
	public Address(String street, String city, String zipcode) {
		this.street = street;
		this.city = city;
		this.zipcode = zipcode;
	}	
	
}







