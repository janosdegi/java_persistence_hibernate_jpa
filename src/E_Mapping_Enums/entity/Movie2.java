
package E_Mapping_Enums.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Movie2 {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String name;

//	@Enumerated // ORDINAL by default
//	@Enumerated(EnumType.ORDINAL)       both of these cases the index of the enum-value would be persisted (0,1,2,..)
	@Enumerated(EnumType.STRING)
	@Column(name = "film_type")
	private FilmTypeEnum filmType;
	
	public Movie2() {}

	public Movie2(String name, FilmTypeEnum filmType) {
		this.name = name;
		this.filmType = filmType;

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

	public FilmTypeEnum getFilmType() {
		return filmType;
	}

	public void setFilmType(FilmTypeEnum filmType) {
		this.filmType = filmType;
	}
}




