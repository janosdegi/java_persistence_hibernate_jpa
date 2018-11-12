package O_Optimistic_Locking_and_Versioning.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

@Entity
public class Guide {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "staff_id", nullable = false)
	private String staffId;

	private String name;
	private Integer salary;
	

	
	// when you're altering the schema of your tables to add the version column to use versioning,
	// don't forget to add the default for that column as 0 like
	// ALTER TABLE `guide` ADD `version` INT(11) NOT NULL DEFAULT '0';
	@Version
	private Integer version;

	// but if you're planning to use versioning right from the beginning,
	// it's always good to have @Column(columnDefinition = "integer DEFAULT 0", nullable = false)
	// mapping added along with the @Version  annotation at the private Integer version;  attribute.
	// It is to define that the column is non-nullable and its default value is 0.
	/*
	@Column(columnDefinition = "integer DEFAULT 0", nullable = false) 
	@Version
	private Integer version;
	*/

	@OneToMany(mappedBy = "guide", cascade={CascadeType.PERSIST, CascadeType.MERGE})
	private Set<Student> students = new HashSet<Student>();

	public Guide() {	}
	public Guide(String staffId, String name, Integer salary) {
		this.staffId = staffId;
		this.name = name;
		this.salary = salary;
	}
	public void addStudent(Student student) {
		students.add(student);
		student.setGuide(this);
	}	
	public void setSalary(Integer salary) {
		this.salary = salary;
	}
	public Set<Student> getStudents() {
		return students;
	}

	public String toString() {
		return "Guide [id=" + id + ", staffId=" + staffId + ", name=" + name
				+ ", salary=" + salary + ", students=" + students + "]";
	}
	
	
	
}

