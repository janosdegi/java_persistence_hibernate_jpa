
package F_Mapping_Collections_of_Basic_ValueTypes.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Friend {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private String name;

	private String email;

	@ElementCollection
	@CollectionTable(name = "friend_nickname", joinColumns = @JoinColumn(name = "friend_id"))
	@Column(name = "nickname")
	private Collection<String> nicknames = new ArrayList<String>();

	@ElementCollection
	@CollectionTable(name = "friend_address", joinColumns = @JoinColumn(name = "friend_id"))
	@AttributeOverrides({
			@AttributeOverride(name="street",column=@Column(name="home_street")),
			@AttributeOverride(name="city",column=@Column(name="home_city")),
			@AttributeOverride(name="zipcode",column=@Column(name="home_zipcode"))
	})
	private Collection<Address> addresses = new ArrayList<Address>();

	public Friend() {}

	public Friend(String name, String email) {
		this.name = name;
		this.email = email;

	}

	public String getName() {
		return name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Collection<String> getNicknames() {
		return nicknames;
	}

	public void setNicknames(Collection<String> nicknames) {
		this.nicknames = nicknames;
	}

	public Collection<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Collection<Address> addresses) {
		this.addresses = addresses;
	}

	@Override
	public String toString() {
		return "Friend ["+ this.id +", "+ this.name +", "+ this.email +", "+ this.nicknames +", "+ this.addresses +" ]";
	}
}




